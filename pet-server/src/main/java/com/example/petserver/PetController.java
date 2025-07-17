package com.example.petserver;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PetController {
    private final PetState state = new PetState("我的宠物", 50, 50);

    @GetMapping("/pet")
    public PetState getState() {
        return state;
    }

    @PostMapping("/feed")
    public PetState feed() {
        state.setHunger(Math.max(0, state.getHunger() - 10));
        state.setHappiness(Math.min(100, state.getHappiness() + 5));
        return state;
    }

    @PostMapping("/play")
    public PetState play() {
        state.setHappiness(Math.min(100, state.getHappiness() + 10));
        state.setHunger(Math.min(100, state.getHunger() + 5));
        return state;
    }

    @PostMapping("/rename")
    public PetState rename(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        if (name != null && !name.isEmpty()) {
            state.setName(name);
        }
        return state;
    }
}
