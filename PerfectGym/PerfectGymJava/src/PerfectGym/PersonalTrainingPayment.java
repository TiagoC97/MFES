package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PersonalTrainingPayment extends Payment {
  private Number fee;

  public void cg_init_PersonalTrainingPayment_1(
      final Client newClient, final Number newFee, final Number newDate, final Number newHour) {

    fee = newFee;
    newClient.addPersonalTrainingPayment(this);
    cg_init_Payment_1(newClient, newDate, newHour, newFee);
  }

  public PersonalTrainingPayment(
      final Client newClient, final Number newFee, final Number newDate, final Number newHour) {

    cg_init_PersonalTrainingPayment_1(newClient, newFee, newDate, newHour);
  }

  public Number getFee() {

    return fee;
  }

  public PersonalTrainingPayment() {}

  public String toString() {

    return "PersonalTrainingPayment{" + "fee := " + Utils.toString(fee) + "}";
  }
}
