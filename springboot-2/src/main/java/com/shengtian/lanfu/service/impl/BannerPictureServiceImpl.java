package com.shengtian.lanfu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shengtian.lanfu.dao.BannerPictureDao;
import com.shengtian.lanfu.model.BannerPicture;
import com.shengtian.lanfu.service.BannerPictureService;
@Service
public class BannerPictureServiceImpl  implements BannerPictureService{
	
	@Autowired
	private BannerPictureDao bannerPictureDao;

	@Override
	public List<BannerPicture> getBannerPictureList() {
		// TODO Auto-generated method stub
		return bannerPictureDao.getBannerPictureList();
	}

	@Override
	public BannerPicture getBannerPicture(int pictureId) {
		// TODO Auto-generated method stub
		return bannerPictureDao.getBannerPicture(pictureId);
	}

}
