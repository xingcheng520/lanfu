package com.shengtian.lanfu.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shengtian.lanfu.model.BannerPicture;
import com.shengtian.lanfu.model.MyProps;
import com.shengtian.lanfu.service.BannerPictureService;

@RestController
@RequestMapping("v1/BannerPicture")
public class BannerPictureController {
	@Autowired
	private BannerPictureService bannerPictureService;
	@Autowired
	private MyProps myProps;

	/**
	 * 获取首页轮播图地址
	 * @param imgID
	 *图片的保存id
	 * @param opinionText
	 * @return file
	 */
	@RequestMapping(value = "getBannerPictureList", method = RequestMethod.GET)
	public Map<String, Object> selectListBannerPicture() {

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<BannerPicture> ListbannerPicture = bannerPictureService
					.getBannerPictureList();
			// List<BannerPicture> listPicture=new ArrayList<>();
			result.put("flag", 1);
			result.put("message", "查询成功");
			result.put("listPicture", ListbannerPicture);
		} catch (Exception e) {
			result.put("flag", 2);
			result.put("message", e);
			return result;
		}

		return result;
	}

	/**
	 * 获取首页轮播图
	 * 
	 * @param imgID
	 * 图片的保存id
	 * @param opinionText
	 * @return file
	 */
	@RequestMapping(value = "getPicture/{pictureId}", method = RequestMethod.GET)
	public void getPicture(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "pictureId") int pictureId) {

		BannerPicture bannerPicture = bannerPictureService
				.getBannerPicture(pictureId);
		String path = bannerPicture.getPictureAddress();

		try {
			OutputStream os = response.getOutputStream();
			File file = new File(path);
			FileInputStream fips = new FileInputStream(file);
			byte[] btImg = readStream(fips);
			os.write(btImg);
			os.flush();

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
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

}
