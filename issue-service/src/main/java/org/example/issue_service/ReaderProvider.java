package org.example.issue_service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class ReaderProvider {

    private final WebClient readerClient;
    private final EurekaClient eurekaClient;

    public ReaderProvider(EurekaClient eurekaClient) {
        readerClient = WebClient.builder().build();
        this.eurekaClient = eurekaClient;
    }

    public Reader getRandomReader(){
        Reader randomReader = readerClient.get()
                .uri(getReaderServiceIp() + "/reader/random")
                .retrieve()
                .bodyToMono(Reader.class)
                .block();
        return randomReader;
    }

    private String getReaderServiceIp(){
        Application application = eurekaClient.getApplication("READER-SERVICE");
        List<InstanceInfo> instanceInfos = application.getInstances();

        Random random = new Random();
        InstanceInfo randomInstance = instanceInfos.get(random.nextInt(instanceInfos.size()));

        return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort();
    }
}
