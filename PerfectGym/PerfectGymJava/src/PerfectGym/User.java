package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  protected String name;
  public static Number curUserID = 0L;
  protected Number id = User.curUserID;
  protected VDMMap inbox = MapUtil.map();
  protected Club club;
  protected Object access;
  protected Number age;
  protected Object gender;
  protected String nationality;

  public void cg_init_User_1(
          final String newName,
          final Object acc,
          final Number newAge,
          final Object newGender,
          final String newNationality) {

    club = null;
    name = newName;
    age = newAge;
    gender = newGender;
    nationality = newNationality;
    curUserID = User.curUserID.longValue() + 1L;
    access = acc;
    return;
  }

  public User(
          final String newName,
          final Object acc,
          final Number newAge,
          final Object newGender,
          final String newNationality) {

    cg_init_User_1(newName, acc, newAge, newGender, newNationality);
  }

  public void setClub(final Club newClub) {

    club = newClub;
  }

  public void sendMessage(final User receiver, final String msg) {

    receiver.receiveMessage(msg, this);
  }

  public void receiveMessage(final String msg, final User user) {

    if (!(SetUtil.inSet(user.getName(), MapUtil.dom(Utils.copy(inbox))))) {
      inbox =
              MapUtil.munion(
                      MapUtil.map(new Maplet(user.getName(), SeqUtil.seq(msg))), Utils.copy(inbox));
    } else {
      Utils.mapSeqUpdate(
              inbox,
              user.getName(),
              SeqUtil.conc(SeqUtil.seq(msg), Utils.copy(((VDMSeq) Utils.get(inbox, user.getName())))));
    }
  }

  public void deleteLastMessageFromUser(final String user) {

    Utils.mapSeqUpdate(inbox, user, SeqUtil.tail(Utils.copy(((VDMSeq) Utils.get(inbox, user)))));
  }

  public void deleteMessageNFromUser(final Number n, final String user) {

    Utils.mapSeqUpdate(
            inbox,
            user,
            SeqUtil.conc(
                    SeqUtil.subSeq(Utils.copy(((VDMSeq) Utils.get(inbox, user))), 1L, n.longValue() - 1L),
                    SeqUtil.subSeq(
                            Utils.copy(((VDMSeq) Utils.get(inbox, user))),
                            n.longValue() + 1L,
                            ((VDMSeq) Utils.get(inbox, user)).size())));
  }

  public void setAccess(final Object newAccess) {

    access = newAccess;
  }

  public VDMMap checkInbox() {

    return Utils.copy(inbox);
  }

  public VDMSeq readMessagesFromUser(final User user) {

    return Utils.copy(((VDMSeq) Utils.get(inbox, user.getName())));
  }

  public String readLastMessageFromUser(final User user) {

    return ((String) ((VDMSeq) Utils.get(inbox, user.getName())).get(0));
  }

  public String readMessageNFromUser(final Number n, final User user) {

    return ((String) Utils.get(((VDMSeq) Utils.get(inbox, user.getName())), n));
  }

  public void getActivity() {

    /* skip */

  }

  public String getName() {

    return name;
  }

  public Number getID() {

    return id;
  }

  public Object getAccess() {

    return access;
  }

  public Number getAge() {

    return age;
  }

  public Object getGender() {

    return gender;
  }

  public String getNationality() {

    return nationality;
  }

  public Club getClub() {

    return club;
  }

  public User() {}

  public String toString() {

    return "User{"
            + "name := "
            + Utils.toString(name)
            + ", curUserID := "
            + Utils.toString(curUserID)
            + ", id := "
            + Utils.toString(id)
            + ", inbox := "
            + Utils.toString(inbox)
            + ", club := "
            + Utils.toString(club)
            + ", access := "
            + Utils.toString(access)
            + ", age := "
            + Utils.toString(age)
            + ", gender := "
            + Utils.toString(gender)
            + ", nationality := "
            + Utils.toString(nationality)
            + "}";
  }
}
