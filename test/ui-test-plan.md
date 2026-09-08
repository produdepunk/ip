# Console UI Test Plan

This plan is executed by the project-specific `test-ui` skill. Each test case starts a fresh program process, sends the listed input lines to standard input, and compares standard output exactly with the expected output after normalizing line endings and the final newline.

The program command must use Java 25 and be runnable from the repository root. Keep it to one executable plus arguments; do not use shell pipes, redirection, or command separators.

## Test case 1: Exit immediately

### Aim
Verify that the application starts, displays its greeting, accepts the `bye` command, and displays the farewell message.

### Program command
```text
java -cp out/production/ip Dexter
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
java -cp out/production/ip Dexter
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
