package com.scb.bookstore.repository;

import com.scb.bookstore.model.book.Book;
import com.scb.bookstore.model.request.OrderRequest;
import com.scb.bookstore.model.response.OrderResponse;
import com.scb.bookstore.model.user.User;

import java.util.List;

public interface UserOrder {
     public OrderResponse createNewOrderByUser(List<Book> bookList, User user, OrderRequest orderRequest);
     public void deleteUserAndOrder(User user);
}
