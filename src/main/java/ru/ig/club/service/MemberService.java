package ru.ig.club.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ig.club.exception.ApplDbNoDataFoundException;
import ru.ig.club.model.Member;
import ru.ig.club.model.dto.MemberDto;
import ru.ig.club.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public List<MemberDto> getMemberList() {
        return repository.findAll()
                .stream()
                .map(MemberDto::of)
                .collect(Collectors.toList())
                ;
    }

    public MemberDto getMemberById(Long memberId) {
        return MemberDto.of(repository.findById(memberId)
                .orElseThrow(ApplDbNoDataFoundException::new)
        );
    }

    public MemberDto getMemberByName(String memberName) {
        return MemberDto.of(repository.findByMemberName(memberName)
                .orElseThrow(ApplDbNoDataFoundException::new)
        );
    }

    @Transactional
    public MemberDto addMember (Member member) {
        return MemberDto.of(repository.save(member));
    }

    @Transactional
    public MemberDto updateMember(Member member) {
        if (!repository.existsById(member.getMemberId())) {
            throw new ApplDbNoDataFoundException();
        }
        return MemberDto.of(repository.save(member));
    }

    @Transactional
    public void deleteMember(Long memberId) {
        if (!repository.existsById(memberId)) {
            throw new ApplDbNoDataFoundException();
        }
        repository.deleteById(memberId);
    }

}
