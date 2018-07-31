package Model;


import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter

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
                phoneNumber;
    }
}
