package PerfectGym.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FemaleQuote {
  private static int hc = 0;
  private static FemaleQuote instance = null;

  public FemaleQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static FemaleQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new FemaleQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof FemaleQuote;
  }

  public String toString() {

    return "<Female>";
  }
}
