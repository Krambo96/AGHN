package com.troypurvis.hn;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class NewsPost implements Parcelable {

    String author, id, score, time, title, type, url;
    String[] kids;

    public NewsPost(String author, String id, String score, String time, String title, String type) {
        this.author = author;
        this.id = id;
        this.score = score;
        this.time = time;
        this.title = title;
        this.type = type;
    }

    public NewsPost(String author, String id, String score, String time, String title, String type, String url) {
        this.author = author;
        this.id = id;
        this.score = score;
        this.time = time;
        this.title = title;
        this.type = type;
        this.url = url;
    }


    public NewsPost(Parcel in){
        this.author = in.readString();
        this.id = in.readString();
        this.score = in.readString();
        this.time = in.readString();
        this.title = in.readString();
        this.type = in.readString();
        this.url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(id);
        dest.writeString(score);
        dest.writeString(time);
        dest.writeString(title);
        dest.writeString(type);
        dest.writeString(url);

    }

    public static final Parcelable.Creator<NewsPost> CREATOR = new Parcelable.Creator<NewsPost>(){
        @Override
        public NewsPost createFromParcel(Parcel source) {
            return new NewsPost(source);
        }

        @Override
        public NewsPost[] newArray(int size) {
            return new NewsPost[size];
        }
    };

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

    public void setUrl(String s){
        this.url = s;
    }
}
