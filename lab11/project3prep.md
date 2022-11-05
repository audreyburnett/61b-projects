# Project 3 Prep

**For tessellating pluses, one of the hardest parts is figuring out where to place each plus/how to easily place plus on screen in an algorithmic way.
If you did not implement tesselation, please try to come up with an algorithmic approach to place pluses on the screen. This means a strategy, pseudocode, or even actual code! 
Consider the `HexWorld` implementation provided near the end of the lab (within the lab video). Note that `HexWorld` was the lab assignment from a previous semester (in which students drew hexagons instead of pluses). 
How did your proposed implementation/algorithm differ from the given one (aside from obviously hexagons versus pluses) ? What lessons can be learned from it?**

Answer: An algorithmic approach could be to calculate all of the possible places that a plus could go next so that there will be no gaps or overlapping as we keep adding to the world. This algorithm would first have to take into account the size of the plus, and then take into account the size of the world. Then, we could divide the sizes in order to create some sort of "shadow" world not visible to the viewer that contains information about where our next plus could possibly go. Something different from the proposed implementation was that they mention anchor points, which I think could be very helpful to figure out where to put our pluses and implement the idea of a shadow world.



**Can you think of an analogy between the process of tessellating pluses and randomly generating a world using rooms and hallways?
What is the plus and what is the tesselation on the Project 3 side?**

Answer: An analogy between the process of tessellating pluses and randomly generating a world is that the pluses create the walls between rooms and hallways, and the entire tesselation is the world. The pluses can create rooms and hallways because they don't overlap.

**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually get to tessellating pluses.**

Answer: I would first write a method that adds a plus of a random size to the world. To do this I would break it down into creating a plus, randomizing its features, and then adding it to a random space in the world. I would then have to write a method that tracks where I have added in the world so that I can mathematically add more pluses that never overlap or have gaps between them.



**What distinguishes a hallway from a room? How are they similar?**

Answer: A room is a destination, while the hallway is the journey to get there. They are both empty with walls around them, except rooms have walls surrounding its entirity, and hallways don't have that requirement.
