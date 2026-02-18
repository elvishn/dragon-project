package com.dragonestate.scheduled;

import com.dragonestate.repository.InMemoryDragonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DragonsScheduled {
    private final InMemoryDragonRepository repository;

    @Value("${dragons.hunger.fixed-rate}")
    @Scheduled(fixedRateString = "${dragons.hunger.fixed-rate}")
    public void increasedHunger() {
        repository.findAll()
                .forEach(dragon -> {
                    repository.updateHunger(dragon.getId(), Math.min(99, dragon.getHunger()) + 1);}); //update
    }

    @Value("${dragons.age.fixed-rate}")
    @Async
    @Scheduled(fixedRateString = "${dragons.age.fixed-rate}")
    public void increasedAge() {
        repository.findAll()
                .forEach(dragon -> {
            repository.updateAge(dragon.getId(), dragon.getAge() + 1);}); //update
    }
}
