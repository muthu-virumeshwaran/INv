package com.example.Inventory.services;

import com.example.Inventory.models.Order;
import com.example.Inventory.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Order order) {
        return orderRepository.save(order);
    }
}
