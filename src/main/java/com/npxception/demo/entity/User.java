package com.npxception.demo.entity;


public class User {
  private int userid;
  private String firstName;
  private String lastName;
  private String email;
  private int age;
  private String gender;
  private String country;
  private String city;
  private String password;
  private String role;

  public User(int userid, String firstName, String lastName, String email,
              int age, String gender, String country, String city,
              String password, String role) {
    this.userid = userid;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
    this.gender = gender;
    this.country = country;
    this.city = city;
    this.password = password;
    this.role = "USER";
    // TODO: This is a followup -> WTF? We set role three times now. Thats not a fix...
    // TODO: Why isn't this set by default to "USER"?
  }

  public User(){}

  public int getId() {
    return userid;
  }

  public void setId(int userid) {
    this.userid = userid;
  }

  public String getFirstName() {
    return firstName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public String getPassword(){
    return this.password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role){
    this.role = role;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
        "userid=" + userid +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", age=" + age +
        ", gender='" + gender + '\'' +
        ", country='" + country + '\'' +
        ", city='" + city + '\'' +
        ", password='" + password + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (userid != user.userid) return false;
    if (age != user.age) return false;
    if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null)
      return false;
    if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
    if (email != null ? !email.equals(user.email) : user.email != null) return false;
    if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
    if (country != null ? !country.equals(user.country) : user.country != null) return false;
    if (city != null ? !city.equals(user.city) : user.city != null) return false;
    return password != null ? password.equals(user.password) : user.password == null;
  }

  @Override
  public int hashCode() {
    int result = userid;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + age;
    result = 31 * result + (gender != null ? gender.hashCode() : 0);
    result = 31 * result + (country != null ? country.hashCode() : 0);
    result = 31 * result + (city != null ? city.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }

}
