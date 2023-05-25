package iloveyouboss.questions;

import java.util.List;

public record ChoiceQuestion(int id, String text, List<String> options) implements Question<String> {
}
