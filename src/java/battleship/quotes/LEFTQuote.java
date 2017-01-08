package battleship.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class LEFTQuote {
  private static int hc = 0;
  private static LEFTQuote instance = null;

  public LEFTQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static LEFTQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new LEFTQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof LEFTQuote;
  }

  public String toString() {

    return "<LEFT>";
  }
}
