package com.scb.bookstore.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.scb.bookstore.exception.ExternalRequestException;
import com.scb.bookstore.exception.UnexpectedException;
import com.scb.bookstore.model.book.Book;
import com.scb.bookstore.repository.impl.ScbExternalAllBooksImpl;
import com.scb.bookstore.repository.impl.ScbExternalRecommendedBooksImpl;

@Repository
public class ScbExternalBookRepository {
	@Autowired
    private ScbExternalAllBooksImpl scbAllBooks;
	@Autowired
    private ScbExternalRecommendedBooksImpl scbRecommendBooks;

	@Value("${scb.uri.book.all}")
	private String bookAll;

	@Value("${scb.uri.book.recommended}")
	private String url;

    public List<Book> findAllBooking() {
        try {
            List<Book> allBooks = scbAllBooks.getBooks(bookAll);
            List<Integer> recommendedBooks = scbRecommendBooks.getBooks(url);
            allBooks.stream()
                    .parallel()
                    .forEach(book -> {
                        if (recommendedBooks.contains(book.getId())) {
                            book.setIsRecommended(Boolean.TRUE);
                        }
                    });
            return allBooks;
        } catch (ExternalRequestException ex) {
           throw new ExternalRequestException("Can not get books from API.", ex.getDeveloperMessage());
        } catch (Exception ex) {
            throw new UnexpectedException(ex.getMessage());
        }
    }
}
