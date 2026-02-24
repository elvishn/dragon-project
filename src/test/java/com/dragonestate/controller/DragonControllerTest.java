package com.dragonestate.controller;

import com.dragonestate.dto.DragonDto;
import com.dragonestate.dto.DragonRequestDto;
import com.dragonestate.dto.IdDto;
import com.dragonestate.model.*;
import com.dragonestate.repository.DragonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import static org.hamcrest.Matchers.*;
import static com.dragonestate.model.DragonType.FOREST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void givenDragons_whenGetExists_thenStatus200_doubleResult() throws Exception {
        Dragon fire = createTestDragon("Twinkle", DragonType.valueOf("FIRE"));
        Dragon ice = createTestDragon("Ledik", DragonType.valueOf("ICE"));

        mockMvc.perform(
                get("/api/dragons/stats/average-power"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());

    }

    @Test
    public void givenDragon_whenFeed_thenStatus200andDragonReturned() throws Exception {
        Dragon fire = createTestDragon("Twinkle", DragonType.valueOf("FIRE"));
        final int nowHealth = fire.getHealth();
        final int nowHunger = fire.getHunger();
        IdDto id = new IdDto(fire.getId());

        mockMvc.perform(
                        post("/api/dragons/train")
                                .content(objectMapper.writeValueAsString(id))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hunger").value(
                        anyOf(
                                is(0),
                                is(nowHunger - 5)
                        )
                ))
                .andExpect(jsonPath("$.health").value(
                        anyOf(
                                is(100),
                                is(nowHealth + 1)
                        )
                ));
    }

    @Test
    public void givenDragon_whenTrain_thenStatus200andDragonReturned() throws Exception {
        Dragon fire = createTestDragon("Twinkle", DragonType.valueOf("FIRE"));
        final int nowPower = fire.getPower();
        final int nowHunger = fire.getHunger();
        IdDto id = new IdDto(fire.getId());

        mockMvc.perform(
                post("/api/dragons/train")
                        .content(objectMapper.writeValueAsString(id))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hunger").value(
                        anyOf(
                                is(100),
                                is(nowHunger + 5)
                        )
                ))
                .andExpect(jsonPath("$.power").value(
                        anyOf(
                                is(100),
                                is(nowPower + 5)
                        )
                ));
    }

    @Test
    @Sql(statements = "DELETE FROM dragons", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenDragon_whenAdd_thenStatus201andDragonReturned() throws Exception {
        DragonRequestDto dragon = new DragonRequestDto("Belka", FOREST);

        mockMvc.perform(
                post("/api/dragons")
                .content(objectMapper.writeValueAsString(dragon))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("Belka"))
                .andExpect(jsonPath("$.peculiarities").value("PoisonLevel"))
                .andExpect(jsonPath("$.type").value("FOREST"))
                .andExpect(jsonPath("$.age").isNumber())
                .andExpect(jsonPath("$.health").isNumber())
                .andExpect(jsonPath("$.weight").isNumber())
                .andExpect(jsonPath("$.hunger").isNumber())
                .andExpect(jsonPath("$.power").isNumber());
    }

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
