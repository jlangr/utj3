package iloveyouboss;

import iloveyouboss.questions.Question;

public record Criterion<T>(Question<T> question, T expectedAnswer, boolean isOptional) {
   public Criterion(Question<T> question, T expectedAnswer) {
      this(question, expectedAnswer, false);
   }

   public boolean isMetBy(T answer) {
      return expectedAnswer().equals(answer);
   }
}
