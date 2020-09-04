package com.springbook.ioc.injection;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class CollectionBeanClient {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext-collection.xml");
		
		CollectionBean bean = (CollectionBean) factory.getBean("collectionBean");
				Properties addressList = bean.getAddressList();		
		
		// 엥.. properties도 map이랑 foreach돌리는 방법 동일..
		for (Entry<Object, Object> address : addressList.entrySet())
			System.out.println(address.getKey() + ": " + address.getValue());
		
				
//		for (String address : addressList) {
//			System.out.println(address.toString()); 
//			// This object (which is already a string!) is itself returned.
//			// toString() 안 해줘도 결과 동일..
//		}
		
		
		// 이거는 내가 map foreach 짠거.... 
//		for (Map.Entry<String, String> entry : addressList.entrySet())
//			System.out.println(entry.getKey() + ": " + entry.getValue());
				
		factory.close();
		
	}
	
	
	
}
