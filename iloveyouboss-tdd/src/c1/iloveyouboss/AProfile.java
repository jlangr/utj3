package iloveyouboss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AProfile {
    Profile profile;
    BooleanQuestion hasReloQuestion;

    @BeforeEach
    void create() {
        profile = new Profile();
        hasReloQuestion = new BooleanQuestion(1, "Has relocation package?");
    }


    // A question consists of text and type (e.g. boolean -> yes/no)
    // An answer is a question plus the value that is true

    // A profile is a bunch of Answers (to questions).
         // e.g.  Does a relocation package exist -> yes

    // A criterion is a question, the desired answer, and (later) a weighting.
    // An interested party sets up criteriq ("many criterion") to determine to what extent they match a profile.

    // We will start by asking whether or not a single criterion matches a profile.

    // Profiles might not answer all questions!

    @Test
    void matchesNothingWhenProfileEmpty() {
        var criterion = new Criterion(hasReloQuestion, true);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }
//    @Test
//    void matchesWhenProfileContainsMatchingAnswer() {
//        var criterion = new Criterion(hasReloQuestion, true);
//        profile.answer(hasReloQuestion, true);
//
//        boolean result = profile.matches(criterion);
//
//        assertTrue(result);
//    }
}