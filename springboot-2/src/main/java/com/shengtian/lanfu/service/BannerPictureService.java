package com.shengtian.lanfu.service;

import java.util.List;

import com.shengtian.lanfu.model.BannerPicture;

public interface BannerPictureService {

	List<BannerPicture> getBannerPictureList();

	BannerPicture getBannerPicture(int pictureId);

}
