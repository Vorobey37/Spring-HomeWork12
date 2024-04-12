package org.example.issue_service;

import jakarta.annotation.PostConstruct;
import lombok.Data;

import java.util.UUID;
@Data
public class Issue {

    private UUID id;
    private Book book;
    private Reader reader;

}
