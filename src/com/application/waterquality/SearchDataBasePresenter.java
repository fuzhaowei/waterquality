package com.application.waterquality;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchDataBasePresenter {

	public void setFruit(ResultSet fruit) throws SQLException;

	public void error(String e);

}