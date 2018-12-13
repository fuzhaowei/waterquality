package com.application.waterquality;

import java.util.List;

public interface AddPresenter {

	List<Object[]> getItem();

	void success();

	void error(String s);

}