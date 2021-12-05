package com.example.dao;

import com.example.model.User;

public interface UserService {
	public boolean LoginUser(String username, String password);

	public void RegisterUser(User user);

}
