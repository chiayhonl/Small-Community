package com.first.community.DTO;

/**
 * @author Chiayhon
 * @create 2019 - 10 - 10
 */
public class GithubUser {
    private String name;
    private long id;
    private String BIO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBIO() {
        return BIO;
    }

    public void setBIO(String BIO) {
        this.BIO = BIO;
    }
}
