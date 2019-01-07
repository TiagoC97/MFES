package PerfectGym.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TuesdayQuote {
  private static int hc = 0;
  private static TuesdayQuote instance = null;

  public TuesdayQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static TuesdayQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new TuesdayQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof TuesdayQuote;
  }

  public String toString() {

    return "<Tuesday>";
  }
}
