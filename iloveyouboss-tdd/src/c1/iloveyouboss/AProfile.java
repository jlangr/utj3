package iloveyouboss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AProfile {
    Profile profile;
    BooleanQuestion hasRelo;
    BooleanQuestion has401K;

    @BeforeEach
    void create() {
        profile = new Profile();
        hasRelo = new BooleanQuestion(1, "Has relocation package?");
        has401K = new BooleanQuestion(2, "Has 401K?");
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
    void doesNotMatchCriterionWhenProfileEmpty() {
        var criterion = new Criterion(hasRelo, true);

        boolean result = profile.matches(List.of(criterion));

        assertFalse(result);
    }

    @Test
    void matchesCriterionWhenProfileContainsMatchingAnswer() {
        var criterion = new Criterion(hasRelo, true);
        profile.answer(hasRelo, true);

        boolean result = profile.matches(List.of(criterion));

        assertTrue(result);
    }


    @Test
    void matchesCriterionAgainstProfileWithMultipleAnswers() {
        var criterion = new Criterion(hasRelo, true);
        profile.answer(hasRelo, true);
        profile.answer(new BooleanQuestion(2, "Has 401K?"), false);

        boolean result = profile.matches(List.of(criterion));

        assertTrue(result);
    }

    // test that answer method throws when given a duplicate question?


    @Test
    void doesNotMatchWhenAnyCriterionNotMet() {
        profile.answer(hasRelo, true);
        profile.answer(has401K, false);

        boolean result = profile.matches(
                List.of(new Criterion(has401K, true),
                        new Criterion(hasRelo, true)));

        assertFalse(result);
    }

    @Test
    void matchesWhenAllCriterionNotMet() {
        profile.answer(hasRelo, true);
        profile.answer(has401K, true);

        boolean result = profile.matches(
                List.of(new Criterion(has401K, true),
                        new Criterion(hasRelo, true)));

        assertTrue(result);
    }
}