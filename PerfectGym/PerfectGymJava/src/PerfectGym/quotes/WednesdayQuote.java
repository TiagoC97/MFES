package PerfectGym.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class WednesdayQuote {
  private static int hc = 0;
  private static WednesdayQuote instance = null;

  public WednesdayQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static WednesdayQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new WednesdayQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof WednesdayQuote;
  }

  public String toString() {

    return "<Wednesday>";
  }
}
