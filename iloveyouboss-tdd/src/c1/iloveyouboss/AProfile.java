package iloveyouboss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    // Questions are uniquely identified by their ID.
    // (The ID is likely generated in some service-level code.)
    // An answer is a question plus the value that is true
    // A profile is a bunch of Answers (to questions).
         // e.g.  Does a relocation package exist -> yes
         // Each question answered by a profile is referenced by its ID.
    // A criterion is a question, the desired answer, and (later) a weighting.
    // An interested party sets up criteria ("many criterion") to determine to what extent they match a profile.
    // We will start by asking whether or not a single criterion matches a profile.
    // Profiles might not answer all questions!

    @Nested
    class WhenDeterminingMatches {
        @Test
        void doesNotMatchWhenProfileHasNoAnswers() {
            var criterion = new Criterion(hasRelo, true);

            assertFalse(profile.matches(List.of(criterion)));
        }

        @Test
        void doesNotMatchWhenAllCriteriaNotMet() {
            profile.answer(hasRelo, true);
            profile.answer(has401K, false);

            assertFalse(profile.matches(
                    List.of(new Criterion(has401K, true),
                            new Criterion(hasRelo, true))));
        }

        @Test
        void matchesWhenAllCriteriaMet() {
            profile.answer(hasRelo, true);
            profile.answer(has401K, true);

            assertTrue(profile.matches(
                    List.of(new Criterion(has401K, true),
                            new Criterion(hasRelo, true))));
        }
    }

    @Nested
    class WhenManagingAnswers {
        @Test
        void returnsNullWhenAskedForNonexistentAnswer() {
            var criterion = new Criterion(has401K, true);
            assertNull(profile.answerFor(criterion));
        }

        @Test
        void returnsAnswerForCorrespondingCriterionQuestion() {
            profile.answer(has401K, true);
            var criterion = new Criterion(has401K, false);

            var answer = profile.answerFor(criterion);

            assertEquals(answer.value(), true);
        }

        @Test
        void throwsWhenAddingDuplicateAnswer() {
            profile.answer(has401K, true);
            var questionWithDuplicateId = new BooleanQuestion(has401K.id(), "?");

            Assertions.assertThrows(DuplicateQuestionException.class,
                    () -> profile.answer(questionWithDuplicateId, false));
        }
    }
}