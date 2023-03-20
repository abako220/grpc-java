package com.grpc.service;

import com.grpc.BookRequest;
import com.grpc.BookResponse;
import com.grpc.BookServiceGrpc;
import com.grpc.entity.Book;
import com.grpc.repository.BookRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {


    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository repository) {
        this.bookRepository = repository;
    }
    @Override
    public void create(BookRequest request, StreamObserver<BookResponse> responseObserver) {
      //  super.create(request, responseObserver);
        Book book = Book.fromProto(request);
        book = bookRepository.save(book);
        responseObserver.onNext(book.toPro());
        responseObserver.onCompleted();

    }
}
