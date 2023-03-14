package com.example.basegit.decorator;

public interface DataSource {
    void writeData(String text);

    String readData();
}
