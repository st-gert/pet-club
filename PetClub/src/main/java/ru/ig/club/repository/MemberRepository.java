package ru.ig.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ig.club.model.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberName(String memberName);

}
