package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TrainingSession extends Session {
  private Client trainee;

  public void cg_init_TrainingSession_1(
      final String newDescription,
      final Trainer newTrainer,
      final Client client,
      final Object newDayOfWeek,
      final Number newStartHour,
      final Number newEndHour,
      final Number newDate) {

    trainee = client;
    cg_init_Session_1(
        newDescription, newTrainer, ((Object) newDayOfWeek), newStartHour, newEndHour, newDate);
  }

  public TrainingSession(
      final String newDescription,
      final Trainer newTrainer,
      final Client client,
      final Object newDayOfWeek,
      final Number newStartHour,
      final Number newEndHour,
      final Number newDate) {

    cg_init_TrainingSession_1(
        newDescription, newTrainer, client, newDayOfWeek, newStartHour, newEndHour, newDate);
  }

  public Client getTrainee() {

    return trainee;
  }

  public TrainingSession() {}

  public String toString() {

    return "TrainingSession{" + "trainee := " + Utils.toString(trainee) + "}";
  }
}
