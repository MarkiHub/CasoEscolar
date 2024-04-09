/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.sistemagestionescolar.mensajeria;

import com.google.gson.Gson;
import com.rabbitmq.client.Delivery;
import edu.itson.dominioescolar.Asignacion;
import edu.itson.dominioescolar.Calificacion;
import edu.itson.sistemagestionescolar.AsignacionDAO;
import edu.itson.sistemagestionescolar.CalificacionDAO;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elkur
 */
//El pirata de culiacan
public class CanalR implements ICanalR {

    @Override
    public void guardarCalificacion(Delivery delivery) {
        try {
            String message = new String(delivery.getBody(), "UTF-8");
            Gson gson = new Gson();
            Calificacion calif = gson.fromJson(message, Calificacion.class);

            CalificacionDAO califDAO = new CalificacionDAO();

            califDAO.insert(calif);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CanalR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CanalR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void guardarAsignacion(Delivery delivery) {
        try {
            String message = new String(delivery.getBody(), "UTF-8");
            Gson gson = new Gson();
            Asignacion asig = gson.fromJson(message, Asignacion.class);

            AsignacionDAO asigDAO = new AsignacionDAO();

            asigDAO.insert(asig);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CanalR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CanalR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String buscarAsignacion(Delivery delivery) {
        try {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(message);
            Gson gson = new Gson();
            AsignacionDAO asigDAO = new AsignacionDAO();

            Long elpepe = Long.valueOf(message);
            
            Asignacion asig1 = asigDAO.get(elpepe);

            System.out.println(asig1);
            
            return gson.toJson(asig1);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CanalR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
