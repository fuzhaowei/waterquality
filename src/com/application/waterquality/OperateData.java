package com.application.waterquality;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperateData {

	private static MySqlDataBase mySqlDataBase;

	public OperateData() {
		if (mySqlDataBase == null) {
			mySqlDataBase = new MySqlDataBase();
		}
	}

	public void search(String sql, Class<?> bean, DataPresenter presenter) {
		mySqlDataBase.search(sql, new SearchDataBasePresenter() {

			@Override
			public void setFruit(ResultSet fruit) throws SQLException {
				// TODO Auto-generated method stub
				try {
					Field[] fidlds = bean.newInstance().getClass().getDeclaredFields();
					List<Object> list = new ArrayList<>();
					while (fruit.next()) {
						Object item = bean.newInstance();
						for (Field field : fidlds) {
							String key = field.getName();
							Class<?> o = field.getType();
							field.setAccessible(true);
							if (o == String.class) {
								field.set(item, fruit.getString(key));
							} else if (o == Integer.class) {
								field.set(item, fruit.getInt(key));
							} else if (o == double.class) {
								field.set(item, fruit.getDouble(key));
							} else if (o == float.class) {
								field.set(item, fruit.getFloat(key));
							} else if (o == short.class) {
								field.set(item, fruit.getShort(key));
							} else if (o == long.class) {
								field.set(item, fruit.getLong(key));
							}
						}
						list.add(item);
					}
					presenter.Fruit(list);
				} catch (Exception e) {
					presenter.error(e.toString());
					e.printStackTrace();
				}
			}

			@Override
			public void error(String e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void add(String table, AddPresenter presenter, String... values) {
		StringBuilder head = new StringBuilder();
		StringBuilder foot = new StringBuilder();
		head.append("INSERT INTO ");
		head.append(table);
		head.append("(");
		foot.append("VALUES(");
		for (int i = 0; i < values.length; i++) {
			head.append(values[i]);
			foot.append("?");
			if (i == values.length - 1) {
				head.append(") ");
				foot.append(") ");
			} else {
				head.append(",");
				foot.append(",");
			}
		}
		head.append(foot.toString());
		mySqlDataBase.operate(head.toString(), new OperateDataBasePresenter() {

			@Override
			public void error(String s) {
				presenter.error(s);
			}

			@Override
			public void add(PreparedStatement ment) throws SQLException {
				List<Object[]> list = presenter.getItem();
				for (Object[] map : list) {
					int index = 1;
					for (Object key : map) {
						Class<?> cla = key.getClass();
						if (cla == String.class) {
							ment.setString(index, key.toString());
						} else if (cla == Integer.class) {
							ment.setInt(index, Integer.valueOf(key.toString()));
						} else if (cla == double.class) {
							ment.setDouble(index, Double.valueOf(key.toString()));
						} else if (cla == float.class) {
							ment.setFloat(index, Float.valueOf(key.toString()));
						} else if (cla == short.class) {
							ment.setShort(index, Short.valueOf(key.toString()));
						} else if (cla == long.class) {
							ment.setLong(0, Long.valueOf(key.toString()));
						}
						index++;
					}
					long i = ment.executeLargeUpdate();
					if (i > 0) {
						System.err.println("数据库添加成功，影响行数:" + i);
					} else {
						presenter.error("数据库添加错误");
					}
				}
			}

			@Override
			public void Fruit(int i) {
				presenter.success();
			}
		});
	}
}