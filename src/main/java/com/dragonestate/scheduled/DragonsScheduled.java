package com.dragonestate.scheduled;

import com.dragonestate.repository.InMemoryDragonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DragonsScheduled {
    private final InMemoryDragonRepository repository;

    @Scheduled(fixedRate = 60000)
    public void increasedHunger() {
        repository.findAll()
                .forEach(dragon -> {
                    repository.updateHunger(dragon.getId(), Math.min(99, dragon.getHunger()) + 1);}); //update
    }

    @Async
    @Scheduled(fixedRate = 300000)
    public void increasedAge() {
        repository.findAll()
                .forEach(dragon -> {
            repository.updateAge(dragon.getId(), dragon.getAge() + 1);}); //update
    }
}
