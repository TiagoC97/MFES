package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Task {
  protected String description;
  protected Number startHour;
  protected Number endHour;
  protected Number date;

  public void cg_init_Task_1(
          final String newDescription,
          final Number newStartHour,
          final Number newEndHour,
          final Number newDate) {

    description = newDescription;
    Number atomicTmp_1 = newStartHour;
    Number atomicTmp_2 = newEndHour;
    {
      /* Start of atomic statement */
      startHour = atomicTmp_1;
      endHour = atomicTmp_2;
    } /* End of atomic statement */

    date = newDate;
    return;
  }

  public Task(
          final String newDescription,
          final Number newStartHour,
          final Number newEndHour,
          final Number newDate) {

    cg_init_Task_1(newDescription, newStartHour, newEndHour, newDate);
  }

  public String getDescription() {

    return description;
  }

  public Number getStartHour() {

    return startHour;
  }

  public Number getEndHour() {

    return endHour;
  }

  public Number getDate() {

    return date;
  }

  public Task() {}

  public String toString() {

    return "Task{"
            + "description := "
            + Utils.toString(description)
            + ", startHour := "
            + Utils.toString(startHour)
            + ", endHour := "
            + Utils.toString(endHour)
            + ", date := "
            + Utils.toString(date)
            + "}";
  }
}
