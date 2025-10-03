package com.demo.em.BUS;
import com.demo.em.DAO.Employee_DAO;
import com.demo.em.DTO.Employee_DTO;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Validates_BUS{
    Employee_DTO employee = new Employee_DTO();
    
    public String CleanString(String text){
        return text.trim().replaceAll("\\s+", " ");
    }
    
    public boolean ValidateIsEmpty(String text){
        if(text.isBlank() || text.isEmpty() || text == null)
            return true;
        return false;
    }
    
    public boolean ValidateIsInteger(String text){        
        try {
            int age = Integer.parseInt(text);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean ValidateHasSpecialCharacter(String text){
        // Nếu không matches --> Có special character --> Return true
        // Có mataches --> Không có special chacacter --> Return false
        return !text.matches("[a-zA-Z0-9 ]+"); 
    }
    
    public boolean ValidateIsStartWithWhitespace(String text){
        if(text.startsWith(" "))
            return true;
        return false;               
    }
    
    public String ValidateFirstName(JTextField firstNameField){
        String firstName = firstNameField.getText();
        
        if(ValidateIsEmpty(firstName) == true)
            return "First name cannot be empty.";
        
        if(ValidateHasSpecialCharacter(firstName) == true)
            return "First name cannot contain special character.";
        
        if(ValidateIsStartWithWhitespace(firstName) == true)
            return "First name cannot start with whitespace.";
        
        employee.setFirstName(CleanString(firstName));
        
        return null;
    }
    
    public String ValidateLastName(JTextField lastNameField){
        String lastName = lastNameField.getText();
        
        if(ValidateIsEmpty(lastName) == true)
            return "Last name cannot be empty.";
        
        if(ValidateHasSpecialCharacter(lastName) == true)
            return "Last name cannot contain special character.";
        
        if(ValidateIsStartWithWhitespace(lastName) == true)
            return "Last name cannot start with whitespace.";
        
        employee.setLastName(CleanString(lastName));
        
        return null;
    }
    
    public String validateAge(JTextField ageField){
        String ageText = ageField.getText();
        
        // Check rỗng
        if(ValidateIsEmpty(ageText) == true)
            return "Age cannot be empty.";
        
        // Check bắt đầu bằng khoảng trắng 
        if(ValidateIsStartWithWhitespace(ageText) == true)
            return "Age cannot be started with whitespace.";

        // Check xem có phải là số
        // Nếu là số --> Không chứa kí tự đặc biệt --> Không cần check thêm
        if(ValidateIsInteger(ageText) == false)
            return "Age must be numbers.";
        
        // Check độ lớn
        int age = Integer.parseInt(ageText);
        
        if(age < 0)
            return "Employee need to be born first.";
        else if (age >= 0 && age < 18)
            return "Employee too young.";
        else if (age >= 70 && age < 150)
            return "Employee too old.";
        else if (age >= 150)
            return "Is employee a vampire ?"; 
        
        //Nếu tất cả validate thành công thì nhận dữ liệu
        employee.setAge(age);
        
        return null;
    }
    
    public String ValidateGender(ButtonGroup genderButtonGroup){
        if(genderButtonGroup.getSelection() == null)
            return "Gender cannot be empty.";
        
        // Trích xuất giá trị từ JRadioButton được chọn: Male ; Female
        String gender = genderButtonGroup.getSelection().getActionCommand();
        employee.setGender(gender);
        
        return null;
    }
    
    public String ValidateSalary(JTextField salaryField){
        String salaryText = salaryField.getText();
        
        if(ValidateIsEmpty(salaryText) == true)
            return "Salary cannot be empty.";
        
        if(ValidateIsInteger(salaryText) == false)
            return "Salary must be numbers.";
        
        if(ValidateIsStartWithWhitespace(salaryText) == true)
            return "Salary cannot be start with white space.";
        
        int salary = Integer.parseInt(salaryText);
        
        if(salary <= 0)
            return "Salary must be > 0";
        
        employee.setSalary(salary);
    
        return null;
    }
    
    public String ValidateJobPosition(JTextField jobPositionField){
        String jobPosition = jobPositionField.getText();
        
        if(ValidateIsEmpty(jobPosition) == true)
            return "Job position cannot be empty.";
       
        if(ValidateIsStartWithWhitespace(jobPosition) == true)
            return "Job position cannot be start with whitespace.";
        
        if(ValidateHasSpecialCharacter(jobPosition) == true)
            return "Job position cannot contain special character.";
        
        employee.setJobPosition(jobPosition);
        
        return null;
    }
    
    public String ValidatePhoneNumbers(JTextField phoneNumbersField){
        String phoneNumbers = phoneNumbersField.getText();
        
        if(ValidateIsEmpty(phoneNumbers) == true)
            return "Phone numbers cannot be empty.";
        
        if(!phoneNumbers.startsWith("0"))
            return "Phone numbers must be start with 0.";
        
        if(phoneNumbers.length() < 8 || phoneNumbers.length() > 10)
            return "Phone numbers length must be 8 - 10.";
        
        if(ValidateIsStartWithWhitespace(phoneNumbers) == true)
            return "Phone numbers cannot be start with whitespace.";
        
        if(ValidateIsInteger(phoneNumbers) == false)
            return "Phone numbers must be numbers.";
        
        employee.setPhoneNumbers(phoneNumbers);
        
        return null;
    }
    
    public String ValidateEmployee(JTextField firstName, JTextField lastName, JTextField age, ButtonGroup gender, JTextField salary, JTextField jobPosition, JTextField phoneNumbers){
        
        String error = null; // Nếu có error thì error sẽ gán giá trị khác null, không thì được gán giá trị null
        
        error = ValidateFirstName(firstName);
        if(error != null) {
            firstName.requestFocus();
            return error;
        } 
        
        error = ValidateLastName(lastName);
        if(error != null){
            lastName.requestFocus();
            return error;
        }
        
        
        error = validateAge(age);
        if(error != null){
            age.requestFocus();
            return error;
        }
        
        error = ValidateGender(gender);
        if(error != null) return error;

        
        error = ValidateSalary(salary);
        if(error != null){
            salary.requestFocus();
            return error;
        }
        
        error = ValidateJobPosition(jobPosition);
        if(error != null){
            jobPosition.requestFocus();
            return error;
        }
        
        error = ValidatePhoneNumbers(phoneNumbers);
        if(error != null){
            phoneNumbers.requestFocus();
            return error;
        }
             
        return error; // Không có lỗi --> error được gán giá trị null
    }
    
    public Employee_DTO ReturnEmployee(){
        return employee;
    }
    
    public void ResetEmployee(){
        employee = null;
    }
}
