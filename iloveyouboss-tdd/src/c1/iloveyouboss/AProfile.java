package iloveyouboss;

import iloveyouboss.questions.Question;
import iloveyouboss.questions.YesNoQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static iloveyouboss.answers.YesNo.*;
import static org.junit.jupiter.api.Assertions.*;

/* basis for explanatory prose:
 A Question consists of text and type (e.g. boolean -> yes/no)
     Questions are uniquely identified by their ID. (The ID is likely generated in some service-level code.)

 An Answer is a question plus the stringValue that is true

 A Profile is a bunch of Answers (to Questions). eg: Does a relocation package exist -> yes
     A Profile might not have an Answer to any given Question.

 A Criterion is a Question plus the desired Answer to that question.
 Criterion might be optional.
 An interested party sets up criteria ("many criterion") to determine to what extent they match a profile.

 */

// TODO: other types of questions

class AProfile {
    Profile profile = new Profile();
    Question hasRelo = new YesNoQuestion(1, "Has relocation package?");
    Question has401K = new YesNoQuestion(2, "Has 401K?");
    Question hasSmelt = new YesNoQuestion(3, "got smelt?");

    Criterion mustHaveRelo = new Criterion(hasRelo, Yes);
    Criterion mustHave401K = new Criterion(has401K, Yes);
    Criterion optionallyHasSmeltShouldBeTrue = new Criterion(hasSmelt, Yes, true);

    @Nested
    class WhenDeterminingMatches {
        @Test
        void doesNotMatchWhenProfileHasNoAnswers() {
            var criteria = new Criteria(new Criterion(hasRelo, Yes));

            assertFalse(profile.matches(criteria));
        }

        @Test
        void doesNotMatchWhenAllCriteriaNotMet() {
            profile.answer(hasRelo, Yes);
            profile.answer(has401K, No);

            assertFalse(profile.matches(
                    new Criteria(mustHaveRelo, mustHave401K)));
        }

        @Nested
        class WithAllQuestionsAnsweredYes {
            @Test
            void matchesWhenAllCriteriaMet() {
                profile.answer(hasRelo, Yes);
                profile.answer(has401K, Yes);

                assertTrue(profile.matches(new Criteria(mustHaveRelo, mustHave401K)));
            }

            @Test
            void matchesDespiteUnmetOptionalCriterion() {
                var optionalCriterion = new Criterion(hasSmelt, Yes, true);
                var criteria = new Criteria(mustHaveRelo, optionalCriterion);
                profile.answer(hasSmelt, No);
                profile.answer(hasRelo, Yes);

                assertTrue(profile.matches(criteria));
            }

            // TDD would not usually demand this test
            @Test
            void stillMatchesWithOnlyMismatchedOptionalCriteria() {
                var criteria = new Criteria(optionallyHasSmeltShouldBeTrue);
                profile.answer(hasSmelt, No);

                assertTrue(profile.matches(criteria));
            }
        }
    }

    @Nested
    class WhenManagingAnswers {
        @Test
        void returnsNullWhenAskedForNonexistentAnswer() {
            assertNull(profile.answerFor(mustHave401K));
        }

        @Test
        void returnsAnswerForCorrespondingCriterionQuestion() {
            profile.answer(has401K, Yes);
            var criterion = new Criterion(has401K, Yes);

            var answer = profile.answerFor(criterion);

            assertEquals(answer, Yes);
        }

        @Test
        void throwsWhenAddingDuplicateAnswer() {
            profile.answer(has401K, Yes);
            var questionWithDuplicateId = new YesNoQuestion(has401K.id(), "?");

            assertThrows(DuplicateQuestionException.class,
                    () -> profile.answer(questionWithDuplicateId, No));
        }
    }

    @Nested
    class Score {
        @Test
        void isZeroWhenNoCriteriaMet() {
            var criteria = new Criteria(mustHaveRelo);
            assertEquals(0, profile.score(criteria));
        }

        @Test
        void isCriteriaWeightWhenSoleCriterionMet() {
            var criteria = new Criteria(mustHaveRelo);

            assertEquals(0, profile.score(criteria));
        }
    }
}