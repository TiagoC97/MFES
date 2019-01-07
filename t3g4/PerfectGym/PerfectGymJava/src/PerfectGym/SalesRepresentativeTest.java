package PerfectGym;

import java.util.*;

import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SalesRepresentativeTest extends MyTestCase {
  private SalesRepresentative salesRepresentative1 =
      new SalesRepresentative("Vasco", 25L, MaleQuote.getInstance(), "brazilian");
  private Lead lead1 =
      new Lead("Maria", 23L, FemaleQuote.getInstance(), "portuguese");
  private Lead lead2 = new Lead("Jorge", 25L, MaleQuote.getInstance(), "spanish");

  public void Run() {

    IO.println("\nSalesRepresentative Tests");
    salesRepresentative1.addLead(lead1);
    salesRepresentative1.addLead(lead2);
    assertEqual(SetUtil.set(lead1.getName(), lead2.getName()), salesRepresentative1.getLeads());
    salesRepresentative1.removeLead(lead2);
    assertEqual(SetUtil.set(lead1.getName()), salesRepresentative1.getLeads());
    salesRepresentative1.getActivity(true);
    IO.println("Finalizing SalesRepresentative Tests");
  }

  public SalesRepresentativeTest() {}

  public String toString() {

    return "SalesRepresentativeTest{"
        + "salesRepresentative1 := "
        + Utils.toString(salesRepresentative1)
        + ", lead1 := "
        + Utils.toString(lead1)
        + ", lead2 := "
        + Utils.toString(lead2)
        + "}";
  }
}
