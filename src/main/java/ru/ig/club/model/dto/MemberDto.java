package ru.ig.club.model.dto;

import ru.ig.club.model.Member;

import java.util.List;
import java.util.stream.Collectors;

public class MemberDto {

    private Long memberId;
    private String memberName;
    private List<PetDtoShort> pets;

    public static MemberDto of(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(member.getMemberId());
        memberDto.setMemberName(member.getMemberName());
        if (member.getPets() != null) {
            memberDto.setPets(
                    member.getPets().stream()
                    .map(PetDtoShort::of)
                    .collect(Collectors.toList())
            );
        }
        return memberDto;
    }

    // generated getters & setters
    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public List<PetDtoShort> getPets() {
        return pets;
    }
    public void setPets(List<PetDtoShort> pets) {
        this.pets = pets;
    }
}
