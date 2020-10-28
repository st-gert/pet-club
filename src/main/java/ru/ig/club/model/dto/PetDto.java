package ru.ig.club.model.dto;

import ru.ig.club.model.Pet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "petId", "kind", "petName" } )
public class PetDto {

    private Long petId;
    private String kind;
    private String petName;

    public static PetDto of(Pet pet) {
        PetDto petDtoShort = new PetDto();
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
