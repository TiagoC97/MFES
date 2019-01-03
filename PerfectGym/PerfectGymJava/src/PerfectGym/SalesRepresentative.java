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

    leads = SetUtil.union(Utils.copy(leads), SetUtil.set(lead));
  }

  public void removeLead(final Lead lead) {

    leads = SetUtil.diff(Utils.copy(leads), SetUtil.set(lead));
  }

  public void getActivity(final Boolean showAllTasks) {

    Number numLeads = leads.size();
    VDMMap tasks = calendar.getTasks();
    Number i = 0L;
    Task t = null;
    IO.println("********* CLIENT STATISTICS *********");
    IO.println("Number of leads: " + SeqUtil.toStr(SeqUtil.seq(numLeads)));
    if (showAllTasks) {
      for (Iterator iterator_31 = MapUtil.dom(Utils.copy(tasks)).iterator();
          iterator_31.hasNext();
          ) {
        Number d = (Number) iterator_31.next();
        i = 0L;
        IO.println("Date: " + SeqUtil.toStr(SeqUtil.seq(d)));
        Boolean whileCond_1 = true;
        while (whileCond_1) {
          whileCond_1 = i.longValue() < ((VDMSeq) Utils.get(tasks, d)).size();
          if (!(whileCond_1)) {
            break;
          }

          {
            t = ((Task) Utils.get(((VDMSeq) Utils.get(tasks, d)), i));
            IO.println(
                "\tTask: "
                    + SeqUtil.toStr(SeqUtil.seq(t.getDescription()))
                    + SeqUtil.toStr(SeqUtil.seq(" started at "))
                    + SeqUtil.toStr(SeqUtil.seq(t.getStartHour()))
                    + SeqUtil.toStr(SeqUtil.seq(" and ended at "))
                    + SeqUtil.toStr(SeqUtil.seq(t.getEndHour())));
            i = i.longValue() + 1L;
          }
        }

        IO.println("");
      }
    }

    IO.println("");
    IO.println("************************************");
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
            + "leads := "
            + Utils.toString(leads) + "}";
  }
}
