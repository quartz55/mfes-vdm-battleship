package battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Cell {
  public Tuple pos;
  public Boolean hit = false;
  public Boolean hasShip = false;
  public Ship ship = null;
  public Board board = null;

  public void cg_init_Cell_1(final Number x, final Number y) {

    pos = Tuple.mk_(x, y);
  }

  public Cell(final Number x, final Number y) {

    cg_init_Cell_1(x, y);
  }

  public void setBoard(final Board b) {

    board = b;
  }

  public void setHasShip(final Boolean n) {

    hasShip = n;
  }

  public void setShip(final Ship s) {

    ship = s;
  }

  public void setHit(final Boolean n) {

    hit = n;
  }

  public Cell() {}

  public static Boolean validPosition(final Tuple pos_1) {

    Boolean andResult_5 = false;

    if (((Number) pos_1.get(0)).longValue() >= 1L) {
      Boolean andResult_6 = false;

      if (((Number) pos_1.get(0)).longValue() <= Board.SIZE.longValue()) {
        Boolean andResult_7 = false;

        if (((Number) pos_1.get(1)).longValue() >= 1L) {
          if (((Number) pos_1.get(1)).longValue() <= Board.SIZE.longValue()) {
            andResult_7 = true;
          }
        }

        if (andResult_7) {
          andResult_6 = true;
        }
      }

      if (andResult_6) {
        andResult_5 = true;
      }
    }

    return andResult_5;
  }

  public String toString() {

    return "Cell{"
        + "pos := "
        + Utils.toString(pos)
        + ", hit := "
        + Utils.toString(hit)
        + ", hasShip := "
        + Utils.toString(hasShip)
        + ", ship := "
        + Utils.toString(ship)
        + ", board := "
        + Utils.toString(board)
        + "}";
  }
}
