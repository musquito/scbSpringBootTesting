package com.scb.bookstore.repository.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scb.bookstore.exception.DataNotFoundException;
import com.scb.bookstore.model.book.Book;
import com.scb.bookstore.model.order.Order;
import com.scb.bookstore.model.request.OrderRequest;
import com.scb.bookstore.model.response.OrderResponse;
import com.scb.bookstore.model.user.User;
import com.scb.bookstore.repository.OrderService;
import com.scb.bookstore.repository.UserOrder;
import com.scb.bookstore.repository.UserService;

@Service
public class UserOrderServiceImpl implements UserOrder {

	@Autowired
    private UserService userService;
	@Autowired
    private OrderService orderService;


    @Transactional
    public OrderResponse createNewOrderByUser(List<Book> bookList, User user, OrderRequest orderRequest) {
        OrderResponse orderResponse = new OrderResponse();
        List<Order> orders = new ArrayList<>();
        for (int bookId : orderRequest.getOrders()) {
            Book book = bookList.stream()
                    .filter(b -> b.getId() == bookId)
                    .findFirst().orElse(null);
            if (book == null) {
                final String message = bookId + " is not found.";
                throw new DataNotFoundException(message, message);
            }
            orderResponse.setPrice(orderResponse.getPrice().add(book.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
            Order order = new Order();
            DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");  
            String strDate = dateFormat.format(new Date());  
            order.setOrderId(user.getId()+"" + bookId +"" + strDate+"");
            order.setDateOfOrder(new Date());
            order.setBookId(bookId);
            order.setUserId(user.getId());
            orders.add(order);
            orderService.save(order);
        }
        return orderResponse;
    }

    @Override
    public void deleteUserAndOrder(User user) {
        orderService.deleteByUserId(user.getId());
        userService.deleteById(user.getId());
    }
}
