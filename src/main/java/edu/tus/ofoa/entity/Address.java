package edu.tus.ofoa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name = "Addresses")
@Transactional
public class Address extends BaseEntity {

    public Address(){}
    private String line1;

    private String state;

    private String zipcode;

    public Address(String line1, String state, String zipcode) {
        this.line1 = line1;
        this.state = state;
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "line1='" + line1 + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
