package com.springbook.ioc.injection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionBean {

	private Properties addressList; // key=value 형태의 String collection
	// The Properties class represents a persistent set of properties. 
	// The Properties can be saved to a stream or loaded from a stream. 
	// Each key and its corresponding value in the property list is a string. 
	
	public Properties getAddressList() {
		return addressList;
	}

	public void setAddressList(Properties addressList) {
		this.addressList = addressList;
	}
	

// Map DI================================
//	private Map<String, String> addressList;
//	
//	public Map<String, String> getAddressList() {
//		return addressList;
//	}
//
//	public void setAddressList(Map<String, String> addressList) {
//		this.addressList = addressList;
//	}

	
// Set DI================================
//	private Set<String> addressList;
//
//	public Set<String> getAddressList() {
//		return addressList;
//	}
//
//	public void setAddressList(Set<String> addressList) {
//		this.addressList = addressList;
//	}

	
// List DI================================
//	private List<String> addressList;
//
//	public List<String> getAddressList() {
//		return addressList;
//	}
//
//	public void setAddressList(List<String> addressList) {
//		this.addressList = addressList;
//	}

}
