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

## Running the program

When first opening the program two windows will appear, the first has some basic instructions on how to use the second window.

On the right side of the second window the map is displayed. On the left side all the buttons and text of the program is displayed. 

To use the program first click on the map or input the coordinates in the text box on the upper left, click the confirm button to confirm the choice. A red circle should have appeared on the map where you selected the first location and the text above the text box should have been updated telling the location. A red text above the text box and the lack of red circle indicates that an invalid location was selected. Try again and carefully choose a location that is not an obstacle. Repeate to choose the ending location.

Once the starting and ending locations have been selected, click on the different run buttons to run the different algorithms. Once clicked the shortest path should have been marked on the map as well as the text below the run algorithm buttons updated with the run time and the length of shortest path.

At the left of the window there are two buttons and one drop down window. The button on the right called 'reset maze' resets the map and you are able to choose new locations and rerun the algorithms. 

To select a different map, click on the drop down window and choose a new map. Confirm your choice by clicking on the 'select' button.
