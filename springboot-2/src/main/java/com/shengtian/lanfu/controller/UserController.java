package com.shengtian.lanfu.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shengtian.lanfu.model.MyProps;
import com.shengtian.lanfu.model.User;
import com.shengtian.lanfu.service.UserService;
import com.shengtian.lanfu.util.InvitationCodeUtil;
import com.shengtian.lanfu.util.SendMessage;

/**
 * 用户模块类
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("v1/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private MyProps myProps;

	/**
	 * 用户发送短信以及注册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sendMessages", method = RequestMethod.POST)
	public Map<String, Object> sendMessages(@Param("phone")String phone) {
		Map<String, Object> result = new HashMap<>();
		try {
			 if(phone==null||phone.equals("")){
				result.put("flag", 200);
				result.put("message", "手机号为空");
				return result;
			 }
			int number = (int) ((Math.random() * 9 + 1) * 100000);
			String number2 = number + "";
			User user = userService.selectUser(phone);
			if (user == null) {
				User newUser = new User();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String format = sdf.format(date);
				String generate = InvitationCodeUtil.generate();
				newUser.setInvitationCode(generate);
				newUser.setUserName(phone);
				newUser.setMassgeTime(format);
				newUser.setPhone(phone);
				newUser.setPassWord(number2);
				userService.insertUser(newUser);
			}
			// 发送短信验证码
			SendMessage.doPost(phone, number);
			result.put("flag", 1);
			result.put("message", "发送成功");
		} catch (Exception e) {
			result.put("flag", 2);
			result.put("message", "发送失败");
		}
		return result;
	}

	/**
	 * 用户登录接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logUser(@RequestBody User user) {
		Map<String, Object> result = new HashMap<>();
		try {
			User Newuser = userService.selectUser(user.getPhone());
			if (!Newuser.getPassWord().equals(user.getPassWord())) {
				result.put("flag", 2);
				result.put("message", "登录失败");
				return result;
			}
			result.put("Newuser", Newuser);
			result.put("flag", 1);
			result.put("message", "登录成功");

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	/**
	 * 头像上传
	 * 
	 * @param <MultipartFormDataInput>
	 * @return
	 */
	@RequestMapping(value = "/uploadUserPic", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(
			@RequestParam("mFile") MultipartFile file,
			@Param( "userId") long userId) {

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			if (file.isEmpty()) {
				result.put("200", "文件为空");
				return result;
			}
			// 获取文件名
			String fileName = file.getOriginalFilename();
			// 获取文件的后缀名
			String Name = fileName.substring(fileName.lastIndexOf("."));
			// 解决中文问题，liunx下中文路径，图片显示问题
			// fileName = UUID.randomUUID() + suffixName;
			File dest = new File(myProps.getFilePath() + userId);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			file.transferTo(dest);
			result.put("flag", 1);
			result.put("message", "上传成功");
			return result;
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		result.put("200", "上传失败");
		return result;

	}

	/**
	 * 获取图片,显示个人头像
	 * 
	 * @param accountID
	 * @return 文件
	 */

	@RequestMapping(value = "/getUserPic/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserpic(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("userID") String userID
			) {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			OutputStream os = response.getOutputStream();
			BufferedOutputStream os1 = new BufferedOutputStream(os);
			File file = new File(myProps.getFilePath() + userID);
			if (!file.exists()) {

				// Resource resource = new ClassPathResource("888.png");
				String url = myProps.getFilePath() + "888.png";
				// File staticfile = resource.getFile();
				FileInputStream fips = new FileInputStream(url);
				byte[] btImg = readStream(fips);
				os1.write(btImg);
				os1.flush();
				result.put("flag", 1);
				result.put("message", "获取成功");
				return result;
			}
			FileInputStream fips = new FileInputStream(file);
			byte[] btImg = readStream(fips);
			os1.write(btImg);
			os1.flush();
			result.put("flag", 1);
			result.put("message", "获取成功");
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		result.put("flag", 2);
		result.put("message", "获取失败");
		return result;

	}

	private byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			return bops.toByteArray();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 修改用户名
	 * 
	 * @param accountID
	 * @return
	 */
	@RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
	public Map<String, Object> updateUserName(@RequestBody User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userService.updateUserName(user);
			result.put("flag", 1);
			result.put("message", "修改成功");

		} catch (Exception e) {

			result.put("flag", 2);
			result.put("message", "修改失败");
			return result;
		}

		return result;

	}

	/**
	 * 修改手机号
	 * 
	 * @param json串
	 * @return
	 */
	@RequestMapping(value = "/updateUserphone", method = RequestMethod.POST)
	public Map<String, Object> updateUserphone(@RequestBody User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userService.updateUserphone(user);
			result.put("flag", 1);
			result.put("message", "修改成功");

		} catch (Exception e) {

			result.put("flag", 2);
			result.put("message", "修改失败");
			return result;
		}

		return result;

	}
	
	/**
	 * 获取我的邀请码
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/getUserInvitationCodeById",method=RequestMethod.POST)
	public  Map<String,Object> getUserInvitationCodeById(@Param("userId")int userId){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User user=userService.getUserInvitationCodeById(userId);
			result.put("invitationCode", user.getInvitationCode());
			result.put("flag", 1);
			result.put("message", "获取成功");

		} catch (Exception e) {

			result.put("flag", 2);
			result.put("message", "获取失败");
			return result;
		}

		return result;
		
	}
}
