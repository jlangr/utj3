package iloveyouboss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

/* basis for explanatory prose:
 A Question consists of text and type (e.g. boolean -> yes/no)
     Questions are uniquely identified by their ID. (The ID is likely generated in some service-level code.)

 An Answer is a question plus the value that is true

 A Profile is a bunch of Answers (to Questions). eg: Does a relocation package exist -> yes
     A Profile might not have an Answer to any given Question.

 A Criterion is a Question plus the desired Answer to that question.
 Criterion might be optional.
 An interested party sets up criteria ("many criterion") to determine to what extent they match a profile.

 */

// TODO: other types of questions

class AProfile {
    Profile profile = new Profile();
    Question hasRelo = new BooleanQuestion(1, "Has relocation package?");
    Question has401K = new BooleanQuestion(2, "Has 401K?");
    Question hasSmelt = new BooleanQuestion(3, "got smelt?");

    Criterion hasReloMustBeTrue = new Criterion(hasRelo, new TrueAnswer());
    Criterion has401KMustBeTrue = new Criterion(has401K, new TrueAnswer());
    Criterion optionallyHasSmeltShouldBeTrue = new Criterion(hasSmelt, new TrueAnswer(), true);

    @Nested
    class WhenDeterminingMatches {
        @Test
        void doesNotMatchWhenProfileHasNoAnswers() {
            var criteria = new Criteria(new Criterion(hasRelo, new TrueAnswer()));

            assertFalse(profile.matches(criteria));
        }

        @Test
        void doesNotMatchWhenAllCriteriaNotMet() {
            profile.answer(hasRelo, new TrueAnswer());
            profile.answer(has401K, new FalseAnswer());

            assertFalse(profile.matches(
                    new Criteria(hasReloMustBeTrue, has401KMustBeTrue)));
        }

        @Nested
        class WithAllQuestionsAnsweredTrue {
            @Test
            void matchesWhenAllCriteriaMet() {
                profile.answer(hasRelo, new TrueAnswer());
                profile.answer(has401K, new TrueAnswer());

                assertTrue(profile.matches(new Criteria(hasReloMustBeTrue, has401KMustBeTrue)));
            }

            @Test
            void matchesDespiteUnmetOptionalCriterion() {
                var optionalCriterion = new Criterion(hasSmelt, new TrueAnswer(), true);
                var criteria = new Criteria(hasReloMustBeTrue, optionalCriterion);
                profile.answer(hasSmelt, new FalseAnswer());
                profile.answer(hasRelo, new TrueAnswer());

                assertTrue(profile.matches(criteria));
            }

            // TDD would not usually demand this test
            @Test
            void stillMatchesWithOnlyMismatchedOptionalCriteria() {
                var criteria = new Criteria(optionallyHasSmeltShouldBeTrue);
                profile.answer(hasSmelt, new FalseAnswer());

                assertTrue(profile.matches(criteria));
            }
        }
    }

    @Nested
    class WhenManagingAnswers {
        @Test
        void returnsNullWhenAskedForNonexistentAnswer() {
            assertNull(profile.answerFor(has401KMustBeTrue));
        }

        @Test
        void returnsAnswerForCorrespondingCriterionQuestion() {
            profile.answer(has401K, new TrueAnswer());
            var criterion = new Criterion(has401K, new TrueAnswer());

            var value = profile.answerFor(criterion);

            assertEquals(value.answer(), true);
        }

        @Test
        void throwsWhenAddingDuplicateAnswer() {
            profile.answer(has401K, new TrueAnswer());
            var questionWithDuplicateId = new BooleanQuestion(has401K.id(), "?");

            assertThrows(DuplicateQuestionException.class,
                    () -> profile.answer(questionWithDuplicateId, new FalseAnswer()));
        }
    }

    @Nested
    class Score {
        @Test
        void isZeroWhenNoCriteriaMet() {
            var criteria = new Criteria(hasReloMustBeTrue);
            assertEquals(0, profile.score(criteria));
        }

        @Test
        void isCriteriaWeightWhenSoleCriterionMet() {
            var criteria = new Criteria(hasReloMustBeTrue);

            assertEquals(0, profile.score(criteria));
        }
    }
}