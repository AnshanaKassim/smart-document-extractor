package com.extractor.smartextractor.repository;

import com.extractor.smartextractor.model.ExtractionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExtractionRepository
        extends JpaRepository<ExtractionResult, Long> {

    List<ExtractionResult> findByDocumentType(String documentType);
}
