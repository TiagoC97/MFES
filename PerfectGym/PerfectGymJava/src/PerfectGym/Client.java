package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;
import PerfectGym.quotes.*;

@SuppressWarnings("all")
public class Client extends User {
  private Trainer trainer;
  private Number personalTrainingFee;
  private VDMSet classes = SetUtil.set();
  private VDMSet trainingSessions = SetUtil.set();
  private VDMSet gymAttendences = SetUtil.set();
  private VDMMap productsBought = MapUtil.map();
  private Number totalSpentOnProducts;
  private VDMSet gymFeePayments = SetUtil.set();
  private VDMSet productPayments = SetUtil.set();
  private VDMSet personalTrainingPayments = SetUtil.set();
  private VDMSet historyGymFeePayments = SetUtil.set();
  private VDMSet historyProductPayments = SetUtil.set();
  private VDMSet historyPersonalTrainingPayments = SetUtil.set();

  public void cg_init_Client_1(
      final String newName,
      final Number newAge,
      final Object newGender,
      final String newNationality) {

    trainer = null;
    personalTrainingFee = 0L;
    totalSpentOnProducts = 0L;
    cg_init_User_1(
        newName,
        ClientQuote.getInstance(),
        newAge,
        ((Object) newGender),
        newNationality);
  }

  public Client(
      final String newName,
      final Number newAge,
      final Object newGender,
      final String newNationality) {

    cg_init_Client_1(newName, newAge, newGender, newNationality);
  }

  public void addTrainer(final Trainer newTrainer, final Number fee) {

    if(trainer != null)
      trainer.removeTrainee(this);
    trainer = newTrainer;
    personalTrainingFee = fee;
  }

  public void removeTrainer() {

    trainer = null;
    personalTrainingFee = 0;
  }

  public void sendMessageToGroup(final String msg, final String groupName) {

    club.getGroupByName(groupName).sendMessage(this, msg);
  }

  public void addGymClass(final GymClass gymClass) {

    Number classDate = gymClass.getDate();
    classes = SetUtil.union(Utils.copy(classes), SetUtil.set(gymClass));
    addGymAttendence(classDate);
  }

  public void addTrainingSession(final TrainingSession trainingSession) {

    Number trainingSessionDate = trainingSession.getDate();
    trainingSessions = SetUtil.union(Utils.copy(trainingSessions), SetUtil.set(trainingSession));

    addGymAttendence(trainingSessionDate);
  }

  public void addGymAttendence(final Number date) {

    gymAttendences = SetUtil.union(Utils.copy(gymAttendences), SetUtil.set(date));
  }

  public void addGymFeePayment(final GymFeePayment payment) {

    gymFeePayments = SetUtil.union(Utils.copy(gymFeePayments), SetUtil.set(payment));
  }

  public void addProductPayment(final ProductPayment payment) {

    productPayments = SetUtil.union(Utils.copy(productPayments), SetUtil.set(payment));
  }

  public void addPersonalTrainingPayment(final PersonalTrainingPayment payment) {

    personalTrainingPayments =
        SetUtil.union(Utils.copy(personalTrainingPayments), SetUtil.set(payment));
  }

  public void addHistoryGymFeePayment(final Payment payment) {

    historyGymFeePayments = SetUtil.union(Utils.copy(historyGymFeePayments), SetUtil.set(payment));
  }

  public void addHistoryProductPayment(final Payment payment) {

    historyProductPayments =
        SetUtil.union(Utils.copy(historyProductPayments), SetUtil.set(payment));
  }

  public void addHistoryPersonalTrainingPayment(final Payment payment) {

    historyPersonalTrainingPayments =
        SetUtil.union(Utils.copy(historyPersonalTrainingPayments), SetUtil.set(payment));
  }

  public void removeGymFeePayment(final Payment payment) {

    addHistoryGymFeePayment(payment);
    gymFeePayments = SetUtil.diff(Utils.copy(gymFeePayments), SetUtil.set(payment));
  }

  public void removeProductPayment(final Payment payment) {

    addHistoryProductPayment(payment);
    productPayments = SetUtil.diff(Utils.copy(productPayments), SetUtil.set(payment));
  }

  public void removePersonalTrainingPayment(final Payment payment) {

    addHistoryPersonalTrainingPayment(payment);
    personalTrainingPayments =
        SetUtil.diff(Utils.copy(personalTrainingPayments), SetUtil.set(payment));
  }

  public void moveAllGymFeePaymentsToHistory() {

    for (Iterator iterator_17 = gymFeePayments.iterator(); iterator_17.hasNext(); ) {
      GymFeePayment payment = (GymFeePayment) iterator_17.next();
      removeGymFeePayment(payment);
    }
  }

  public void moveAllProductPaymentsToHistory() {

    for (Iterator iterator_18 = productPayments.iterator(); iterator_18.hasNext(); ) {
      ProductPayment payment = (ProductPayment) iterator_18.next();
      removeProductPayment(payment);
    }
  }

  public void moveAllPersonalTrainingPaymentsToHistory() {

    for (Iterator iterator_19 = personalTrainingPayments.iterator(); iterator_19.hasNext(); ) {
      PersonalTrainingPayment payment = (PersonalTrainingPayment) iterator_19.next();
      removePersonalTrainingPayment(payment);
    }
  }

  public void payGymFee(final Number date, final Number hour) {

    GymFeePayment payment = new GymFeePayment(this, club.getFee(), date, hour);
    addGymFeePayment(payment);
  }

  public void payPersonalTrainingFee(final Number date, final Number hour) {

    PersonalTrainingPayment payment = new PersonalTrainingPayment(this, club.getFee(), date, hour);
    addPersonalTrainingPayment(payment);
  }

