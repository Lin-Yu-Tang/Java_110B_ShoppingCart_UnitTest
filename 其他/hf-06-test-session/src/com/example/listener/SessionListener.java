package com.example.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	
	static private int activesSessions;
	
	public static int getActivesSession() {
		
		return activesSessions;
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session create");
		activesSessions++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session destroyed");
		activesSessions--;
	}
	
	
	
	
	
}
