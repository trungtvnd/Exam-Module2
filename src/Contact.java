import java.time.LocalDate;

public class Contact {
    private String phoneNumber;
    private String group;
    private String name;
    private String gender;
    private String Address;
    private int birth;
    private String email;

    public Contact(String phoneNumber,String group, String name, String gender, String address, int birth, String email) {
        this.phoneNumber = phoneNumber;
        this.group = group;
        this.name = name;
        this.gender = gender;
        this.Address = address;
        this.birth = birth;
        this.email = email;
    }

    @Override
    public String toString() {
        return (String.format("%-20S%-20S%-20s%-20S%-20S%-20d%-20S\n", getPhoneNumber(), getGroup(), getName(), getGender(), getAddress(), getBirth(), getEmail())) ;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
