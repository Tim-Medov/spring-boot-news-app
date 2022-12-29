
package enterprise.models;

import org.springframework.lang.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Size(min = 1, max = 20, message = "Category name must be from 1 to 20 symbols")
    @Column(name = "name")
    private String name;

    @OneToMany (mappedBy = "category")
    private Set<News> news;

    public Category() {}

    public Category(Integer id, String name, Set<News> news) {
        this.id = id;
        this.name = name;
        this.news = news;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }
}
