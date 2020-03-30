package com.example.employees;

import com.example.jdbc.DBConnector;

import com.example.employees.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;

public class EmployeeDAO {

    public int insert(Employee employee)  {
        Connection con = DBConnector.getConnection();
        int count = 0;

      /*  
        private long id;
        private String name;
        private String lastName;
        private String birthDate;
        private String empRole;
        private String department;
        private String email;
        
        */
        
        try {
            PreparedStatement pstmt =
                    con.prepareStatement( "INSERT INTO EMPLOYEE (name, lastName, birthDate, empRole, department, email) VALUES (?,?,?,?,?,?)");

            if(employee.getName() != null ) {
                pstmt.setString(1, employee.getName());
            }
            else {
                pstmt.setNull(1, Types.VARCHAR);
            }            
            if(employee.getLastName() != null ) {
                pstmt.setString(2, employee.getLastName());
            }
            else {
                pstmt.setNull(2, Types.VARCHAR);
            }
            if(employee.getBirthDate() != null ) {
                pstmt.setString(3, employee.getBirthDate());
            }
            else {
                pstmt.setNull(3, Types.VARCHAR);
            }
            if(employee.getEmpRole() != null ) {
                pstmt.setString(4, employee.getEmpRole());
            }
            else {
                pstmt.setNull(4, Types.VARCHAR);
            }
            if(employee.getDepartment() != null ) {
                pstmt.setString(5, employee.getDepartment());
            }
            else {
                pstmt.setNull(5, Types.VARCHAR);
            }
            if(employee.getEmail() != null ) {
                pstmt.setString(6, employee.getEmail());
            }
            else {
                pstmt.setNull(6, Types.VARCHAR);
            }
        

            count = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    // UPDATE TESTTABLE BESCHREIBUNG=? WHERE ID=?
    public int update(Employee employee) {
        return 0;
    }

    // DELETE FROM TESTTABLE WHERE ID=?
    public int delete(Employee employee) {
        return 0;
    }

    // SELECT ID,BEZEICHNUNG FROM TESTTABLE WHERE ID = ?
    public Employee findById(int id) {
        return null;
    }

    /*
        String sqlstr = "SELECT ID,BESCHREIBUNG FROM TESTTABLE ";
        if(where != null {
            sqlstr += where;
        }
     */
	public Vector<Employee> select(String where) {
        Connection con = DBConnector.getConnection();
        String sqlStmt = "SELECT id, frage FROM fragen ";
        
        Vector<Employee> employees = new Vector<Employee>();
        
        
        if(where != null) {
        	sqlStmt += where;
        }
        
        try {
           
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(sqlStmt);
            while(rs.next()) {
            	Employee employee = new Employee();
            	employee.setId(rs.getInt(1));
            	employee.setName(rs.getString(2));
            	employee.setLastName(rs.getString(3));
            	employee.setBirthDate(rs.getString(4));
            	employee.setEmpRole(rs.getString(5));
            	employee.setDepartment(rs.getString(6));
            	employee.setEmail(rs.getString(7));
            	employees.addElement(employee);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return employees;
	}
}
