package ru.mirea.burmakin.mireaproject;

public class HistoryObj {
    private String name; // название
    private String author;  // автор
    private String history; // История

    public HistoryObj(String name, String author, String history){

        this.name=name;
        this.author=author;
        this.history=history;
    }

    public String getNameHistory() {
        return this.name;
    }

    public void setNameHistory(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHistoryResource() {
        return this.history;
    }

    public void setHistoryResource(String history) {
        this.history = history;
    }
}

