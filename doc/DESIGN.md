# Breakout Design
## Alana Zinkin


## Design Goals
* DESIGN-01 - Multiple Classes: Divide code into multiple classes and ensure that classes are small
and have a useful purpose
* DESIGN-02 - Meaningful Names: Ensure that all variables, constants, and methods are properly named
and reflect their use cases.
* DESIGN-03 - Named Constants: declare any "magic numbers" as static constants
* DESIGN-04 - DRY: Refactor code to ensure that lines of code are not repeated, and instead
re-written as tightly scoped methods.
* DESIGN-05 - Interact Through Methods: avoid inappropriate class intimacy by collaborating via method calls.
* DESIGN-06 - Consistent Code Formatting: code follows consistent course formatting instructions.
* DESIGN-07 - Tightly scoped methods: Each method should be small with a single-purpose.
* DESIGN-08 - Comments: comments should follow Javadoc conventions and should be used judiciously. 


## High-Level Design
* A Main class contains all the game elements and methods necessary to run the game engine, such as 
the step method and setScene method. 
* We need a bouncer, paddle, block class to create all the relevant objects in the breakout game
* We also need Level, Score, and Life classes to keep track of critical game elements
* We require Splash Screen, Screen Text, and Game Display classes to present relevant game information 
to the player.
* We also need a Game class for winning/losing game related actions

## Assumptions or Simplifications
* We assume that the "bouncer" is a plain circle shape and that the "paddle" is a rectangle. 
* "Blocks" are also created as rectangles. 
* We assume that the developer knows how many level files exist to maintain the static variable
NUMLEVELS.
* We also assume that powerups are not random, but rather encoded in the blocks upon level initialization.
* We assume that the developer can choose how many lives to give upon initialization with the static NUMLIVES
variable.
* We also assume that the user can manually map the numbers of blocks to specific colors in the colorMapping
array. This also means the user should update these mapping in the initBlocks method switch statement.


## Changes from the Plan
* Did not include different shaped blocks as a block variant.
* Did not create new variants of the Breakout Game
* Did not create catch and release paddle or the V shaped paddle with two sides.
* "Tough blocks" take 2 hits to break rather than 5. This functionality could be easily changed to include 5 hits, but
makes the game much longer.
* Did not make different shaped blocks because I chose to make the Blocks all rectangles rather than extend the 
shape class. It makes the game display more aesthetic.
* Instead of slowing down the ball, I chose to speed up the ball, but this feature could easily be implemented given the current methods.
* I did not include the "?" key idea simply because I never had the chance to implement a random powerup generator. This feature
would have been significantly easier to make had I used inheritance with an abstract Powerup class and specific powerup subclasses.
* The levels do not become increasingly challenging because the paddle gets smaller, but rather because the paddle has more targeted bouncing functionality.


## How to Add New Levels
1. Create a new level file titled: lvl_LEVELNUMBER.txt in the resources folder.
2. Add rows of numbers (0 through 6 inclusive) to represent the different types of block types, with 0 meaning "no block."
* Go to readLevelFile method to understand what each block number symbolizes. 
3. Update NUMLEVELS public static constant in the Main class at the top of the class. It should be a number that
is 1 higher than the highest level since levels begin at 0 (this is intentional to prevent index out of bounds errors).

