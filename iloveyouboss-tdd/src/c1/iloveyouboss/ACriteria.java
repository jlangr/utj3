package iloveyouboss;

import iloveyouboss.questions.YesNoQuestion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static iloveyouboss.answers.TrueFalse.False;
import static iloveyouboss.answers.TrueFalse.True;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ACriteria {
    Criterion criterion1 = new Criterion(new YesNoQuestion(1, "?"), True);
    Criterion criterion2 = new Criterion(new YesNoQuestion(2, "?"), False);
    Criterion criterion3 = new Criterion(new YesNoQuestion(3, "?"), False);

    @Test
    void holdsACollectionOfCriterion() {
        var criteria = new Criteria(List.of(criterion1, criterion2));

        assertEquals(listOfCriterion(criteria), List.of(criterion1, criterion2));
    }

    @Test
    void canBeConstructedWithVarargs() {
        var criteria = new Criteria(criterion1, criterion2);

        assertEquals(listOfCriterion(criteria), List.of(criterion1, criterion2));
    }

    @Test
    void canAcceptAdditionalCriterion() {
        var criteria = new Criteria(criterion1);

        criteria.add(criterion2);
        criteria.add(criterion3);

        assertEquals(listOfCriterion(criteria), List.of(criterion1, criterion2, criterion3));
    }

    private static List<Criterion> listOfCriterion(Criteria criteria) {
        return criteria.stream().collect(toList());
    }
}