/**
 * Pieces
 */
public class Pieces {
    public Spaces piece;
    public boolean state;
    public int x;
    public int y;
    private static int quex = 0;
    private static int quey = 0;

    public Pieces() {
        this.piece = Spaces.empty;
        state = false;
        this.x = quex;
        this.y = quey;
        quex++;
        if (quex >= 3) {
            quex = 0;
            quey++;
        }
    }

}
