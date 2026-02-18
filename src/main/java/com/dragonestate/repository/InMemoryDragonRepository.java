package com.dragonestate.repository;

import com.dragonestate.model.Dragon;
import com.dragonestate.model.DragonMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class InMemoryDragonRepository implements DragonRepository{
    private final NamedParameterJdbcTemplate jdbc;

    public InMemoryDragonRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Dragon save(Dragon dragon) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", dragon.getId());
        params.put("name", dragon.getName());
        params.put("peculiarities", dragon.getPeculiarities().name());
        params.put("type", dragon.getType().name());
        params.put("age", dragon.getAge());
        params.put("health", dragon.getHealth());
        params.put("weight", dragon.getWeight());
        params.put("hunger", dragon.getHunger());
        params.put("power", dragon.getPower());
        SqlParameterSource paramSource = new MapSqlParameterSource(params);
        jdbc.update("insert into dragons (id, name, peculiarities, type, age, health, weight, hunger, power) values " +
                "(:id, :name, :peculiarities, :type, :age, :health, :weight, :hunger, :power)", paramSource);
        return dragon;
    }

    @Override
    public Dragon getById(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject("select * from dragons where id=:id", params, new DragonMapper());
    }

    @Override
    public List<Dragon> findAll() {
        return jdbc.query("select * from dragons", new DragonMapper());
    }

    @Override
    public int deleteById(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.update("delete from dragons where id=:id", params);
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from dragons", new HashMap<>(), Integer.class);
    }

    public double avgPower() {
        return jdbc.queryForObject("select avg(power) from dragons", new HashMap<>(), Double.class);
    }

    public void updateAge(String id, int num) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("age", num);
        jdbc.update("update dragons set age=:age where id=:id", params);
    }

    public void updateHunger(String id, int num) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("hunger", num);
        jdbc.update("update dragons set hunger=:hunger where id=:id", params);
    }
}
