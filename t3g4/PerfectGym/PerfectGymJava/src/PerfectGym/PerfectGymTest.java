package PerfectGym;

import java.util.*;

import PerfectGym.quotes.MaleQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PerfectGymTest extends MyTestCase {
  private Owner owner1 =
          new Owner("Rui", 21L, MaleQuote.getInstance(), "portuguese");
  private Owner owner2 =
          new Owner("Tiago", 21L, MaleQuote.getInstance(), "portuguese");
  private Club club1 = new Club("Bombados", owner1, 17L);
  private Club club2 = new Club("PuxaFerro", owner2, 35L);
  private PerfectGym perfectGym1 = PerfectGym.getInstance();

  public void Run() {

    IO.println("\nPerfectGym Tests");
    perfectGym1.addClub(club1);
    perfectGym1.addClub(club2);
    assertEqual(
            MapUtil.map(new Maplet("Bombados", club1), new Maplet("PuxaFerro", club2)),
            perfectGym1.getClubs());
    IO.println("Finalizing PerfectGym Tests");
  }

  public PerfectGymTest() {}

  public String toString() {

    return "PerfectGymTest{"
            + "owner1 := "
            + Utils.toString(owner1)
            + ", owner2 := "
            + Utils.toString(owner2)
            + ", club1 := "
            + Utils.toString(club1)
            + ", club2 := "
            + Utils.toString(club2)
            + ", perfectGym1 := "
            + Utils.toString(perfectGym1)
            + "}";
  }
}
