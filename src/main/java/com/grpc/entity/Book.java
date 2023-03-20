package com.grpc.entity;

import com.grpc.BookRequest;
import com.grpc.BookResponse;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Data
public class Book {

    @Id
    Long id;
    private String name;
    private String publish_year;
    private String author;
    private boolean available;

    public Book() {}
    public Book(String name, String publish_year, String author, boolean available) {
        this.name = name;
        this.publish_year = publish_year;
        this.author = author;
        this.available = available;
    }

    public BookResponse toPro() {
        return BookResponse.newBuilder()
                .setId(getId())
                .setName(getName())
                .setPublishYear(getPublish_year())
                .setAuthor(getAuthor())
                .setAvailable(isAvailable())
                .build();
    }

    public static Book fromProto(BookRequest bookRequest){
        Book  book = new Book();
        book.setId(bookRequest.getId());
        book.setName(bookRequest.getName());
        book.setPublish_year(bookRequest.getPublishYear());
        book.setAuthor(bookRequest.getAuthor());
        book.setAvailable(bookRequest.getAvailable());
        return book;
    }
}
