package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    public void addEmployee(Employee e) {
        em.persist(e);
    }

    public Employee findEmployeeById(Long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAllEmployees() {
        TypedQuery<Employee> query = em.createQuery("FROM Employee", Employee.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

    public void updateEmployee(Employee e) {
        Employee oldEmployee = em.find(Employee.class, e.getId());
        oldEmployee.setName(e.getName());
        oldEmployee.setAddress(e.getAddress());
        oldEmployee.setPhoneNumber(e.getPhoneNumber());
        oldEmployee.setWebUser(e.getWebUser());
        oldEmployee.setRole(e.getRole());
    }

    public void saveEmployee(Employee e) {
        if ((e.getId() != null))
            em.merge(e);
        else
            em.persist(e);
    }

    public int deleteEmployeeById(Long id) {
        Query query = em.createQuery("DELETE FROM Employee e WHERE e.id = :id")
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}