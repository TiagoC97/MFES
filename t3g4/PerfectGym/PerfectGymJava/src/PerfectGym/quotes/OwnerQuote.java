package PerfectGym.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class OwnerQuote {
  private static int hc = 0;
  private static OwnerQuote instance = null;

  public OwnerQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static OwnerQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new OwnerQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof OwnerQuote;
  }

  public String toString() {

    return "<Owner>";
  }
}
