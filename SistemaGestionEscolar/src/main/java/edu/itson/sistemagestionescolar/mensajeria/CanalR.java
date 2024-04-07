/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.sistemagestionescolar.mensajeria;

import com.google.gson.Gson;
import com.rabbitmq.client.Delivery;
import edu.itson.dominioescolar.Calificacion;
import edu.itson.sistemagestionescolar.CalificacionDAO;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elkur
 */
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

}
