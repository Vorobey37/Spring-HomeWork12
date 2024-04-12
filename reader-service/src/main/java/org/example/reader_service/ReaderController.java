package org.example.reader_service;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("reader")
public class ReaderController {

    private final List<Reader> readers = new ArrayList<>();

    @PostConstruct
    public void createReaders(){
        for (int i = 0; i < 10; i++) {
            Reader reader = new Reader();
            reader.setId(UUID.randomUUID());
            reader.setFirstName("Имя читателя №" + i);
            reader.setLastName("Фамилия читателя №" + i);

            readers.add(reader);

        }
    }

    @GetMapping()
    public List<Reader> getAllReaders(){
        return readers;
    }

    @GetMapping("random")
    public Reader getRandomReader(){
        Random random = new Random();
        return readers.get(random.nextInt(readers.size()));
    }
}
