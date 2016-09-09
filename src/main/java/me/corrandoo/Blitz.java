package me.corrandoo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Blitz {
    static List<User> topUsers = new ArrayList<User>();
    static List<Event> events = new ArrayList<Event>();
    static List<Step> steps = new ArrayList<Step>();
    static List<User> users = new ArrayList<User>();

    public static void main(String[] args) {
        eventsFileToList("src/main/resources/course-217-events.csv");
        structureFileToList("src/main/resources/course-217-structure.csv");
        getUsersList();
        getTopUsers();
        for(User user : topUsers){
            System.out.println(user.getId());
        }
    }
    public static void eventsFileToList(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String s;
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
            while(reader.readLine() != null){
                String s = reader.readLine();
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
        for (int i = 0; i < events.size(); i++) {
            if((users.size() == 0) && (i == 0))
                users.add(new User(events.get(i).getUserId()));
            else if(users.size() > 0){
                for (int j = 0; j < users.size(); j++) {
                    if(users.get(j).getId() == events.get(i).getUserId()){
                        isUserTrue = false;
                        break;
                    }
                    else if(users.get(j).getId() != events.get(i).getUserId())
                        isUserTrue = true;
                }
            }
            if(isUserTrue){
                users.add(new User(events.get(i).getUserId()));
            }
        }
    }

    public static void getTopUsers(){
        Step step;
        Event event;
        User user;
        for (int i = 1; i <= events.size(); i++) {
            event = events.get(events.size() - i);
            for (int j = 0; j < steps.size(); j++) {
                if(event.getStepId() == steps.get(j).getStepId()){
                    step = steps.get(j);
                    for (int k = 0; k < users.size(); k++) {
                        if(event.getUserId() == users.get(k).getId()){
                            user = users.get(k);
                            user.setScore(user.getScore() + step.getStepCost());
                            users.set(k, user);
                            if(topUsers.size() > 10)
                                break;
                            if(users.get(k).getScore() >= 24){
                                topUsers.add(users.get(k));
                            }
                            if(topUsers.size() > 10)
                                break;
                        }
                    }
                }

            }
        }
    }

}
///
