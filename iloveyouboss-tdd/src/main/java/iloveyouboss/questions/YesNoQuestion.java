package iloveyouboss.questions;

import iloveyouboss.answers.YesNo;

import java.util.List;

import static iloveyouboss.answers.YesNo.No;
import static iloveyouboss.answers.YesNo.Yes;

public record YesNoQuestion(int id, String text) implements Question<YesNo> {
   @Override
   public List<String> options() {
      return List.of(Yes.toString(), No.toString());
   }

   @Override
   public Class<YesNo> answerType() {
      return YesNo.class;
   }
}
