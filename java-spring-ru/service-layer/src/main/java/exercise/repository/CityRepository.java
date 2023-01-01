package exercise.repository;

import exercise.model.City;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    // BEGIN
    List<City> findByNameIgnoreCaseStartingWithOrderByNameAsc(String symbol);//OrderByName

    List<City> findByNameStartingWithIgnoreCase(String prefix);
    List<City> findAllByOrderByName();

    Optional<City> findById(long id);
    // END
}
