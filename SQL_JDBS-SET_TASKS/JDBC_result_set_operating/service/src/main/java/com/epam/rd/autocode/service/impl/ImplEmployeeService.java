package com.epam.rd.autocode.service.impl;

import com.epam.rd.autocode.dao.EmployeeDao;
import com.epam.rd.autocode.dao.impl.ImplEmployeeDao;
import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.service.EmployeeService;
import com.epam.rd.autocode.service.Paging;

import java.util.Comparator;
import java.util.List;

public class ImplEmployeeService implements EmployeeService
{
    private final EmployeeDao dao = new ImplEmployeeDao();

    @Override
    public List<Employee> getAllSortByHireDate(Paging paging) {
        List<Employee> employees = dao.getAll();
        employees.sort(Comparator.comparing(Employee::getHired));
        return getSubList(employees, paging);
    }

    @Override
    public List<Employee> getAllSortByLastname(Paging paging) {
        List<Employee> employees = dao.getAll();
        employees.sort(Comparator.comparing(e -> e.getFullName().getLastName()));
        return getSubList(employees, paging);
    }

    @Override
    public List<Employee> getAllSortBySalary(Paging paging) {
        List<Employee> employees = dao.getAll();
        employees.sort(Comparator.comparing(Employee::getSalary));
        return getSubList(employees, paging);
    }

    @Override
    public List<Employee> getAllSortByDepartmentNameAndLastname(Paging paging) {
        List<Employee> employees = dao.getAll();
        employees.sort(Comparator.comparing(e -> e.getFullName().getLastName()));
        employees.sort((e1, e2) -> {
            if (e1.getDepartment() != null && e2.getDepartment() != null) {
                return e1.getDepartment().getName().compareTo(e2.getDepartment().getName());
            } else if (e1.getDepartment() == null) {
                return -1;
            } else {
                return 1;
            }
        });
        return getSubList(employees, paging);
    }

    @Override
    public List<Employee> getByDepartmentSortByHireDate(Department department, Paging paging) {
        List<Employee> employees = dao.getByDepartment(department);
        employees.sort(Comparator.comparing(Employee::getHired));
        return getSubList(employees, paging);
    }

    @Override
    public List<Employee> getByDepartmentSortBySalary(Department department, Paging paging) {
        List<Employee> employees = dao.getByDepartment(department);
        employees.sort(Comparator.comparing(Employee::getSalary));
        return getSubList(employees, paging);
    }

    @Override
    public List<Employee> getByDepartmentSortByLastname(Department department, Paging paging) {
        List<Employee> employees = dao.getByDepartment(department);
        employees.sort(Comparator.comparing(e -> e.getFullName().getLastName()));
        return getSubList(employees, paging);
    }

    @Override
    public List<Employee> getByManagerSortByLastname(Employee manager, Paging paging) {
        List<Employee> employees = dao.getByManager(manager);
        employees.sort(Comparator.comparing(e -> e.getFullName().getLastName()));
        return getSubList(employees, paging);
    }

    @Override
    public List<Employee> getByManagerSortByHireDate(Employee manager, Paging paging) {
        List<Employee> employees = dao.getByManager(manager);
        employees.sort(Comparator.comparing(Employee::getHired));
        return getSubList(employees, paging);
    }

    @Override
    public List<Employee> getByManagerSortBySalary(Employee manager, Paging paging) {
        List<Employee> employees = dao.getByManager(manager);
        employees.sort(Comparator.comparing(Employee::getSalary));
        return getSubList(employees, paging);
    }

    @Override
    public Employee getWithDepartmentAndFullManagerChain(Employee employee) {
        return dao.getByIdWithFullChain(employee.getId());
    }

    @Override
    public Employee getTopNthBySalaryByDepartment(int salaryRank, Department department) {
        List<Employee> employees = dao.getByDepartment(department);
        employees.sort(Comparator.comparing(e -> e.getSalary().negate()));
        int listIndex = salaryRank - 1;
        int lastIndex = employees.size() - 1;
        return employees.get(Math.min(lastIndex, listIndex));
    }

    private <T> List<T> getSubList(List<T> list, Paging paging) {
        int fromIndex = getByIndex(paging);
        int toIndex = Math.min(list.size(), getToIndex(paging));
        return list.subList(fromIndex, toIndex);
    }

    private int getByIndex(Paging paging) {
        return (paging.page - 1) * paging.itemPerPage;
    }

    private int getToIndex(Paging paging) {
        return getByIndex(paging) + paging.itemPerPage;
    }

}
