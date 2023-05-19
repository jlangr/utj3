package iloveyouboss;

import iloveyouboss.questions.ChoiceQuestion;
import iloveyouboss.questions.Question;
import iloveyouboss.questions.YesNoQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.List;

import static iloveyouboss.answers.YesNo.No;
import static iloveyouboss.answers.YesNo.Yes;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ACriterion {
    @Nested
    class WithABooleanQuestion {
        Question question = new YesNoQuestion(1, "?");

        @Test
        void isNotMetByNullAnswer() {
            var criterion = new Criterion(question, Yes);

            assertFalse(criterion.isMetBy(null));
        }

        @Test
        void isMetByAnswerMatchingItsExpectedAnswer() {
            var criterion = new Criterion(question, Yes);

            assertTrue(criterion.isMetBy(Yes));
        }

        @Test
        void isNotMetByAnswerMismatchingItsExpectedAnswer() {
            var criterion = new Criterion(question, Yes);

            assertFalse(criterion.isMetBy(No));
        }
    }

    @Nested
    class WithAChoiceQuestion {
        Question question = new ChoiceQuestion(1, "?", List.of("eeny", "meeny", "miny", "moe"));

//        @Test
//        void isNotMetByNullAnswer() {
//            var criterion = new Criterion(question, true);
//
//            assertFalse(criterion.isMetBy(null));
//        }
//
//        @Test
//        void isMetByAnswerMatchingItsExpectedAnswer() {
//            var criterion = new Criterion(question, true);
//
//            assertTrue(criterion.isMetBy(new Answer(true)));
//        }
//
//        @Test
//        void isNotMetByAnswerMismatchingItsExpectedAnswer() {
//            var criterion = new Criterion(question, true);
//
//            assertFalse(criterion.isMetBy(new Answer(false)));
//        }
    }
}
