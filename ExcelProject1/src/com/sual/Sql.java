/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Sql {
    protected Connection connect(){
        String url = "jdbc:sqlite:tercume.sqlite";
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return conn;
    } 
    
    public void insert(String soz1, String soz2, int nomre) {
        
        String sql = "INSERT INTO sozler (soz1, soz2, nomresi) VALUES (?,?,?)";
        
        try(Connection conn = this.connect();
                PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, soz1);
            ps.setString(2, soz2);
            ps.setInt(3, nomre);
            
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    public void delete() {
        String sql = "DELETE FROM sozler";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void Update(int dq2) {
        String sql = "UPDATE dq SET vaxt = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
             pstmt.setInt(1, dq2);
            
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public String selectDq(){
        String sl = "SELECT vaxt FROM dq";
        String dq1=null;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sl)){
            
            
            while (rs.next()) {
                dq1 = rs.getString("vaxt");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dq1;
    }
    
     public String selectTesadufi(){
        String sl = "SELECT nomresi FROM sozler ORDER BY nomresi";
        String ras="";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sl)){
            
            
            while (rs.next()) {
                ras = rs.getString("nomresi");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ras;
    }
     
     
     public static void main(String[] args) {

        Sql app = new Sql();

        app.insert("Raw Materials", "Raw Materials", 3000);
        app.delete();
    }
     
     
}
