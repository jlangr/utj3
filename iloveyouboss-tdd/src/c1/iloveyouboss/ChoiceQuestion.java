package iloveyouboss;

import java.util.List;

public record ChoiceQuestion(int id, String text, List<String> choices) implements Question {
}
