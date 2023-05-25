package iloveyouboss.questions;

import java.util.List;

public interface Question<T> {
   int id();
   List<String> options();
   Class<T> answerType();
}
