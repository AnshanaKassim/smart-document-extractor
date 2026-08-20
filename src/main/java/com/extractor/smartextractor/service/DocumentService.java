package com.extractor.smartextractor.service;

import com.extractor.smartextractor.model.ExtractionResult;
import com.extractor.smartextractor.repository.ExtractionRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class DocumentService {

    private final ExtractionRepository repository;
    private final OpenAIService openAIService;

    public DocumentService(ExtractionRepository repository,
                           OpenAIService openAIService) {
        this.repository = repository;
        this.openAIService = openAIService;
    }

    public ExtractionResult processDocument(MultipartFile file)
            throws IOException {

        // Step 1 - Extract text from PDF
        PDDocument document = Loader.loadPDF(file.getBytes());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();
        String truncatedText = text.length() > 3000 ? text.substring(0, 3000) : text;

        // Step 2 - Send text to OpenAI
        String extractedJson = openAIService.extractData(truncatedText);

        // Step 3 - Parse document type from response
        String documentType = "unknown";
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> parsed = mapper.readValue(extractedJson, Map.class);
            documentType = (String) parsed.getOrDefault("document_type", "unknown");
        } catch (Exception e) {
            documentType = "unknown";
        }

        // Step 4 - Save result to database
        ExtractionResult result = new ExtractionResult();
        result.setFileName(file.getOriginalFilename());
        result.setExtractedData(extractedJson);
        result.setDocumentType(documentType);



        return repository.save(result);
    }

    public List<ExtractionResult> getAll() {
        return repository.findAll();
    }

    public List<ExtractionResult> searchByType(String type) {
        return repository.findByDocumentType(type);
    }
}