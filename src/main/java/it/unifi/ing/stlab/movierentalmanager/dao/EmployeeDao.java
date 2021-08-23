package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Employee;

import javax.transaction.Transactional;

public class EmployeeDao extends BaseDao<Employee> {

    public EmployeeDao() {
        super(Employee.class);
    }

    @Transactional
    public void update(Employee e) {
        Employee oldEmployee = getEm().find(Employee.class, e.getId());
        oldEmployee.setName(e.getName());
        oldEmployee.setAddress(e.getAddress());
        oldEmployee.setPhoneNumber(e.getPhoneNumber());
        oldEmployee.setWebUser(e.getWebUser());
        oldEmployee.setRole(e.getRole());
    }

}
