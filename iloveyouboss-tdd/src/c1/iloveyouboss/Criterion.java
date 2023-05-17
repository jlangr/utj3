package iloveyouboss;

public record Criterion(BooleanQuestion question, boolean expectedAnswer) {
    public boolean isMetBy(Answer answer) {
        return answer != null && expectedAnswer() == answer.value();
    }
}
