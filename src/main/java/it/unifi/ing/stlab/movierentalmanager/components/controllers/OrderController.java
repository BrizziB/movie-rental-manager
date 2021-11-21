package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.OrderDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteOrderDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.OrderMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.CustomerDao;
import it.unifi.ing.stlab.movierentalmanager.dao.OrderDao;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.Order;
import it.unifi.ing.stlab.movierentalmanager.model.users.Customer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class OrderController {

    @Inject private OrderDao orderDao;
    @Inject private CustomerDao customerDao;
    @Inject private OrderMapper orderMapper;
    private Gson gson;

    public LiteOrderDto getOrderById(Long id) {
        Order order = orderDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Order not found")
        );
        return orderMapper.convert(order);
    }

    public List<LiteOrderDto> getOrdersByCustomerID(Long id) {
        return orderDao.retrieveOrdersByIDs( customerDao.retrieveOrdersIDs(id) )
                       .stream()
                       .map(orderMapper::convert)
                       .collect(Collectors.toList());
    }

    public List<LiteOrderDto> getOrderBetweenDates(Date start, Date end) {
        return orderDao.retrieveOrdersBetweenDates(start, end)
                       .stream()
                       .map(orderMapper::convert)
                       .collect(Collectors.toList());
    }

    public List<LiteOrderDto> getAllOrders(Integer offset, Integer limit, List<String> orderBy) {
        return orderDao.findAll(offset, limit, orderBy)
                .stream()
                .map(orderMapper::convert)
                .collect(Collectors.toList());
    }

    public void addOrderToDb(String json, Long customerID) {
        Customer c = customerDao.findById(customerID).orElseThrow( () -> new IllegalArgumentException("No customer found with given ID") );
        gson = new Gson();
        OrderDto dto = gson.fromJson(json, OrderDto.class);
        Order order = ModelFactory.initOrder();
        order.setCustomer(c);
        orderMapper.transfer(dto, order);
        orderDao.add(order);
    }

    public void updateOrderOnDb(String json, Long customerID) {
        gson = new Gson();
        OrderDto dto = gson.fromJson(json, OrderDto.class);
        Order oldOrder = orderDao.findById(customerID).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any order on database")
        );
        orderMapper.transfer(dto, oldOrder);
        orderDao.update(oldOrder);
    }

    public void disableOrderOnDb(boolean disabled, Long customerID) {
        Order order = orderDao.findById(customerID).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any order on database")
        );
        order.setDisabled(disabled);
        orderDao.save(order);
    }

}
