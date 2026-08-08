package com.softpro.Project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softpro.Project2.entity.Order;
import com.softpro.Project2.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    // Save Order
    public Order saveOrder(Order order) {

        return orderRepository.save(order);
    }


    // Get All Orders
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }


    // Get Order By ID
    public Order getOrderById(int id) {

        return orderRepository.findById(id).orElse(null);
    }


    // Update Order Status
    public void updateStatus(int id, String status) {

        Order order =
                orderRepository.findById(id).orElse(null);

        if (order != null) {

            order.setStatus(status);

            orderRepository.save(order);
        }
    }

}