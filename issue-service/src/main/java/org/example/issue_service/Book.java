package org.example.issue_service;

import lombok.Data;
import org.example.book_service.Author;

import java.util.UUID;

@Data
public class Book {

    private UUID id;
    private String name;
    private Author author;
}
