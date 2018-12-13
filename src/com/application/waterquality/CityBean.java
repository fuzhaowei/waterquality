package com.application.waterquality;

public class CityBean {

	private int id;
	private int provincecode;
	private String provincename;
	private int citycode;
	private String cityname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProvincecode() {
		return provincecode;
	}

	public void setProvincecode(int provincecode) {
		this.provincecode = provincecode;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + ":" + provincename + ":" + citycode + ":" + cityname;
	}

}