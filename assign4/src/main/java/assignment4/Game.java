
package assignment4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static Player player;
    private static FormSequence formSequence;
    private static List<Avatar> avatarList;
    private static Scanner scanner;
    
    public static void init()
    {
        formSequence = new FormSequence();
        avatarList = new ArrayList<Avatar>();
        scanner = new Scanner(System.in);
    }
    
    public static List<String> enterAvatarList()
    {
        List<String> nameList = new ArrayList<String>();
        System.out.println("Enter avatar list ( space between avatars ) : ");
        scanner.nextLine();
        String mystring = scanner.nextLine();
        System.out.println();
        for(String word : mystring.split("\\s+"))
        {
            nameList.add(word);
        }
        return nameList;
    }
    
    public static void askActivity()
    {
        int myint = 0;
        while (myint != 4)
        {
            System.out.println("--------------------");
            System.out.println("1. Perform action");
            System.out.println("2. Transform next");
            System.out.println("3. Transform back");
            System.out.println("4. Exit");
            System.out.println("--------------------");
            System.out.print("Enter your selection: ");
            myint = scanner.nextInt();
            
            switch (myint) {
                case 1: 
                    if (player.getAvatar() != null) {
                        player.performAction();
                    }
                    else 
                        System.out.println("Cannot perform action for null avatar");
                    break;
                case 2:
                    player.transformNext();
                    printCurrentAvatar();
                    break;
                case 3: 
                    player.transformBack();
                    printCurrentAvatar();
                    break;
            }            
        }
    }
        
    public static void printCurrentAvatar()
    {
        if (player.getAvatar() != null) {
                System.out.println("Current avatar: " + player.getAvatar().getClass().getName());
        }
        else
        {
            System.out.println("Current avatar: Null");
        }
    }
    
    public static void newGame() throws FileNotFoundException
    {
        System.out.println("--------------------");
        System.out.println("1. Get form sequence from input file");
        System.out.println("2. Enter form sequence");
        System.out.println("--------------------");
        System.out.print("Enter your selection: ");
        int myint = scanner.nextInt();
        switch (myint) {
            case 1: 
                avatarList = formSequence.inputFromFile("input.txt");                
                break;
            case 2: 
                avatarList = formSequence.createAvatarList(enterAvatarList());
                break;
        }
        player = new Player(avatarList.get(0), avatarList);
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        init();       
        newGame();
        askActivity();
    }
}
