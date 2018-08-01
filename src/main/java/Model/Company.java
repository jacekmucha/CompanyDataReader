package Model;

import javafx.beans.property.SimpleStringProperty;
import java.util.Objects;

public class Company {

    private SimpleStringProperty name;
    private SimpleStringProperty address;
    private SimpleStringProperty website;
    private SimpleStringProperty contactPerson;
    private SimpleStringProperty email;
    private SimpleStringProperty phoneNumber;


    public Company(String name, String address, String website, String contactPerson, String email, String phonenumber){
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.website = new SimpleStringProperty(website);
        this.contactPerson = new SimpleStringProperty(contactPerson);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phonenumber);
    }


    public Company() {
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getWebsite() {
        return website.get();
    }

    public SimpleStringProperty websiteProperty() {
        return website;
    }

    public void setWebsite(String website) {
        this.website.set(website);
    }

    public String getContactPerson() {
        return contactPerson.get();
    }

    public SimpleStringProperty contactPersonProperty() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson.set(contactPerson);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
                Objects.equals(address, company.address) &&
                Objects.equals(website, company.website) &&
                Objects.equals(contactPerson, company.contactPerson) &&
                Objects.equals(email, company.email) &&
                Objects.equals(phoneNumber, company.phoneNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, address, website, contactPerson, email, phoneNumber);
    }

    @Override
    public String toString() {
        return  name + "\n" +
                address + "\n" +
                website + "\n" +
                contactPerson + "\n" +
                email + "\n" +
                phoneNumber+ "\n\n";
    }
}
