package iloveyouboss.questions;

import java.util.List;

public interface Question {
   int id();
   List<String> options();
   Class<?> answerType();
}
