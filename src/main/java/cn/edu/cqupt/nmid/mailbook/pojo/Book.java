package cn.edu.cqupt.nmid.mailbook.pojo;

import java.util.Date;

public class Book {
    private Integer id;

    private String username;

    private String name;

    private String publisher;

    private Float price;

    private Float degree;

    private Date time;

    private String description;

    private String cover;

    private String incover;

    private Byte type;

    private Byte ishandled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDegree() {
        return degree;
    }

    public void setDegree(Float degree) {
        this.degree = degree;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getIncover() {
        return incover;
    }

    public void setIncover(String incover) {
        this.incover = incover == null ? null : incover.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getIshandled() {
        return ishandled;
    }

    public void setIshandled(Byte ishandled) {
        this.ishandled = ishandled;
    }
}