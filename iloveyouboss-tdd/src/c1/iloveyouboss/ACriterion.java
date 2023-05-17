package iloveyouboss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ACriterion {
    BooleanQuestion question = new BooleanQuestion(1, "?");

    @Test
    void isNotMetByNullAnswer() {
        var criterion = new Criterion(question, true);

        assertFalse(criterion.isMetBy(null));
    }

    @Test
    void isMetByAnswerMatchingItsExpectedAnswer() {
        var criterion = new Criterion(question, true);

        assertTrue(criterion.isMetBy(new Answer(true)));
    }

    @Test
    void isNotMetByAnswerMismatchingItsExpectedAnswer() {
        var criterion = new Criterion(question, true);

        assertFalse(criterion.isMetBy(new Answer(false)));
    }
}