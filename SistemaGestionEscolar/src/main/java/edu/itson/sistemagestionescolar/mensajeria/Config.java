/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.sistemagestionescolar.mensajeria;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private static final String ROUTING_KEYS[] = {"guardarCalificaciones","guardarAsignaciones"};

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
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();
        
        for (String KEY : ROUTING_KEYS) {
            channel.queueBind(queueName, EXCHANGE_NAME, KEY);
        }
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            ICanalR canal = new CanalR();
            String routingKey = delivery.getEnvelope().getRoutingKey();
            System.out.println(routingKey);
            if (routingKey.equalsIgnoreCase("guardarCalificaciones")) {
                canal.guardarCalificacion(delivery);
            } else if(routingKey.equalsIgnoreCase("guardarAsignaciones")){
                canal.guardarAsignacion(delivery);
            }

            System.out.println("Se guardo algo");
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
