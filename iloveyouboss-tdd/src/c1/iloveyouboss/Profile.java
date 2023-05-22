package iloveyouboss;

import iloveyouboss.questions.Question;

import java.util.HashMap;
import java.util.Map;

public class Profile {
   Map<Integer, Object> answers = new HashMap<>();

   public boolean matches(Criteria criteria) {
      return criteria.stream()
         .filter(criterion -> !criterion.isOptional())
         .allMatch(criterion -> criterion.isMetBy(answerFor(criterion)));
   }

   public <T> void answer(Question<T> question, T answer) {
      if (answers.containsKey(question.id()))
         throw new DuplicateQuestionException();
      answers.put(question.id(), answer);
   }

   public <T> T answerFor(Criterion<T> criterion) {
      return (T)answers.get(criterion.question().id());
   }

   public int score(Criteria criteria) {
      return 0;
   }
}
