package ru.ig.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ig.club.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
