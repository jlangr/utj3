package iloveyouboss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile {
    Map<Integer,Answer> answers = new HashMap<>();

    public boolean matches(List<Criterion> criteria) {
        return criteria.stream().allMatch(criterion -> {
            var answer = answers.get(criterion.question().id());
            return criterion.isMetBy(answer);
        });
    }

    public void answer(BooleanQuestion question, boolean answer) {
        answers.put(question.id(), new Answer(answer));
    }
}
