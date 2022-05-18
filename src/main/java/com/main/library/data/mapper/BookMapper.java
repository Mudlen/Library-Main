package com.main.library.data.mapper;

import com.main.library.data.dto.BookDtoFull;
import com.main.library.data.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "path",source = "staticPath")
    BookDtoFull toBookDto(Book book);

    List<BookDtoFull> toBookDtoList(List<Book> books);
}
