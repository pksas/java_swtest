package ru.stqa.ptf.addressbook.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LinksId implements Serializable {

  private int contactId;

  private int groupId;

  public LinksId(int contactId, int grooupId) {
    this.contactId = contactId;
    this.groupId = grooupId;
  }

  public LinksId() {}

  public int getContactId() {
    return contactId;
  }

  public void setContactId(int contactId) {
    this.contactId = contactId;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }
}