  public void addProductBought(final Product product, final Number qtt, final Number spent) {

    totalSpentOnProducts = totalSpentOnProducts.doubleValue() + spent.doubleValue();
    if (SetUtil.inSet(product.getName(), MapUtil.dom(Utils.copy(productsBought)))) {
      Utils.mapSeqUpdate(
          productsBought,
          product.getName(),
          ((Number) Utils.get(productsBought, product.getName())).longValue() + qtt.longValue());
    } else {
      productsBought =
          MapUtil.munion(
              Utils.copy(productsBought), MapUtil.map(new Maplet(product.getName(), qtt)));
    }
  }

  public void addProductToPayment(
      final ProductPayment payment, final Product product, final Number qtt) {

    payment.addProduct(product, qtt);
  }

  public void getActivity() {

    Number numClasses = classes.size();
    Number numTrainingSessiosn = trainingSessions.size();
    Number numAttendences = gymAttendences.size();
    String personalTrainer = "None";
    Number numProductsBought = MapUtil.dom(Utils.copy(productsBought)).size();
    if (!(Utils.equals(trainer, null))) {
      personalTrainer = trainer.getName();
    }

    IO.println("********* CLIENT STATISTICS *********");
    IO.println("Personal trainer: " + SeqUtil.toStr(SeqUtil.seq(personalTrainer)));
    IO.println("Number of gym classes: " + SeqUtil.toStr(SeqUtil.seq(numClasses)));
    IO.println("Number of training sessions: " + SeqUtil.toStr(SeqUtil.seq(numTrainingSessiosn)));
    IO.println("Number of gym attendences: " + SeqUtil.toStr(SeqUtil.seq(numAttendences)));
    IO.println(
        "Number of different products bought: " + SeqUtil.toStr(SeqUtil.seq(numProductsBought)));
    IO.println("");
    IO.println("************************************");
  }

  public Trainer getTrainer() {

    return trainer;
  }

  public Number getPersonalTrainingFee() {

    return personalTrainingFee;
  }

  public Number getTotalSPentOnProducts() {

    return totalSpentOnProducts;
  }

  public VDMSet getClasses() {

    return Utils.copy(classes);
  }

  public VDMSet getTrainingSessions() {

    return Utils.copy(trainingSessions);
  }

  public VDMSet getGymAttendences() {

    return Utils.copy(gymAttendences);
  }

  public VDMMap getProductsBought() {

    return Utils.copy(productsBought);
  }

  public VDMSet getGymFeePayments() {

    return Utils.copy(gymFeePayments);
  }

  public VDMSet getProductPayments() {

    return Utils.copy(productPayments);
  }

  public VDMSet getPersonalTrainingPayments() {

    return Utils.copy(personalTrainingPayments);
  }

  public VDMSet getHistoryGymFeePayments() {

    return Utils.copy(historyGymFeePayments);
  }

  public VDMSet getHistoryProductPayments() {

    return Utils.copy(historyProductPayments);
  }

  public VDMSet getHistoryPersonalTrainingPayments() {

    return Utils.copy(historyPersonalTrainingPayments);
  }

  public VDMMap readGroupMessages(final String groupName) {

    return club.getGroupByName(groupName).checkInbox(this);
  }

  public VDMSeq readGroupOffers(final String groupName) {

    return club.getGroupByName(groupName).checkOffers(this);
  }

  public VDMSeq readGroupMessagesFromUser(final String groupName, final User user) {

    return club.getGroupByName(groupName).getMessagesFromUser(user.getName(), this);
  }

  public String readGroupLastMessageFromUser(final String groupName, final User user) {

    return club.getGroupByName(groupName).getLastMessageFromUser(user.getName(), this);
  }

  public VDMSet getPaymentsOfGivenType(final String type) {

    String stringPattern_1 = type;
    Boolean success_1 = Utils.equals(stringPattern_1, "product");

    if (!(success_1)) {
      String stringPattern_2 = type;
      success_1 = Utils.equals(stringPattern_2, "gymFee");

      if (!(success_1)) {
        String stringPattern_3 = type;
        success_1 = Utils.equals(stringPattern_3, "personalTraining");

        if (success_1) {
          return Utils.copy(personalTrainingPayments);
        }

      } else {
        return Utils.copy(gymFeePayments);
      }

    } else {
      return Utils.copy(productPayments);
    }

    return SetUtil.set();
  }

  public Client() {}

  public String toString() {

    return "Client{"
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
          + ", trainer := "
        + Utils.toString(trainer)
        + ", personalTrainingFee := "
        + Utils.toString(personalTrainingFee)
        + ", classes := "
        + Utils.toString(classes)
        + ", trainingSessions := "
        + Utils.toString(trainingSessions)
        + ", gymAttendences := "
        + Utils.toString(gymAttendences)
        + ", productsBought := "
        + Utils.toString(productsBought)
        + ", totalSpentOnProducts := "
        + Utils.toString(totalSpentOnProducts)
        + ", gymFeePayments := "
        + Utils.toString(gymFeePayments)
        + ", productPayments := "
        + Utils.toString(productPayments)
        + ", personalTrainingPayments := "
        + Utils.toString(personalTrainingPayments)
        + ", historyGymFeePayments := "
        + Utils.toString(historyGymFeePayments)
        + ", historyProductPayments := "
        + Utils.toString(historyProductPayments)
        + ", historyPersonalTrainingPayments := "
        + Utils.toString(historyPersonalTrainingPayments)
        + "}";
  }
}
