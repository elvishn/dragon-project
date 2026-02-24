package com.dragonestate.service;

import com.dragonestate.activaty.Feeding;
import com.dragonestate.activaty.Training;
import com.dragonestate.dto.DragonDto;
import com.dragonestate.dto.DragonRequestDto;
import com.dragonestate.model.*;
import com.dragonestate.repository.InMemoryDragonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DragonService {
    private final InMemoryDragonRepository repository;
    private Feeding  feed = new Feeding();
    private Training train = new Training();

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public List<DragonDto> getAllDragons() {
        return repository.findAll()
                .stream()
                .map(DragonDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<DragonDto> getDragonDetail(String id) {
        return Optional.of(DragonDto.fromEntity(repository.getById(id)));
    }

    public DragonDto feedDragon(String id) {
        Dragon dragon = repository.getById(id);
        repository.deleteById(id);
        feed.perform(dragon);
        repository.save(dragon);
        return DragonDto.fromEntity(dragon);
    }

    public DragonDto trainDragon(String id) {
        Dragon dragon = repository.getById(id);
        repository.deleteById(id);
        train.perform(dragon);
        repository.save(dragon);
        return DragonDto.fromEntity(dragon);
    }

    public DragonDto transformToDTO(Dragon dragon) {
        return DragonDto.fromEntity(dragon);
    }

    public Optional<DragonDto> createDragon(DragonRequestDto request) {
        if (request.getType() == DragonType.FIRE) {
            FireDragon fire = new FireDragon(request.getName());
            repository.save(fire);
            return Optional.of(DragonDto.fromEntity(fire));
        } else if (request.getType() == DragonType.ICE) {
            IceDragon ice = new IceDragon(request.getName());
            repository.save(ice);
            return Optional.of(DragonDto.fromEntity(ice));
        } else if (request.getType() == DragonType.FOREST) {
            ForestDragon forest = new ForestDragon(request.getName());
            repository.save(forest);
            return Optional.of(DragonDto.fromEntity(forest));
        }
        return Optional.empty();
    }

    public double getAveragePower() {
        return repository.avgPower();
    }
}
