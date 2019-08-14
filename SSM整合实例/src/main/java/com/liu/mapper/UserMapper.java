package com.liu.mapper;


import com.liu.entity.UserInfo;

public interface UserMapper {

	void addUser(UserInfo user);

	UserInfo login(UserInfo user);

}
