package DataRegistration.src.pkg1;
import java.time.LocalDate;

public class UserInfo {
    String fName;
    String lName;
    LocalDate DOB;
    String gender;
    String email;
    LocalDate timeStamp;
    UserInfo(String fName, String lName, LocalDate DOB ,String gender, String email, LocalDate timeStamp){
        this.fName = fName;
        this.lName = lName;
        this.DOB = DOB;
        this.gender = gender;
        this.email = email;
        this.timeStamp = timeStamp;



    }

    UserInfo(){
        
    }


    //TODO: Age of Race(AKA: How old they will be when the race starts)
    //Time of race - Date of birth = Age of Race


    public String getfName() {
        return fName;
    }


    public String getlName() {
        return lName;
    }


    public LocalDate getDOB() {
        return DOB;
    }


    public String getGender() {
        return gender;
    }


    public String getEmail() {
        return email;
    }


    public LocalDate getTimeStamp() {
        return timeStamp;
    }


    public void setfName(String fName) {
        this.fName = fName;
    }


    public void setlName(String lName) {
        this.lName = lName;
    }


    public void setDOB(LocalDate dOB) {
        DOB = dOB;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    

}
