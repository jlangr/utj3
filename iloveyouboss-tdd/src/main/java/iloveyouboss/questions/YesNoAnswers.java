package iloveyouboss.questions;

import iloveyouboss.Answer;

import static iloveyouboss.questions.YesNo.No;
import static iloveyouboss.questions.YesNo.Yes;

public enum YesNoAnswers implements Answer<YesNo> {
   YesAnswer {
      @Override public YesNo value() { return Yes; }
   },
   NoAnswer {
      @Override public YesNo value() { return No; }
   };
}
