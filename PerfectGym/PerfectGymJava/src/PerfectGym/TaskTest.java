package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TaskTest extends MyTestCase {
  private Task task1 =
      new Task(
          "ReuniÃ£o com o patrÃ£o",
          cg_Utils.CreateHour(9L, 0L),
              cg_Utils.CreateHour(10L, 0L),
              cg_Utils.CreateDate(2019L, 1L, 12L));

  public void Run() {

    IO.println("\nTask Tests");
    assertEqual("ReuniÃ£o com o patrÃ£o", task1.getDescription());
    assertEqual(900L, task1.getStartHour());
    assertEqual(1000L, task1.getEndHour());
    assertEqual(20190112L, task1.getDate());
    IO.println("Finalizing Task Tests");
  }

  public TaskTest() {}

  public String toString() {

    return "TaskTest{" + "task1 := " + Utils.toString(task1) + "}";
  }
}
