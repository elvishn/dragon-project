package com.dragonestate.activaty;
import com.dragonestate.model.Dragon;

public class Feeding implements Activaty{
    @Override
    public void perform(Dragon dragon) {
        int new_hunger;
        int new_health;
        if (dragon.getHunger() >= 5) {
            new_hunger = dragon.getHunger() - 5;
        } else {new_hunger = 0;}
        if (dragon.getHealth() <= 95) {
            new_health = dragon.getHealth() + 5;
        } else {new_health = 100;}
        dragon.setHunger(new_hunger);
        dragon.setHealth(new_health);
        System.out.println("The dragon's appetite is equal " + new_hunger);
        System.out.println("The dragon's health is equal " + new_health);
    }
}
