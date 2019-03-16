package ru.stqa.ptf.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String address;
  private final String homephonenumber;
  private final String email;
  private String group;

  public ContactData(String firstname, String middlename, String lastname, String nickname, String address, String homephonenumber, String email, String group) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.homephonenumber = homephonenumber;
    this.email = email;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephonenumber() {
    return homephonenumber;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
