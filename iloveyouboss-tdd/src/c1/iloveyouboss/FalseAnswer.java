package iloveyouboss;

public record FalseAnswer() implements Value<Boolean> {
    @Override
    public Boolean answer() {
        return false;
    }
}
