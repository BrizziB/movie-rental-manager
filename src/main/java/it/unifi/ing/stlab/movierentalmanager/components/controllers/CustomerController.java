package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteCustomerDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.CustomerMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.CustomerDao;
import it.unifi.ing.stlab.movierentalmanager.model.users.Customer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class CustomerController {

    @Inject private CustomerDao customerDao;
    @Inject private CustomerMapper customerMapper;
    private Gson gson;

    public LiteCustomerDto getCustomerById(Long id) {
        Customer customer = customerDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Customer not found")
        );
        return customerMapper.convert(customer);
    }

    public List<LiteCustomerDto> getCustomersByName(String name) {
        return customerDao.retrieveCustomersByName(name)
                          .stream()
                          .map(customerMapper::convert)
                          .collect(Collectors.toList());
    }

    public List<LiteCustomerDto> getAllCustomers(Integer offset, Integer limit) {
        return customerDao.findAll(offset, limit)
                .stream()
                .map(customerMapper::convert)
                .collect(Collectors.toList());
    }

    public void addCustomerToDb(String json) {
        gson = new Gson();
        LiteCustomerDto dto = gson.fromJson(json, LiteCustomerDto.class);
        Customer customer = ModelFactory.initCustomer();
        customerMapper.transfer(dto, customer);
        customerDao.add(customer);
    }

    public void updateCustomerOnDb(String json, Long id) {
        gson = new Gson();
        LiteCustomerDto dto = gson.fromJson(json, LiteCustomerDto.class);
        Customer oldCustomer = customerDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any customer on database")
        );
        customerMapper.transfer(dto, oldCustomer);
        customerDao.update(oldCustomer);
    }

    public void disableCustomerOnDb(boolean disabled, Long id) {
        Customer customer = customerDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any customer on database")
        );
        customer.setDisabled(disabled);
        customerDao.save(customer);
    }

}
