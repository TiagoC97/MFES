package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CRM {
  private VDMMap leads = MapUtil.map();

  public void cg_init_CRM_1() {

    return;
  }

  public CRM() {

    cg_init_CRM_1();
  }

  public void addLead(final Lead lead) {

    leads = MapUtil.munion(Utils.copy(leads), MapUtil.map(new Maplet(lead, null)));
  }

  public void addLeadWithSR(final Lead lead, final SalesRepresentative sr) {

    leads = MapUtil.munion(Utils.copy(leads), MapUtil.map(new Maplet(lead, sr)));
  }

  public void setLeadSR(final Lead lead, final SalesRepresentative sr) {

    if (!(Utils.equals(((SalesRepresentative) Utils.get(leads, lead)), null))) {
      ((SalesRepresentative) Utils.get(leads, lead)).removeLead(lead);
    }

    Utils.mapSeqUpdate(leads, lead, sr);
    sr.addLead(lead);
  }

  public void removeLead(final Lead lead) {

    VDMMap newLeads = MapUtil.map();
    for (Iterator iterator_16 = MapUtil.dom(Utils.copy(leads)).iterator();
        iterator_16.hasNext();
        ) {
      Lead l = (Lead) iterator_16.next();
      if (!(Utils.equals(lead, l))) {
        newLeads =
            MapUtil.munion(
                Utils.copy(newLeads),
                MapUtil.map(new Maplet(l, ((SalesRepresentative) Utils.get(leads, l)))));
      }
    }
    if (!(Utils.equals(((SalesRepresentative) Utils.get(leads, lead)), null))) {
      ((SalesRepresentative) Utils.get(leads, lead)).removeLead(lead);
    }

    leads = Utils.copy(newLeads);
  }

  public VDMMap getLeads() {

    return Utils.copy(leads);
  }

  public SalesRepresentative getLeadSR(final Lead lead) {

    return ((SalesRepresentative) Utils.get(leads, lead));
  }

  public String toString() {

    return "CRM{" + "leads := " + Utils.toString(leads) + "}";
  }
}
