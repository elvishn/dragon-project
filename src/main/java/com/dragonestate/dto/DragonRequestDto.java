package com.dragonestate.dto;

import com.dragonestate.model.DragonType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DragonRequestDto {
    private String name;
    private DragonType type;


}
