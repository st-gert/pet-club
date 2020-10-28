package ru.ig.club.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ig.club.exception.ApplDbNoDataFoundException;
import ru.ig.club.model.Member;
import ru.ig.club.model.Pet;
import ru.ig.club.model.dto.PetOwnerDto;
import ru.ig.club.model.dto.PetRequest;
import ru.ig.club.repository.PetRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<PetOwnerDto> getPetList() {
        return repository.findAll()
                .stream()
                .map(PetOwnerDto::of)
                .collect(Collectors.toList())
                ;
    }

    public PetOwnerDto getPetById(Long petId) {
        return PetOwnerDto.of(
                repository.findById(petId)
                .orElseThrow(ApplDbNoDataFoundException::new)
        );
    }

    @Transactional
    public PetOwnerDto addPet(PetRequest request) {
        Member member = new Member();
        member.setMemberId(request.getOwnerId());
        Pet pet = new Pet();
        pet.setOwner(member);
        pet.setPetId(request.getPetId());
        pet.setKind(request.getKind());
        pet.setPetName(request.getPetName());
        return PetOwnerDto.of(repository.save(pet));
    }

    @Transactional
    public PetOwnerDto updatePet(PetRequest request) {
        if (!repository.existsById(request.getPetId())) {
            throw new ApplDbNoDataFoundException();
        }
        return addPet(request);
    }

    @Transactional
    public void deletePet(Long petId) {
        if (!repository.existsById(petId)) {
            throw new ApplDbNoDataFoundException();
        }
        repository.deleteById(petId);
    }

}
