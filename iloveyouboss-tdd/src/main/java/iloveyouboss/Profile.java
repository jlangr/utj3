package iloveyouboss;

import iloveyouboss.answers.Answer;
import iloveyouboss.questions.Question;

import java.util.HashMap;
import java.util.Map;

public class Profile {
   Map<Integer, Answer<?>> answers = new HashMap<>();

   public boolean matches(Criteria criteria) {
      return criteria.stream()
         .filter(criterion -> !criterion.isOptional())
         .allMatch(criterion -> criterion.isMetBy(answerFor(criterion)));
   }

   public <T> void answer(Question<T> question, Answer<T> answer) {
      if (answers.containsKey(question.id()))
         throw new DuplicateQuestionException();
      answers.put(question.id(), answer);
   }

   public <T> Answer<?> answerFor(Criterion<T> criterion) {
      return answers.get(criterion.question().id());
   }

   public int score(Criteria criteria) {
      return 0;
   }
}
