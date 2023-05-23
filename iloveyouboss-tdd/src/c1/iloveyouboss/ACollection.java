package iloveyouboss;

import iloveyouboss.questions.ChoiceQuestion;
import iloveyouboss.questions.YesNoQuestion;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ACollection {

   @Test
   void x() {
      var criticCollection = new CriticCollection();
      criticCollection.add(new Critic<>(new YesNoQuestion(1, "?"), "X"));
      criticCollection.add(new Critic<>(new ChoiceQuestion(1, "?", List.of("a", "b")), "b"));

      criticCollection.stream().forEach(c -> System.out.println(c));
   }
}
