package entity;

import java.sql.Date;

public class Article {
    private String url;
    private String title;
    private String description;
    private java.util.Date publicDate;
    private String content;
    private String source;
    private String category;
    //constructors

    public Article() {
    }

    public Article(String url, String title, String description, java.util.Date publicDate, String content, String source, String category) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.publicDate = publicDate;
        this.content = content;
        this.source = source;
        this.category = category;
    }
//getters and setters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(java.util.Date publicDate) {
        this.publicDate = publicDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publicDate=" + publicDate +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
