# breakout
## Alana Zinkin


DO NO FORK THIS REPOSITORY, clone it directly to your computer.


This project implements the game of Breakout with multiple levels.

### Timeline

 * Start Date: Friday January 10th, 2025

 * Finish Date: Tuesday January 21st, 2025

 * Hours Spent: 35


### Attributions

 * Resources used for learning (including AI assistance)
   * Block cooldown idea -- https://plarium.com/en/glossary/cooldown/#:~:text=The%20cooldown%20definition%20in%20gaming,it%20fair%20and%20more%20strategic.
   * ChatGPT for debugging assistance in interpreting error messages
   * TA Vincent in assisting with readLevelFile method (Thanks Vincent!)
  


 * Resources used directly (including AI assistance)
   * El Capitan spinning beachball GIF by madebyjw.com -- https://gfycat.com/rapidathleticilladopsis
   * OpenAI's ChatGPT assisted in writing a stale/active bouncer method to assist with my Powerup implementation
   * Making power-ups last for a specific amount of time -- https://stackoverflow.com/questions/44358098/javafx-call-method-on-specific-time
   
   

### Running the Program

 * Main class: Main.java

 * Data files needed: lvl_NUMLEVEL.txt (the level files for each level)

 * Key/Mouse inputs: Right/Left keys move the paddle

 * Cheat keys:
   * R: resets the paddle to its original position
   * S: automatically advances player to the next level
   * U: Changes the bouncer's X direction
   * Backspace: increment's the player's lives
   * Tab: Changes the bouncer's Y direction

   
### Notes/Assumptions

 * Assumptions or Simplifications:
   * Assume that power-ups are based on the specific block hit and are applied automatically. Some may last
for a specific amount of time, and others occur until the level ends
   * Assume that blocks are rectangles, bouncers are the same size, and paddle are also rectangles.
   * 

 * Known Bugs: 
   * When the bouncer hits the paddle on its side, it will vibrate through the paddle.
   * When the bouncer hits a block very quickly, it may appear to break through the block instead
   of bouncing off it. This bug can be reduced by reducing the cooldown time of a block.
   
 * Features implemented:
   * BREAK-01 through BREAK-15
   * BREAK-16A
   * BREAK-17A, BREAK-17B, BREAK-17C
   * BREAK-18A, BREAK-18B, BREAK-18D
   * BREAK19B, BREAK19D, BREAK19E, BREAK19F, BREAK19G

 * Features unimplemented:
   * BREAK-16B

 * Noteworthy Features:
   * Different blocks are associated with different power-ups, so the developer can choose
   which power-ups to offer for a given level.



### Assignment Impressions
This assignment was a perfect introduction to this class. It taught me various 
essential skills for this course, and made me excited to learn new strategies for
designing and debugging code. This project would have been significantly easier had I
learned inheritance earlier, and I am excited to learn techniques such as abstraction for future
development. However, it was also very challenging given the limited
office hours held during the first two weeks. A bit of extra support may have been helpful
as students get adjusted to this course. Overall, I enjoyed this project and am extremely proud of 
the progress I made in such a short period of time. I would certainly retain this project in future
semesters. 

