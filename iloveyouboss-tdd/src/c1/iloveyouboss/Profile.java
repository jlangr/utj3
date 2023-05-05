package iloveyouboss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile {
    Map<Integer,Boolean> answers = new HashMap<>();

    public boolean matches(List<Criterion> criteria) {
        var criterion = criteria.get(0);
        var answer = answers.get(criterion.question().id());
        return answer != null && criterion.expectedAnswer() == answer.booleanValue();
    }

    public void answer(BooleanQuestion question, boolean answer) {
        answers.put(question.id(), answer);
    }
}
