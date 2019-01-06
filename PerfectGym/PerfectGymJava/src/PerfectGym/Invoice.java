package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Invoice {
  private VDMSet payments = SetUtil.set();
  private Number totalAmount = 0L;
  private Number date;
  private Number hour;
  private String type;
  private Client client;

  public void cg_init_Invoice_1(
          final VDMSet newPayments,
          final Number newDate,
          final Number newHour,
          final String newType,
          final Boolean allActivePayments,
          final Client newClient) {

    payments = Utils.copy(newPayments);
    date = newDate;
    hour = newHour;
    type = newType;
    client = newClient;
    if (Utils.equals(allActivePayments, true)) {
      String stringPattern_4 = type;
      Boolean success_2 = Utils.equals(stringPattern_4, "product");

      if (!(success_2)) {
        String stringPattern_5 = type;
        success_2 = Utils.equals(stringPattern_5, "gymFee");

        if (!(success_2)) {
          String stringPattern_6 = type;
          success_2 = Utils.equals(stringPattern_6, "personalTraining");

          if (success_2) {
            client.moveAllPersonalTrainingPaymentsToHistory();
          }

        } else {
          client.moveAllProductPaymentsToHistory();
        }

      } else {
        client.moveAllGymFeePaymentsToHistory();
      }

      for (Iterator iterator_32 = payments.iterator(); iterator_32.hasNext(); ) {
        Payment p = (Payment) iterator_32.next();
        totalAmount = totalAmount.doubleValue() + p.getAmount().doubleValue();
      }

    } else {
      for (Iterator iterator_33 = payments.iterator(); iterator_33.hasNext(); ) {
        Payment p = (Payment) iterator_33.next();
        totalAmount = totalAmount.doubleValue() + p.getAmount().doubleValue();
        String stringPattern_7 = type;
        Boolean success_3 = Utils.equals(stringPattern_7, "product");

        if (!(success_3)) {
          String stringPattern_8 = type;
          success_3 = Utils.equals(stringPattern_8, "gymFee");

          if (!(success_3)) {
            String stringPattern_9 = type;
            success_3 = Utils.equals(stringPattern_9, "personalTraining");

            if (success_3) {
              client.removePersonalTrainingPayment(p);
            }

          } else {
            client.removeGymFeePayment(p);
          }

        } else {
          client.removeProductPayment(p);
        }
      }
    }

    return;
  }

  public Invoice(
          final VDMSet newPayments,
          final Number newDate,
          final Number newHour,
          final String newType,
          final Boolean allActivePayments,
          final Client newClient) {

    cg_init_Invoice_1(
            Utils.copy(newPayments), newDate, newHour, newType, allActivePayments, newClient);
  }

  public void addPayment(final VDMSet newPayments) {

    payments = SetUtil.union(Utils.copy(payments), Utils.copy(newPayments));
    for (Iterator iterator_34 = newPayments.iterator(); iterator_34.hasNext(); ) {
      Payment p = (Payment) iterator_34.next();
      totalAmount = totalAmount.doubleValue() + p.getAmount().doubleValue();
      String stringPattern_10 = type;
      Boolean success_4 = Utils.equals(stringPattern_10, "product");

      if (!(success_4)) {
        String stringPattern_11 = type;
        success_4 = Utils.equals(stringPattern_11, "gymFee");

        if (!(success_4)) {
          String stringPattern_12 = type;
          success_4 = Utils.equals(stringPattern_12, "personalTraining");

          if (success_4) {
            client.removePersonalTrainingPayment(p);
          }

        } else {
          client.removeGymFeePayment(p);
        }

      } else {
        client.removeProductPayment(p);
      }
    }
  }

  public void removePayment(final VDMSet newPayments) {

    payments = SetUtil.diff(Utils.copy(payments), Utils.copy(newPayments));
    for (Iterator iterator_35 = newPayments.iterator(); iterator_35.hasNext(); ) {
      Payment p = (Payment) iterator_35.next();
      totalAmount = totalAmount.doubleValue() - p.getAmount().doubleValue();
    }
  }

  public VDMSet getPayments() {

    return Utils.copy(payments);
  }

  public Number getTotalAmount() {

    return totalAmount;
  }

  public Number getDate() {

    return date;
  }

  public Number getHour() {

    return hour;
  }

  public String getType() {

    return type;
  }

  public Client getClient() {

    return client;
  }

  public Invoice() {}

  public String toString() {

    return "Invoice{"
            + "payments := "
            + Utils.toString(payments)
            + ", totalAmount := "
            + Utils.toString(totalAmount)
            + ", date := "
            + Utils.toString(date)
            + ", hour := "
            + Utils.toString(hour)
            + ", type := "
            + Utils.toString(type)
            + ", client := "
            + Utils.toString(client)
            + "}";
  }
}
