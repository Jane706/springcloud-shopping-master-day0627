package com.ying.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

// 自定义映射接口
@FunctionalInterface
public interface MapperRow<T> {
    T rowMapper(ResultSet rs) throws SQLException;
}
