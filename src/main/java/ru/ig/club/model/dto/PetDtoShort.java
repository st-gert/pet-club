package ru.ig.club.model.dto;

import ru.ig.club.model.Pet;

public class PetDtoShort {

    private Long petId;
    private String kind;
    private String petName;

    public static PetDtoShort of(Pet pet) {
        PetDtoShort petDtoShort = new PetDtoShort();
        petDtoShort.setPetId(pet.getPetId());
        petDtoShort.setKind(pet.getKind());
        petDtoShort.setPetName(pet.getPetName());
        petDtoShort.setPetName(petDtoShort.getPetName());
        return petDtoShort;
    }

    // generated getters & setters
    public Long getPetId() {
        return petId;
    }
    public void setPetId(Long petId) {
        this.petId = petId;
    }
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getPetName() {
        return petName;
    }
    public void setPetName(String petName) {
        this.petName = petName;
    }
}
