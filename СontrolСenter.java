

import Commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Scanner;

public class СontrolСenter {
    Scanner scanner = new Scanner(System.in);
    public СontrolСenter (){

        String com = firstUpperCase(scanner.nextLine());
        while (!com.equalsIgnoreCase("Exit")){

            System.out.println(com);
            try {
                if (com.split(" ").length == 1) {
                    Class clazz = Class.forName("Commands." + com);
                    System.out.println(clazz.getName());
                    Command instance = (Command) clazz.getConstructor().newInstance();
                } else {
                    Class clazz = Class.forName("Commands." + com.split(" ")[0]);
                    System.out.println(clazz.getName());
                    Command instance = (Command) clazz.getConstructor(String.class).newInstance(com.split(" ")[1]);
                }

            } catch (ClassNotFoundException e) {

                System.out.println("No such command( \nPlease try again");
                //throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException | NoSuchMethodException e) {
                System.out.println("You have entered incorrect data");
                //throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            com =  firstUpperCase(scanner.nextLine());
        }
    }

    private String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.toLowerCase().substring(1);
    }
}
