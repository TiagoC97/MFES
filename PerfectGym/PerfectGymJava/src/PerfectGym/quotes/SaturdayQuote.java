package PerfectGym.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SaturdayQuote {
  private static int hc = 0;
  private static SaturdayQuote instance = null;

  public SaturdayQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static SaturdayQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new SaturdayQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof SaturdayQuote;
  }

  public String toString() {

    return "<Saturday>";
  }
}
