package iloveyouboss;

public record Answer(boolean value) {
    public boolean matches(Criterion criterion) {
        return criterion.expectedAnswer() == this.value();
    }
}
