/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2;

import es.teis.ud2.model.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 *
 * @author maria
 */
public class Main {

    final static String SEPARATOR = "\t\t\t\t";

    public static void main(String[] args) {
        consultarDepts();
        consultarEmpleadosRangoSalarial(1000.50f, 2000.50f);
//        borrarDept(40);
//        Departamento operacionesDept = new Departamento(40,"OPERATIONS", "BOSTON");
//        insertarDepartamentoConIdentity(operacionesDept);
//        insertarDepartamento(operacionesDept);
//        operacionesDept.setDeptName("OPERACIONES 2");
//        actualizarDept(operacionesDept);
    }

    private static void consultarDepts() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  Statement sentencia = conexion.createStatement();  ResultSet result = sentencia.executeQuery("SELECT * FROM dbo.DEPT");) {

            int columnas = result.getMetaData().getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(result.getMetaData().getColumnName(i) + SEPARATOR);
            }

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getInt(1) + SEPARATOR + result.getString(2) + SEPARATOR + result.getString(3) + SEPARATOR);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }

    private static void consultarEmpleadosRangoSalarial(float minSalario, float maxSalario) {

        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement("SELECT ENAME, SAL FROM EMP WHERE SAL >= ? AND SAL <=? ORDER BY SAL DESC");) {

            pstmt.setFloat(1, minSalario);
            pstmt.setFloat(2, maxSalario);

            ResultSet result = pstmt.executeQuery();

            int columnas = result.getMetaData().getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(result.getMetaData().getColumnName(i) + SEPARATOR);
            }

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getString(1) + SEPARATOR + result.getFloat(2));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }

    private static void borrarDept(int deptNo) {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement("DELETE FROM dept WHERE DEPTNO=?");) {

            pstmt.setInt(1, deptNo);

            int result = pstmt.executeUpdate();

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("El número de filas afectadas  es: " + result);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
    
    private static void insertarDepartamentoConIdentity(Departamento departamento){
         DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                         "SET IDENTITY_INSERT dbo.DEPT ON; \n"
                         + "INSERT INTO [dbo].[DEPT](DEPTNO, DNAME,  LOC) VALUES(?, ?, ?);\n"
                                 + " SET IDENTITY_INSERT dbo.DEPT OFF");) {

            pstmt.setInt(1, departamento.getDeptno());
            pstmt.setString(2, departamento.getDeptName());
            pstmt.setString(3, departamento.getLoc());

            int result = pstmt.executeUpdate();

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("El número de filas afectadas es: " + result);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
    
    
     private static void insertarDepartamento(Departamento departamento){
         DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                      
                          "INSERT INTO [dbo].[DEPT]( DNAME,  LOC) VALUES( ?, ?);"
                              )) {

            pstmt.setString(1, departamento.getDeptName());
            pstmt.setString(2, departamento.getLoc());

            int result = pstmt.executeUpdate();

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("El número de filas afectadas es: " + result);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
    
    
      private static void actualizarDept(Departamento departamento){
         DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                         "UPDATE [dbo].[DEPT]  SET DNAME=?,  LOC=? WHERE DEPTNO = ?")) {

            
            pstmt.setString(1, departamento.getDeptName());
            pstmt.setString(2, departamento.getLoc());
            pstmt.setInt(3, departamento.getDeptno());

            int result = pstmt.executeUpdate();

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("El número de filas afectadas es: " + result);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
}
