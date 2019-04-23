package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.ContactsInGroupData;
import ru.stqa.ptf.addressbook.model.ContactsInGroups;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemovingFromGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    //если нет групп - то создаём группу
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test88"));
    }
    //если нет контактов - то создаём контакт
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData().withFirstname("contactForRemoving").withPhoto(new File("src/test/resources/BeKind.png")));
    }
    //если нет контактов в группах - добавляем контакт в группу
    if (app.db().contactsInGroups().size() == 0) {
      app.goTo().homePage();
      app.contact().addContactInGroup();
    }
  }

  @Test
  public void testContactRemovingFromGroup() {
    ContactsInGroups before = app.db().contactsInGroups();
    ContactsInGroupData deletedLink = before.iterator().next();
    app.goTo().homePage();
    app.contact().removeContactFromGroup(deletedLink.getContactId(), deletedLink.getGroupId());
    ContactsInGroups after = app.db().contactsInGroups();
    assertThat(after, equalTo(before.without(deletedLink)));
  }
}
