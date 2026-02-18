package com.dragonestate.dto;

import com.dragonestate.model.DragonType;
import lombok.Data;

@Data
public class DragonResponseDto {
    private String name;
    private DragonType type;
}
