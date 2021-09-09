package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteEmployeeDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.EmployeeMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.EmployeeDao;
import it.unifi.ing.stlab.movierentalmanager.model.users.Employee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class EmployeeController {

    @Inject private EmployeeDao employeeDao;
    @Inject private EmployeeMapper employeeMapper;
    private Gson gson;

    public LiteEmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Employee not found")
        );
        return employeeMapper.convert(employee);
    }

    public List<LiteEmployeeDto> getEmployeesByName(String name) {
        return employeeDao.retrieveEmployeesByName(name)
                .stream()
                .map(employeeMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteEmployeeDto> getAllEmployees(Integer offset, Integer limit) {
        return employeeDao.findAll(offset, limit)
                .stream()
                .map(employeeMapper::convert)
                .collect(Collectors.toList());
    }

    public void addEmployeeToDb(String json) {
        gson = new Gson();
        LiteEmployeeDto dto = gson.fromJson(json, LiteEmployeeDto.class);
        Employee employee = ModelFactory.initEmployee();
        employeeMapper.transfer(dto, employee);
        employeeDao.add(employee);
    }

    public void updateEmployeeOnDb(String json, Long id) {
        gson = new Gson();
        LiteEmployeeDto dto = gson.fromJson(json, LiteEmployeeDto.class);
        Employee oldCustomer = employeeDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any employee on database")
        );
        employeeMapper.transfer(dto, oldCustomer);
        employeeDao.update(oldCustomer);
    }

    public void disableEmployeeOnDb(boolean disabled, Long id) {
        Employee employee = employeeDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any employee on database")
        );
        employee.setDisabled(disabled);
        employeeDao.save(employee);
    }


}
