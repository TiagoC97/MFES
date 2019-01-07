package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Session extends Task {
    protected Number trainer;
    protected Object dayOfWeek;

    public void cg_init_Session_1(
            final String newDescription,
            final Trainer newTrainer,
            final Object newDayOfWeek,
            final Number newStartHour,
            final Number newEndHour,
            final Number newDate) {

        trainer = newTrainer.getID();
        dayOfWeek = newDayOfWeek;
        cg_init_Task_1(newDescription, newStartHour, newEndHour, newDate);
    }

    public Session(
            final String newDescription,
            final Trainer newTrainer,
            final Object newDayOfWeek,
            final Number newStartHour,
            final Number newEndHour,
            final Number newDate) {

        cg_init_Session_1(newDescription, newTrainer, newDayOfWeek, newStartHour, newEndHour, newDate);
    }

    public Number getTrainer() {

        return trainer;
    }

    public Object getDayOfWeek() {

        return dayOfWeek;
    }

    public Session() {}

    public String toString() {

        return "Session{"
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
                + "}";
    }
}
