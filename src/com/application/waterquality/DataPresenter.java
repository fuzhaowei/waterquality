package com.application.waterquality;

import java.util.List;

public interface DataPresenter<T> {
	
	void Fruit(List<T> list);
	
	void error(String s);
	
}