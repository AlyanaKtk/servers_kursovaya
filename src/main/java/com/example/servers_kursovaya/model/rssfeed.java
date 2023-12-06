package com.example.servers_kursovaya.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Timestamp;
import java.util.Objects;

//новостной агрегатор
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rssfeed")
public class rssfeed extends baseModel implements Comparable<rssfeed>{


    @Column(name = "link")
    private String link;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "publication_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Timestamp publicationDate;

    @Column(name = "updated_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Timestamp updatedDate;

    @Override
    public int compareTo(rssfeed rssFeed) {
        return this.getPublicationDate().compareTo((rssFeed).getPublicationDate()) == 1 ? 0 : 1;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        rssfeed rssFeed = (rssfeed) o;
        //сравнение дат
        if (!Objects.equals(link, rssFeed.link)) return false;
        if (!Objects.equals(title, rssFeed.title)) return false;
        if (!Objects.equals(description, rssFeed.description)) return false;
        return Objects.equals(publicationDate, rssFeed.publicationDate);
    }

    @Override
    public int hashCode() {
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }
}
