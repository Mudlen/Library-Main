package com.main.library.service;

import com.main.library.data.dto.BookDtoFull;
import com.main.library.data.mapper.BookMapper;
import com.main.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public ResponseEntity<List<BookDtoFull>> getListBooks(){
        return ResponseEntity.ok(mapper.toBookDtoList(bookRepository.findAll()));
    }

    public ResponseEntity<BookDtoFull> getBook(UUID id){
        return ResponseEntity.ok(mapper
                .toBookDto(bookRepository.findById(id).orElseThrow()));
    }
}
