package lnu.asmoderos.webApplication.model;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CourseFromPage {
    private String title;
    private String author;
    private int course;
    private Set<Category> categories;
    private String description;
    private String video;
    private String image;
    private String categoryString;
    private List<String> lessons;
    private int price;

    public List<String> getLessons() {
        return lessons;
    }

    public void setLessons(List<String> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "CourseFromPage{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", course=" + course +
                ", categories=" + categories +
                ", description='" + description + '\'' +
                ", video='" + video + '\'' +
                ", categoryString='" + categoryString + '\'' +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryString() {
        return categoryString;
    }

    public void setCategoryString(String categoryString) {
        this.categoryString = categoryString;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
