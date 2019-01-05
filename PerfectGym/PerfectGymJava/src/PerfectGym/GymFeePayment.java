package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class GymFeePayment extends Payment {
  private Number fee;

  public void cg_init_GymFeePayment_1(
      final Client newClient, final Number newFee, final Number newDate, final Number newHour) {

    fee = newFee;
    newClient.addGymFeePayment(this);
    cg_init_Payment_1(newClient, newDate, newHour, newFee);
  }

  public GymFeePayment(
      final Client newClient, final Number newFee, final Number newDate, final Number newHour) {

    cg_init_GymFeePayment_1(newClient, newFee, newDate, newHour);
  }

  public Number getFee() {

    return fee;
  }

  public GymFeePayment() {}

  public String toString() {

    return "GymFeePayment{" + "fee := " + Utils.toString(fee) + "}";
  }
}
