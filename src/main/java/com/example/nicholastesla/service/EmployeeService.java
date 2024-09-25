package com.example.nicholastesla.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nicholastesla.model.Employee;
import java.util.List;

@Service
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get all employees
    public List<Employee> getAllEmployees() {
    	logger.info("getAllEmployees() started...");
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    // Get employee by ID
    public Employee getEmployeeById(Long id) {
    	logger.info("getEmployeeById() started...");
        String sql = "SELECT * FROM employees WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), id);
    }

    // Save a new employee
    public int saveEmployee(Employee employee) {
    	logger.info("saveEmployee() started...");
        String sql = "INSERT INTO employees (name, role) VALUES (?, ?)";
        return jdbcTemplate.update(sql, employee.getName(), employee.getRole());
    }

    // Update an existing employee
    public int updateEmployee(Employee employee) {
    	logger.info("updateEmployee() started...");
        String sql = "UPDATE employees SET name = ?, role = ? WHERE id = ?";
        return jdbcTemplate.update(sql, employee.getName(), employee.getRole(), employee.getId());
    }

    // Delete an employee by ID
    public int deleteEmployee(Long id) {
    	logger.info("deleteEmployee() started...");
        String sql = "DELETE FROM employees WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
