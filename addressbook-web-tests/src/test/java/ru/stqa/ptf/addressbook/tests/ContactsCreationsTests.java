package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationsTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withFirstname("firstname 1").withLastname("lastname 1").withMiddlename("middlename 1")
            .withNickname("nickname 1").withHomePhone("11 01").withMobilePhone("22 01").withEmail1("1_1@m.ru").withEmail2("2_1@m.ru").withAddress("address 1")
            .withPhoto( new File("src/test/resources/BeKind.png"))});
    list.add(new Object[] {new ContactData().withFirstname("firstname 2").withLastname("lastname 2").withMiddlename("middlename 2")
            .withNickname("nickname 2").withHomePhone("11 02").withMobilePhone("22 02").withEmail1("1_2@m.ru").withEmail2("2_2@m.ru").withAddress("address 2")});
    list.add(new Object[] {new ContactData().withFirstname("firstname 3").withLastname("lastname 3").withMiddlename("middlename 3")
            .withNickname("nickname 3").withHomePhone("11 03").withMobilePhone("22 03").withEmail1("1_3@m.ru").withEmail2("2_3@m.ru").withAddress("address 3")});
    return list.iterator();
  }

  @Test (dataProvider = "validContacts")
  public void testContactsCreations(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewContactPage();
    app.contact().create(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactsCreations() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact =
            new ContactData().
                    withFirstname("test11'").
                    withMiddlename("test2").
                    withLastname("Ivanovich").
                    withNickname("vano").
                    withAddress("Ivanovo").
                    withallphonenumbers("+7(111)2223344").
                    withAllEmails("vano@mail.ru").
                    withGroup("test1");
    app.goTo().addNewContactPage();
    app.contact().create(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

}