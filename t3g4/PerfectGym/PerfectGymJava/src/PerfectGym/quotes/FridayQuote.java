package PerfectGym.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FridayQuote {
  private static int hc = 0;
  private static FridayQuote instance = null;

  public FridayQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static FridayQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new FridayQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof FridayQuote;
  }

  public String toString() {

    return "<Friday>";
  }
}
