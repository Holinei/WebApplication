package lnu.asmoderos.webApplication.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "tnews")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Lob
    @Column(name = "text", length = 3000)
    private String text;
    private String author;
    private String image;
    private Date date;
    private boolean forStudents = false;

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", forStudents=" + forStudents +
                '}';
    }

    public boolean isForStudents() {
        return forStudents;
    }

    public void setForStudents(boolean forStudents) {
        this.forStudents = forStudents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
