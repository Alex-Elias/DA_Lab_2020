# User Guide

## Generating an executable jar

To create an executable jar use command:
 ` mvn package`
 at the base of the repository to generate a jar file to the target folder

## Runing the jar file

To run the program use command:
 `java -jar target/pathfinder-1.0-SNAPSHOT.jar -Dexec.mainClass=Main`

## Running the unit tests

To run the tests use command:
 `mvn test`

To generate the test report use the command:
 `mvn test jacoco:report`

The report is found at `/target/site/jacoco/index.html`.


