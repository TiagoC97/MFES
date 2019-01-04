package PerfectGym;

import java.util.*;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TrainingSession extends Session {
    private Number trainee;

    public void cg_init_TrainingSession_1(
            final String newDescription,
            final Trainer newTrainer,
            final Client client,
            final Object newDayOfWeek,
            final Number newStartHour,
            final Number newEndHour,
            final Number newDate) {

        trainee = client.getID();
        cg_init_Session_1(
                newDescription, newTrainer, ((Object) newDayOfWeek), newStartHour, newEndHour, newDate);
    }

    public TrainingSession(
            final String newDescription,
            final Client client,
            final Object newDayOfWeek,
            final Number newStartHour,
            final Number newEndHour,
            final Number newDate) {

        Trainer newTrainer = client.getTrainer();

        newTrainer.addTask(this);
        newTrainer.addTrainingSession(this);
        client.addTrainingSession(this);
        cg_init_TrainingSession_1(
                newDescription, newTrainer, client, newDayOfWeek, newStartHour, newEndHour, newDate);
    }

    public Number getTrainee() {

        return trainee;
    }

    public TrainingSession() {
    }

    public String toString() {

        return "TrainingSession{"
                + "description := "
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
                + ", trainee := "
                + Utils.toString(trainee)
                + "}";
    }
}
