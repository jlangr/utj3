package iloveyouboss.answers;

public enum YesNo implements Answer<Boolean> {
    Yes {
       public Boolean value() { return true; }
    },
    No {
        public Boolean value() { return false; }
    };
}
