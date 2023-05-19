package iloveyouboss;

public record Criterion(Question question, boolean expectedAnswer, boolean isOptional) {
    public Criterion(Question question, boolean expectedAnswer) {
        this(question, expectedAnswer, false);
    }

    public boolean isMetBy(Answer answer) {
        return answer != null && expectedAnswer() == answer.value();
    }
}
