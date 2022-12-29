
package enterprise.repositories;

import enterprise.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    List<Category> findAll();
    Category findByName(String categoryName);
    boolean existsByName(String categoryName);
}
