### Domain overview
A Question consists of text and type (e.g. boolean -> yes/no)
Questions are uniquely identified by their ID. (The ID is likely generated in some service-level code.)

A Profile is a bunch of Answers (to Questions). eg: Does a relocation package exist -> yes
A Profile might not have an Answer to any given Question.

A Criterion is a Question plus the desired Answer to that question.
Criterion might be optional.
An interested party sets up criteria ("many criterion") to determine to what extent they match a profile.
