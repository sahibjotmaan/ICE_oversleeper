enum Symbol {
    SKIP, REVERSE, DRAW_TWO
}

enum Color {
    RED, GREEN, BLUE, YELLOW,BLACK
}



class Card {
    Color color;   int number; Symbol symbol;

    public Card(){
        this.color = null;
        this.number = -999;
        this.symbol = null;
    }
     public Card(Color color, int number,Symbol symbol) {
        this.color = color;
        this.number = number;
        this.symbol = symbol;
    }

    public Card(Color color, int number) {
        this.color = color;
        this.number = number;
        this.symbol = null;
    }

    public Card(Color color, Symbol symbol) {
        this.color = color;
        this.number = -1;
        this.symbol = symbol;
    }
   
}