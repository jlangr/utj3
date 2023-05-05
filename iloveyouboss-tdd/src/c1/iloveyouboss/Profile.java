package iloveyouboss;

public class Profile {
    Boolean answer = null;

    public boolean matches(Criterion criterion) {
        return answer != null && criterion.expectedAnswer() == answer.booleanValue();
    }

    public void answer(BooleanQuestion question, boolean answer) {
        this.answer = answer;
    }
}
