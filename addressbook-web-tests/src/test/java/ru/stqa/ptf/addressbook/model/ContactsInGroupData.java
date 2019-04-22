package ru.stqa.ptf.addressbook.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address_in_groups")
@IdClass(LinksId.class)
public class ContactsInGroupData {

  public ContactsInGroupData() {
  }

  public ContactsInGroupData(LinksId linksId) {
    contactId = linksId.getContactId();
    groupId = linksId.getGroupId();
  }

  @Id
  @AttributeOverrides({
          @AttributeOverride(name = "contactId", column = @Column(name = "id")),
          @AttributeOverride(name = "groupId", column = @Column(name = "group_id"))
  })

  @Column(name = "id")
  private int contactId;

  @Column(name = "group_id")
  private int groupId;

  @Override
  public String toString() {
    return "ContactsInGroupData{" +
            "contactId=" + contactId +
            ", groupId=" + groupId +
            '}';
  }

  public int getContactId() {
    return contactId;
  }

  public int getGroupId() {
    return groupId;
  }

  public ContactsInGroupData withContactId(int contactId) {
    this.contactId = contactId;
    return this;
  }

  public ContactsInGroupData withGroupId(int groupId) {
    this.groupId = groupId;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactsInGroupData that = (ContactsInGroupData) o;
    return contactId == that.contactId &&
            groupId == that.groupId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, groupId);
  }
}
