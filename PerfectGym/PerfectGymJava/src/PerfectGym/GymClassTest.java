package PerfectGym;

import java.util.*;

import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;
import PerfectGym.quotes.TuesdayQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class GymClassTest extends MyTestCase {
  private Trainer trainer1 =
      new Trainer("Vasco", 25L, MaleQuote.getInstance(), "brazilian");
  private Client client1 =
      new Client("Maria", 23L, FemaleQuote.getInstance(), "portuguese");
  private GymClass gymClass1 =
      new GymClass(
          "Aula de baixa intensidade",
          "Pilates",
          trainer1,
          TuesdayQuote.getInstance(),
          cg_Utils.CreateHour(9L, 0L),
          cg_Utils.CreateHour(10L, 0L),
          cg_Utils.CreateDate(2019L, 1L, 15L));

  public void Run() {

    IO.println("\nGymClass Tests");
    gymClass1.addAttendee(client1);
    assertEqual("Pilates", gymClass1.getName());
    assertEqual(SetUtil.set(client1.getID()), gymClass1.getAttendees());
    IO.println("Finalizing GymClass Tests");
  }

  public GymClassTest() {}

  public String toString() {

    return "GymClassTest{"
        + "trainer1 := "
        + Utils.toString(trainer1)
        + ", client1 := "
        + Utils.toString(client1)
        + ", gymClass1 := "
        + Utils.toString(gymClass1)
        + "}";
  }
}
