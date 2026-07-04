package com.extractor.smartextractor.controller;

import com.extractor.smartextractor.model.ExtractionResult;
import com.extractor.smartextractor.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working!");
    }

    @PostMapping("/extract")
    public ResponseEntity<ExtractionResult> extract(
            @RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(
                documentService.processDocument(file)
        );
    }

    @GetMapping
    public ResponseEntity<List<ExtractionResult>> getAll() {
        return ResponseEntity.ok(documentService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ExtractionResult>> search(
            @RequestParam String type) {
        return ResponseEntity.ok(
                documentService.searchByType(type)
        );
    }
}