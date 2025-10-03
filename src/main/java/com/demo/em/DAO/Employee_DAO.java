package com.demo.em.DAO;

import com.demo.em.DTO.Employee_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Employee_DAO{
    /*
    Database SQL:
    CREATE TABLE employees (
        id INT AUTO_INCREMENT PRIMARY KEY,    -- AUTO_INCREMENT PRIMARY KEY: tạo Id khi chèn 1 hàng - row vào database
        first_name VARCHAR(50) NOT NULL,      -- first name
        last_name VARCHAR(50) NOT NULL,       -- last name
        age TINYINT UNSIGNED NOT NULL,        -- 0-255
        gender CHAR(1) NOT NULL,              -- 'M' or 'F'
        salary INT UNSIGNED NOT NULL,         -- 0-4,200,000,000
        job VARCHAR(50) NOT NULL,             -- job position
        phone VARCHAR(10) NOT NULL            -- 8-10 digits
    );
    */
    

    public boolean AddEmployee(Employee_DTO employee) {
        String sql = "INSERT INTO employees (first_name, last_name, age, gender, salary, job, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // Add thông tin Employee từ user input --> Dùng Prepared statement
        
        try (Connection connection = DatabaseConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
              
            // setString(int parameterIndex, String x) ; int parameterIndex: Vị trí để nhập dữ liệu
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setInt(5, employee.getSalary());
            preparedStatement.setString(6, employee.getJobPosition());
            preparedStatement.setString(7, employee.getPhoneNumbers());
            
            int rowsInserted = preparedStatement.executeUpdate(); // Hiện số dòng đã chèn vào database
            // Nếu như số dòng chèn vào > 0 --> Chèn data vào database thành công
            // Nếu số dòng chèn vào = 0 --> Chèn data vào database thất bại
            
            if (rowsInserted > 0) { 
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){ // getGeneratedKeys trích xuất Id được tạo tự động khi insert employee vào database
                    if (resultSet.next() == true) // true khi có dòng kế tiếp (dòng id được tạo tự động), không trả về false
                        employee.setId(resultSet.getInt(1)); // resultSet.getInt(1) trich xuất giá trị integer tại cột 1 (cột Id)
                        // Gán Id trích xuất đc vào object employee
                }
            }
            
            if (rowsInserted > 0) // Chèn vào thành công thì return true
                return true;
            else // Chèn vào thất bại thì return false
                return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Trích xuất toàn bộ employee từ database
    public ArrayList<Employee_DTO> GetAllEmployees() {
        ArrayList<Employee_DTO> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM employees";    
        // Truy cứu không cần user input --> dùng Statement
        
        try (Connection connect = DatabaseConnect.getConnection();
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            
            
            while (resultSet.next()){ // Loop qua từng dòng employee, trích xuất thông tin employee trong dòng đó
                Employee_DTO employee = new Employee_DTO();
                employee.setId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setGender(resultSet.getString("gender"));
                employee.setSalary(resultSet.getInt("salary"));
                employee.setJobPosition(resultSet.getString("job"));
                employee.setPhoneNumbers(resultSet.getString("phone"));
                
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return employeeList;
    }
    
    // Trích xuất employee bằng ID
    public Employee_DTO GetEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        // Biến id có thể chứa sql injection --> sử dụng prepared statemenet
        
        Employee_DTO employee = new Employee_DTO();
        
        try (Connection connection = DatabaseConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                employee = new Employee_DTO();
                employee.setId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setGender(resultSet.getString("gender"));
                employee.setSalary(resultSet.getInt("salary"));
                employee.setJobPosition(resultSet.getString("job"));
                employee.setPhoneNumbers(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return employee;
    }
    
    // Cập nhật thông tin employee lên database sau khi sửa đổi
    public boolean UpdateEmployee(Employee_DTO employee) {   
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, age = ?, gender = ?, salary = ?, job = ?, phone = ? WHERE id = ?";
        // Thông tin sửa đổi được lấy từ user input --> dùng prepared statement
        
        try (Connection connection = DatabaseConnect.getConnection();
            PreparedStatement reparedStatement = connection.prepareStatement(sql)) {
            
            reparedStatement.setString(1, employee.getFirstName());
            reparedStatement.setString(2, employee.getLastName());
            reparedStatement.setInt(3, employee.getAge());
            reparedStatement.setString(4, employee.getGender());
            reparedStatement.setInt(5, employee.getSalary());
            reparedStatement.setString(6, employee.getJobPosition());
            reparedStatement.setString(7, employee.getPhoneNumbers());
            reparedStatement.setInt(8, employee.getId());
            
            int rowsUpdated = reparedStatement.executeUpdate();
            
            if(rowsUpdated > 0)
                return true;
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Xoá employee khỏi database
    public boolean DeleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        // Biến id có thể chứa sql injection --> sử dụng prepared statemenet
        
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement data = connection.prepareStatement(sql)) {
            
            data.setInt(1, id);
            int rowsDeleted = data.executeUpdate();
            return rowsDeleted > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
