package iloveyouboss;

public record Choice(String value) implements Value<String> {
    @Override
    public String answer() {
        return value;
    }
}
