package pl.lukasz.szawronski.mydriverapi.driver;

import pl.lukasz.szawronski.mydriverapi.truck.Truck;

import javax.persistence.*;

//TODO Change boiler plate code to Lombok Annotations!.
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 25)
    private String firstName;

    @Column(nullable = false, length = 25)
    private String lastName;

    @Column(nullable = false, length = 11, unique = true)
    private String pesel;
    //todo good validation

    @Column(nullable = false, length = 9)
    private String phoneNumber;

    @Column(nullable = false, length = 12, name = "date_of_born")
    private String dateOfBorn;
    //todo good validation

    @Column(nullable = false, length = 12, name = "date_of_employment")
    private String dateOfEmployment;
    //todo good validation

    @Column(nullable = false, length = 8, name = "registration_number")
    private String registrationNumber;

    @Column(nullable = false)
    private float fuel;

    @Column(nullable = false)
    private float kilometers;

    @Column(nullable = false)
    private float salary;

    public Driver() {
    }

    public float getConsumption() {
        return consumption;
    }

    private float consumption = showConsumption();

    private float finalSalary = showFinalSalary();

    private float salaryRate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "driver")
    private Truck truck;

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBorn='" + dateOfBorn + '\'' +
                ", dateOfEmployment='" + dateOfEmployment + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", fuel=" + fuel +
                ", kilometers=" + kilometers +
                ", salary=" + salary +
                ", consumption=" + consumption +
                ", finalSalary=" + finalSalary +
                ", salaryRate=" + salaryRate +
                '}';
    }

    public float getSalaryRate() {
        return salaryRate;
    }

    public void setSalaryRate(float salaryRate) {
        this.salaryRate = salaryRate;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public float getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(float finalSalary) {
        this.finalSalary = finalSalary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(String dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public float getKilometers() {
        return kilometers;
    }

    public void setKilometers(float kilometers) {
        this.kilometers = kilometers;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float showFinalSalary(){
        float finalSalary = salary + getKilometers() * salaryRate;
        return finalSalary;
    }
    public float showConsumption(){
        float fuelConsumption = fuel / kilometers * 100;
        return Math.round(fuelConsumption);
    }
}
