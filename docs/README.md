# Assistant Hermione User Guide


Huge fan of Hermione in Harry Potter? Super disorganised and need an easy platform to keep track of your tasks? Look
no further! Assistant Hermione is here to organise your life! Assistant Hermione is a bot that will save the tasks you
need to do whenever you type them in. It organises your tasks into 3 formats: todo, deadlines and events.
With this chatbot, you will never have to worry about forgetting your tasks again!


## Features
- Allows user to save tasks as todo/deadlines/events.
- Auto saves and remembers tasks entered.
- Allows marking tasks as done or deleting tasks.
- Allows updating task description or time.

## Commands


### Todo
Adds a todo task into the task list. A confirmation will be sent if it is successfully added.
Format: "todo <task description>"


### Deadline
Adds a deadline task into the task list. A confirmation will be sent if it is successfully added.
Format: "deadline <task description> /by dd/mm/yyyy hhmm"



### Event
Adds an Event task into the task list. A confirmation will be sent if it is successfully added.
Format: "event <task description> /at dd/mm/yyyy hhmm-hhmm"


### List
Displays the list of tasks that user added previously. Also shows whether tasks are done.
Format: "list"


### Done
Marks a task as done. 1 indicates a task is done while 0 indicates a task is not done.
Format: "done <integer>" (integer indicates position of task in the list.)


### Delete
Deletes a task from the list.
Format: "delete <integer>" (integer indicates position of task in the list.)


### Find
Returns tasks which contains keyword typed by user.
Format: "find <keyword>"


### Update
Updates task description or date and time of task.
Format 1 (for todo, deadline, event): "update <integer> task <new task description>"
Format 2 (for deadline, event): "update <integer> date <new date>"
Format 3 (for deadline, event): "update <integer> time <new time>"
Format 4 (for event): "update <integer> end <new end time>"


### Bye
Exits the program.
Format: "bye"