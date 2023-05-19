package iloveyouboss;

import java.util.HashMap;
import java.util.Map;

public class Profile {
    Map<Integer,Value> answers = new HashMap<>();

    public boolean matches(Criteria criteria) {
        return criteria.stream()
                .filter(criterion -> !criterion.isOptional())
                .allMatch(criterion -> criterion.isMetBy(answerFor(criterion)));
    }

    public void answer(Question question, Value answer) {
        if (answers.containsKey(question.id()))
            throw new DuplicateQuestionException();
        answers.put(question.id(), answer);
    }

    public Value answerFor(Criterion criterion) {
        return answers.get(criterion.question().id());
    }

    public int score(Criteria criteria) {
        return 0;
    }
}
