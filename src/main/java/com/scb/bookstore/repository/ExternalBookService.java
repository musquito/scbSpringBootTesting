package com.scb.bookstore.repository;


import java.util.List;

public interface ExternalBookService<T> {

    List<T> getBooks(String url);
}
