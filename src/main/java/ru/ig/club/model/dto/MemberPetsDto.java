package ru.ig.club.model.dto;

import ru.ig.club.model.Member;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "memberId", "memberName", "pets" } )
public class MemberPetsDto {

    private Long memberId;
    private String memberName;
    private List<PetDto> pets;

    public static MemberPetsDto of(Member member) {
        MemberPetsDto memberDto = new MemberPetsDto();
        memberDto.setMemberId(member.getMemberId());
        memberDto.setMemberName(member.getMemberName());
        if (member.getPets() != null) {
            memberDto.setPets(
                    member.getPets().stream()
                    .map(PetDto::of)
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
    public List<PetDto> getPets() {
        return pets;
    }
    public void setPets(List<PetDto> pets) {
        this.pets = pets;
    }
}
