package iloveyouboss;

import iloveyouboss.questions.Question;

public record Critic<T>(Question question, T expectedAnswer, boolean isOptional) {
   public Critic(Question question, T expectedAnswer) {
      this(question, expectedAnswer, false);
   }

   public boolean isMetBy(Object answer) {
      return expectedAnswer().equals(answer);
   }
}
