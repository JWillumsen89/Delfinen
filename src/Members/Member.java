package Members;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member {

    private String name;
    private String email;
    private String memberID;
 //   private LocalDate dateOfBirth;

    private int age;
    private int phoneNumber;

    private boolean active;
    private boolean paid;

    private ArrayList<Member>members;
    private ArrayList<Member>restance;

    public Member (String name, int age, int phoneNumber, String email, String memberID, boolean active, boolean paid ) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.memberID = memberID;
        this.active = active;
        this.paid = paid;

    }

    //-----------------Setter-----------------

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }


    //-----------------Getter-----------------

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public String getMemberID() {
        return getMemberID();
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean isPaid() {
        return this.paid;
    }



}
