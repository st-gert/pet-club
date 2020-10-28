package ru.ig.club.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "ownerId", "petId", "kind", "petName" } )
public class PetRequest {

    private Long ownerId;
    private Long petId;
    private String kind;
    private String petName;

    // generated getters & setters
    public Long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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
    public Long getPetId() {
        return petId;
    }
    public void setPetId(Long petId) {
        this.petId = petId;
    }
}
