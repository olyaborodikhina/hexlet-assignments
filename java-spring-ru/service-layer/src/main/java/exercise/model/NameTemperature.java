package exercise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
//@Entity
public class NameTemperature {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;*/
    private String name;
    private String temperature;
}
