package com.scb.bookstore.model.book;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {

    public Book(){}

    public Book(int id, String bookName, String authorName, BigDecimal price) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
    }

    @JsonProperty("id")
    private int id;

    @JsonProperty("book_name")
    private String bookName;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("is_recommended")
    private Boolean isRecommended = Boolean.FALSE;

}
