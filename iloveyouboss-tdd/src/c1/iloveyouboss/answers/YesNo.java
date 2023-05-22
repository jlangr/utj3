package iloveyouboss.answers;

public enum YesNo implements Answer {
   Yes {
      public Boolean value() {
         return true;
      }
   },
   No {
      public Boolean value() {
         return false;
      }
   }
}
