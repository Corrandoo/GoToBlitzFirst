package me.corrandoo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Blitz {
    static List<Event> events = new ArrayList<Event>();
    static List<Step> steps = new ArrayList<Step>();
    static List<User> users = new ArrayList<User>();

    public static void main(String[] args) {
        eventsFileToList("src/main/resources/course-217-events.csv");
        structureFileToList("src/main/resources/course-217-structure.csv");
        getUsersList();
        getListOnTop();
        users.sort((o1, o2) -> o1.getCourseTime() - o2.getCourseTime());
        getTenUsers();
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
        boolean isUserTrue = false;
        Event event;
        for (int i = 1; i <= events.size(); i++) {
            event = events.get(events.size() - i);
            if((users.size() == 0))
                users.add(new User(event.getUserId(), event.getTime()));
            else if(users.size() > 0){
                for (int j = 0; j < users.size(); j++) {
                    if(users.get(j).getId() == event.getUserId()){
                        isUserTrue = false;
                        break;
                    }
                    else if(users.get(j).getId() != event.getUserId())
                        isUserTrue = true;
                }
            }
            if(isUserTrue){
                users.add(new User(event.getUserId(), event.getTime()));
            }
        }
    }

    public static void getListOnTop(){
        Step step;
        Event event;
        for (int i = 1; i <= events.size(); i++) {
            event = events.get(events.size() - i);
            if (event.getEventType().equals("passed")) {
                for (int j = 0; j < steps.size(); j++) {
                    if (event.getStepId() == steps.get(j).getStepId()) {
                        step = steps.get(j);
                        for (int k = 0; k < users.size(); k++) {
                            if ((event.getUserId() == users.get(k).getId() && (users.get(k).getScore() < 24))) {
                                users.get(k).setScore(users.get(k).getScore() + step.getStepCost());
                                users.get(k).setLastTime(event.getTime());

                            } else if ((event.getUserId() == users.get(k).getId() && (users.get(k).getScore() == 24)) && !users.get(k).isCompleted()) {
                                users.get(k).setFinalTime(event.getTime());
                                users.get(k).setScore(users.get(k).getScore() + step.getStepCost());
                                users.get(k).setCompleted(true);
                            }
                            else if((event.getUserId() == users.get(k).getId()) && (users.get(k).getScore() >= 24)){
                                users.get(k).setScore(users.get(k).getScore() + step.getStepCost());
                                users.get(k).setLastTime(event.getTime());
                            }
                        }
                    }

                }
            }
        }

    }
    public static void getTenUsers(){
        int j = 0;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).isCompleted()){
                System.out.print(users.get(i).getId());
                j++;
                if(j == 10)
                    break;
                System.out.print(",");
            }

        }

    }

}
///
