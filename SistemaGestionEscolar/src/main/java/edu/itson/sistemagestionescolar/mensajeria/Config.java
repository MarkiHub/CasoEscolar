/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.sistemagestionescolar.mensajeria;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import edu.itson.dominioescolar.Asignacion;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elkur
 */
public class Config {

    private static final String EXCHANGE_NAME = "escolar";
    private static final String EXCHANGE_NAME2 = "escolar_r";
    private static final String ROUTING_KEYS_IN[] = {"guardarCalificaciones", "guardarAsignaciones", "consultarAsignacion"};
    private static final String ROUTING_KEYS_OUT[] = {"enviarAsignacion"};

    public static void main(String[] args) {
        try {
            new Config().configChannel();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void configChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channelIn = connection.createChannel();
        Channel channelOut = connection.createChannel();

        channelIn.exchangeDeclare(EXCHANGE_NAME, "direct");
        channelOut.exchangeDeclare(EXCHANGE_NAME, "direct");

        String queueNameIn = "solicitud";
        String queueNameOut = "respuesta";
        channelIn.queueDeclare(queueNameIn, false, false, false, null);
        channelOut.queueDeclare(queueNameOut, false, false, false, null);
        
        for (String KEY : ROUTING_KEYS_IN) {
            channelIn.queueBind(queueNameIn, EXCHANGE_NAME, KEY);
        }

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            ICanalR canal = new CanalR();
            String routingKey = delivery.getEnvelope().getRoutingKey();
            System.out.println(routingKey);
            if (routingKey.equalsIgnoreCase("guardarCalificaciones")) {
                canal.guardarCalificacion(delivery);
            } else if (routingKey.equalsIgnoreCase("guardarAsignaciones")) {
                canal.guardarAsignacion(delivery);
            } else if (routingKey.equalsIgnoreCase("consultarAsignacion")) {
                String asig = canal.buscarAsignacion(delivery);
                channelOut.queueBind(queueNameOut, EXCHANGE_NAME, routingKey);
                channelOut.basicPublish("",queueNameOut, null, asig.getBytes("UTF-8"));
            }

            System.out.println("Se guardo algo");
        };

        channelIn.basicConsume(queueNameIn, true, deliverCallback, consumerTag -> {
        });
    }
}
