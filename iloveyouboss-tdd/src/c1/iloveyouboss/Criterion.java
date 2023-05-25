package iloveyouboss;

import iloveyouboss.answers.Answer;
import iloveyouboss.questions.Question;

public record Criterion<T>(Question<T> question, Answer<T> expectedAnswer, boolean isOptional) {
   public Criterion(Question<T> question, Answer<T> expectedAnswer) {
      this(question, expectedAnswer, false);
   }

   public boolean isMetBy(Answer<?> answer) {
      return answer != null && expectedAnswer().value().equals(answer.value());
   }
}
