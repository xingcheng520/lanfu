package com.shengtian.lanfu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shengtian.lanfu.dao.UserDao;
import com.shengtian.lanfu.model.User;
import com.shengtian.lanfu.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User selectUser(String phone) {
		// TODO Auto-generated method stub
		return userDao.selectUser(phone);
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userDao.insertUser(user);
	}

	@Override
	public void updateUserName(User user) {
		// TODO Auto-generated method stub
		userDao.updateUserName(user);
	}

	@Override
	public void updateUserphone(User user) {
		// TODO Auto-generated method stub
		userDao.updateUserphone(user);
	}

	@Override
	public User getUserInvitationCodeById(int userId) {
		// TODO Auto-generated method stub
		return userDao.getUserInvitationCodeById(userId);
	}

}
