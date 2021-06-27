package com.epam.rd.autocode;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SetMapper<T> {
    T mapSet(ResultSet resultSet) throws SQLException;
}
