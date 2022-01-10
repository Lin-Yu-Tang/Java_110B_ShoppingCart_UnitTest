package com.example.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		String name = se.getName();
		Object value = se.getValue();
		
		System.out.println("Attribute added: " + name + ": " + value);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		String name = se.getName();
		Object value = se.getValue();
		
		System.out.println("Attribute removed: " + name + ": " + value);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		String name = se.getName();
		Object value = se.getValue();
		
		System.out.println("Attribute replaced: " + name + ": " + value);
	}
	
	
}
