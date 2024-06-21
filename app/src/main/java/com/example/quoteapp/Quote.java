package com.example.quoteapp;

public class Quote {
    private String key,quote,author;

    public Quote(String key, String quote, String author) {
        this.key = key;
        this.quote = quote;
        this.author = author;
    }

    public Quote() {
    }

    public String getKey() {
        return key;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
