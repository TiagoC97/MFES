package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PerfectGym {
  private VDMMap clubs = MapUtil.map();
  private static PerfectGym perfectGym = new PerfectGym();

  public void cg_init_PerfectGym_1() {

    return;
  }

  public PerfectGym() {

    cg_init_PerfectGym_1();
  }

  public void addClub(final Club club) {

    clubs = MapUtil.munion(Utils.copy(clubs), MapUtil.map(new Maplet(club.getName(), club)));
  }

  public static PerfectGym getInstance() {

    return PerfectGym.perfectGym;
  }

  public VDMMap getClubs() {

    return Utils.copy(clubs);
  }

  public String toString() {

    return "PerfectGym{"
            + "clubs := "
            + Utils.toString(clubs)
            + ", perfectGym := "
            + Utils.toString(perfectGym)
            + "}";
  }
}
