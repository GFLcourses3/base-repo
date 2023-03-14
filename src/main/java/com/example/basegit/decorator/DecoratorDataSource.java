package com.example.basegit.decorator;

public class DecoratorDataSource implements DataSource {

    private DataSource dataSource;

    public DecoratorDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void writeData(String text) {
        dataSource.writeData(text);
    }

    @Override
    public String readData() {
        return dataSource.readData();
    }
}
