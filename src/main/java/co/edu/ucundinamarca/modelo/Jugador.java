/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.modelo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eison
 */
public class Jugador extends Equipo{
      
    private String nombre;
    private byte turno;
    private byte ubicacion;
    private Object o;
    StringBuffer ganador = new StringBuffer();

    
    public Jugador(){

    }

    public Jugador(String nombre, byte turno,  byte ubicacion, String nombreEquipo, Object o) {
        super(nombreEquipo);
        this.nombre = nombre;
        this.turno = turno;
        this.ubicacion = ubicacion;
        this.o = o;
    }
    
     public void correr(){
         byte numeroSaltos = 0;
         while(numeroSaltos < (30)){
            numeroSaltos += ( Math.random()*4+1);
            ubicacion += numeroSaltos;
            System.out.println(nombre+" está corriendo con el equipo "+super.getNombreEquipo()+" y dió "+ numeroSaltos+" pasos ♥");
        }
        if(turno != 3){
           synchronized(o){
              o.notify();
           }
           interrupt();
        }
     }
     
     public void esperar(){
        try {
            //System.out.println(nombre+" se va a quedar jugando minecraft");
            synchronized(o){
                o.wait();
            }
            //System.out.println("Ash "+nombre+" me toco salir con el turno "+turno);
        } catch (InterruptedException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public synchronized void generarRanking() {
        if(turno == 3){
        String auxiliar="\n";
        auxiliar += "Llegó --> Equipo: "+super.getNombreEquipo()+"\t\n";
        ganador.append(auxiliar);
        System.out.println(ganador.toString());
        }
    }
     
     @Override
    public void run(){
        
            esperar();
            correr();
        try {
            Thread.sleep(1500);
            
        } catch (InterruptedException ex) {
            System.out.println("");
        }
            generarRanking();
    } 
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;

}

    public byte getTurno() {
        return turno;
    }

    public void setTurno(byte turno) {
        this.turno = turno;
    }

    public byte getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(byte ubicacion) {
        this.ubicacion = ubicacion;
    }

}
