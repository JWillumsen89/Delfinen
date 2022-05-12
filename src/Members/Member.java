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


    public  Member (String name, int age, int phoneNumber, String email, String memberID, boolean active, boolean paid ) { //TODO: ændret til void, for at kunne estanchere den i payments
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.memberID = memberID;
        this.active = active;
        this.paid = paid;

    }
    public Member(){

    }


    //-----------------Setter-----------------

    public void setActive(boolean active) {
        this.active = active;

    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    public void setAge(int age){
        this.age = age;
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
        return memberID;
    }

    public boolean isActive() {
        active = false;
        return false;
    }

    public boolean isPaid() {
        return this.paid;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", memberID='" + memberID + '\'' +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", active=" + active +
                ", paid=" + paid +
                '}';
    }
}
