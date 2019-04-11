package ru.stqa.ptf.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.thoughtworks.xstream.XStream;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander.newBuilder().addObject(generator).build().parse(args);
    generator.run();

  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    }  else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    }
    else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("firstname %s", i)).withLastname(String.format("lastname %s", i))
              .withMiddlename(String.format("middlename %s", i)).withNickname(String.format("nickname %s", i))
              .withHomePhone(String.format("11 11 %s", i)).withMobilePhone(String.format("22 22 %s", i)).withAddress(String.format("address %s", i))
              .withEmail1(String.format("email%s@test1.com", i)).withEmail2(String.format("email%s@test2.com", i)));
    }
    return contacts;
  }

  private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;\n", contact.getFirstname(), contact.getLastname(), contact.getMiddlename(),
              contact.getNickname(), contact.getHomephone(), contact.getMobilephone(), contact.getAddress(), contact.getEmail1(), contact.getEmail2()));
    }
    writer.close();
  }
}
