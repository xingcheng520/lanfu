package com.shengtian.lanfu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shengtian.lanfu.bean.UserAddressBean;
import com.shengtian.lanfu.model.UserReceivingAddress;
import com.shengtian.lanfu.service.UserReceivingaddressService;
/**
 * 用户收货地址类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("v1/UserReceivingaddress")
public class UserReceivingaddressController {
	
	@Autowired
	private UserReceivingaddressService userReceivingaddressService;
	
	
	/**
	 * 新增用户地址
	 * @param userReceivingAddress
	 * @return
	 */
	@RequestMapping(value="/insertuserReceivingaddress",method=RequestMethod.POST)
	public Map<String,Object> insertuserReceivingaddress(@RequestBody UserReceivingAddress userReceivingAddress){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			userReceivingaddressService.insertuserReceivingaddress(userReceivingAddress);
			result.put("flag", 1);
			result.put("message", "新增成功");
		} catch (Exception e) {
			result.put("flag", 2);
			result.put("message", "新增失败");
		}
		return result;
	}

	/**
	 * 查询当前用户收货地址列表
	 * @return
	 */
	@RequestMapping(value="/selectListuserReceivingaddress/{userId}",method=RequestMethod.GET)
	public Map<String,Object> selectListuserReceivingaddress(@PathVariable(value="userId")int userId){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
		List<UserAddressBean> listUserAddressBean=	userReceivingaddressService.selectListuserReceivingaddress(userId);
		    result.put("listUserAddressBean", listUserAddressBean);
			result.put("flag", 1);
			result.put("message", "查询成功");
		} catch (Exception e) {
			result.put("flag", 2);
			result.put("message", "查询失败");
		}
		return result;
	
	}
	/**
	 * 更新收货地址
	 * @param userReceivingAddress
	 * @return
	 */
	@RequestMapping(value="/updateReceivingaddress",method=RequestMethod.POST)
	public Map<String,Object> updateReceivingaddress(@RequestBody UserReceivingAddress userReceivingAddress){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			userReceivingaddressService.updateReceivingaddress(userReceivingAddress);
			result.put("flag", 1);
			result.put("message", "更新成功");
		} catch (Exception e) {
			result.put("flag", 2);
			result.put("message", "更新失败");
			return result;
		}
		return result;
		
	}
	/**
	 * 删除收货地址
	 * @param userReceivingAddress
	 * @return
	 */
	@RequestMapping(value="/deleteReceivingaddress/{id}",method=RequestMethod.GET)
	public Map<String,Object>deleteReceivingaddress(@PathVariable(value="id") int id){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			userReceivingaddressService.deleteReceivingaddress(id);
			result.put("flag", 1);
			result.put("message", "删除成功");
		} catch (Exception e) {
			result.put("flag", 2);
			result.put("message", "删除失败");
			return result;
		}
		return result;
		
	}
}
