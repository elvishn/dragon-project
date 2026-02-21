package com.dragonestate.controller;

import com.dragonestate.dto.DragonDto;
import com.dragonestate.model.*;
import com.dragonestate.repository.DragonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DragonControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DragonRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(statements = "DELETE FROM dragons", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenDragons_whenGetDragons_thenStatus200() throws Exception {
        Dragon fire = createTestDragon("Twinkle", DragonType.valueOf("FIRE"));
        Dragon ice = createTestDragon("Ledik", DragonType.valueOf("ICE"));
        Dragon forest = createTestDragon("Toothless", DragonType.valueOf("FOREST"));

        mockMvc.perform(
                get("/api/dragons"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().json(objectMapper.writeValueAsString(Arrays.asList(fire,ice, forest))));
    }

    @Test
    public void givenId_whenGetExistingDragon_thenStatus200andDragonReturned() throws Exception {

        String id = createTestDragon("Michail", DragonType.valueOf("FIRE")).getId();

        mockMvc.perform(
                        get("/api/dragons/{id}", id))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(id))
                .andExpect((ResultMatcher) jsonPath("$.name").value("Michail"));
    }

    private Dragon createTestDragon(String name, DragonType type) {
        return switch (type) {
            case FIRE -> {
                FireDragon fire = new FireDragon(name);
                repository.save(fire);
                yield fire;
            }
            case ICE -> {
                IceDragon ice = new IceDragon(name);
                repository.save(ice);
                yield ice;
            }
            case FOREST -> {
                ForestDragon forest = new ForestDragon(name);
                repository.save(forest);
                yield forest;
            }
        };
    }

}
