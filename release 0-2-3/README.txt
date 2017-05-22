NOTE!!! THE ENEMY AND ASTEROID COMMANDS HAVE BEEN CHANGED, ARGUMENTS ARE NO LONGER PRECEDED BY “-“, NOW ITS “ “.



Thank you for trying out the game! here are some instructions:

If it isn’t running, google “download jre” and go to the java website. should be pretty simple

on scripting:

making levels is easy. Simply create an empty text file (should be plaintext) and list instructions.  Each instruction must be followed by a semicolon (;)

each instruction is followed by an argument, indicating what, or how many, or something to do

here are the current commands:

arm_weapons (allows the player to use the weapon)
textbox-line1-line2-line3 (brings up a textbox on the screen, replace line1, 				line2, and line3 with whatever text you want 					to display. EMPTY LINES MUST BE REPRESENTED BY 			SPACES!! so, an empty textbox would be textbox- - - ;)

stop_time (stops physics simulation, put before textboxes to cause game to 			pause)

start_time (opposite of stop_time, put after all textboxes in a sequence)

asteroid <number> (spawns <number> of asteroids)

set_cutscene_back <image path> (loads the image at image path to the cutscene
					background)

enter_cutscene (enters cutscene mode, automatically stops time)

exit_cutscene (exits cutscene mode, automatically restarts time)

wait <time> (execute no scripting commands for <time> frames. there are 			roughly 60 frames per second)

enemy <number> (might not work), but its worth a shot: spawns <number> of 				enemies)

clear_text_box (clears all text boxes from the screen)

change_level <path> (changes the current level to <path>. use to link levels 			together)

EOL (should be placed at the last line of the level, triggers “game over” 		screen and cleanly stops the game. failing to use this will likely 		result in an out of bounds error)