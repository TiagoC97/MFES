package PerfectGym;

import java.util.*;

import PerfectGym.quotes.MaleQuote;
import PerfectGym.quotes.MondayQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SessionTest extends MyTestCase {
  private Trainer trainer1 =
      new Trainer("Vasco", 25L, MaleQuote.getInstance(), "brazilian");
  private Session session1 =
      new Session(
          "Treino",
          trainer1,
          MondayQuote.getInstance(),
          cg_Utils.CreateHour(9L, 0L),
          cg_Utils.CreateHour(10L, 0L),
          cg_Utils.CreateDate(2019L, 1L, 12L));

  public void Run() {

    IO.println("\nSession Tests");
    assertEqual(trainer1.getID(), session1.getTrainer());
    assertEqual(MondayQuote.getInstance(), ((Object) session1.getDayOfWeek()));
    IO.println("Finalizing Session Tests");
  }

  public SessionTest() {}

  public String toString() {

    return "SessionTest{"
        + "trainer1 := "
        + Utils.toString(trainer1)
        + ", session1 := "
        + Utils.toString(session1)
        + "}";
  }
}
