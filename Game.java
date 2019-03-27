import java.util.ArrayList;

/**
 * Game
 */
public class Game {
    public Board board;
    public ArrayList<Pieces> pieces;

    public void drawState() {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).state) {
                if (pieces.get(i).piece == Spaces.x) {
                    board.drawX(board.convertPixel(pieces.get(i).x), board.convertPixel(pieces.get(i).y));
                } else if (pieces.get(i).piece == Spaces.o) {
                    board.drawO(board.convertPixel(pieces.get(i).x), board.convertPixel(pieces.get(i).y));
                }
            }
        }
    }

    public void render() {
        board.drawBoard();
        drawState();
        board.drawWin();
        board.bs.show();
    }

    public void start() {
        while (true) {
            render();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPieces() {
        for (int i = 0; i < 9; i++) {
            pieces.add(new Pieces());
        }
    }

    public void restart() {
        for (int i = 0; i < pieces.size(); i++) {
            pieces.get(i).state = false;
            pieces.get(i).piece = Spaces.empty;
        }
        board.gameFinished = false;
        board.clearScreen();
    }

    public Game() {
        board = new Board();
        pieces = new ArrayList<Pieces>();
        addPieces();
        board.ml.pieces = this.pieces;
        board.ml.game = this;
    }

}