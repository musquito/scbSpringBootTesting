package com.scb.bookstore.repository.impl;


import com.scb.bookstore.model.book.Book;
import com.scb.bookstore.repository.ExternalBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("scbExternalAllBooks")
public class ScbExternalAllBooksImpl implements ExternalBookService {
	@Autowired
    RestTemplate restTemplate;


    @Override
    public List<Book> getBooks(String url) {
        ResponseEntity<List<Book>> responseEntity =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Book>>() {
                        });

        return responseEntity.getBody();
    }
}
