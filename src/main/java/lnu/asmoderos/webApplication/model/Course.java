package lnu.asmoderos.webApplication.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tcourse")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;
    private int course;
    private String title;
    private String author;
    @Lob
    @Column(name = "description", length = 3000)
    private String description;
    private String video;
    private String image;
    private int price;

    @Column(name = "lessons")
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "lessons", joinColumns = @JoinColumn(name = "course_id"))
    private List<String> lessons;

    @ElementCollection(targetClass = Category.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Category> categories;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public List<String> getLessons() {
        return lessons;
    }

    public void setLessons(List<String> lessons) {
        this.lessons = lessons;
    }

    public int getId() {
        return course_id;
    }

    public void setId(int course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + course_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", course=" + course +
                ", categories=" + categories +
                ", description='" + description + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
