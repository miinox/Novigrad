package com.example.novigrad;
public class Service {
    private String title;
    private String description;

    private String formulaire = "-";


    /**
     * Constructeur
     * @param title
     * @param description
     */

    public Service(String title, String description, String forumlaire) {
        this.title = title;
        this.description = description;
        this.formulaire = forumlaire;
    }

    public Service()
    {

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

    public String getFormulaire() {
        return formulaire;
    }

    public String toString() {
        return  "Title : " + title +
                "\nDescription : " + description;
    }
}
