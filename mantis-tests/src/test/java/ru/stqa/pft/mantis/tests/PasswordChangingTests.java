package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangingTests extends TestBase {


  @Test
  public void testPasswordChanging() throws IOException, MessagingException {
    Users users = app.db().users();
    UserData admin = new UserData().withId(1).withUsername("administrator").withEmail("root@localhost");
    users = users.without(admin);
    UserData targetUser = users.iterator().next();
    String password = "password";
    String passwordNew = "password1";
    app.lifeSession().login("administrator", "root1");
    app.usermanager().resetPasswordById(targetUser.getId());
    app.james().drainEmail(targetUser.getUsername(), password);
    List<MailMessage> mailMessages = app.james().waitForMail(targetUser.getUsername(), password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, targetUser.getEmail());
    app.usermanager().confirmNewPassword(confirmationLink, passwordNew);
    assertTrue(app.newSession().login(targetUser.getUsername(), passwordNew));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
