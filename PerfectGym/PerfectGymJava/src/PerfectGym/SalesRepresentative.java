package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SalesRepresentative extends Employee {
  private VDMSet leads = SetUtil.set();

  public void cg_init_SalesRepresentative_1(
          final String newName,
          final Number newAge,
          final Object newGender,
          final String newNationality) {

    cg_init_Employee_1(newName, newAge, ((Object) newGender), newNationality);
  }

  public SalesRepresentative(
          final String newName,
          final Number newAge,
          final Object newGender,
          final String newNationality) {

    cg_init_SalesRepresentative_1(newName, newAge, newGender, newNationality);
  }

  public void addLead(final Lead lead) {

    leads = SetUtil.union(Utils.copy(leads), SetUtil.set(lead.getName()));
  }

  public void removeLead(final Lead lead) {

    leads = SetUtil.diff(Utils.copy(leads), SetUtil.set(lead.getName()));
  }

  public void getActivity(final Boolean showAllTasks) {

    Number numLeads = leads.size();
    VDMMap tasks = calendar.getTasks();
    Number i = 0L;
    Task t = null;
    IO.println("********* SALES REPRESENTATIVE STATISTICS *********");
    IO.print("Number of leads: ");
    IO.println(numLeads);
    if (showAllTasks) {
      for (Iterator iterator_36 = MapUtil.dom(Utils.copy(tasks)).iterator();
           iterator_36.hasNext();
              ) {
        Number d = (Number) iterator_36.next();
        i = 0L;
        IO.print("Date: ");
        IO.println(d);
        Boolean whileCond_1 = true;
        while (whileCond_1) {
          whileCond_1 = i.longValue() < ((VDMSeq) Utils.get(tasks, d)).size();
          if (!(whileCond_1)) {
            break;
          }

          {
            t = ((Task) Utils.get(((VDMSeq) Utils.get(tasks, d)), i));
            IO.print("\tTask: ");
            IO.print(t.getDescription());
            IO.print(" started at ");
            IO.print(t.getStartHour());
            IO.print(" and ended at ");
            IO.println(t.getEndHour());
            i = i.longValue() + 1L;
          }
        }

        IO.println("");
      }
    }

    IO.println("");
    IO.println("***************************************************");
  }

  public VDMSet getLeads() {

    return Utils.copy(leads);
  }

  public SalesRepresentative() {}

  public String toString() {

    return "SalesRepresentative{"
            + "userID := "
            + Utils.toString(id)
            + ", name := "
            + Utils.toString(name)
            + ", age := "
            + Utils.toString(age)
            + ", gender := "
            + Utils.toString(gender)
            + ", nationality := "
            + Utils.toString(nationality)
            + ", inbox := "
            + Utils.toString(inbox)
            + ", access := "
            + Utils.toString(access)
            + ", leads := "
            + Utils.toString(leads) + "}";
  }
}
