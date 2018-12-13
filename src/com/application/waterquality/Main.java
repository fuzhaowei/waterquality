package com.application.waterquality;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		OperateData data = new OperateData();
		data.search("SELECT * FROM city", CityBean.class, new DataPresenter<CityBean>() {

			@Override
			public void Fruit(List<CityBean> list) {
				// TODO Auto-generated method stub
				for (CityBean bean : list) {
					System.err.println(bean.toString());
				}
			}

			@Override
			public void error(String s) {
				// TODO Auto-generated method stub

			}
		});
		data.add("city", new AddPresenter() {

			@Override
			public void success() {

			}

			@Override
			public List<Object[]> getItem() {
				List<Object[]> list = new ArrayList<>();
				Object[] item1 = new Object[] { 33, 58, "浙江省", 5166, "test1" };
				Object[] item2 = new Object[] { 34, 58, "浙江省", 5166, "test2" };
				Object[] item3 = new Object[] { 35, 58, "浙江省", 5166, "test3" };
				Object[] item4 = new Object[] { 36, 58, "浙江省", 5166, "test4" };
				list.add(item1);
				list.add(item2);
				list.add(item3);
				list.add(item4);
				return list;
			}

			@Override
			public void error(String s) {
				System.err.println(s);

			}
		}, "id", "provincecode", "provincename", "citycode", "cityname");
	}

}