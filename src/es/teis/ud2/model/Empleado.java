/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2.model;

import java.sql.Date;

/**
 *
 * @author David Marín
 */
public class Empleado {
    
    private int emptno;
    private String ename;
    private String job;
    private int MGR;
    private Date fecha;
    private float sal;
    private float comm;
    private int deptno;

    public Empleado(String ename, String job, int MGR, Date fecha, float sal, float comm, int deptno) {
        this.ename = ename;
        this.job = job;
        this.MGR = MGR;
        this.fecha = fecha;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public float getComm() {
        return comm;
    }

    public void setComm(float comm) {
        this.comm = comm;
    }
    
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMGR() {
        return MGR;
    }

    public void setMGR(int MGR) {
        this.MGR = MGR;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getSal() {
        return sal;
    }

    public void setSal(float sal) {
        this.sal = sal;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
    
    
    
}
