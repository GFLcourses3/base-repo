package com.example.basegit.decorator;

public class Application {

    public static void main(String[] args) {
        String text = "Hello World";

        DecoratorDataSource decoratorDataSource = new AdditionalDataSourceDecorator(
                new EncryptionDataSourceDecorator(
                        new FileDatasource(text)));

        DecoratorDataSource decoratorDataSource2 = new AdditionalDataSourceDecorator(
                new FileDatasource(text));

        decoratorDataSource2.writeData(text);
        decoratorDataSource2.readData();

        decoratorDataSource.writeData(text);
        decoratorDataSource.readData();

    }
}
