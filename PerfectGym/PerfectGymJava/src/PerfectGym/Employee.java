package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;
import PerfectGym.quotes.*;

@SuppressWarnings("all")
public class Employee extends User {
  protected EmployeeCalendar calendar;

  public void cg_init_Employee_1(
      final String newName,
      final Number newAge,
      final Object newGender,
      final String newNationality) {

    calendar = new EmployeeCalendar();
    cg_init_User_1(
        newName,
        EmployeeQuote.getInstance(),
        newAge,
        ((Object) newGender),
        newNationality);
  }

  public Employee(
      final String newName,
      final Number newAge,
      final Object newGender,
      final String newNationality) {

    cg_init_Employee_1(newName, newAge, newGender, newNationality);
  }

  public void addTask(final Task task) {

    calendar.addTask(task);
  }

  public void getActivity(final Boolean showAllTasks) {

    /* skip */

  }

  public EmployeeCalendar getCalendar() {

    return calendar;
  }

  public Employee() {}

  public String toString() {

    return "Employee{" + "calendar := " + Utils.toString(calendar) + "}";
  }
}
