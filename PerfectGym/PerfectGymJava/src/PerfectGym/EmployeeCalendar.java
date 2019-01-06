package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class EmployeeCalendar {
  private VDMMap calendar = MapUtil.map();

  public void cg_init_EmployeeCalendar_1() {

    return;
  }

  public EmployeeCalendar() {

    cg_init_EmployeeCalendar_1();
  }

  public void addTask(final Task task) {

    Number date = task.getDate();
    if (!(SetUtil.inSet(date, MapUtil.dom(Utils.copy(calendar))))) {
      calendar =
              MapUtil.munion(Utils.copy(calendar), MapUtil.map(new Maplet(date, SeqUtil.seq(task))));
    } else {
      Utils.mapSeqUpdate(
              calendar,
              date,
              SeqUtil.conc(Utils.copy(((VDMSeq) Utils.get(calendar, date))), SeqUtil.seq(task)));
    }
  }

  public VDMMap getTasks() {

    return Utils.copy(calendar);
  }

  public VDMSeq getTasksForGivenDate(final Number date) {

    return Utils.copy(((VDMSeq) Utils.get(calendar, date)));
  }

  public static Boolean overlapsTasks(final VDMSeq tasks) {

    throw new UnsupportedOperationException();
  }

  public static Boolean overlapsTask(
          final Number startHour1,
          final Number endHour1,
          final Number startHour2,
          final Number endHour2) {

    Boolean orResult_4 = false;

    Boolean andResult_155 = false;

    if (startHour1.longValue() >= startHour2.longValue()) {
      if (startHour1.longValue() < endHour2.longValue()) {
        andResult_155 = true;
      }
    }

    if (andResult_155) {
      orResult_4 = true;
    } else {
      Boolean orResult_5 = false;

      Boolean andResult_156 = false;

      if (endHour1.longValue() > startHour2.longValue()) {
        if (endHour1.longValue() <= endHour2.longValue()) {
          andResult_156 = true;
        }
      }

      if (andResult_156) {
        orResult_5 = true;
      } else {
        Boolean andResult_157 = false;

        if (startHour1.longValue() <= startHour2.longValue()) {
          if (endHour1.longValue() >= endHour2.longValue()) {
            andResult_157 = true;
          }
        }

        orResult_5 = andResult_157;
      }

      orResult_4 = orResult_5;
    }

    if (orResult_4) {
      return true;

    } else {
      return false;
    }
  }

  public String toString() {

    return "EmployeeCalendar{" + "calendar := " + Utils.toString(calendar) + "}";
  }
}
