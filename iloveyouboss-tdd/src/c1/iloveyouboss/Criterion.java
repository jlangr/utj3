package iloveyouboss;

public record Criterion(BooleanQuestion question, boolean expectedAnswer, boolean isOptional) {
    public Criterion(BooleanQuestion question, boolean expectedAnswer) {
        this(question, expectedAnswer, false);
    }

    public boolean isMetBy(Answer answer) {
        return answer != null && expectedAnswer() == answer.value();
    }
}
