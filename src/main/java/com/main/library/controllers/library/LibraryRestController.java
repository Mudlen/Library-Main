package com.main.library.controllers.library;

import com.main.library.api.LibraryApi;
import com.main.library.data.dto.BookDtoFull;
import com.main.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/library-storage/1.0.0")
public class LibraryRestController implements LibraryApi {

    private final LibraryService libraryService;

    @Override
    public ResponseEntity<BookDtoFull> getBook(UUID id) {
        return libraryService.getBook(id);
    }

    @Override
    public ResponseEntity<List<BookDtoFull>> getListBooks() {
        return libraryService.getListBooks();
    }
}
