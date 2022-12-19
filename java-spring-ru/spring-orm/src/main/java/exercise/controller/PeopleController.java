package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {

    // Автоматически заполняем значение поля
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return this.personRepository.findById(id);
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public void add(@RequestBody Person person){
        this.personRepository.save(person);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        this.personRepository.delete(personRepository.findById(id));
    }

    @PatchMapping(path = "/{id}")
    public void update(@PathVariable("id") long id, @RequestBody Person person){
        this.personRepository.findById(id).setFirstName(person.getFirstName());
        this.personRepository.findById(id).setLastName(person.getLastName());
    }
    // END
}