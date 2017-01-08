package battleship;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Game {
  public Player player1;
  public Player player2;
  public Player currentPlayer;
  public Boolean isGameOver = false;
  private VDMSeq inputQueue = SeqUtil.seq();

  public void cg_init_Game_1(
      final String p1name,
      final Boolean p1bot,
      final VDMSeq p1ships,
      final String p2name,
      final Boolean p2bot,
      final VDMSeq p2ships) {

    player1 = new Player(p1name, p1bot, Utils.copy(p1ships));
    player2 = new Player(p2name, p2bot, Utils.copy(p2ships));
    currentPlayer = player1;
  }

  public Game(
      final String p1name,
      final Boolean p1bot,
      final VDMSeq p1ships,
      final String p2name,
      final Boolean p2bot,
      final VDMSeq p2ships) {

    cg_init_Game_1(p1name, p1bot, Utils.copy(p1ships), p2name, p2bot, Utils.copy(p2ships));
  }

  public void turn() {

    Tuple move = null;
    Player ternaryIfExp_1 = null;

    if (Utils.equals(currentPlayer, player1)) {
      ternaryIfExp_1 = player2;
    } else {
      ternaryIfExp_1 = player1;
    }

    Player otherPlayer = ternaryIfExp_1;
    if (currentPlayer.isBot) {
      move = currentPlayer.bot_genCoords();

    } else if (inputQueue.size() > 0L) {
      move = Utils.copy(((Tuple) inputQueue.get(0)));
      inputQueue = SeqUtil.tail(Utils.copy(inputQueue));
    }

    Boolean andResult_9 = false;

    if (!(Utils.equals(move, null))) {
      if (currentPlayer.boardplay.validPlay(Utils.copy(move))) {
        andResult_9 = true;
      }
    }

    if (andResult_9) {
      currentPlayer.boardplay.mark(Utils.copy(move), otherPlayer.boardown.hit(Utils.copy(move)));
      isGameOver = checkVictory(otherPlayer.fleet);
      if (!(isGameOver)) {
        currentPlayer = otherPlayer;
      }
    }
  }

  public void play(final Number x, final Number y) {

    inputQueue = SeqUtil.conc(Utils.copy(inputQueue), SeqUtil.seq(Tuple.mk_(x, y)));
  }

  public Player getWinner() {

    return currentPlayer;
  }

  public Boolean checkVictory(final VDMSeq ships) {

    Boolean forAllExpResult_1 = true;
    VDMSet set_6 = SeqUtil.elems(Utils.copy(ships));
    for (Iterator iterator_6 = set_6.iterator(); iterator_6.hasNext() && forAllExpResult_1; ) {
      Ship s = ((Ship) iterator_6.next());
      forAllExpResult_1 = s.isDown();
    }
    return forAllExpResult_1;
  }

  public VDMSet getAllBoards() {

    return SetUtil.set(player1.boardown, player2.boardown, player1.boardplay, player2.boardplay);
  }

  public Game() {}

  public String toString() {

    return "Game{"
        + "player1 := "
        + Utils.toString(player1)
        + ", player2 := "
        + Utils.toString(player2)
        + ", currentPlayer := "
        + Utils.toString(currentPlayer)
        + ", isGameOver := "
        + Utils.toString(isGameOver)
        + ", inputQueue := "
        + Utils.toString(inputQueue)
        + "}";
  }
}
