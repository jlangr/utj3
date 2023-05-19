package iloveyouboss;

public record TrueAnswer() implements Value<Boolean> {
    @Override
    public Boolean answer() {
        return true;
    }
}
