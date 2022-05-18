package com.main.library.controllers.view;

import com.main.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewControllerMain {
    private final LibraryService libraryService;
    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/pdf-view/{id}")
    public String getPdfView(@PathVariable("id") UUID id, Model model){

        model.addAttribute("path",
                Objects.requireNonNull(libraryService.getBook(id).getBody()).getPath());
        return "pdfView";
    }
}
