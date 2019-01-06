package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Group {
  private VDMSet clients = SetUtil.set();
  private VDMMap groupInbox = MapUtil.map();
  private VDMSeq offers = SeqUtil.seq();

  public void cg_init_Group_1(final VDMSet newClients) {

    clients = Utils.copy(newClients);
    return;
  }

  public Group(final VDMSet newClients) {

    cg_init_Group_1(Utils.copy(newClients));
  }

  public void addClient(final Client client) {

    clients = SetUtil.union(Utils.copy(clients), SetUtil.set(client));
  }

  public void removeClient(final Client client) {

    clients = SetUtil.diff(Utils.copy(clients), SetUtil.set(client));
  }

  public void sendOffer(final String offer) {

    offers = SeqUtil.conc(SeqUtil.seq(offer), Utils.copy(offers));
  }

  public void sendMessage(final User user, final String msg) {

    if (!(SetUtil.inSet(user.getName(), MapUtil.dom(Utils.copy(groupInbox))))) {
      groupInbox =
              MapUtil.munion(
                      MapUtil.map(new Maplet(user.getName(), SeqUtil.seq(msg))), Utils.copy(groupInbox));
    } else {
      Utils.mapSeqUpdate(
              groupInbox,
              user.getName(),
              SeqUtil.conc(
                      Utils.copy(((VDMSeq) Utils.get(groupInbox, user.getName()))), SeqUtil.seq(msg)));
    }
  }

  public VDMMap checkInbox(final Client client) {

    return Utils.copy(groupInbox);
  }

  public String getLastMessageFromUser(final String senderName, final Client client) {

    return ((String) ((VDMSeq) Utils.get(groupInbox, senderName)).get(0));
  }

  public VDMSeq getMessagesFromUser(final String senderName, final Client client) {

    return Utils.copy(((VDMSeq) Utils.get(groupInbox, senderName)));
  }

  public VDMSeq checkOffers(final Client client) {

    return Utils.copy(offers);
  }

  public VDMSet getClients() {

    return Utils.copy(clients);
  }

  public Group() {}

  public String toString() {

    return "Group{"
            + "clients := "
            + Utils.toString(clients)
            + ", groupInbox := "
            + Utils.toString(groupInbox)
            + ", offers := "
            + Utils.toString(offers)
            + "}";
  }
}
