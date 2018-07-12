package com.shengtian.lanfu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shengtian.lanfu.bean.UserAddressBean;
import com.shengtian.lanfu.dao.UserReceivingaddressDao;
import com.shengtian.lanfu.model.UserReceivingAddress;
import com.shengtian.lanfu.service.UserReceivingaddressService;
@Service
public class UserReceivingaddressServiceImpl  implements UserReceivingaddressService {
	
	@Autowired
	private UserReceivingaddressDao  userReceivingaddressServiceDao;

	@Override
	public void insertuserReceivingaddress(
			UserReceivingAddress userReceivingAddress) {
		// TODO Auto-generated method stub
		userReceivingaddressServiceDao.insertuserReceivingaddress(userReceivingAddress);
	}

	@Override
	public List<UserAddressBean> selectListuserReceivingaddress(int userId) {
		// TODO Auto-generated method stub
		return userReceivingaddressServiceDao.selectListuserReceivingaddress(userId);
	}

	@Override
	public void updateReceivingaddress(UserReceivingAddress userReceivingAddress) {
		// TODO Auto-generated method stub
		userReceivingaddressServiceDao.updateReceivingaddress(userReceivingAddress);
	}

	@Override
	public void deleteReceivingaddress(int id) {
		// TODO Auto-generated method stub
		userReceivingaddressServiceDao.deleteReceivingaddress(id);
	}

}
