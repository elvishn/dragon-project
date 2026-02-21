package com.dragonestate.controller;

import com.dragonestate.dto.DragonDto;
import com.dragonestate.dto.DragonRequestDto;
import com.dragonestate.dto.IdDto;
import com.dragonestate.service.DragonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dragons")
public class DragonController {
    private final DragonService service;

    @GetMapping
    public List<DragonDto> getAllDragons() {
        return service.getAllDragons();
    }

    @GetMapping("/{id}")
    public Optional<DragonDto> getDragonById(@PathVariable String id) {
        return service.getDragonDetail(id);
    }

    @GetMapping("/stats/average-power")
    public Double getAveragePower() {
        return service.getAveragePower();
    }

    @PostMapping
    public ResponseEntity<Optional<DragonDto>> createDragon(@RequestBody DragonRequestDto dragon) {
        return new ResponseEntity<>(service.createDragon(dragon), HttpStatus.CREATED);
    }

    @PostMapping("/feed")
    public ResponseEntity<DragonDto> feedDragon(@RequestBody IdDto request) {
        return new ResponseEntity<>(service.feedDragon(request.getId()), HttpStatus.OK);
    }

    @PostMapping("/train")
    public ResponseEntity<DragonDto> trainDragon(@RequestBody IdDto request) {
        return new ResponseEntity<>(service.trainDragon(request.getId()), HttpStatus.OK);
    }
}
