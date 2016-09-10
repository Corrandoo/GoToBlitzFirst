package me.corrandoo.blitzfirstgoto;

import me.corrandoo.blitzfirstgoto.service.Event;
import me.corrandoo.blitzfirstgoto.service.Step;
import me.corrandoo.blitzfirstgoto.service.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blitz {
    private static List<Event> events = new ArrayList<Event>();
    private static List<Step> steps = new ArrayList<Step>();
    private static List<User> users = new ArrayList<User>();
    private static Map<Integer, Integer> userMap = new HashMap<Integer, Integer>();
    private static Map<Integer, Integer> stepMap = new HashMap<Integer, Integer>();


    public static void main(String[] args) {
        eventsFileToList("src/main/resources/course-217-events.csv");
        structureFileToList("src/main/resources/course-217-structure.csv");
        events.sort((o1, o2) -> o1.getTime() - o2.getTime());
        getUsersList();
        getListOnTop();
        users.sort((o1, o2) -> o1.getCourseTime() - o2.getCourseTime());
        getTenUsers();
        getTimeForFuckingNumbers();
    }
    public static void eventsFileToList(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String s;
            reader.readLine();
            while((s = reader.readLine()) != null){
                String[] str = s.split(",");
                int userId = Integer.parseInt(str[0]);
                String actionType = str[1];
                int stepId = Integer.parseInt(str[2]);
                int time = Integer.parseInt(str[3]);

                events.add(new Event(userId, actionType, stepId, time));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Данный файл лога не найден.");
        }
        catch(IOException e){
            System.out.println("Ошибка при обработке файла лога.");
        }
    }
    public static void structureFileToList(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String s;
            reader.readLine();
            while((s = reader.readLine()) != null){
                String[] str = s.split(",");
                int stepId = Integer.parseInt(str[5]);
                int stepCost = Integer.parseInt(str[8]);
                stepMap.put(stepId, steps.size());
                steps.add(new Step(stepId, stepCost));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Данный файл структуры курса не найден.");
        }
        catch(IOException e){
            System.out.println("Ошибка при обработке файла структуры курса.");
        }
    }
    public static void getUsersList(){
        for (Event event : events) {
            if(!userMap.containsKey(event.getUserId())){
                userMap.put(event.getUserId(), users.size());
                users.add(new User(event.getUserId(), event.getTime()));
            }
        }
    }

    public static void getListOnTop(){
        for (Event event : events) {
            if(event.getEventType().equals("passed")) {
                users.get(userMap.get(event.getUserId())).plusPoint(steps.get(stepMap.get(event.getStepId())).getStepCost());
                if (users.get(userMap.get(event.getUserId())).getScore() < 24) {
                    users.get(userMap.get(event.getUserId())).setLastTime(event.getTime());
                }
            }
        }
    }
    public static void getTenUsers(){
        int j = 0;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getScore() >= 24){
                System.out.print(users.get(i).getId());
                j++;
                if(j == 10)
                    break;
                System.out.print(",");
            }

        }
        System.out.println();
        System.out.println();

    }
    public static void getTimeForFuckingNumbers(){
        int j = 0;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId() == 972 || users.get(i).getId() == 4280 || users.get(i).getId() == 1291 ){
                System.out.println(users.get(i).getId() + " " + users.get(i).getCourseTime() + " " + users.get(i).getFirstTime() + " " + users.get(i).getLastTime());
            }
        }
    }


}
