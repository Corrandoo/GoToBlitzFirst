package me.corrandoo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Blitz {
    static User[] topUsers = new User[10];
    static List<Event> events = new ArrayList<Event>();
    static List<Step> steps = new ArrayList<Step>();
    static List<User> users = new ArrayList<User>();
    public static void eventsFileToList(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            while(reader.readLine() != null){
                String s = reader.readLine();
                String[] str = s.split(",");
                int userId = Integer.parseInt(str[0]);
                String actionType = str[1];
                int stepId = Integer.parseInt(str[2]);
                int time = Integer.parseInt(str[3]);

                events.add(new Event(userId, actionType, stepId, time));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Данный файл не найден.");
        }
        catch(IOException e){
            System.out.println("Ошибка при работе с файлом.");
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
            System.out.println("Данный файл не найден.");
        }
        catch(IOException e){
            System.out.println("Ошибка при работе с файлом.");
        }
    }
    public static void getUsersList(){
        boolean isTrue = false;
        for (int i = 0; i < events.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                if(users.get(i).getId() == events.get(i).getUserId()){
                    isTrue = false;
                    break;
                }
                else{
                    isTrue = true;
                }
            }
            if(isTrue)
                users.add(new User(events.get(i).getUserId()));
        }
    }

}
