/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2;

import es.teis.ud2.model.Departamento;
import es.teis.ud2.model.Empleado;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 *
 * @author maria
 */
public class Main {

    final static String SEPARATOR = "\t\t\t\t";

    public static void main(String[] args) throws ParseException {
        
        ejercicio04(crearEmpleado(), 120.0f);
        ejercicio01();

        //consultarDepts();
        //consultarEmpleadosRangoSalarial(1000.50f, 2000.50f);
        //  borrarDept(40);
        // Departamento operacionesDept = new Departamento(40, "OPERATIONS", "BOSTON");
//        insertarDepartamentoConIdentity(operacionesDept);
        // insertarDepartamento(operacionesDept);
//        operacionesDept.setDeptName("OPERACIONES 2");
//        actualizarDept(operacionesDept);
    }

    private static void consultarDepts() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();
        String consulta01 = "SELECT * FROM dbo.DEPT";
        String consulta02 = "SELECT ENAME, SAL FROM dbo.EMP ORDER BY SAL";
        

        try (
                 Connection conexion = ds.getConnection();  Statement sentencia = conexion.createStatement();  ResultSet result = sentencia.executeQuery(consulta02);) {

            int columnas = result.getMetaData().getColumnCount();
//            for (int i = 1; i <= columnas; i++) {
//                System.out.print(result.getMetaData().getColumnName(i) + SEPARATOR);
//            }

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getString("ENAME") + SEPARATOR + result.getString("SAL"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
    
    private static void ejercicio01() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();
        String consulta = "SELECT ENAME, SAL FROM dbo.EMP ORDER BY SAL";
        

        try (
                 Connection conexion = ds.getConnection();  Statement sentencia = conexion.createStatement();  ResultSet result = sentencia.executeQuery(consulta);) {

            System.out.print(result.getMetaData().getColumnName(1) + SEPARATOR + result.getMetaData().getColumnName(2));

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getString("ENAME") + SEPARATOR + result.getString("SAL"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
    
    private static void ejercicio02() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();
        //String consulta = "SELECT a.ENAME, a.SAL, b.ENAME b.SAL FROM dbo.EMP a INNER JOIN dbo.EMP b ON a.MGR = b.EMPNO";
        String consulta = "SELECT DISTINCT E.ENAME AS empleado, E.SAL, J.ENAME AS jefe, J.SAL FROM EMP AS E JOIN EMP AS J ON E.MGR = J.EMPNO ORDER BY J.ENAME";
        

        try (
                 Connection conexion = ds.getConnection();  Statement sentencia = conexion.createStatement();  ResultSet result = sentencia.executeQuery(consulta);) {

            System.out.print(result.getMetaData().getColumnName(1) + SEPARATOR + result.getMetaData().getColumnName(2)
             + SEPARATOR + result.getMetaData().getColumnName(3) + SEPARATOR + result.getMetaData().getColumnName(4));

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getString("empleado") + SEPARATOR + result.getString("SAL") + SEPARATOR + result.getString("jefe") + SEPARATOR + result.getString(4));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
    
    private static void ejercicio03() throws ParseException {
            DataSource ds = DBCPDataSourceFactory.getDataSource();
            //Para trabajar con el dato tipo fecha
            DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
            java.sql.Date sqlDate = new java.sql.Date(date.parse("07-07-1981").getTime());
            //Se crea un empleado
            Empleado empleado = new Empleado("DAVID", "EMPLEADO", 7839, sqlDate, 1000, 22, 40);
            //Se ordena por numero de empleado por orden descendente para saber el ultimo numero de empleado
            String consultaIdentificador = "SELECT EMPNO FROM dbo.EMP ORDER BY EMPNO DESC";
            //Se inserta el empleado creado
            String consulta = "USE empresa INSERT INTO dbo.EMP (ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)"
                    + "VALUES ('" + empleado.getEname() + "', '" + empleado.getJob() + "', '"
                    + empleado.getMGR() +  "', '" + empleado.getFecha() + "', '" + empleado.getSal() + "', '" + empleado.getComm() 
                    + "', '" + empleado.getDeptno() + "')";
            try (
                    Connection conexion = ds.getConnection();  Statement sentencia = conexion.createStatement(); ResultSet result = sentencia.executeQuery(consultaIdentificador);) {
                int nombre = 0;
                //Se ejecuta la sentencia y se hace un break para saber el ultimo numero de empleado introducido
                while (result.next()){
                    nombre =  result.getInt("EMPNO");
                    System.out.println("El numero es: " + nombre);
                    break;
                }
                //Se inserta el empleado en la tabla
                int resulta = sentencia.executeUpdate(consulta);
                //Se obtiene el ultimo numero de empleado de la BBDD y se le suma 1
                System.out.println("Se ha añadido el empleado " + (nombre + 1) + " correctamente");
                } catch (SQLException ex) {
                ex.printStackTrace();
                System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
                
            }
    }
    
    private static Empleado crearEmpleado() {
        Empleado empleado = null;
        try {
            //Para trabajar con el dato tipo fecha
            DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
            java.sql.Date sqlDate = new java.sql.Date(date.parse("07-07-1981").getTime());
            //Se crea un empleado
            empleado = new Empleado("DAVID", "EMPLEADO", 7839, sqlDate, 1000, 22, 40);
            System.out.println("Empleado creado correctamente");
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }
   
    
    private static void ejercicio04(Empleado empleado, float incremento) {
            DataSource ds = DBCPDataSourceFactory.getDataSource();
            try (
                    Connection conexion = ds.getConnection();  
                    PreparedStatement sentencia = conexion.prepareStatement("UPDATE EMP SET SAL=SAL+? WHERE ENAME = ?"); ) {
                sentencia.setFloat(1,incremento);
                sentencia.setString(2, empleado.getEname());
                System.out.println("El sueldo es de " + empleado.getSal());
                } catch (SQLException ex) {
                ex.printStackTrace();
                System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
                
            }
    }

    private static void consultarEmpleadosRangoSalarial(float minSalario, float maxSalario) {

        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                "SELECT ENAME, SAL FROM EMP WHERE SAL >= ? AND SAL <=? ORDER BY SAL DESC");) {

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

    private static void insertarDepartamentoConIdentity(Departamento departamento) {
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

    private static void insertarDepartamento(Departamento departamento) {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                "INSERT INTO [dbo].[DEPT]( DNAME,  LOC) VALUES( ?, ?);", Statement.RETURN_GENERATED_KEYS
        );) {

            pstmt.setString(1, departamento.getDeptName());
            pstmt.setString(2, departamento.getLoc());

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            int result = pstmt.executeUpdate();
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("El número de filas afectadas es: " + result);

            ResultSet clavesResultado = pstmt.getGeneratedKeys();

            while (clavesResultado.next()) {
                System.out.println("La clave asignada al nuevo registro es: " + clavesResultado.getInt(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }

    private static void actualizarDept(Departamento departamento) {
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
