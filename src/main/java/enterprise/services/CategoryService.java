
package enterprise.services;

import enterprise.models.Category;
import org.springframework.stereotype.Service;
import enterprise.repositories.CategoryRepository;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findOrAdd(String categoryName) {

        if (categoryRepository.existsByName(categoryName))

            return categoryRepository.findByName(categoryName);

        else {

            Category newCategory = new Category();

            newCategory.setName(categoryName);

            categoryRepository.save(newCategory);

            return newCategory;
        }
    }
}
