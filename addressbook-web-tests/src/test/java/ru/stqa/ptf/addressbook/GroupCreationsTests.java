package ru.stqa.ptf.addressbook;

import org.testng.annotations.Test;

public class GroupCreationsTests extends TestBase{

  @Test
  public void testGroupCreations() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    returntoGroupPage();
  }
}
