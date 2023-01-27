package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class Parser {


    public static String execute(String command, String content, TaskList taskList) {
        String response = "";
        if (command.equals("list")) {
            response = "Here are the tasks in your list\n" + taskList.toString();
        } else if (command.equals("todo")) {
            try {
                Task task = Todo.create(content);
                taskList.addTask(task);
                response = "Ok boss. Added task:\n" + task.toString() +
                        "\nNow you have " + taskList.getSize() + " in the list.";
            } catch (DukeException e) {
                response = "☹ OOPS!!! The description of a todo cannot be empty.";
            }

        } else if (command.equals("deadline")) {
            try {
                Task task = Deadline.create(content);
                taskList.addTask(task);
                response = "Ok boss. Added task:\n" + task.toString() +
                        "\nNow you have " + taskList.getSize() + " in the list.";
            } catch (DukeException e) {
                response = "☹ OOPS!!! Invalid input for deadline.";
            }
        } else if (command.equals("event")) {
            try {
                Task task = Event.create(content);
                taskList.addTask(task);
                response = "Ok boss. Added task:\n" + task.toString() +
                        "\nNow you have " + taskList.getSize() + " in the list.";
            } catch (DukeException e) {
                response = "☹ OOPS!!! Invalid input for event.";
            }
        } else if (command.equals("mark")) {
            if (content.length() < 2) {
                response = "☹ OOPS!!! Invalid input for mark command.";
            } else {
                int index = Integer.parseInt(content.substring(1)) - 1;
                if (index >= taskList.getSize() || index < 0) {
                    response = "☹ OOPS!!! No such task in list.";
                } else {
                    Task task = taskList.getTask(index);
                    task.markDone();
                    response = "Ok boss! Marked this task as done: \n" + task.toString();
                }
            }
        } else if (command.equals("unmark")) {
            if (content.length() < 2) {
                response = "☹ OOPS!!! Invalid input for unmark command.";
            } else {
                int index = Integer.parseInt(content.substring(1)) - 1;
                if (index >= taskList.getSize() || index < 0) {
                    response = "☹ OOPS!!! No such task in list.";
                } else {
                    Task task = taskList.getTask(index);
                    task.unmarkDone();
                    response = "Ok boss! Marked this task as not done yet: \n" + task.toString();
                }
            }
        } else if (command.equals("delete")) {
            if (content.length() < 2) {
                response = "☹ OOPS!!! Invalid input for delete command.";
            } else {
                int index = Integer.parseInt(content.substring(1)) - 1;
                if (index >= taskList.getSize() || index < 0) {
                    response = "☹ OOPS!!! No such task in list.";
                } else {
                    Task task = taskList.deleteTask(index);
                    response = "Ok boss! Duke.Task removed: \n" + task.toString() +
                            "\nNow you have " + taskList.getSize() + " in the list.";
                }
            }
        } else if (command.equals("find")) {
            if (content.length() < 1) {
                response = "☹ OOPS!!! Invalid keyword for find command.";
            } else {
                String keyWord = content.substring(1);
                ArrayList<Integer> arrayList = taskList.findIndexesContaining(keyWord);
                if (arrayList.size() == 0) {
                    response = "no matches found!";
                } else {
                    response = "Here are the matching tasks from your list:\n"
                            + taskList.toStringIndexes(arrayList);
                }

            }
        } else {
            response = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return response;
    }
}