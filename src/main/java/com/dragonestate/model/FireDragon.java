package com.dragonestate.model;

import com.dragonestate.service.DragonService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor

public class FireDragon extends Dragon{

    public FireDragon(String name) {
        super(DragonService.generateId(), name, DragonType.FIRE, SpecialAbility.FlameIntensity);
    }

    // for database
    public FireDragon(String id, String name, int age, int health,
                      int weight, int hunger, int power) {
        super(id, name, SpecialAbility.FlameIntensity ,DragonType.FIRE, age, health, weight, hunger, power);
    }

    @Override
    public String makeSound() {
        return "I'm a big Fire Dragon. I like smoke!";
    }

    public void breathFire() {
        log.info("Vuuuuuuuusssshhh. I don't talk with ash..");
    }

    @Override
    public String toString() {
        return String.format("FireDragon(id='%s', name='%s', age=%d, health=%d, weight=%d, hunger=%d, power=%d, type=%s, peculiarities=%s)",
                getId(), getName(), getAge(), getHealth(), getWeight(), getHunger(), getPower(), getType(), getPeculiarities());
    }
}

