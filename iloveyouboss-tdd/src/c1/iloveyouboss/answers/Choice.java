package iloveyouboss.answers;

public record Choice(String stringValue) implements Answer<String> {
    @Override
    public String value() {
        return stringValue;
    }
}
