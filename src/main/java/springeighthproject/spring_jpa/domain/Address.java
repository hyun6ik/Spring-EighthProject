package springeighthproject.spring_jpa.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Embeddable
@Getter
//값 타입은 Setter를 사용하지 않는것이 좋다
// 생성자에서 갑승ㄹ 모두 초기화해서 변경 불가능한 클래스를 만드는 것이 좋다.
// 또한 기본생성자를 protected로 설정해야 안전하다
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
