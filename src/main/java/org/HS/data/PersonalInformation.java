package org.HS.data;

import java.util.List;

public class PersonalInformation {
    private String name;
    private int phoneNumber;
    private String email;
    private List<String> work;
    private List<String> competences;



    private AddressData address;

    public PersonalInformation(String name, int phoneNumber, String email, AddressData address, List<String> work, List<String> competences) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.work = work;
        this.competences = competences;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getWork() {
        return work;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public AddressData getAddress() {
        return address;
    }
}
