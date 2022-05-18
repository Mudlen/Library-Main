package com.main.library.controllers.storage;

import com.main.library.api.StorageApi;
import com.main.library.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/library-storage/1.0.0")
public class StorageRestController implements StorageApi {

    private final StorageService storageService;

    @Override
    @SneakyThrows
    public ResponseEntity<Resource> downloadFile(UUID id) {
        return storageService.download(id);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<String> uploadFile(MultipartFile file) {
        return storageService.upload(file);
    }
}
