# Canvas Sketcher
Program to draw lines and rectangles on a canvas, supported commands:
### C w h 
Should create a new canvas of width w and height h. 
### L x1 y1 x2 y2 
Should create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are - supported. Horizontal and vertical lines will be
drawn using the 'x' character. 
### R x1 y1 x2 y2
Should create a new rectangle, whose upper left corner is (x1,y1) and
lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character. 
### Q 
Should quit the program.

# Assumptions made
1. x,y,w,h args will always be > 0 and (x1, y1) will precede (x2, y2)
2. Lines and Rectangle cannot be drawn outside Canvas width and height
3. Only one Canvas sketch is supported
4. It's a simple program, not designed as a full-fledged application
5. Additional dependencies - Lombok
