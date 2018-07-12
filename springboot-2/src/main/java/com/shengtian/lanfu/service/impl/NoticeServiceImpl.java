package com.shengtian.lanfu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shengtian.lanfu.dao.NoticeDao;
import com.shengtian.lanfu.model.Notice;
import com.shengtian.lanfu.service.NoticeService;

@Service
public class NoticeServiceImpl implements  NoticeService{	
	@Autowired
	private NoticeDao  noticeDao;

	@Override
	public List<Notice> selectlistNotice() {
		// TODO Auto-generated method stub
		return noticeDao.selectlistNotice();
	}

}
