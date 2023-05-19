package iloveyouboss;

public record Criterion(Question question, Value expectedAnswer, boolean isOptional) {
    public Criterion(Question question, Value expectedAnswer) {
        this(question, expectedAnswer, false);
    }

    public boolean isMetBy(Value answer) {
        return answer != null && expectedAnswer().answer().equals(answer.answer());
    }
}
