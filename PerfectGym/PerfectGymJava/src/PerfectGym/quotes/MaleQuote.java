package PerfectGym.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MaleQuote {
  private static int hc = 0;
  private static MaleQuote instance = null;

  public MaleQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static MaleQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new MaleQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof MaleQuote;
  }

  public String toString() {

    return "<Male>";
  }
}
