package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditonToGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    //если нет групп - то создаём группу
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    //если нет контактов - то создаём контакт
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData().withFirstname("contactForAdiition").withPhoto(new File("src/test/resources/BeKind.png")));
    } else
    //если все контакты определены в группы - то создаём ещё один котакт
    if (app.db().contactsInGroups().size() == (app.db().contacts().size() * app.db().groups().size())) {
      app.goTo().homePage();
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData().withFirstname("contactForAdiition").withPhoto(new File("src/test/resources/BeKind.png")));
    }
  }

  @Test
  public void testContactAdditionToGroup() {
    ContactsInGroups before = app.db().contactsInGroups();
    ContactsInGroups afterExpected = app.db().contactsInGroups();
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    int contactId = 0;
    int groupId = 0;

    // Ищем Id пару контакт - группа не связанных между собой
    ArrayList<Integer> contactIds = (ArrayList<Integer>) contacts.stream().map((c) -> (c.getId())).collect(Collectors.toList()); // Коллекция Id контактов
    ArrayList<Integer> groupIds = (ArrayList<Integer>) groups.stream().map((g) -> (g.getId())).collect(Collectors.toList()); // Коллекция Id групп
    for (int i = 0; i < contactIds.size(); i++) {
      for (int j = 0; j < groupIds.size(); j++) {
        afterExpected.add(new ContactsInGroupData().withContactId(contactIds.get(i)).withGroupId(groupIds.get(j)));
        // Далее смотрим: если набор пар Id увеличился, значит пара уникальна. Найдены Id для проведения теста.
        if (!afterExpected.equals(before)) {
          contactId = contactIds.get(i);
          groupId = groupIds.get(j);
          i = contactIds.size();
          j = groupIds.size();
        }
      }

    }

    app.goTo().homePage();
    app.contact().addContactInGroupByIds(contactId,groupId);
    ContactsInGroups after = app.db().contactsInGroups();
    assertThat(after, equalTo(afterExpected));
  }
}
