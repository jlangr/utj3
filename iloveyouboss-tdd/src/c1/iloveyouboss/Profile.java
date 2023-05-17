package iloveyouboss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile {
    Map<Integer,Answer> answers = new HashMap<>();

    public boolean matches(List<Criterion> criteria) {
        return criteria.stream().allMatch(
                criterion -> criterion.isMetBy(answerFor(criterion)));
    }

    public void answer(BooleanQuestion question, boolean answer) {
        if (answers.containsKey(question.id()))
            throw new DuplicateQuestionException();
        answers.put(question.id(), new Answer(answer));
    }

    public Answer answerFor(Criterion criterion) {
        return answers.get(criterion.question().id());
    }
}
