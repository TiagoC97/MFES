package PerfectGym;

import java.util.*;

import PerfectGym.quotes.MaleQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class EmployeeTest extends MyTestCase {
  private Employee employee1 =
      new Employee("Vasco", 25L, MaleQuote.getInstance(), "brazilian");
  private Task task1 =
      new Task(
          "ReuniÃ£o com o patrÃ£o",
          cg_Utils.CreateHour(9L, 0L),
          cg_Utils.CreateHour(10L, 0L),
          cg_Utils.CreateDate(2019L, 1L, 12L));

  public void Run() {

    IO.println("\nEmployee Tests");
    employee1.addTask(task1);
    assertEqual(
        MapUtil.map(new Maplet(20190112L, SeqUtil.seq(task1))), employee1.getCalendar().getTasks());
    assertEqual(SeqUtil.seq(task1), employee1.getCalendar().getTasksForGivenDate(20190112L));
    employee1.getActivity(true);
    IO.println("Finalizing Employee Tests");
  }

  public EmployeeTest() {}

  public String toString() {

    return "EmployeeTest{"
        + "employee1 := "
        + Utils.toString(employee1)
        + ", task1 := "
        + Utils.toString(task1)
        + "}";
  }
}
