package loginpractice.domain.member;

import lombok.Data;

@Data // getter, setter 자동 적용
public class Member {

    private Long id;
    private String loginId;
    private String loginPw;
    private String name;
}
