package ru.ig.club.controller.soap;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.ig.club.exception.ApplDbConstraintException;
import ru.ig.club.exception.ApplDbNoDataFoundException;
import ru.ig.club.model.dto.PetAddRequest;
import ru.ig.club.model.dto.PetOwnerDto;
import ru.ig.club.model.dto.PetUpdateRequest;
import ru.ig.club.service.PetService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://ig.ru/ws/club/2020-10-27")
public class PetSoapController {

    private final PetService service;

    public PetSoapController(PetService service) {
        this.service = service;
    }


    @WebMethod
    public List<PetOwnerDto> getAllPets() {
        return service.getPetList();
    }

    @WebMethod
    public PetOwnerDto getPetById(@WebParam(name = "petId") Long petId) {
        try {
            return service.getPetById(petId);
        } catch (ApplDbNoDataFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet ID " + petId + " not found");
        }
    }

    @WebMethod
    public PetOwnerDto addPet(@WebParam(name = "request") PetAddRequest petRequest) {
        try {
            return service.addPet(petRequest);
        } catch (ApplDbConstraintException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet kind/name is not unique");
        }
    }

    @WebMethod
    public PetOwnerDto updatePet(@WebParam(name = "request") PetUpdateRequest petRequest) {
        try {
            return service.updatePet(petRequest);
        } catch (ApplDbNoDataFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet ID " + petRequest.getPetId() + " not found");
        }
    }

    @WebMethod
    public void deleteGenre(@WebParam(name = "petId") Long id) {
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
