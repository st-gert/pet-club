package ru.ig.club.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.ig.club.exception.ApplDbConstraintException;
import ru.ig.club.exception.ApplDbNoDataFoundException;
import ru.ig.club.model.dto.PetOwnerDto;
import ru.ig.club.model.dto.PetRequest;
import ru.ig.club.service.PetService;

import java.util.List;

@RestController
@RequestMapping("/club/rest/pet")
public class PetRestController {

    private final PetService service;

    public PetRestController(PetService service) {
        this.service = service;
    }


    @GetMapping
    public List<PetOwnerDto> getAllPets() {
        return service.getPetList();
    }

    @GetMapping("/{id}")
    public PetOwnerDto getPetById(@PathVariable("id") Long petId) {
        try {
            return service.getPetById(petId);
        } catch (ApplDbNoDataFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet ID " + petId + " not found");
        }
    }

    @PostMapping
    public PetOwnerDto addPet(@RequestBody PetRequest petRequest) {
        if (petRequest.getPetId() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Pet ID is not empty");
        }
        try {
            return service.addPet(petRequest);
        } catch (ApplDbConstraintException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet kind/name is not unique");
        }
    }

    @PutMapping
    public PetOwnerDto updatePet(@RequestBody PetRequest petRequest) {
        try {
            return service.updatePet(petRequest);
        } catch (ApplDbNoDataFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet ID " + petRequest.getPetId() + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") Long id) {
        try {
            service.deletePet(id);
        } catch (ApplDbConstraintException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "Unable to delete pet ID " + id + ". There are related pets.");
        } catch (ApplDbNoDataFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet ID " + id + " not found");
        }
    }

}
