package iloveyouboss.answers;

public enum TrueFalse implements Answer<Boolean> {
    True {
       public Boolean value() { return true; }
    },
    False {
        public Boolean value() { return false; }
    };
}
