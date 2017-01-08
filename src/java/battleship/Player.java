package battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Player {
  public static final VDMSeq FLEET = SeqUtil.seq(2L, 2L, 2L, 2L, 3L, 3L, 3L, 4L, 4L, 5L);
  public Number it = 0L;
  public String name;
  public VDMSeq fleet;
  public Board boardown;
  public Board boardplay;
  public Boolean isBot;

  public void cg_init_Player_1(final String n, final Boolean isbot, final VDMSeq ships) {

    name = n;
    isBot = isbot;
    boardown = new Board(false);
    boardplay = new Board(true);
    fleet = SeqUtil.seq();
    long toVar_3 = Player.FLEET.size();

    for (Long i = 1L; i <= toVar_3; i++) {
      fleet =
          SeqUtil.conc(
              Utils.copy(fleet),
              SeqUtil.seq(
                  new Ship(
                      Utils.copy(((Ship.Placement) Utils.get(ships, i))),
                      ((Number) Utils.get(FLEET, i)),
                      boardown)));
    }
  }

  public Player(final String n, final Boolean isbot, final VDMSeq ships) {

    cg_init_Player_1(n, isbot, Utils.copy(ships));
  }

  public Tuple bot_genCoords() {

    Number x = Utils.mod(it.longValue(), Board.SIZE.longValue()) + 1L;
    Number y = Utils.div(it.longValue(), Board.SIZE.longValue()) + 1L;
    it = it.longValue() + 1L;
    return Tuple.mk_(x, y);
  }

  public Player() {}

  public String toString() {

    return "Player{"
        + "FLEET = "
        + Utils.toString(FLEET)
        + ", it := "
        + Utils.toString(it)
        + ", name := "
        + Utils.toString(name)
        + ", fleet := "
        + Utils.toString(fleet)
        + ", boardown := "
        + Utils.toString(boardown)
        + ", boardplay := "
        + Utils.toString(boardplay)
        + ", isBot := "
        + Utils.toString(isBot)
        + "}";
  }
}
