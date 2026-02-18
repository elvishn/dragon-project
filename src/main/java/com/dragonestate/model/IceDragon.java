package com.dragonestate.model;

import com.dragonestate.service.DragonService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor // Конструктор без аргументов
@Getter

public class IceDragon extends Dragon{

    public IceDragon(String name){
        super(DragonService.generateId(), name, DragonType.ICE, SpecialAbility.FrostPower);
    }

    // for database
    public IceDragon(String id, String name, int age, int health,
                      int weight, int hunger, int power) {
        super(id, name, SpecialAbility.FrostPower ,DragonType.ICE, age, health, weight, hunger, power);
    }

    @Override
    public String makeSound() {
        return "I-m an elegant Ice Dragon! I like eat some ice...";
    }

    public void freeze() {
        log.info("You became an iceman. Big or small? I don't know!");
    }

    @Override
    public String toString() {
        return String.format(
                "IceDragon(id='%s', name='%s', age=%d, health=%d, weight=%d, hunger=%d, power=%d, type=%s, peculiarities=%s)",
                getId(), getName(), getAge(), getHealth(), getWeight(), getHunger(), getPower(), getType(), getPeculiarities());
    }
}
