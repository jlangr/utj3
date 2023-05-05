package iloveyouboss;

public class Profile {
    private Answer answer;

    public boolean matches(Criterion criterion) {
        return false;
    }

    public void add(Answer answer) {
        this.answer = answer;
    }

    public void answer(BooleanQuestion question, boolean b) {
    }
}
