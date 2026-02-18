package com.dragonestate.model;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Data
@NoArgsConstructor // Конструктор без аргументов
@AllArgsConstructor
public abstract class Dragon {
    private String id;
    private String name;

    private SpecialAbility peculiarities;

    private DragonType type;
    private int age;
    private int health;
    private int weight;
    private int hunger;
    private int power;

    public Dragon(String id, String name, DragonType type, SpecialAbility peculiarities) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.peculiarities = peculiarities;
        this.age = 0;
        this.health = 100;
        this.weight = 15;
        this.hunger = 50;
        this.power = 27;
    }

    public abstract String makeSound();

    public void sleep() {
        this.power += 15;
        log.info("Hhhhhrrrr-ssssss-hhhhrrrr");
        log.info("The dragon became stronger by 15 units");
    }
}
