package com.eshore.mapper;


import com.eshore.entity.UserInfo;

public interface UserMapper {

	void addUser(UserInfo user);

	UserInfo login(UserInfo user);

}
