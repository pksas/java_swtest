package ru.stqa.ptf.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ContactsInGroups extends ForwardingSet<ContactsInGroupData> {

  private Set<ContactsInGroupData> delegate;

  public ContactsInGroups(ContactsInGroups contactsInGroups) {
    this.delegate = new HashSet<ContactsInGroupData>(contactsInGroups.delegate);
  }

  public ContactsInGroups() {
    this.delegate = new HashSet<ContactsInGroupData>();
  }

  public ContactsInGroups(Collection<ContactsInGroupData> contactsInGroups) {
    this.delegate = new HashSet<ContactsInGroupData>(contactsInGroups);
  }

  public ContactsInGroups withAdded(ContactsInGroupData contactsInGroup) {
    ContactsInGroups contactsInGroups = new ContactsInGroups(this);
    contactsInGroups.add(contactsInGroup);
    return contactsInGroups;
  }

  public ContactsInGroups without(ContactsInGroupData contactsInGroup) {
    ContactsInGroups contactsInGroups = new ContactsInGroups(this);
    contactsInGroups.remove(contactsInGroup);
    return contactsInGroups;
  }

  @Override
  protected Set<ContactsInGroupData> delegate() {
    return delegate;
  }
}
