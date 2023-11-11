package com.example.novigrad;
public class Service {
    private String title;
    private String description;


    /**
     * Constructeur
     * @param title
     * @param description
     */
    public Service(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // SETTERS
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // GETTERS
    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

//    public String toString() {
//        return  "Title : " + title + "\n" +
//                "Description : " + description;
//    }
}
