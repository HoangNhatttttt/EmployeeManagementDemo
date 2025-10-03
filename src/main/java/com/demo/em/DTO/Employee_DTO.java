package com.demo.em.DTO;
public class Employee_DTO {
    private int id, age, salary;
    private String firstName, lastName, jobPosition, gender, phoneNumbers;
    
    // Constructer rỗng
    public Employee_DTO(){ }
    
    // Constructer dùng để thêm Eployee, không có ID, ID quản lý tự động
    public Employee_DTO(String firstName, String lastName, int age, String gender, int salary, String jobPosition, String phoneNumbers){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.jobPosition = jobPosition;
        this.phoneNumbers = phoneNumbers;
    }
    
    // Constructer dùng để lấy thông tin từ database, có ID
    public Employee_DTO(int id, String firstName, String lastName, int age, String gender, int salary, String jobPosition, String phoneNumbers){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.jobPosition = jobPosition;
        this.phoneNumbers = phoneNumbers;
    }
    
    // Setter
    public void setId(int id){ this.id = id; }
    public void setAge(int age){ this.age = age; }
    public void setSalary(int salary){ this.salary = salary; }
    public void setFirstName(String firstName){ this.firstName = firstName; }
    public void setLastName(String lastName){ this.lastName = lastName; }
    public void setJobPosition(String jobPosition){ this.jobPosition = jobPosition; }
    public void setGender(String gender){ this.gender = gender; }
    public void setPhoneNumbers(String phoneNumbers){ this.phoneNumbers = phoneNumbers; }
    
    // Getter
    public int getId(){ return this.id; }
    public int getAge(){ return this.age; }
    public int getSalary(){ return this.salary; }
    public String getFirstName(){ return this.firstName; }
    public String getLastName(){ return this.firstName; }
    public String getJobPosition(){ return this.jobPosition; }
    public String getGender(){ return this.gender; }
    public String getPhoneNumbers(){ return this.phoneNumbers; }
    
    // Hàm dùng cho debug
    public void PrintInformation(){
        System.out.println("Employee Id: " + getId());
        System.out.println("Employee Age: " + getAge());
        System.out.println("Employee Salary: " + getSalary());
        System.out.println("Employee First name: " + getFirstName());
        System.out.println("Employee Last name: " + getLastName());
        System.out.println("Employee Job position: " + getJobPosition());
        System.out.println("Employee Gender: " + getGender());
        System.out.println("Employee Phone numbers: " + getPhoneNumbers());
    }
}
