package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.users.Customer;
import it.unifi.ing.stlab.movierentalmanager.model.users.Employee;
import it.unifi.ing.stlab.movierentalmanager.model.users.OfficeRole;
import it.unifi.ing.stlab.movierentalmanager.model.users.WebUser;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Employee findByUsername(String username) {
        TypedQuery<Employee> query = getEm().createQuery(
                "SELECT e FROM Employee e WHERE e.webUser.username = :username",
                Employee.class
        ).setParameter("username", username);
        return query.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Optional<Long> retrieveEmployeeIDByUsername(String username) {
        TypedQuery<Long> query = getEm().createQuery(
                "SELECT e.id FROM Employee e WHERE e.webUser.username = :username",
                Long.class
        ).setParameter("username", username);
        return Optional.ofNullable( query.getSingleResult() );
    }

    public WebUser retrieveWebUserByUsername(String username) {
        TypedQuery<WebUser> query = getEm().createQuery(
                "SELECT e.webUser FROM Employee e WHERE e.webUser.username = :username",
                WebUser.class
        ).setParameter("username", username);
        return query.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
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
