package PerfectGym.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SundayQuote {
  private static int hc = 0;
  private static SundayQuote instance = null;

  public SundayQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static SundayQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new SundayQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof SundayQuote;
  }

  public String toString() {

    return "<Sunday>";
  }
}
