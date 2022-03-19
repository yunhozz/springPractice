package joinexample.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Entity 를 사용함으로써 DB 에 쓰일 필드와 여러 엔티티간 연관관계를 정의한다.
 * ex) 엑셀 테이블: 엔티티 / 각각의 행: 엔티티 객체 / 각각의 열: 필드 (= Column)
 * JPA 에서 정의된 필드들을 바탕으로 DB 에 테이블을 만들어준다.
 */
@Data
@Entity // 쉽게 말해 엑셀 표, 절대 setter 메소드를 만들지 않는다.
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 아예 없는 기본 생성자를 자동으로 만들어준다.
public class Account {

    @Id // 해당 엔티티의 주요 키가 될 값을 지정해줌 (PK)
    @Column(name = "user_id") // 필드 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql 에서 자동생성 되도록 돕는 어노테이션
    private Long id; // -> PK

    private String username;
    private String password;
    private String email;
    private String age;
    private String role;

    @Builder // 해당 클래스에 해당하는 엔티티 객체를 만들 때 Builder 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션
    public Account(Long id, String username, String password, String email, String age, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;

        // DTO -> .toEntity() -> Account.builder().id(id).build()
    }
}
