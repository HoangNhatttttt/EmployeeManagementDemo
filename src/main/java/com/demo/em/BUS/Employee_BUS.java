package com.demo.em.BUS;
import com.demo.em.DTO.Employee_DTO;
import com.demo.em.DAO.Employee_DAO;
import java.util.ArrayList;

public class Employee_BUS{
    private Employee_DAO employeeDAO = new Employee_DAO();
    
    
    public boolean AddEmployee(Employee_DTO employee){
          
        return(employeeDAO.AddEmployee(employee)); // Nếu thành công thì trả về True, thất bại thì trả về False
    }
        
    // Trích xuất toàn bộ employee từ database
    public ArrayList<Employee_DTO> GetAllEmployees() {
        return employeeDAO.GetAllEmployees();
    }
    
    // Trích xuất employee thông qua ID
    public Employee_DTO GetEmployeeById(int id) {
        return employeeDAO.GetEmployeeById(id);
    }
    
    // Cập nhật thông tin Employee lên database sau khi sửa đổi
    public boolean UpdateEmployee(Employee_DTO employee) {
        // Cần add validate
        return employeeDAO.UpdateEmployee(employee);
    }
    
    // Xoá employee khỏi database bằng id
    public boolean DeleteEmployee(int id) {
        return employeeDAO.DeleteEmployee(id);
    }
}
