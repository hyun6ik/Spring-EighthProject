package springeighthproject.spring_jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
