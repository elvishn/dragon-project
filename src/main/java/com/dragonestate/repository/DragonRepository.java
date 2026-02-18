package com.dragonestate.repository;

import com.dragonestate.model.Dragon;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DragonRepository {
    public Dragon save(Dragon dragon);

    public Dragon getById(String id);

    public List<Dragon>  findAll();

    public int deleteById(String id);

    public int count();
}
