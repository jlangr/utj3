package iloveyouboss.questions;

import java.util.List;

import static iloveyouboss.questions.YesNo.No;
import static iloveyouboss.questions.YesNo.Yes;

public record YesNoQuestion(int id, String text) implements Question<YesNo> {
   @Override
   public List<YesNo> options() {
      return List.of(Yes, No);
   }
}
