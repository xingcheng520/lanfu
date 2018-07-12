package com.shengtian.lanfu.service;

import com.shengtian.lanfu.model.User;

public interface UserService  {

	User selectUser(String phone);

	void insertUser(User user);

	void updateUserName(User user);

	void updateUserphone(User user);

	User getUserInvitationCodeById(int userId);

}
