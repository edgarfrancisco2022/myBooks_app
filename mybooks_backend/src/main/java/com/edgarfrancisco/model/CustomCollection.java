package com.edgarfrancisco.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customCollectionName;

    @ManyToMany(mappedBy = "customCollections", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT) // hibernate does not like two collections with FetchType.EAGER // this fixes the problem but must refactor with FetchType.Lazy
    private List<Book> books; // Unidirectional

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public CustomCollection() {
    }

    public CustomCollection(Long id, String customCollectionName, List<Book> books, User user) {
        this.id = id;
        this.customCollectionName = customCollectionName;
        this.books = books;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomCollectionName() {
        return customCollectionName;
    }

    public void setCustomCollectionName(String customCollectionName) {
        this.customCollectionName = customCollectionName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.getAuthors().remove(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
