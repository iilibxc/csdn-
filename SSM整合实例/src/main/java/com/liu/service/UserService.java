package com.liu.service;

import com.liu.entity.UserInfo;
import org.springframework.stereotype.Service;



@Service
public interface UserService {

	void addUser(UserInfo user);

	UserInfo login(UserInfo user);

}
