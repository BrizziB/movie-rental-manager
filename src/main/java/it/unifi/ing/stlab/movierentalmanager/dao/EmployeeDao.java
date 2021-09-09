package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.users.Employee;
import it.unifi.ing.stlab.movierentalmanager.model.users.OfficeRole;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
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

    public List<Employee> retrieveEmployeesByName(String name) {
        TypedQuery<Employee> query = getEm().createQuery(
                "FROM Employee e WHERE e.name LIKE CONCAT('%', :name, '%')",
                Employee.class
        ).setParameter("name", name);
        return query.getResultList();
    }

    public List<Employee> retrieveEmployeesByRole(OfficeRole role) {
        TypedQuery<Employee> query = getEm().createQuery(
                "FROM Employee e WHERE e.role = :role",
                Employee.class
        ).setParameter("role", role);
        return query.getResultList();
    }

}
