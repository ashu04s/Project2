package com.softpro.Project2.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softpro.Project2.entity.Order;
import com.softpro.Project2.entity.User;
import com.softpro.Project2.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    // ================= PLACE ORDER =================

    @PostMapping("/place")
    public String placeOrder(
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam double totalAmount,
            HttpSession session,
            Model model) {

        // Check user login
        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }


        // Create Order
        Order order = new Order();

        order.setUserId(
                user.getId().intValue()
        );

        order.setTotalAmount(totalAmount);

        order.setAddress(address);

        order.setPhone(phone);

        order.setOrderDate(
                LocalDateTime.now()
        );

        order.setStatus("Pending");


        // Save Order
        Order savedOrder =
                orderService.saveOrder(order);


        // Send order to success page
        model.addAttribute(
                "order",
                savedOrder
        );


        return "order-success";
    }


    // ================= MY ORDERS =================

    @GetMapping("/my-orders")
    public String myOrders(
            HttpSession session,
            Model model) {

        // Check user login
        User user =
                (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }


        // Get orders
        model.addAttribute(
                "orders",
                orderService.getAllOrders()
        );


        return "my-orders";
    }


    // ================= ADMIN ORDERS =================

    @GetMapping("/admin/orders")
    public String adminOrders(
            HttpSession session,
            Model model) {

        // Check admin login
        Object admin =
                session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }


        // Get all orders
        model.addAttribute(
                "orders",
                orderService.getAllOrders()
        );


        return "admin/orders";
    }


    // ================= UPDATE ORDER STATUS =================

    @PostMapping("/admin/orders/status")
    public String updateOrderStatus(
            @RequestParam int orderId,
            @RequestParam String status,
            HttpSession session) {

        // Check admin login
        Object admin =
                session.getAttribute("loggedAdmin");

        if (admin == null) {
            return "redirect:/admin/login";
        }


        // Update status
        orderService.updateStatus(
                orderId,
                status
        );


        // Back to orders page
        return "redirect:/order/admin/orders";
    }
}