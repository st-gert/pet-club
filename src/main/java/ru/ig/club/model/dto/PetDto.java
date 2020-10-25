package ru.ig.club.model.dto;

import ru.ig.club.model.Pet;

public class PetDto {

    private Long petId;
    private String kind;
    private String petName;
    private Long ownerId;
    private String ownerName;

    public static PetDto of(Pet pet) {
        PetDto petDto = new PetDto();
        petDto.setPetId(pet.getPetId());
        petDto.setKind(pet.getKind());
        petDto.setPetName(pet.getPetName());
        petDto.setPetName(petDto.getPetName());
        petDto.setOwnerId(pet.getOwner().getMemberId());
        petDto.setOwnerName(pet.getOwner().getMemberName());
        return petDto;
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
    public Long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
