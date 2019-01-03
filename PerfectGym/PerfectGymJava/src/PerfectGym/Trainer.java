package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Trainer extends Employee {
  private VDMSet trainees = SetUtil.set();
  private VDMSet classes = SetUtil.set();
  private VDMSet trainingSessions = SetUtil.set();

  public void cg_init_Trainer_1(
      final String newName,
      final Number newAge,
      final Object newGender,
      final String newNationality) {

    cg_init_Employee_1(newName, newAge, ((Object) newGender), newNationality);
  }

  public Trainer(
      final String newName,
      final Number newAge,
      final Object newGender,
      final String newNationality) {

    cg_init_Trainer_1(newName, newAge, newGender, newNationality);
  }

  public void addTrainee(final Client client) {

    trainees = SetUtil.union(Utils.copy(trainees), SetUtil.set(client));
  }

  public void removeTrainee(final Client client) {

    trainees = SetUtil.diff(Utils.copy(trainees), SetUtil.set(client));
  }

  public void addGymClass(final GymClass gymClass) {

    classes = SetUtil.union(Utils.copy(classes), SetUtil.set(gymClass));
  }

  public void addTrainingSession(final TrainingSession trainingSession) {

    trainingSessions = SetUtil.union(Utils.copy(trainingSessions), SetUtil.set(trainingSession));
  }

  public void getActivity(final Boolean showAllTasks) {

    Number numClasses = classes.size();
    Number numTrainingSessiosn = trainingSessions.size();
    Number numTrainees = trainees.size();
    VDMMap tasks = calendar.getTasks();
    Number i = 0L;
    Task t = null;
    IO.println("********* CLIENT STATISTICS *********");
    IO.println("Number of gym classes: " + SeqUtil.toStr(SeqUtil.seq(numClasses)));
    IO.println("Number of training sessions: " + SeqUtil.toStr(SeqUtil.seq(numTrainingSessiosn)));
    IO.println("Number of trainees: " + SeqUtil.toStr(SeqUtil.seq(numTrainees)));
    if (showAllTasks) {
      for (Iterator iterator_32 = MapUtil.dom(Utils.copy(tasks)).iterator();
          iterator_32.hasNext();
          ) {
        Number d = (Number) iterator_32.next();
        i = 0L;
        IO.println("Date: " + SeqUtil.toStr(SeqUtil.seq(d)));
        Boolean whileCond_2 = true;
        while (whileCond_2) {
          whileCond_2 = i.longValue() < ((VDMSeq) Utils.get(tasks, d)).size();
          if (!(whileCond_2)) {
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

  public VDMSet getTrainees() {

    return Utils.copy(trainees);
  }

  public VDMSet getClasses() {

    return Utils.copy(classes);
  }

  public VDMSet getTrainingSessions() {

    return Utils.copy(trainingSessions);
  }

  public Trainer() {}

  public String toString() {

    return "Trainer{"
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
        + "trainees := "
        + Utils.toString(trainees)
        + ", classes := "
        + Utils.toString(classes)
        + ", trainingSessions := "
        + Utils.toString(trainingSessions)
        + "}";
  }
}
