package com.dragonestate.model;

import com.dragonestate.service.DragonService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor // Конструктор без аргументов
@Getter

public class ForestDragon extends Dragon{

    public ForestDragon(String name){
        super(DragonService.generateId(), name, DragonType.FOREST, SpecialAbility.PoisonLevel);
    }

    // for database
    public ForestDragon(String id, String name, int age, int health,
                      int weight, int hunger, int power) {
        super(id, name, SpecialAbility.PoisonLevel ,DragonType.FOREST, age, health, weight, hunger, power);
    }

    @Override
    public String makeSound() {
        return "I'm a pretty Forest Dragon. If I'll hide, you never can't found me!";
    }

    public void camouflage() {
        log.info("I hiding!");
    }

    @Override
    public String toString() {
        return String.format(
                "ForestDragon(id='%s', name='%s', age=%d, health=%d, weight=%d, hunger=%d, power=%d, type=%s, peculiarities=%s)",
                getId(), getName(), getAge(), getHealth(), getWeight(), getHunger(), getPower(), getType(), getPeculiarities());
    }
}
