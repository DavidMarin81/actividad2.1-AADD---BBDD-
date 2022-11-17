/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2;

import java.sql.Connection;
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
}
