package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class GymClass extends Session {
  private String name;
  private VDMSet attendees = SetUtil.set();

  public void cg_init_GymClass_1(
      final String newDescription,
      final String className,
      final Trainer newTrainer,
      final Object newDayOfWeek,
      final Number newStartHour,
      final Number newEndHour,
      final Number newDate) {

    name = className;
    cg_init_Session_1(
        newDescription, newTrainer, ((Object) newDayOfWeek), newStartHour, newEndHour, newDate);
  }

  public GymClass(
      final String newDescription,
      final String className,
      final Trainer newTrainer,
      final Object newDayOfWeek,
      final Number newStartHour,
      final Number newEndHour,
      final Number newDate) {

    newTrainer.addTask(this);
    newTrainer.addGymClass(this);

    cg_init_GymClass_1(
        newDescription, className, newTrainer, newDayOfWeek, newStartHour, newEndHour, newDate);
  }

  public void addAttendee(final Client client) {

    attendees = SetUtil.union(Utils.copy(attendees), SetUtil.set(client.getID()));
    client.addGymClass(this);
  }

  public String getName() {

    return name;
  }

  public VDMSet getAttendees() {

    return Utils.copy(attendees);
  }

  public GymClass() {}

  public String toString() {

    return "GymClass{"
          + "name := "
          + Utils.toString(name)
              + ", description := "
              + Utils.toString(description)
              + ", startHour := "
              + Utils.toString(startHour)
              + ", endHour := "
              + Utils.toString(endHour)
              + ", date := "
              + Utils.toString(date)
              + ", dayOfWeek := "
              + Utils.toString(dayOfWeek)
              + ", trainer := "
              + Utils.toString(trainer)
          + ", attendees := "
          + Utils.toString(attendees)
        + "}";
  }
}
