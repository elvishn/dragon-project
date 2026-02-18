package com.dragonestate.model;

import lombok.NonNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DragonMapper implements RowMapper<Dragon> {
    @Override
    public Dragon mapRow(ResultSet resultSet, int i) throws SQLException {
        final String id = resultSet.getString("id");
        final String name =resultSet.getString("name");
        final String peculiarities = resultSet.getString("peculiarities");
        final String type = resultSet.getString("type");
        final int age = resultSet.getInt("age");
        final int health = resultSet.getInt("health");
        final int weight = resultSet.getInt("weight");
        final int hunger = resultSet.getInt("hunger");
        final int power = resultSet.getInt("power");

        switch (type) {
            case "FIRE":
                return new FireDragon(id, name, age, health, weight, hunger, power);

            case "ICE":
                return new IceDragon(id, name, age, health, weight, hunger, power);

            case "FOREST":
                return new ForestDragon(id, name, age, health, weight, hunger, power);
        }
        return null;
    }
}
