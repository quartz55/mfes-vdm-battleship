package battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Ship {
  private static final VDMSet sizes = SetUtil.set(2L, 3L, 4L, 5L);
  private static final VDMMap orientations =
      MapUtil.map(
          new Maplet(battleship.quotes.UPQuote.getInstance(), Tuple.mk_(0L, -1L)),
          new Maplet(battleship.quotes.RIGHTQuote.getInstance(), Tuple.mk_(1L, 0L)),
          new Maplet(battleship.quotes.DOWNQuote.getInstance(), Tuple.mk_(0L, 1L)),
          new Maplet(battleship.quotes.LEFTQuote.getInstance(), Tuple.mk_(-1L, 0L)));
  public Number hits = 0L;
  public Number size;
  public Board board;
  public VDMSet cells = SetUtil.set();

  public void cg_init_Ship_1(final Placement $placement, final Number $size, final Board $board) {

    VDMSet coords = null;
    size = $size;
    board = $board;
    coords = SetUtil.set(Utils.copy($placement.cell));
    long toVar_4 = size.longValue() - 1L;

    for (Long i = 1L; i <= toVar_4; i++) {
      coords =
          SetUtil.union(
              Utils.copy(coords),
              SetUtil.set(
                  Tuple.mk_(
                      ((Number) $placement.cell.get(0)).longValue()
                          + ((Number)
                                      ((Tuple) Utils.get(orientations, $placement.orientation))
                                          .get(0))
                                  .longValue()
                              * i.longValue(),
                      ((Number) $placement.cell.get(1)).longValue()
                          + ((Number)
                                      ((Tuple) Utils.get(orientations, $placement.orientation))
                                          .get(1))
                                  .longValue()
                              * i.longValue())));
    }
    fillCells(Utils.copy(coords));
  }

  public Ship(final Placement $placement, final Number $size, final Board $board) {

    cg_init_Ship_1(Utils.copy($placement), $size, $board);
  }

  public void fillCells(final VDMSet coords) {

    for (Iterator iterator_17 = coords.iterator(); iterator_17.hasNext(); ) {
      Tuple c = (Tuple) iterator_17.next();
      {
        Cell cell = null;
        Boolean success_5 = false;
        VDMSet set_5 = board.cells;
        for (Iterator iterator_5 = set_5.iterator(); iterator_5.hasNext() && !(success_5); ) {
          cell = ((Cell) iterator_5.next());
          Boolean andResult_13 = false;

          if (Utils.equals(((Number) cell.pos.get(0)), ((Number) c.get(0)))) {
            if (Utils.equals(((Number) cell.pos.get(1)), ((Number) c.get(1)))) {
              andResult_13 = true;
            }
          }

          success_5 = andResult_13;
        }
        if (!(success_5)) {
          throw new RuntimeException("Let Be St found no applicable bindings");
        }

        {
          cell.setHasShip(true);
          cell.setShip(this);
          cells = SetUtil.union(Utils.copy(cells), SetUtil.set(cell));
        }
      }
    }
  }

  public void inc() {

    hits = hits.longValue() + 1L;
  }

  public Boolean isDown() {

    return Utils.equals(size, hits);
  }

  public Ship() {}

  public String toString() {

    return "Ship{"
        + "sizes = "
        + Utils.toString(sizes)
        + ", orientations = "
        + Utils.toString(orientations)
        + ", hits := "
        + Utils.toString(hits)
        + ", size := "
        + Utils.toString(size)
        + ", board := "
        + Utils.toString(board)
        + ", cells := "
        + Utils.toString(cells)
        + "}";
  }

  public static class Placement implements Record {
    public Tuple cell;
    public Object orientation;

    public Placement(final Tuple _cell, final Object _orientation) {

      cell = _cell != null ? Utils.copy(_cell) : null;
      orientation = _orientation != null ? _orientation : null;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Placement)) {
        return false;
      }

      Placement other = ((Placement) obj);

      return (Utils.equals(cell, other.cell)) && (Utils.equals(orientation, other.orientation));
    }

    public int hashCode() {

      return Utils.hashCode(cell, orientation);
    }

    public Placement copy() {

      return new Placement(cell, orientation);
    }

    public String toString() {

      return "mk_Ship`Placement" + Utils.formatFields(cell, orientation);
    }
  }
}
