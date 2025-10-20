package DataRegistration.src.pkg1;
import java.time.LocalDate;
import java.time.Period;

public class UserInfo {
    String fName;
    String lName;
    LocalDate DOB;
    String gender;
    String email;
    LocalDate regDate;
    int ageOnRaceDay;
    String race;
    LocalDate raceDay;


    

    UserInfo(String fName, String lName, LocalDate DOB ,String gender, String email, LocalDate registrationDate, String raceType, LocalDate raceDay){
        this.fName = fName;
        this.lName = lName;
        this.DOB = DOB;
        this.gender = gender;
        this.email = email;
        this.regDate = registrationDate;
        this.race = raceType;
        this.ageOnRaceDay = AgeOnRaceDay();
        this.raceDay = raceDay(raceType);



    }

    
    UserInfo(){
        
    }
    //TODO: Age of Race(AKA: How old they will be when the race starts)
    int AgeOnRaceDay(){
        return Period.between(raceDay, DOB).getYears();

    }
    public int getAgeOnRaceDay() {
        return ageOnRaceDay;
    }

    public LocalDate raceDay(String raceType){
        LocalDate saturday = LocalDate.of(2025,5,2);
        LocalDate sunday = LocalDate.of(2025,5,3);
        if(raceType.equals("5k") || raceType.equals("10k")){
            return saturday;

        }else{
            return sunday;

        }


    }

    //Time of race - Date of birth = Age of Race

    
    public String getRaceType(){
        return race;
    }

    public void setRaceType(String race){
        this.race = race;

    }


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


    public LocalDate getRegDate() {
        return regDate;
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


    public void setTimeStamp(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString(){
        return "\nUser Info :" +
                "\nFirstName : " +getfName() +
                "\nLastName : " + getlName() +
                "\nDOB : " + getDOB() +
                "\nGender : " + getEmail() +
                "\nEmail Address : " + getEmail() +
                "\nRegister Timestamps : " + getRegDate() +
                "\nType of Race : " + getRaceType() +
                "\nAge of Race : " + getAgeOnRaceDay();
                

    } 


}
