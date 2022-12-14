package exercise.controller;

import exercise.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "")
    public List<Map<String, Object>> getPerson() {
        //ResultSet resultSet  = jdbc.execute("SELECT * FROM person");

        List<Map<String, Object>> list = jdbc.queryForList("SELECT * FROM person");
        return list;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getPersonById(@PathVariable("id") Integer id) {
        String querysql = "SELECT * FROM person WHERE id = " + id;
        Map<String, Object> p = jdbc.queryForMap(querysql);
        System.out.println(p);
        return p;
    }
    // END
}
