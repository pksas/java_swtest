package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationsTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.csv"));
    String line = reader.readLine();
    while (line != null) {
      String[] splt = line.split(";");
      list.add(new Object[] {new ContactData().withFirstname(splt[0]).withLastname(splt[1]).withMiddlename(splt[2])
              .withNickname(splt[3]).withHomePhone(splt[4]).withMobilePhone(splt[5]).withAddress(splt[6]).withEmail1(splt[7]).withEmail2(splt[8])});
      line = reader.readLine();
    }
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