package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
    private String title;
    private String description;
    private String content;
    private String category;
    private java.util.Date publicDate;
    private String url;
    private String source;

    //
    public void printArticleDetails() {
        String[] splitContent = this.content.split("\\."); //split theo dấu chấm để xuống dòng cho content
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
        System.out.println("Tiêu đề: " + this.getTitle());
        System.out.println("-----------------------------------------------------");
        System.out.println("Mô tả: " + this.description);
        System.out.println("-----------------------------------------------------");
        System.out.println("Nội dung tin: ");
        for (int i = 0; i < splitContent.length; i++) {
            System.out.println(splitContent[i].trim() + ".");
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf("Nguồn: %s                                  Ngày xuất bản: %s.\n",
                this.source, simpleDateFormat.format(this.publicDate));
        System.out.println("Link báo gốc: " + this.url);
        System.out.println("===========================================================================================");
    }
    //getters and setters

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Article(String title, String description, String content, String category, Date publicDate, String url, String source) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.category = category;
        this.publicDate = publicDate;
        this.url = url;
        this.source = source;
    }

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                ", publicDate=" + publicDate +
                ", url='" + url + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
