package iloveyouboss;

import iloveyouboss.questions.Question;

public record Criterion(Question question, Object expectedAnswer, boolean isOptional) {
   public Criterion(Question question, Object expectedAnswer) {
      this(question, expectedAnswer, false);
   }

   public boolean isMetBy(Object answer) {
      return expectedAnswer().equals(answer);
   }
}
