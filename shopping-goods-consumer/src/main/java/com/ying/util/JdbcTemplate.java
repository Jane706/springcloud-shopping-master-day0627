package com.ying.util;


import com.ying.dao.MapperRow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 07 02 7:03
 * @Description:
 */
public class JdbcTemplate {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**通用的查询记录总数*/
	public Integer getCount(String sql,Object...params){
		Integer num = 0;

		try{
			try {
				conn = DBUtil.getConnection();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			// 设置动态参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}

			rs = ps.executeQuery();

			if(rs.next()){
				// 结果为一行一列：通过索引
				num = rs.getInt(1);
			}
		}catch (SQLException e){
			throw  new RuntimeException(e.getMessage());
		}finally {
			DBUtil.closeAll(conn,ps,rs);
		}

		return num;
	}

	/**
	 * 通用的修改方法(添加、更新、删除)
	 * @param sql
	 * @param params
	 * @return
	 */
	public Boolean update(String sql,Object...params ){
		boolean flag = false;

		try{
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			// 设置动态参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}

			int rows = ps.executeUpdate();
			// 处理结果
			if(rows>0){
				flag = true;
			}

		}catch (SQLException e){
			throw  new RuntimeException(e.getMessage());
		}finally {
			DBUtil.closeAll(conn,ps,rs);
		}

		return  flag;
	}


	/**
	 * 通用的查询所有对象的方法
	 *
	 * @param sql    sql语句
	 * @param mapper 对象的结果映射
	 * @param params 语句中的参数
	 * @param <T>
	 * @return
	 */
	public <T> List<T> selectList(String sql, MapperRow<T> mapper, Object... params) {
		List<T> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);

			// 设置动态参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}

			// 处理结果集
			rs = ps.executeQuery();
			while (rs.next()) {
				// 策略模式：结果映射
				T obj = mapper.rowMapper(rs);
				list.add(obj);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			DBUtil.closeAll(conn, ps, rs);
		}

		return list;
	}

	/**
	 * 通用的查询单个对象的方法
	 *
	 * @param sql    sql语句
	 * @param mapper 对象的结果映射
	 * @param params 语句中的参数
	 * @param <T>
	 * @return
	 */
	public <T> T selectForObject(String sql, MapperRow<T> mapper, Object... params) {
		T obj = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);

			// 设置动态参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}

			// 处理结果集
			rs = ps.executeQuery();
			while (rs.next()) {
				// 策略模式：结果映射
				if (obj == null) {
					obj = mapper.rowMapper(rs);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			DBUtil.closeAll(conn, ps, rs);
		}

		return obj;
	}

}
