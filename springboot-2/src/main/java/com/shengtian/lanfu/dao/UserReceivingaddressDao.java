package com.shengtian.lanfu.dao;

import java.util.List;

import com.shengtian.lanfu.bean.UserAddressBean;
import com.shengtian.lanfu.model.UserReceivingAddress;

public interface UserReceivingaddressDao {

	void insertuserReceivingaddress(UserReceivingAddress userReceivingAddress);

	List<UserAddressBean> selectListuserReceivingaddress(int userId);

	void updateReceivingaddress(UserReceivingAddress userReceivingAddress);

	void deleteReceivingaddress(int id);

}
