package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().addNewContactPage();
      app.contact().create
              (new ContactData().
                      withFirstname("forModeFirstName").
                      withMiddlename("forModeMiddleName").
                      withLastname("forModeLastName").
                      withNickname("forModeNickName").
                      withAddress("forModeAddress").
                      withHomePhone("999 999").
                      withMobilePhone("888 888").
                      withWorkPhone("777 777").
                      withSecondaryPhone("666 666").
                      withEmail1("formode1@mail.ru").
                      withEmail2("formode2@mail.ru").
                      withEmail3("formode3@mail.ru").
                      withPhoto(new File("src/test/resources/BeKind.png")));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).
            withFirstname("modeFirstName").
            withMiddlename("modeMiddleName").
            withLastname("modeLastName").
            withNickname("modeNickName").
            withAddress("modeAddress").
            withHomePhone("9 9 9").
            withMobilePhone("8 8 8").
            withWorkPhone("7 7 7").
            withSecondaryPhone("6 6 6").
            withEmail1("mode1@mail.ru").
            withEmail2("mode2@mail.ru").
            withEmail3("mode3@mail.ru").
            withPhoto(new File("src/test/resources/BeKind.png"));
    app.contact().modify(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.without(modifiedContact).withAdded(contact)));
    verifyContactsInUI();
  }

}
