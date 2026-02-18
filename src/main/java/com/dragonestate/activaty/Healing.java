package com.dragonestate.activaty;

import com.dragonestate.model.Dragon;

public class Healing implements Activaty{
    @Override
    public void perform(Dragon dragon) {
        int new_health;
        if (dragon.getHealth() <= 90) {
            new_health = dragon.getHealth() + 10;
        } else {new_health = 100;}
        dragon.setHealth(new_health);
        System.out.println("The dragon's health is equal " + new_health);
    }
}
