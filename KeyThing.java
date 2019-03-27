import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * KeyThing
 */
public class KeyThing implements MouseListener {
    public int margin;
    public int space;
    public boolean isP1Turn = true;
    public ArrayList<Pieces> pieces;
    public Game game;

    public void isWon(Spaces spc) {
        if (isWonwon(spc)) {
            System.out.println("Wining player is:" + spc);
            game.board.gameFinished = true;
            isP1Turn = true;
        }
    }

    public boolean isWonwon(Spaces spc) {
        int c = 0;
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).state) {
                c++;
            }
        }
        if (c >= pieces.size()) {
            return true;
        }
        // first row
        if (pieces.get(0).piece == spc && pieces.get(1).piece == spc && pieces.get(2).piece == spc) {
            game.board.setWinLine(pieces.get(0).x, pieces.get(0).y, pieces.get(2).x, pieces.get(2).y);
            return true;
        }
        // second row
        if (pieces.get(3).piece == spc && pieces.get(4).piece == spc && pieces.get(5).piece == spc) {
            game.board.setWinLine(pieces.get(3).x, pieces.get(3).y, pieces.get(5).x, pieces.get(5).y);
            return true;
        }
        // third row
        if (pieces.get(6).piece == spc && pieces.get(7).piece == spc && pieces.get(8).piece == spc) {
            game.board.setWinLine(pieces.get(6).x, pieces.get(6).y, pieces.get(8).x, pieces.get(8).y);
            return true;
        }
        // first col
        if (pieces.get(0).piece == spc && pieces.get(3).piece == spc && pieces.get(6).piece == spc) {
            game.board.setWinLine(pieces.get(0).x, pieces.get(0).y, pieces.get(6).x, pieces.get(6).y);
            return true;
        }
        // second col
        if (pieces.get(1).piece == spc && pieces.get(4).piece == spc && pieces.get(7).piece == spc) {
            game.board.setWinLine(pieces.get(1).x, pieces.get(1).y, pieces.get(7).x, pieces.get(7).y);
            return true;
        }
        // third col
        if (pieces.get(2).piece == spc && pieces.get(5).piece == spc && pieces.get(8).piece == spc) {
            game.board.setWinLine(pieces.get(2).x, pieces.get(2).y, pieces.get(8).x, pieces.get(8).y);
            return true;
        }
        // first cross
        if (pieces.get(0).piece == spc && pieces.get(4).piece == spc && pieces.get(8).piece == spc) {
            game.board.setWinLine(pieces.get(0).x, pieces.get(0).y, pieces.get(8).x, pieces.get(8).y);
            return true;
        }
        // second cross
        if (pieces.get(2).piece == spc && pieces.get(4).piece == spc && pieces.get(6).piece == spc) {
            game.board.setWinLine(pieces.get(2).x, pieces.get(2).y, pieces.get(6).x, pieces.get(6).y);
            return true;
        }
        return false;
    }

    public int turnCorToIndex(int[] cor) {
        int ret = 0;
        ret += cor[0];
        ret += cor[1] * 3;
        return ret;
    }

    public int[] getCor(int x, int y) {
        int[] bam = new int[2];
        bam[0] = ((x - margin) / space);
        bam[1] = ((y - margin) / space);

        return bam;
    }

    public boolean ifInBounds(int x, int y) {
        if ((x >= margin && x <= space * 3 + margin) && (y >= margin && y <= space * 3 + margin)) {
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.board.gameFinished) {
            game.restart();
        } else if (ifInBounds(e.getX(), e.getY())) {
            int clickdex = turnCorToIndex(getCor(e.getX(), e.getY()));
            pieces.get(clickdex).state = true;
            if (isP1Turn) {
                pieces.get(clickdex).piece = Spaces.x;
                isP1Turn = false;
                isWon(Spaces.x);
            } else {
                pieces.get(clickdex).piece = Spaces.o;
                isP1Turn = true;
                isWon(Spaces.o);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public KeyThing(int margin, int space) {
        this.margin = margin;
        this.space = space;
    }

}