package com.edgarfrancisco.repository;

import com.edgarfrancisco.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);

    Book findByCallNumber(String callNumber);

    @Query(value = "SELECT * FROM book WHERE call_number LIKE CONCAT('%', :searchQuery, '%') " +
                   "AND user_id = :user_id ORDER BY book.title", nativeQuery = true)
    List<Book> searchByCallnumber(String searchQuery, Long user_id);

    @Query(value = "SELECT * FROM book WHERE title LIKE CONCAT('%', :searchQuery, '%') " +
                   "AND user_id = :user_id ORDER BY book.title", nativeQuery = true)
    List<Book> searchByTitle(String searchQuery, Long user_id);

    @Query(value = "SELECT * FROM book WHERE subtitle LIKE CONCAT('%', :searchQuery, '%') " +
                   "AND user_id = :user_id ORDER BY book.title", nativeQuery = true)
    List<Book> searchBySubtitle(String searchQuery, Long user_id);

    @Query(value = "SELECT * FROM book WHERE year LIKE CONCAT('%', :searchQuery, '%') " +
            "AND user_id = :user_id ORDER BY book.title", nativeQuery = true)
    List<Book> searchByYear(String searchQuery, Long user_id);

}
