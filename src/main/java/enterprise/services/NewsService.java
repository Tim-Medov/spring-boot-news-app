
package enterprise.services;

import enterprise.models.Category;
import enterprise.models.News;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import enterprise.repositories.NewsRepository;
import java.util.Date;
import java.util.List;

@Service
public class NewsService {

    private NewsRepository newsRepository;
    private CategoryService categoryService;

    public NewsService(NewsRepository newsRepository, CategoryService categoryService) {
        this.newsRepository = newsRepository;
        this.categoryService = categoryService;
    }

    public List<News> findAllNews() {
        return newsRepository.findAll();
    }

    public News findById(int id) {
        return newsRepository.findById(id).orElse(null);
    }

    public List<News> findByCategoryId(int id) {
        return newsRepository.findByCategoryId(id);
    }

    public List<News> findByTitle(String query) {
        return newsRepository.findAllByTitleContaining(query);
    }

    public List<News> findByText(String query) {
        return newsRepository.findAllByTextContaining(query);
    }

    public void addNews(News newsForm, Category categoryForm) {

        Category category = categoryService.findOrAdd(categoryForm.getName());

        News news = new News();

        if (newsForm.getId() != null) {

            try {

                news = newsRepository.findById(newsForm.getId())
                        .orElseThrow(() -> new NotFoundException("News with id = " + newsForm.getId() + " not found"));

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            news.setText(newsForm.getText());
            news.setTitle(newsForm.getTitle());

        } else {
            news = newsForm;
        }

        news.setPublicationDate(new Date());
        news.setCategory(category);

        newsRepository.save(news);
    }

    public void deleteNews(int id) {
        newsRepository.deleteById(id);
    }
}
