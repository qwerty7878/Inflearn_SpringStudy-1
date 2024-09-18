package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity
//  jpa를 사용하기 위해
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    db에 id 값을 자동 생성해주는 것
    private Long id;
//    @Column(name = "username")
//    db안에 이름
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
