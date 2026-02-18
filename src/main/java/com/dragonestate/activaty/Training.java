package com.dragonestate.activaty;
import com.dragonestate.model.Dragon;

public class Training implements Activaty{
    @Override
    public void perform(Dragon dragon) {
        int new_power;
        int new_hungry;
        if (dragon.getPower() <= 95) {
            new_power = dragon.getPower() + 5;
        } else {new_power = 100;}
        if (dragon.getHunger() >= 5) {
            new_hungry = dragon.getHunger() - 5;
        } else {new_hungry = 0;}
        dragon.setPower(new_power);
        dragon.setHunger(new_hungry);
        System.out.println("The dragon became stronger by 5 units, but its very hunger!");
    }
}
