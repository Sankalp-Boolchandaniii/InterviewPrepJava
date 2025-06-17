package org.streams;

public class Streams {

    //Q. What are streams??
    //A. a sequence of variables supporting different elements,
    // to process data in a simplified manner and to improve readability of the code.

    // Streams consists of intermediate operations and terminal operations.
    // A stream doesn't operate without a terminal operation

    // intermediate - .filter(), .map(), .sorted(), .distinct(), .limit(), .skip()

    // terminal - .collect(), .count(), .toList(), .forEach(), .reduce(), .anyMatch(), .allMatch(), .noneMatch()
    // below are also terminal operations but short circuit. Meaning they stop execution as soon as they get the first result
    // .findAny(), .findFirst()

}
