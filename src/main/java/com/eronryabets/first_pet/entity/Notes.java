package com.eronryabets.first_pet.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="pet_notes")
public class Notes {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "tag")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "string_date")
    private String stringDate;

    public Notes() {
    }

    public Notes(String text, String tag, User author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
        LocalDateTime timeNow = LocalDateTime.now();
        this.date = timeNow;
        this.stringDate = formatter(timeNow);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public Integer getId() {
        return id;
    }

    public static String formatter(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String time = date.format(formatter);
        return time;
    }
}
