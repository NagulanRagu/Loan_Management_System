package com.lms.BDM.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lms.BDM.model.BorrowerDocument;
import com.lms.BDM.pojo.ResponseFile;
import com.lms.BDM.service.BorrowerDocumentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BorrowerDocumentController {

    @Autowired
    BorrowerDocumentService borrowerDocumentService;

    @PostMapping("{borrowerName}/uploadFile/{fileDetail}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile documents,
            @PathVariable String borrowerName, @PathVariable String fileDetail)
            throws IOException {

        try {
            BorrowerDocument addedBorrowerDocument = borrowerDocumentService.saveBorrowerDocuments(documents,
                    borrowerName, fileDetail);
            String fileUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path("/{id}").build(addedBorrowerDocument.getId())
                    .toString();
            return new ResponseEntity<>(
                    new ResponseFile(addedBorrowerDocument.getFileName(), addedBorrowerDocument.getFileDetail(),
                            addedBorrowerDocument.getFileType(), fileUri,
                            addedBorrowerDocument.getFile().length),
                    HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("allFiles")
    public ResponseEntity<?> downloadAllFiles() {

        try {
            List<ResponseFile> responseFiles = borrowerDocumentService.getAllDocuments().stream()
                    .map(borrowerDocument -> {
                        String fileUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/downloadFile/")
                                .path("/{id}").build(borrowerDocument.getId())
                                .toString();
                        return new ResponseFile(borrowerDocument.getFileName(), borrowerDocument.getFileDetail(),
                                borrowerDocument.getFileType(), fileUri, borrowerDocument.getFile().length);
                    }).collect(Collectors.toList());
            return new ResponseEntity<>(responseFiles, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("downloadFile/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable int id) {

        try {
            BorrowerDocument borrowerDocument = borrowerDocumentService.getById(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(borrowerDocument.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename: \"" + borrowerDocument.getFileName() + "\"")
                    .body(borrowerDocument.getFile());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{borrowerName}/downloadFile")
    public ResponseEntity<?> downloadbyBorrowerName(@PathVariable String borrowerName) {

        try {
            List<ResponseFile> responseFiles = borrowerDocumentService.getByBorrowerName(borrowerName).stream()
                    .map(borrowerDocument -> {
                        String fileUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/downloadFile/")
                                .path("/{id}").build(borrowerDocument.getId())
                                .toString();
                        return new ResponseFile(borrowerDocument.getFileName(), borrowerDocument.getFileDetail(),
                                borrowerDocument.getFileType(), fileUri, borrowerDocument.getFile().length);
                    }).collect(Collectors.toList());
            return new ResponseEntity<>(responseFiles, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteFile/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {

        borrowerDocumentService.deleteBorrowerDocument(id);
        return new ResponseEntity<>("Document is Deleted", HttpStatus.OK);
    }
}
