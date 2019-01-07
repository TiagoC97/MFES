package PerfectGym.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class EmployeeQuote {
  private static int hc = 0;
  private static EmployeeQuote instance = null;

  public EmployeeQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static EmployeeQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new EmployeeQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof EmployeeQuote;
  }

  public String toString() {

    return "<Employee>";
  }
}
