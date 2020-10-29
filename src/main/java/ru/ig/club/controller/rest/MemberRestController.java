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
import ru.ig.club.model.Member;
import ru.ig.club.model.dto.MemberAddRequest;
import ru.ig.club.model.dto.MemberDto;
import ru.ig.club.model.dto.MemberPetsDto;
import ru.ig.club.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/club/rest/member")
public class MemberRestController {

    private final MemberService service;

    public MemberRestController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public List<MemberPetsDto> getAllMembers() {
        return service.getMemberList();
    }

    @GetMapping("/{id}")
    public MemberPetsDto getMemberById(@PathVariable("id") Long memberId) {
        try {
            return service.getMemberById(memberId);
        } catch (ApplDbNoDataFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member ID " + memberId + " not found");
        }
    }

    @GetMapping("/name/{name}")
    public MemberPetsDto getMemberByName(@PathVariable("name") String memberName) {
        try {
            return service.getMemberByName(memberName);
        } catch (ApplDbNoDataFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member " + memberName + " not found");
        }
    }

    @PostMapping
    public MemberDto addMember(@RequestBody MemberAddRequest memberAddRequest) {
        try {
            Member member = new Member();
            member.setMemberName(memberAddRequest.getMemberName());
            return service.addMember(member);
        } catch (ApplDbConstraintException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member name is not unique");
        }
    }

    @PutMapping
    public MemberDto updateMember(@RequestBody MemberDto memberDto) {
        try {
            Member member = new Member();
            member.setMemberName(memberDto.getMemberName());
            member.setMemberId(memberDto.getMemberId());
            return service.updateMember(member);
        } catch (ApplDbNoDataFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member ID " + memberDto.getMemberId() + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") Long id) {
        try {
            service.deleteMember(id);
        } catch (ApplDbConstraintException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "Unable to delete member ID " + id + ". There are related pets.");
        } catch (ApplDbNoDataFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member ID " + id + " not found");
        }
    }

}
