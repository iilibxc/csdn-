package com.liu.service.impl;


import com.liu.entity.UserInfo;
import com.liu.mapper.UserMapper;
import com.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
	@Override
	public void addUser(UserInfo user) {
		userMapper.addUser(user);
		System.out.print("我是古天乐");
	}
	@Override
	public UserInfo login(UserInfo user) {
		return userMapper.login(user);
	}

}
