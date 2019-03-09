package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionsTests extends TestBase {

  @Test
  public void testGroupDeletions() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleyeSelectedGroups();
    app.getGroupHelper().returntoGroupPage();
  }

}
