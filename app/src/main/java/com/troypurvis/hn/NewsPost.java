package com.troypurvis.hn;

import java.util.Arrays;

public class NewsPost {

    String author, id, score, time, title, type;
    String[] kids;

    public NewsPost(String author, String id, String score, String time, String title, String type) {
        this.author = author;
        this.id = id;
        this.score = score;
        this.time = time;
        this.title = title;
        this.type = type;
    }

    @Override
    public String toString() {
        return "NewsPost{" +
                "author='" + author + '\'' +
                ", id='" + id + '\'' +
                ", score='" + score + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'';
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String[] getKids() {
        return kids;
    }
}
