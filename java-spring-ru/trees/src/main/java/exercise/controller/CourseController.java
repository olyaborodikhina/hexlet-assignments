package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public List<Course> getCoursesById(@PathVariable long id) {
        String path = courseRepository.findById(id).getPath();
        List<Long> pathes = getPathArray(path);

        return pathes.stream()
                .filter(Objects::nonNull)
                .map(i -> courseRepository.findById(i).get())
                .collect(Collectors.toList());
    }

    private List<Long> getPathArray(String path) {
        if (path != null && path != "") {
            return Arrays.stream(path.split("\\.")).map(Long::parseLong)
                    .collect(Collectors.toList());
        } else return List.of();
    }
    // END

}
