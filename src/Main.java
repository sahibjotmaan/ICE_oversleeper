import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
  
        Scanner sc = new Scanner(System.in);
        System.out.print("Please Enter the numbers of Players: ");
        int numofPlayers = sc.nextInt();
        sc.nextLine(); 
        List<String> players = new ArrayList<>();
        
        for (int i = 0; i < numofPlayers; i++) {
            System.out.print("Name of Player " + (i + 1) + ": ");
            String playerName = sc.nextLine();
            players.add(playerName);
        }
        UNOGame game = new UNOGame(players);
       sc.close();
        
        
}
}
