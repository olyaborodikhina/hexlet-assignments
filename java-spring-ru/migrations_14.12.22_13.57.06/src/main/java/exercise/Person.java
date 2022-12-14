package exercise;

import liquibase.pro.packaged.C;

import javax.persistence.*;

@Table(name="person")
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column
    String first_name;

    @Column
    String last_name;

}