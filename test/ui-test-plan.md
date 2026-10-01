# Console UI Test Plan

This plan is executed by the project-specific `test-ui` skill. Each test case starts a fresh program process, sends the listed input lines to standard input, and compares standard output exactly with the expected output after normalizing line endings and the final newline.

The program command must use Java 25 and be runnable from the repository root. Keep it to one executable plus arguments; do not use shell pipes, redirection, or command separators.

## Test case 1: Exit immediately

### Aim
Verify that the application starts, displays its greeting, accepts the `bye` command, and displays the farewell message.

### Program command
```text
java -cp out/production/ip dexter.Dexter
```

### Inputs
```text
bye
```

### Expected output
```text
DDDD   EEEEE  XX XX  TTTTT  EEEEE  RRRR
D   D  E       X X     T    E      R   R
D   D  EEEE     X      T    EEEE   RRRR
D   D  E        X      T    E      R R
DDDD   EEEEE   X X     T    EEEEE  R  RR

Welcome my fellow big-brainer! What question do you have in mind?
See you again soon!
```

## Test case 2: Add and list a todo

### Aim
Verify that a todo command creates a task and that `list` displays the stored task.

### Program command
```text
java -cp out/production/ip dexter.Dexter
```

### Inputs
```text
todo buy milk
list
bye
```

### Expected output
```text
DDDD   EEEEE  XX XX  TTTTT  EEEEE  RRRR
D   D  E       X X     T    E      R   R
D   D  EEEE     X      T    EEEE   RRRR
D   D  E        X      T    E      R R
DDDD   EEEEE   X X     T    EEEEE  R  RR

Welcome my fellow big-brainer! What question do you have in mind?
Alright! Added:
[T][ ] buy milk
Now you have 1 items
Sure! Here is your list.
[T][ ] buy milk
See you again soon!
```

## Test case 3: Parse different task types and recover from invalid input

### Aim
Verify that deadline and event details are preserved, invalid commands and missing details display their existing errors without adding tasks, and subsequent valid commands still work. Start with an empty data directory.

### Program command
```text
java -cp out/production/ip dexter.Dexter
```

### Inputs
```text
deadline submit report by Friday
event study group from Monday to Tuesday
todo
deadline report
event meeting from Monday
unknown

todo buy bread
list
bye
```

### Expected output
```text
DDDD   EEEEE  XX XX  TTTTT  EEEEE  RRRR
D   D  E       X X     T    E      R   R
D   D  EEEE     X      T    EEEE   RRRR
D   D  E        X      T    E      R R
DDDD   EEEEE   X X     T    EEEEE  R  RR

Welcome my fellow big-brainer! What question do you have in mind?
Alright! Added:
[D][ ] submit report (by: Friday)
Now you have 1 items
Alright! Added:
[E][ ] study group (from: Monday to: Tuesday)
Now you have 2 items
Oh, I think you may have missed out some details. Can you repeat?
Oh, I think you may have missed out some details. Can you repeat?
Oh, I think you may have missed out some details. Can you repeat?
Sorry but I do not understand. Can you repeat?
Sorry but I do not understand. Can you repeat?
Alright! Added:
[T][ ] buy bread
Now you have 3 items
Sure! Here is your list.
[D][ ] submit report (by: Friday)
[E][ ] study group (from: Monday to: Tuesday)
[T][ ] buy bread
See you again soon!
```

## Test case 4: Mark, unmark, delete and command errors

### Aim
Verify the extracted Function updates the shared list and preserves confirmations and errors for empty lists, missing numbers, invalid numbers and out-of-range numbers. Start with an empty data directory.

### Program command
```text
java -cp out/production/ip dexter.Dexter
```

### Inputs
```text
mark 1
todo buy milk
mark
mark abc
unmark 0
delete 2
mark 1
list
unmark 1
list
delete 1
list
bye
```

### Expected output
```text
DDDD   EEEEE  XX XX  TTTTT  EEEEE  RRRR
D   D  E       X X     T    E      R   R
D   D  EEEE     X      T    EEEE   RRRR
D   D  E        X      T    E      R R
DDDD   EEEEE   X X     T    EEEEE  R  RR

Welcome my fellow big-brainer! What question do you have in mind?
Hey! Your list is still empty!
Alright! Added:
[T][ ] buy milk
Now you have 1 items
Oh no! You need to have a number to indicate the item in the list.
Oh no! The task number is not valid. Please enter a valid number.
Please enter a value within the list size!
Please enter a value within the list size!
Alright! Marked it as done!
Sure! Here is your list.
[T][X] buy milk
Alright! I have unchecked the task!
Sure! Here is your list.
[T][ ] buy milk
Ok! Removed:
[T][ ] buy milk
Now you have 0 items
Sure! Here is your list.
See you again soon!
```
