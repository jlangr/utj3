package iloveyouboss;

import iloveyouboss.answers.Answer;
import iloveyouboss.questions.Question;

public record Criterion(Question question, Answer expectedAnswer, boolean isOptional) {
   public Criterion(Question question, Answer expectedAnswer) {
      this(question, expectedAnswer, false);
   }

   public boolean isMetBy(Answer answer) {
      return expectedAnswer().equals(answer);
   }
}
