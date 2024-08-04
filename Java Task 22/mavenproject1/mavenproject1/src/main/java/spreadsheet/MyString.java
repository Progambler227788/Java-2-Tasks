package spreadsheet;

public class MyString extends Content {
    private String text;

    public MyString(String text) {
        this.text = text;
    }

    @Override
    public String getValue() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
