package com.eshore.service;

import com.eshore.entity.UserInfo;
import org.springframework.stereotype.Service;



@Service
public interface UserService {

	void addUser(UserInfo user);

	UserInfo login(UserInfo user);

}
