package com.example.dao;

import com.example.model.User;

/**
 * @author 柯孟言
 * @apiNote 定義使用者登入驗證相關方法
 */
public interface UserService {
	public boolean LoginUser(String username, String password);

	public void RegisterUser(User user);
	
	public String getUserId(String username);
}
