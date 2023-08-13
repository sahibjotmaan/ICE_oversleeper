
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;



class UNOGame {
    List<Player> players;
    List<Card> discardPile;
    List<Card> deck;
   
    boolean reverse;
    boolean drawtwo;
    int currentPlayerIndex;
    Color currColor;
    

    public UNOGame(List<String> names) {
        players = new ArrayList<>();
        discardPile = new ArrayList<>();
        deck = new ArrayList<>();
        
        
        currentPlayerIndex = 0;
        currColor = null;
        reverse = false;


        initializeDeck();
        initPlayers(names);
        startGame();
    }

      private void initPlayers(List<String> names) {
        for (String playerName : names) {
            players.add(new Player(playerName));
        }
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.hand.add(drawCard());
            }
        }
        Card initCard = drawCard();
        while (initCard.symbol != null) {
            discardPile.add(initCard);
            initCard = drawCard();
        }
        discardPile.add(initCard);
        currColor = initCard.color;
    }

    private void initializeDeck() {
        for (Color color : Color.values()) {
            if (color != Color.BLACK) {
                for (int number = 0; number <= 9; number++) {
                    deck.add(new Card(color, number));
                    if (number != 0) {
                        deck.add(new Card(color, number));
                    }
                }
                deck.add(new Card(color,-1, Symbol.SKIP));
                deck.add(new Card(color,-2, Symbol.REVERSE));
                deck.add(new Card(color,-3, Symbol.DRAW_TWO));
            }
        }
        Collections.shuffle(deck);
       
    }


    private void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameTerminate = false;
        
        while (!gameTerminate) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\nName of the Current Player: " + currentPlayer.nameofPlayer+"\n");
            if(drawtwo==true){
            currentPlayer.hand.add(drawCard());
            currentPlayer.hand.add(drawCard());
            drawtwo  = false;
            }
           

            displayTopCard();
            displayPlayerHand(currentPlayer);
            
            boolean cardPlayed = false;
            while (!cardPlayed) {
                cardPlayed = playTurn(currentPlayer, scanner);
            }

            if (currentPlayer.hand.size() == 0) {
                System.out.println(currentPlayer.nameofPlayer + " is the Winner!");
                gameTerminate = true;
            } else {
                nextPlayer();
            }
        }

        scanner.close();
    }

    private void displayTopCard() {
        Card topCard = discardPile.get(discardPile.size() - 1);
        System.out.println("Current Top card: " + topCard.color + " " + topCard.number + " " + topCard.symbol+"\n");
    }

    private void displayPlayerHand(Player player) {
        System.out.print(player.nameofPlayer + "'s Cards are:\t ");
        for (Card card : player.hand) {
            System.out.print(card.color + " " + card.number + " " + card.symbol + " \n\t\t\t ");
        }
        System.out.println();
    }

    private boolean playTurn(Player player, Scanner scanner) {
        Scanner scan = new Scanner(System.in);
        Card topCard = discardPile.get(discardPile.size() - 1);

        System.out.println("Choose a card to play or Press D/d to Draw a card: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("D")) {
            player.hand.add(drawCard());
            return true;
        }

        String[] str = input.split(" ");
        if (str.length != 2) {
            System.out.println("Invalid!! Please Enter Again");
            return false;
        }

        Color chosenColor = Color.valueOf(str[0].toUpperCase());
        int chosenNumber = Integer.parseInt(str[1]);
        

        for (Card card : player.hand) {
            if ((card.color == chosenColor && card.number == chosenNumber) && canPlayCard(card, topCard)) {
                discardPile.add(card);
                player.hand.remove(card);
                if (card.color != Color.BLACK) {
                    currColor = card.color;
                }
              
                if(chosenNumber == (-1)){
                     currentPlayerIndex = (currentPlayerIndex +1) % players.size();
    
                }
                if(chosenNumber==(-2)){
                    currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
                }
                
                if(chosenNumber==(-3)){
                    drawtwo  = true;
                }
                
                return true;
            }
        }

        System.out.println("Invalid Card!! Please Try again.");
        return false;
    }

    private boolean canPlayCard(Card card, Card topCard) {
        return card.color == currColor || card.number == topCard.number || card.symbol == topCard.symbol;
    }

    private Card drawCard() {
        if (deck.isEmpty()) {
            Collections.shuffle(discardPile);
            deck.addAll(discardPile);
            discardPile.clear();
        }
        return deck.remove(deck.size() - 1);
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}