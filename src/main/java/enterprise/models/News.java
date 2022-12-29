
package enterprise.models;

import org.springframework.lang.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Size(min = 1, max = 20, message = "News title must be from 1 to 20 symbols")
    @Column(name = "title")
    private String title;

    @NonNull
    @Size(min = 1, max = 2000, message = "News text must be from 1 to 2000 symbols")
    @Column(name = "text")
    private String text;

    @Column(name = "publication_date")
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public News() {}

    public News(Integer id, String title, String text, Date publicationDate, Category category) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.publicationDate = publicationDate;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String usaDateFormat() {

        DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");

        return dateFormat.format(publicationDate);
    }
}
