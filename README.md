I wrote to handle multiple people choosing different floor

Elevator works in way that oldest floor in memory of Elevator is 
destiny but on each floor elevator checks if this floor is already in memory 
if so it will stop here. 

Elevator engine decide what elevator choose to use in case of pickup based upon if 
current direction is same as the elevator or stopping and speed (which one is the fastest one
its calculated using simulation - check fastest). This approach is not good force to calculate speed
in very crud way. Solve this cloud be:
changing pick up input to current floor and destiny floor this way we could calculate 
most optimistic speed for this travel more or less like this:

Time between current destiny floor to the next floor one with checking if other 
chosen floor exist between those two (if so add time and remove) 
calculate this until reach end (floor that we choose). It looks very much us something 
that would be written with tail recursion.

Next improvement could be adding Actor System to be able to handle many thread 
and with this change this to rest API

Description how code work is defined by console prompts and tests