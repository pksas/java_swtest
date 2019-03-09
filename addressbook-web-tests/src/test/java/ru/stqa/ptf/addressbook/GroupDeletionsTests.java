package ru.stqa.ptf.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionsTests extends TestBase{

  @Test
  public void testGroupDeletions() throws Exception {
    gotoGroupPage();
    selectGroup();
    deleyeSelectedGroups();
    returntoGroupPage();
  }

}
