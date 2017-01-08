import battleship.Board;
import battleship.Cell;
import battleship.Game;
import battleship.Ship;
import battleship.quotes.*;
import org.overture.codegen.runtime.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CLI {
    public static void main(String[] args) {
        Game g = new Game("HUMAN", false, createFleet(), "CPU", true, createFleet());
        while (!g.isGameOver) {
            if (!g.currentPlayer.isBot){
                boolean valid_play = false;
                int x = 0, y = 0;
                do {
                    printCurrentPlayer(g);
                    Scanner s = new Scanner(System.in);
                    System.out.print("Play > ");
                    String play_str = s.nextLine();
                    Pattern p = Pattern.compile("^([A-Z])(\\d+)$");
                    Matcher m = p.matcher(play_str);
                    if (m.matches()) {
                        try {
                            x = Integer.parseInt(m.group(2));
                            y = (int) m.group(1).charAt(0) - 64;
                            valid_play = validPlay(x, y, g.currentPlayer.boardown.toList().size());
                        } catch (Exception e) {
                            valid_play = false;
                        }
                    }
                    if (!valid_play) {
                        System.out.println("Invalid play! *PRESS ENTER*");
                        s.nextLine();
                    }
                } while (!valid_play);

                g.play(x, y);
            }
            g.turn();
        }
        printGame(g);
    }

    private static boolean validPlay(int x, int y, int size) {
        return x >= 1 && x <= size && y >= 1 && y <= size;
    }

    private static VDMSeq createFleet() {
        VDMSeq tmp = SeqUtil.seq();
        for (int i = 1; i <= 10; ++i) {
            tmp = SeqUtil.conc(
                    Utils.copy(tmp),
                    SeqUtil.seq(new Ship.Placement(Tuple.mk_(i, 1),
                                                   DOWNQuote.getInstance())));
        }
        return tmp;
    }

    public static void printGame(Game g) {
        System.out.println("Player: " + g.player1.name);
        printBoard(g.player1.boardown);
        System.out.println("-----------------------");
        printBoard(g.player1.boardplay);
        System.out.println("=======================");
        printBoard(g.player2.boardplay);
        System.out.println("-----------------------");
        printBoard(g.player2.boardown);
        System.out.println("Player: " + g.player2.name);
    }

    public static void printCurrentPlayer(Game g) {
        System.out.println("=======================");
        printBoard(g.currentPlayer.boardplay);
        System.out.println("-----------------------");
        printBoard(g.currentPlayer.boardown);
        System.out.println("Player: " + g.currentPlayer.name);
    }

    public static void printBoard(Board board) {
        VDMSeq b = board.toList();
        StringBuilder header_str = new StringBuilder(" ");
        for (int i = 0; i < b.size(); ++i) header_str.append(" ").append(i+1);
        System.out.println(header_str);
        for (int y = 0; y < b.size(); ++y) {
            VDMSeq line = (VDMSeq) b.get(y);
            Character row_letter = (char)(65+y);
            StringBuilder line_str = new StringBuilder().append(row_letter);
            for (int x = 0; x < line.size(); ++x) {
                Cell cell = (Cell) line.get(x);
                line_str.append("|");
                if (cell.hasShip) {
                    if (cell.hit) {
                        line_str.append("X");
                    } else {
                        line_str.append("O");
                    }
                } else if (cell.hit){
                    line_str.append("+");
                }
                else {
                    line_str.append(" ");
                }
            }
            line_str.append("|").append(row_letter);
            System.out.println(line_str);
        }
        System.out.println(header_str);
    }
}
