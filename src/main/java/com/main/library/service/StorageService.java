package com.main.library.service;

import com.main.library.data.entity.Book;
import com.main.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final String basePath = "src/main/resources/static/uploads";
    private final BookRepository bookRepository;

    public ResponseEntity<String> upload(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path path = Paths.get(basePath+"/"+fileName);
        Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
        String staticPath = "/uploads/"+fileName;
        Book book = new Book();
        book.setFormat("PDF");
        book.setName(fileName);
        String simpleName = fileName.replaceAll("[-_]"," ").replace(".pdf","");
        book.setSimpleName(simpleName);
        book.setStaticPath(staticPath);
        return ResponseEntity.ok(bookRepository.saveAndFlush(book).getId().toString());
    }

    public ResponseEntity<Resource> download(UUID id) throws MalformedURLException {
        Book book = bookRepository.findById(id).orElseThrow();
        Path path = Paths.get(basePath+"/"+book.getName());
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok(resource);
    }
}
