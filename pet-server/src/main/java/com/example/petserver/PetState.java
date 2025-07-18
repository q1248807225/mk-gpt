package com.example.petserver;

public class PetState {
    private String name;
    private int hunger;
    private int happiness;

    public PetState() {}

    public PetState(String name, int hunger, int happiness) {
        this.name = name;
        this.hunger = hunger;
        this.happiness = happiness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }
}
