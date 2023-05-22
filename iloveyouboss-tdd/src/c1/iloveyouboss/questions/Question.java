package iloveyouboss.questions;

import java.util.List;

public interface Question<AnswerType> {
   int id();
   List<String> options();
}
