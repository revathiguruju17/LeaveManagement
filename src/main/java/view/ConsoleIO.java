package view;

import java.util.Scanner;

public class ConsoleIO implements IO {

    @Override
    public String readInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public void displayMessage(String message){
        System.out.println(message);
    }
}
