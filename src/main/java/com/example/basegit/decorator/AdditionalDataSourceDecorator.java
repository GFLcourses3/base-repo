package com.example.basegit.decorator;

public class AdditionalDataSourceDecorator extends DecoratorDataSource {
    public AdditionalDataSourceDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(String text) {
        super.writeData(addTextToData(text));
    }

    @Override
    public String readData() {
        return removeTextFromData(super.readData());
    }

    private String addTextToData(String text) {
        return String.format("%s-%s", text, "Some Text");
    }

    private String removeTextFromData(String text) {
        String[] split = text.split("-");
        return split[0];
    }
}
