package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ImplSetMapper implements SetMapper<Set<Employee>>
{
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_SALARY = "SALARY";
    private static final String COLUMN_HIREDATE = "HIREDATE";
    private static final String COLUMN_FIRSTNAME = "FIRSTNAME";
    private static final String COLUMN_LASTNAME = "LASTNAME";
    private static final String COLUMN_MIDDLENAME = "MIDDLENAME";
    private static final String COLUMN_POSITION = "POSITION";
    private static final String COLUMN_MANAGER = "MANAGER";

    @Override
    public Set<Employee> mapSet(ResultSet resultSet) throws SQLException {
        Set<Employee> employees = new HashSet<>();
        try {
            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
        }
        return employees;
    }

    private Employee createEmployee(ResultSet resultSet) {
        try {
            Position position = getPosition(resultSet);
            FullName fullName = getFullName(resultSet);
            BigInteger id = BigInteger.valueOf(resultSet.getInt(COLUMN_ID));
            LocalDate hired = getHired(resultSet);
            BigDecimal salary = resultSet.getBigDecimal(COLUMN_SALARY);
            int managerID = resultSet.getInt(COLUMN_MANAGER);
            Employee manager = getManager(resultSet, managerID);
            return new Employee(id, fullName, position, hired, salary, manager);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Employee getManager(ResultSet resultSet, int managerId) {
        Employee manager = null;
        try {
            int lineToReturn = resultSet.getRow();
            resultSet.beforeFirst();
            while (resultSet.next()) {
                if (resultSet.getInt(COLUMN_ID) == managerId) {
                    manager = createEmployee(resultSet);
                    break;
                }
            }
            resultSet.absolute(lineToReturn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    private Position getPosition(ResultSet resultSet)
            throws SQLException
    {
        String rawPosition = resultSet.getString(COLUMN_POSITION);
        return Position.valueOf(rawPosition.toUpperCase());
    }

    private FullName getFullName(ResultSet resultSet)
            throws SQLException
    {
        String firstName = resultSet.getString(COLUMN_FIRSTNAME);
        String lastName = resultSet.getString(COLUMN_LASTNAME);
        String middleName = resultSet.getString(COLUMN_MIDDLENAME);
        return new FullName(firstName, lastName, middleName);
    }

    private LocalDate getHired(ResultSet resultSet)
            throws SQLException
    {
        Date rawDate = resultSet.getDate(COLUMN_HIREDATE);
        return LocalDate.parse(rawDate.toString());
    }



}
