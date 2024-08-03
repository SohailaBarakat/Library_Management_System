package com.books.library.management.system.mapper;


import com.books.library.management.system.dto.BookDTO;
import com.books.library.management.system.entity.Book;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


public class BookMapper {
    public BookMapper(){}

    public Book toEntity(BookDTO bookReqDTO) {
        if ( bookReqDTO == null ) {
            return null;
        }

        return Book.builder()
                .title( bookReqDTO.getTitle() )
                .author( bookReqDTO.getAuthor() )
                .isbn( bookReqDTO.getIsbn() )
                .publisher( bookReqDTO.getPublisher() )
                .yearPublished( bookReqDTO.getYearPublished() )
                .genre( bookReqDTO.getGenre() )
                .copiesAvailable( bookReqDTO.getCopiesAvailable() )
                .build();

    }


    public BookDTO toDto(Book book) {
        if ( book == null ) {
            return null;
        }

        return BookDTO.builder()
                .id(book.getId())
                .title( book.getTitle() )
                .author( book.getAuthor() )
                .isbn( book.getIsbn() )
                .publisher( book.getPublisher() )
                .yearPublished( book.getYearPublished() )
                .genre( book.getGenre() )
                .copiesAvailable( book.getCopiesAvailable() )
                .build();
    }

    public  List<BookDTO> toDto(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookDTO> list = new ArrayList<BookDTO>( books.size() );
        for ( Book book : books ) {
            list.add( toDto( book ) );
        }

        return list;
    }
}
