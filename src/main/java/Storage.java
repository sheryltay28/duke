import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    public void rewriteFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).toString();
            fw.write(text + "\n");
        }
        fw.close();
    }

    public int convertToInt(String month) {
        if (month.equals("January")) {
            return 0;
        }
        else if (month.equals("February")) {
            return 1;
        }
        else if (month.equals("March")) {
            return 2;
        }
        else if (month.equals("April")) {
            return 3;
        }
        else if (month.equals("May")) {
            return 4;
        }
        else if (month.equals("June")) {
            return 5;
        }
        else if (month.equals("July")) {
            return 6;
        }
        else if (month.equals("August")) {
            return 7;
        }
        else if (month.equals("September")) {
            return 8;
        }
        else if (month.equals("October")) {
            return 9;
        }
        else if (month.equals("November")) {
            return 10;
        } else {
            return 11;
        }
    }

    public Calendar deadlineConvertToCalendar(String input) {
        String[] findDate = input.split(" ");
        int day = Integer.parseInt(findDate[0]);
        int month = convertToInt(findDate[1]);
        int year = Integer.parseInt(findDate[2]);
        int hour = Integer.parseInt(findDate[3].substring(0, 2));
        int min = Integer.parseInt(findDate[3].substring(2));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, min);
        return calendar;
    }

    public ArrayList eventConvertToCalendar(String input) {
        ArrayList<Calendar> calendars = new ArrayList<>();
        String[] findTo = input.split("-");
        Calendar from = deadlineConvertToCalendar(findTo[0]);
        calendars.add(from);
        int toHour = Integer.parseInt(findTo[1].substring(0, 2));
        int toMin = Integer.parseInt(findTo[1].substring(2));
        Calendar to = Calendar.getInstance();
        to.set(Calendar.HOUR_OF_DAY, toHour);
        to.set(Calendar.MINUTE, toMin);
        calendars.add(to);
        return calendars;
    }

    public TaskList load() throws FileNotFoundException {
        TaskList inputs = new TaskList();
        filePath = "/Users/sheryl/CS2103/duke/src/main/java/Duke.txt";
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] words = line.split("\\|");
            if (words[0].equals("T")) {
                Todo task = new Todo(words[2]);
                inputs.add(task);
            } else if (words[0].equals("D")) {
                Deadline task = new Deadline(words[2], deadlineConvertToCalendar(words[3].substring(1)));
                inputs.add(task);
            } else if (words[0].equals("E")) {
                ArrayList<Calendar> calendars = eventConvertToCalendar(words[3].substring(1));
                Event task = new Event(words[2], calendars.get(0), calendars.get(1));
                inputs.add(task);
            }
        }
        return inputs;
    }
}
