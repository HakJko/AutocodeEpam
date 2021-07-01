package com.epam.rd.autocode.dao;

public class DaoFactory {

    public EmployeeDao employeeDAO() {
        return new ImplEmployeeDao();
    }

    public DepartmentDao departmentDAO() {
        return new ImplDepartmentDao();
    }
}
