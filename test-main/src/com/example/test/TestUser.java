package com.example.test;

import com.example.dao.UserServiceImpl;

public class TestUser {

	public static void main(String[] args) {
		
		UserServiceImpl service = new UserServiceImpl();
		
		String userId = service.getUserId("tom1234");
		
		System.out.println(userId);
	}

}
