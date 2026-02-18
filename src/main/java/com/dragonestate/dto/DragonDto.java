package com.dragonestate.dto;

import com.dragonestate.model.Dragon;
import com.dragonestate.model.SpecialAbility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DragonDto {
    public String id;
    public String name;
    public String peculiarities;
    public String type;
    public int age;
    public int health;
    public int hunger;
    public int power;

    public static DragonDto fromEntity(Dragon dragon) {
        String id = dragon.getId();
        String name = dragon.getName();
        String type = dragon.getType().name();
        int age = dragon.getAge();
        int health = dragon.getHealth();
        int hunger = dragon.getHunger();
        int power = dragon.getPower();
        String peculiarities = dragon.getPeculiarities().name();
        DragonDto data = new DragonDto(id, name, peculiarities, type, age, health, hunger, power);
        return data;
    }
}
