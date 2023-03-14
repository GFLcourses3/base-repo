package com.example.basegit.decorator;

import java.util.Base64;

public class EncryptionDataSourceDecorator extends DecoratorDataSource {

    public EncryptionDataSourceDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(String text) {
        super.writeData(encode(text));
    }

    @Override
    public String readData() {
        return decode(super.readData());
    }

    private String encode(String text) {
        byte[] bytes = text.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    private String decode(String text) {
        byte[] decode = Base64.getDecoder().decode(text);
        return new String(decode);
    }
}
