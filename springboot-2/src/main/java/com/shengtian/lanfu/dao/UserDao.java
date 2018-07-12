package com.shengtian.lanfu.dao;






import com.shengtian.lanfu.model.User;


public interface UserDao {

	User selectUser(String phone);
	 
	void insertUser(User user);

	void updateUserName(User user);

	void updateUserphone(User user);

	User getUserInvitationCodeById(int userId);

}
