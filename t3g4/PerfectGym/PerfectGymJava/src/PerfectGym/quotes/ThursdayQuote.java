package PerfectGym.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ThursdayQuote {
  private static int hc = 0;
  private static ThursdayQuote instance = null;

  public ThursdayQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ThursdayQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ThursdayQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ThursdayQuote;
  }

  public String toString() {

    return "<Thursday>";
  }
}
