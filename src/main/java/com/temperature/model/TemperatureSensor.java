package com.temperature.model;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TemperatureSensor {

    private final ApplicationEventPublisher publisher;

    private final Random random = new Random();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public TemperatureSensor(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    @PostConstruct
    private void startProcessing(){
        executorService.schedule(this::probe, 3, TimeUnit.SECONDS);
    }

    private void probe(){
        double temperature = 10 + random.nextGaussian() * 20;
        publisher.publishEvent(new Temperature(temperature));
        executorService.schedule(this::probe, random.nextInt(5000), TimeUnit.MILLISECONDS);
    }
}
