package iloveyouboss;

// There need be no test for this, as there is no
// real logic. It will be tested in the context of its usage.

public record BooleanQuestion(int id, String text) implements Question {}
