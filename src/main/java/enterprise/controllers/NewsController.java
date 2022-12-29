
package enterprise.controllers;

import enterprise.models.Category;
import enterprise.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import enterprise.services.CategoryService;
import enterprise.services.NewsService;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class NewsController {

    private NewsService newsService;
    private CategoryService categoryService;

    @Autowired
    public NewsController(NewsService newsService, CategoryService categoryService) {
        this.newsService = newsService;
        this.categoryService = categoryService;
    }

    @ModelAttribute(name = "categoryList")
    public List<Category> categoryList() {
        return categoryService.findAll();
    }

    @ModelAttribute(name = "newsForm")
    public News newsForm() {
        return new News();
    }

    @ModelAttribute(name = "categoryForm")
    public Category categoryForm() {
        return new Category();
    }

    @GetMapping("/index")
    public String showAllNews(Model model) {

        model.addAttribute("newsListAttr", newsService.findAllNews());

        return "news";
    }

    @GetMapping("/news/{id}")
    public String showAllNews(@PathVariable int id, Model model) {

        if (newsService.findById(id) == null)
            return "notFound404";

        model.addAttribute("oneNews", newsService.findById(id));

        return "oneNews";
    }

    @PostMapping("/news/byCategory")
    public String newsByCategory(Category categoryForm, Model model) {

        int categoryId = categoryForm.getId();

        model.addAttribute("newsListAttr", categoryId == 1 ? newsService.findAllNews()
                : newsService.findByCategoryId(categoryId));

        return "news";
    }

    @PostMapping("/news/byTitle")
    public String newsByTitle(News newsForm, Model model) {

        String query = newsForm.getTitle();

        model.addAttribute("newsListAttr", query == null ? newsService.findAllNews()
                : newsService.findByTitle(query));

        return "news";
    }

    @PostMapping("/news/byText")
    public String newsByText(News newsForm, Model model) {

        String query = newsForm.getText();

        model.addAttribute("newsListAttr", query == null ? newsService.findAllNews()
                : newsService.findByText(query));

        return "news";
    }

    @GetMapping("/news/new")
    public String createNews() {
        return "newsForm";
    }

    @PostMapping("/news/new")
    public String createNews(Model model, @ModelAttribute("newsForm") @Valid News newsForm,
                             BindingResult bindingResult1, @ModelAttribute("categoryForm")
                                 @Valid Category categoryForm, BindingResult bindingResult2) {

        if (bindingResult1.hasErrors() || bindingResult2.hasErrors()) {

            return "newsForm";

        } else {

            newsService.addNews(newsForm, categoryForm);

            return "redirect:/index";
        }
    }

    @GetMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable int id) {

        newsService.deleteNews(id);

        return "redirect:/index";
    }

    @GetMapping("/news/edit/{id}")
    public String editNews(@PathVariable int id, Model model) {

        News news = newsService.findById(id);

        model.addAttribute("newsForm", news);
        model.addAttribute("categoryForm", news.getCategory());

        return "newsForm";
    }
}
