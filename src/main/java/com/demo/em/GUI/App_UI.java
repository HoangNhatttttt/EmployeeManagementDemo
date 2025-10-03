package com.demo.em.GUI;
import com.demo.em.BUS.Employee_BUS;
import javax.swing.*;
import com.demo.em.BUS.Validates_BUS;
import com.demo.em.DAO.Employee_DAO;
import com.demo.em.DTO.Employee_DTO;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class App_UI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(App_UI.class.getName());
    
    public App_UI() {
        initComponents();
        
        // Chọn cái nào thì sẽ trả về giá trị đó, trích xuất thông qua ButtonGroup
        femaleRadioButton.setActionCommand("F");
        maleRadioButton.setActionCommand("M");
        
        // JTable cho Employee list
        InitEmployeeTable();
        UpdateEmployeeTable();
    }
    // Tạo default table 
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    public void InitEmployeeTable(){
        // Trỏ default table tới employee table
        employeeTable.setModel(defaultTableModel);
        
        // Thêm cột cho default table --> employee table cũng thêm cột
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("First name");
        defaultTableModel.addColumn("Last name");
        defaultTableModel.addColumn("Age");
        defaultTableModel.addColumn("Gender");
        defaultTableModel.addColumn("Salary");
        defaultTableModel.addColumn("Job");
        defaultTableModel.addColumn("Phone");
        
        // Chỉ được chọn 1 dòng/employee trong 1 lúc
        ListSelectionModel listSelectionModel = employeeTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //Mỗi lần chọn 1 employee sẽ hiện thông tin employee lên information panel
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                // Trích xuất vị trí ô dòng/employee được chọn
                
                int rowIndex = employeeTable.getSelectedRow();                
                if(rowIndex != -1){ //TH database không có dữ liệu thì không cần trích xuất dữ liệu từ JTable và không update Information Panel
                    
                    // Trích xuất dữ liệu các cột của dòng được chọn
                    String id = employeeTable.getValueAt(rowIndex, 0).toString(); 
                    String firstName = employeeTable.getValueAt(rowIndex, 1).toString();
                    String lastName = employeeTable.getValueAt(rowIndex, 2).toString();
                    String age = employeeTable.getValueAt(rowIndex, 3).toString();
                    String gender = employeeTable.getValueAt(rowIndex, 4).toString();
                    String salary = employeeTable.getValueAt(rowIndex, 5).toString();
                    String jobPosition = employeeTable.getValueAt(rowIndex, 6).toString();
                    String phoneNumbers = employeeTable.getValueAt(rowIndex, 7).toString();

                    // Hiển thị dữ liệu lên information panel
                    idTextField.setText(id);
                    firstNameTextField.setText(firstName);
                    lastNameTextField.setText(lastName);
                    ageTextField.setText(age);
                    salaryTextField.setText(salary);
                    jobPositionTextField.setText(jobPosition);
                    phoneNumbersTextField.setText(phoneNumbers);

                    // Kiểm tra giới tính rồi chọn tương ứng
                    if(gender.equals("M"))
                        maleRadioButton.setSelected(true);
                    else
                        femaleRadioButton.setSelected(true);
                }  
            }
        });
    }
    
    public void ClearTextFields(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) 
                ((JTextField) c).setText("");

            else if (c instanceof Container) 
                ClearTextFields((Container) c);      
        }
    }
    
    private void UpdateEmployeeTable(){
        try {
            // Trích xuất toàn bộ employee từ database
            Employee_BUS employee_BUS = new Employee_BUS();
            ArrayList<Employee_DTO> employeeList = employee_BUS.GetAllEmployees();
            
            // Hàm sẽ lấy toàn bộ dữ liệu từ database (bao gồm cả dữ liệu có sẵn trong JTable) --> Làm sạch JTable rồi add lại toàn bộ dữ liệu
            defaultTableModel.setRowCount(0); // Xoá toàn bộ dòng trong JTable 
            
            System.out.println(employeeList.size());
            
            for (Employee_DTO employee : employeeList) {
                defaultTableModel.addRow(new Object[]{
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getAge(),
                    employee.getGender(),
                    employee.getSalary(),
                    employee.getJobPosition(),
                    employee.getPhoneNumbers()
                });
            } 
        }
        
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading employee data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderButtonGroup = new javax.swing.ButtonGroup();
        informationPanel = new javax.swing.JPanel();
        idLabel = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        ageLabel = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        salaryLabel = new javax.swing.JLabel();
        jobPositionLabel = new javax.swing.JLabel();
        phoneNumberLabel = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        firstNameTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        ageTextField = new javax.swing.JTextField();
        maleRadioButton = new javax.swing.JRadioButton();
        femaleRadioButton = new javax.swing.JRadioButton();
        salaryTextField = new javax.swing.JTextField();
        jobPositionTextField = new javax.swing.JTextField();
        phoneNumbersTextField = new javax.swing.JTextField();
        employeeList = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        utilitiesPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        editButton1 = new javax.swing.JButton();
        Label1 = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));

        informationPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        idLabel.setText("ID");

        firstNameLabel.setText("First name:");

        lastNameLabel.setText("Last name:");

        ageLabel.setText("Age:");

        genderLabel.setText("Gender");

        salaryLabel.setText("Salary:");

        jobPositionLabel.setText("Job position:");

        phoneNumberLabel.setText("Phone numbers:");

        idTextField.setEditable(false);

        genderButtonGroup.add(maleRadioButton);
        maleRadioButton.setText("Male");

        genderButtonGroup.add(femaleRadioButton);
        femaleRadioButton.setText("Female");

        javax.swing.GroupLayout informationPanelLayout = new javax.swing.GroupLayout(informationPanel);
        informationPanel.setLayout(informationPanelLayout);
        informationPanelLayout.setHorizontalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstNameLabel)
                    .addComponent(idLabel)
                    .addComponent(lastNameLabel)
                    .addComponent(ageLabel)
                    .addComponent(genderLabel)
                    .addComponent(salaryLabel)
                    .addComponent(jobPositionLabel)
                    .addComponent(phoneNumberLabel))
                .addGap(35, 35, 35)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(salaryTextField)
                    .addComponent(firstNameTextField)
                    .addComponent(lastNameTextField)
                    .addComponent(ageTextField)
                    .addGroup(informationPanelLayout.createSequentialGroup()
                        .addComponent(maleRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                        .addComponent(femaleRadioButton))
                    .addComponent(jobPositionTextField)
                    .addComponent(phoneNumbersTextField)
                    .addComponent(idTextField))
                .addContainerGap(266, Short.MAX_VALUE))
        );
        informationPanelLayout.setVerticalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ageLabel)
                    .addComponent(ageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maleRadioButton)
                    .addComponent(genderLabel)
                    .addComponent(femaleRadioButton))
                .addGap(15, 15, 15)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salaryLabel)
                    .addComponent(salaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jobPositionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jobPositionLabel))
                .addGap(15, 15, 15)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNumbersTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberLabel))
                .addGap(30, 30, 30))
        );

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        employeeList.setViewportView(employeeTable);

        utilitiesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmployeeButton(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateJTableButton(evt);
            }
        });

        newButton.setText("New");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newEmployeeButton(evt);
            }
        });

        editButton1.setText("Edit");
        editButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton(evt);
            }
        });

        javax.swing.GroupLayout utilitiesPanelLayout = new javax.swing.GroupLayout(utilitiesPanel);
        utilitiesPanel.setLayout(utilitiesPanelLayout);
        utilitiesPanelLayout.setHorizontalGroup(
            utilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, utilitiesPanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(utilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        utilitiesPanelLayout.setVerticalGroup(
            utilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, utilitiesPanelLayout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(newButton)
                .addGap(57, 57, 57)
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateButton)
                .addGap(45, 45, 45)
                .addComponent(editButton1)
                .addGap(41, 41, 41)
                .addComponent(deleteButton)
                .addGap(26, 26, 26))
        );

        Label1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Label1.setText("Employee information:");

        Label2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Label2.setText("Employee list");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(informationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employeeList))
                .addGap(18, 18, 18)
                .addComponent(utilitiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(Label1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(informationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Label2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(employeeList, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(utilitiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateJTableButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateJTableButton
        UpdateEmployeeTable();
        JOptionPane.showMessageDialog(null, "Employees list table updated");  
    }//GEN-LAST:event_updateJTableButton
    
    private void newEmployeeButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newEmployeeButton
        ClearTextFields(informationPanel);
        
    }//GEN-LAST:event_newEmployeeButton

    private void deleteButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton
        String id = idTextField.getText();
        if(id.isEmpty()){
            String error = "Choose employee in JTable first before delete";
            JOptionPane.showMessageDialog(this, error, "Delete employee", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Employee_BUS employee_BUS = new Employee_BUS();
        employee_BUS.DeleteEmployee(Integer.parseInt(id));
        UpdateEmployeeTable();   
    }//GEN-LAST:event_deleteButton

    private void editButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton
        String id = idTextField.getText();
        if(id.isEmpty()){
            String error = "Choose employee in JTable first before edit";
            JOptionPane.showMessageDialog(this, error, "Edit employee", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Validates_BUS validate = new Validates_BUS();
        String result = validate.ValidateEmployee(firstNameTextField, lastNameTextField, ageTextField, genderButtonGroup, salaryTextField, jobPositionTextField, phoneNumbersTextField);
        
        Employee_DTO employee = new Employee_DTO();
        if(result == null){
            employee = validate.ReturnEmployee();
            employee.setId(Integer.parseInt(id));
        }
            
        else{
            JOptionPane.showMessageDialog(this, result, "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Employee_BUS employee_BUS = new Employee_BUS();
        
        if(employee_BUS.UpdateEmployee(employee) == true)
            JOptionPane.showMessageDialog(null, "Employee edited successfully!");   
        else
           JOptionPane.showMessageDialog(null, "Failed to edit employee!"); 
    }//GEN-LAST:event_editButton

    
    
    private void addEmployeeButton(java.awt.event.ActionEvent evt) {                                          
        String id = idTextField.getText();
        if (id.isEmpty() == false){
            String error = "Hit the 'New' button before add Employee";
            JOptionPane.showMessageDialog(this, error, "Add employee", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Validates_BUS validate = new Validates_BUS();
        String result = validate.ValidateEmployee(firstNameTextField, lastNameTextField, ageTextField, genderButtonGroup, salaryTextField, jobPositionTextField, phoneNumbersTextField);
        
        Employee_DTO employee = new Employee_DTO();
        if(result == null)
            employee = validate.ReturnEmployee();
             
        else{
            JOptionPane.showMessageDialog(this, result, "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Employee_DAO employee_DAO = new Employee_DAO();
        if(employee_DAO.AddEmployee(employee) == true)
                JOptionPane.showMessageDialog(null, "Employee added successfully!");   
        else
           JOptionPane.showMessageDialog(null, "Failed to add employee!"); 
        
        // Sau khi thêm employee thì update lại table Employee list
        UpdateEmployeeTable();
        }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label2;
    private javax.swing.JButton addButton;
    private javax.swing.JLabel ageLabel;
    private javax.swing.JTextField ageTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton1;
    private javax.swing.JScrollPane employeeList;
    private javax.swing.JTable employeeTable;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.ButtonGroup genderButtonGroup;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTextField;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JLabel jobPositionLabel;
    private javax.swing.JTextField jobPositionTextField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JRadioButton maleRadioButton;
    private javax.swing.JButton newButton;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JTextField phoneNumbersTextField;
    private javax.swing.JLabel salaryLabel;
    private javax.swing.JTextField salaryTextField;
    private javax.swing.JButton updateButton;
    private javax.swing.JPanel utilitiesPanel;
    // End of variables declaration//GEN-END:variables
}
