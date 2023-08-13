import java.util.ArrayList;
import java.util.List;


class Player {
    String nameofPlayer;
    List<Card> hand;

    public Player(String name) {
        this.nameofPlayer = name;
        this.hand = new ArrayList<>();
    }
}