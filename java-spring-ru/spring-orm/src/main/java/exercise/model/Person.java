package exercise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// BEGIN
@Entity
// END
public class Person {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // BEGIN
    @Column
    @Getter
    @Setter
    private String firstName;

    @Column
    @Getter
    @Setter
    private String lastName;
    // END
}
