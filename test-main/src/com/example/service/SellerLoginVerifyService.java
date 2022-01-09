package com.example.service;

import com.example.dao.SellerServiceImpl;

/**
 * @author Tom Lin
 * @apiNote 賣家登入驗證專用
 */
public class SellerLoginVerifyService {
	
	/**
	 * 登入驗證
	 */
	public boolean loginCheck(String accountName, String password) {
		boolean result = false;
		if (accountName == null)
			return result;
		
		SellerServiceImpl service = new SellerServiceImpl();
		String pass = service.getLoginPass(accountName);
		if (pass == null) {
			// no such account
			return result;
		}
		
		if (pass.equals(password)) {
			result = true;
		}
		return result;
	}
	
}
