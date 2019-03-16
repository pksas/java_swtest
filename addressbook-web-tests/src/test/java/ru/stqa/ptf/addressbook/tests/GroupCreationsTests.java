package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;

public class GroupCreationsTests extends TestBase {

  @Test
  public void testGroupCreations() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
  }
}
