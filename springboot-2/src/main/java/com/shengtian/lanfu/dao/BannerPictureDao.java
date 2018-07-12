package com.shengtian.lanfu.dao;

import java.util.List;

import com.shengtian.lanfu.model.BannerPicture;

public interface BannerPictureDao {

	List<BannerPicture> getBannerPictureList();

	BannerPicture getBannerPicture(int pictureId);

}
