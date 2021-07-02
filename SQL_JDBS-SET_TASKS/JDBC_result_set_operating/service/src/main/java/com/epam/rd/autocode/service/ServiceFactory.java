package com.epam.rd.autocode.service;

import com.epam.rd.autocode.service.impl.ImplEmployeeService;

public class ServiceFactory {

    public EmployeeService employeeService(){

        return new ImplEmployeeService();
    }
}
