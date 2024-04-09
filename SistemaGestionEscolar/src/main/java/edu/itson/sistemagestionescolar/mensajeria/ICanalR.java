/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.itson.sistemagestionescolar.mensajeria;

import com.rabbitmq.client.Delivery;

/**
 *
 * @author Elkur
 */
public interface ICanalR {
    public void guardarCalificacion(Delivery delivery);
    public void guardarAsignacion(Delivery delivery);
}
