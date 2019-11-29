import java.io.*;
import java.util.*;
import project.controller.*;

public class StartGame{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("start");
        System.out.println("quit");
        System.out.print("Please enter > ");
        String userInput = sc.nextLine();;

        while(!userInput.toLowerCase().equals("quit") && !userInput.toLowerCase().equals("start")){
            System.out.println("Input is not valid. Please try again.");
            userInput = "";
            System.out.println("start");
            System.out.println("quit");
            System.out.print("Please enter > ");

            userInput = sc.nextLine();
        }

        if (userInput.toLowerCase().equals("quit")){
            System.out.println("quit");
        } else if (userInput.toLowerCase().equals("start")){
            GameManager gameManager = new GameManager();
            gameManager.initGame();
        }
    }
}