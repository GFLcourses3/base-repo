package com.example.basegit.decorator;

import java.sql.SQLOutput;

public class FileDatasource implements DataSource {

    private String text;

    public FileDatasource(String text) {
        this.text = text;
    }

    @Override
    public void writeData(String text) {
        System.out.println("Imitation of write text -> " + text);
    }

    @Override
    public String readData() {
        System.out.println("Imitation of read text -> " + text);
        return text;
    }
}
