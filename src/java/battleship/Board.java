package battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Board {
  public static final Number SIZE = 10L;
  public static final Number MISS = 0L;
  public static final Number HIT = 1L;
  public static final Number SHIP_SUNK = 2L;
  public Boolean playable;
  public VDMSet cells = SetUtil.set();

  public void cg_init_Board_1(final Boolean type) {

    playable = type;
    for (Iterator iterator_14 = SetUtil.range(1L, Board.SIZE).iterator(); iterator_14.hasNext(); ) {
      Number x = (Number) iterator_14.next();
      for (Iterator iterator_15 = SetUtil.range(1L, Board.SIZE).iterator();
          iterator_15.hasNext();
          ) {
        Number y = (Number) iterator_15.next();
        cells = SetUtil.union(Utils.copy(cells), SetUtil.set(new Cell(x, y)));
      }
    }
    for (Iterator iterator_16 = cells.iterator(); iterator_16.hasNext(); ) {
      Cell c = (Cell) iterator_16.next();
      c.setBoard(this);
    }
  }

  public Board(final Boolean type) {

    cg_init_Board_1(type);
  }

  public Number hit(final Tuple coords) {

    {
      Cell c = null;
      Boolean success_1 = false;
      VDMSet set_1 = Utils.copy(cells);
      for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext() && !(success_1); ) {
        c = ((Cell) iterator_1.next());
        Boolean andResult_1 = false;

        if (Utils.equals(((Number) c.pos.get(0)), ((Number) coords.get(0)))) {
          if (Utils.equals(((Number) c.pos.get(1)), ((Number) coords.get(1)))) {
            andResult_1 = true;
          }
        }

        success_1 = andResult_1;
      }
      if (!(success_1)) {
        throw new RuntimeException("Let Be St found no applicable bindings");
      }

      {
        Boolean orResult_1 = false;

        if (!(c.hasShip)) {
          orResult_1 = true;
        } else {
          orResult_1 = c.hit;
        }

        if (orResult_1) {
          return Board.MISS;
        }

        c.ship.inc();
        c.setHit(true);
        if (c.ship.isDown()) {
          return Board.SHIP_SUNK;

        } else {
          return Board.HIT;
        }
      }
    }
  }

  public void mark(final Tuple coords, final Number res) {

    {
      Cell c = null;
      Boolean success_2 = false;
      VDMSet set_2 = Utils.copy(cells);
      for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext() && !(success_2); ) {
        c = ((Cell) iterator_2.next());
        Boolean andResult_2 = false;

        if (Utils.equals(((Number) c.pos.get(0)), ((Number) coords.get(0)))) {
          if (Utils.equals(((Number) c.pos.get(1)), ((Number) coords.get(1)))) {
            andResult_2 = true;
          }
        }

        success_2 = andResult_2;
      }
      if (!(success_2)) {
        throw new RuntimeException("Let Be St found no applicable bindings");
      }

      {
        c.setHit(true);
        Boolean orResult_2 = false;

        if (Utils.equals(res, Board.HIT)) {
          orResult_2 = true;
        } else {
          orResult_2 = Utils.equals(res, Board.SHIP_SUNK);
        }

        if (orResult_2) {
          c.setHasShip(true);
        }
      }
    }
  }

  public VDMSeq toList() {

    VDMSeq builder = SeqUtil.seq();
    VDMSeq aux = null;
    long toVar_2 = Board.SIZE.longValue();

    for (Long y = 1L; y <= toVar_2; y++) {
      aux = SeqUtil.seq();
      long toVar_1 = Board.SIZE.longValue();

      for (Long x = 1L; x <= toVar_1; x++) {
        {
          Cell cell = null;
          Boolean success_3 = false;
          VDMSet set_3 = Utils.copy(cells);
          for (Iterator iterator_3 = set_3.iterator(); iterator_3.hasNext() && !(success_3); ) {
            cell = ((Cell) iterator_3.next());
            Boolean andResult_3 = false;

            if (Utils.equals(((Number) cell.pos.get(0)), x)) {
              if (Utils.equals(((Number) cell.pos.get(1)), y)) {
                andResult_3 = true;
              }
            }

            success_3 = andResult_3;
          }
          if (!(success_3)) {
            throw new RuntimeException("Let Be St found no applicable bindings");
          }

          {
            aux = SeqUtil.conc(Utils.copy(aux), SeqUtil.seq(cell));
          }
        }
      }
      builder = SeqUtil.conc(Utils.copy(builder), SeqUtil.seq(Utils.copy(aux)));
    }
    return Utils.copy(builder);
  }

  public Boolean validPlay(final Tuple play) {

    Cell c = null;
    Boolean success_4 = false;
    VDMSet set_4 = Utils.copy(cells);
    for (Iterator iterator_4 = set_4.iterator(); iterator_4.hasNext() && !(success_4); ) {
      c = ((Cell) iterator_4.next());
      Boolean andResult_4 = false;

      if (Utils.equals(((Number) c.pos.get(0)), ((Number) play.get(0)))) {
        if (Utils.equals(((Number) c.pos.get(1)), ((Number) play.get(1)))) {
          andResult_4 = true;
        }
      }

      success_4 = andResult_4;
    }
    if (!(success_4)) {
      throw new RuntimeException("Let Be St found no applicable bindings");
    }

    {
      return !(c.hit);
    }
  }

  public Board() {}

  public String toString() {

    return "Board{"
        + "SIZE = "
        + Utils.toString(SIZE)
        + ", MISS = "
        + Utils.toString(MISS)
        + ", HIT = "
        + Utils.toString(HIT)
        + ", SHIP_SUNK = "
        + Utils.toString(SHIP_SUNK)
        + ", playable := "
        + Utils.toString(playable)
        + ", cells := "
        + Utils.toString(cells)
        + "}";
  }
}
