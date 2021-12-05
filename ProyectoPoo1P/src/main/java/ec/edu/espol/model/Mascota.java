/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import static ec.edu.espol.model.Dueno.getDueñoSearchedByMail;
import static ec.edu.espol.model.Dueno.getIdDueñoSearhedByMail;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Mascota {
    private int id, idDueño;
    private String nombre, raza, tipo;
    private Date fechaNacimiento;
    private Dueno dueño;
    private ArrayList<Inscripcion> inscripciones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDueño() {
        return idDueño;
    }

    public void setIdDueño(int idDueño) {
        this.idDueño = idDueño;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Dueno getDueño() {
        return dueño;
    }

    public void setDueño(Dueno dueño) {
        this.dueño = dueño;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public Mascota(int id, int idDueño, String nombre, String raza, String tipo, Date fechaNacimiento, Dueno dueño) {
        this.id = id;
        this.idDueño = idDueño;
        this.nombre = nombre;
        this.raza = raza;
        this.tipo = tipo;
        this.fechaNacimiento = fechaNacimiento;
        this.dueño = dueño;
        this.inscripciones = new ArrayList();
    }

    @Override
    public String toString() {
        return "Mascota{" + "nombre=" + nombre + ", raza=" + raza + ", tipo=" + tipo + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
    
    public static Mascota nextMascota (Scanner sc, String nomfile){
        System.out.println("Registrar Mascota");
        
        int id = Util.nextID(nomfile);
        String correo;
        do{
            System.out.println("Ingrese el correo electrónico del Dueño de la mascota: ");
            correo = sc.next();
        }while(Dueno.correoInFile(correo));
        int idDueño = getIdDueñoSearhedByMail(correo);
        Dueno dueño = getDueñoSearchedByMail(correo);
        System.out.println("Ingrese el nombre de su mascota: ");
        String nombre = sc.next();
        System.out.println("Ingrese la raza de su mascota: ");
        String raza = sc.next();
        System.out.println("Ingrese el tipo de su mascota: ");
        String tipo = sc.next();
        System.out.println("Ingrese el año de nacimiento de su mascota: ");
        int year = sc.nextInt() - 1900;
        System.out.println("Ingrese el mes de nacimiento de su mascota: ");
        int mes = sc.nextInt() - 1;
        System.out.println("Ingrese el día de nacimiento de su mascota:");
        int dia = sc.nextInt();
        Date fechaNacimiento = new Date(year, mes, dia);
        
        Mascota mascota = new Mascota(id, idDueño, nombre, raza, tipo, fechaNacimiento, dueño);
        mascota.saveFile(nomfile);        
        return mascota;
    }

    public void saveFile(String nomfile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true))){
            pw.println(this.id + "|" + this.idDueño + "|" + this.nombre + "|" + this.raza + "|" + this.tipo + "|" + this.fechaNacimiento + "|" + this.dueño + "|" + this.inscripciones);
        }
        catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    
    public static void saveFile(ArrayList<Mascota> mascotas, String nomfile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile)))){
            for(Mascota m : mascotas)
                pw.println(m.getId() + "|" +m.getIdDueño()+ "|" + m.getNombre() + "|" + m.getRaza() + "|" + m.getTipo() + "|" + m.getFechaNacimiento() + "|" + m.getDueño() + "|" + m.getInscripciones());
        }
        catch(Exception e){
        System.out.println(e.getMessage());
        }
    }    
}
