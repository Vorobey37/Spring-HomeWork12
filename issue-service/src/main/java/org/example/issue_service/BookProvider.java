package org.example.issue_service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class BookProvider {

    private final WebClient bookClient;
    private final EurekaClient eurekaClient;

    public BookProvider(EurekaClient eurekaClient) {
        bookClient = WebClient.builder().build();
        this.eurekaClient = eurekaClient;
    }

    public Book getRandomBook(){
        Book randomBook = bookClient.get()
                .uri(getBookServiceIp() + "/book/random")
                .retrieve()
                .bodyToMono(Book.class)
                .block();
        return randomBook;
    }

    private String getBookServiceIp(){
        Application application = eurekaClient.getApplication("BOOK-SERVICE");
        List<InstanceInfo> instanceInfos = application.getInstances();

        Random random = new Random();
        InstanceInfo randomInstance = instanceInfos.get(random.nextInt(instanceInfos.size()));

        return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort();
    }
}
