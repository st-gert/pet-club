package ru.ig.club.controller.soap;

import ru.ig.club.exception.ApplDbConstraintException;
import ru.ig.club.exception.ApplDbNoDataFoundException;
import ru.ig.club.exception.ApplException;
import ru.ig.club.model.Member;
import ru.ig.club.model.dto.MemberDto;
import ru.ig.club.model.dto.MemberPetsDto;
import ru.ig.club.service.MemberService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://ig.ru/ws/club/2020-10-27")
public class MemberSoapController {

    private final MemberService service;

    public MemberSoapController(MemberService service) {
        this.service = service;
    }

    @WebMethod
    public List<MemberPetsDto> getAllMembers() {
        return service.getMemberList();
    }

    @WebMethod
    public MemberPetsDto getMemberById(@WebParam(name = "memberId") Long memberId) {
        try {
            return service.getMemberById(memberId);
        } catch (ApplDbNoDataFoundException e) {
            throw new ApplException("Member ID " + memberId + " not found");
        }
    }

    @WebMethod
    public MemberPetsDto getMemberByName(@WebParam(name = "memberName") String memberName) {
        try {
            return service.getMemberByName(memberName);
        } catch (ApplDbNoDataFoundException e) {
            throw new ApplException("Member " + memberName + " not found");
        }
    }

    @WebMethod
    public MemberDto addMember(@WebParam(name = "memberName") String memberName) {
        try {
            Member member = new Member();
            member.setMemberName(memberName);
            return service.addMember(member);
        } catch (ApplDbConstraintException e) {
            throw new ApplException("Member name is not unique");
        }
    }

    @WebMethod
    public MemberDto updateMember(@WebParam(name = "request") MemberDto memberDto) {
        try {
            Member member = new Member();
            member.setMemberId(memberDto.getMemberId());
            member.setMemberName(memberDto.getMemberName());
            return service.updateMember(member);
        } catch (ApplDbNoDataFoundException e) {
            throw new ApplException("Member ID " + memberDto.getMemberId() + " not found");
        }
    }

    @WebMethod
    public void deleteGenre(@WebParam(name = "memberId") Long id) {
        try {
            service.deleteMember(id);
        } catch (ApplDbConstraintException e) {
            throw new ApplException("Unable to delete member ID " + id + ". There are related pets.");
        } catch (ApplDbNoDataFoundException e) {
            throw new ApplException("Member ID " + id + " not found");
        }
    }

}
