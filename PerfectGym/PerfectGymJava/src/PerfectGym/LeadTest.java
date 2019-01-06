package PerfectGym;

import java.util.*;

import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class LeadTest extends MyTestCase {
  private Lead lead1 =
      new Lead("Maria", 23L, FemaleQuote.getInstance(), "portuguese");
  private Lead lead2 = new Lead("Jorge", 25L, MaleQuote.getInstance(), "spanish");

  public void Run() {

    IO.println("\nLead Tests");
    assertEqual("Maria", lead1.getName());
    assertEqual(23L, lead1.getAge());
    assertEqual(FemaleQuote.getInstance(), ((Object) lead1.getGender()));
    assertEqual("portuguese", lead1.getNationality());
    assertEqual(0L, lead1.getID());
    assertEqual(1L, lead2.getID());
    IO.println("Finalizing Lead Tests");
  }

  public LeadTest() {}

  public String toString() {

    return "LeadTest{"
        + "lead1 := "
        + Utils.toString(lead1)
        + ", lead2 := "
        + Utils.toString(lead2)
        + "}";
  }
}
