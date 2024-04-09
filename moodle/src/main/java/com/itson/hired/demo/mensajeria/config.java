/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.mensajeria;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author xeron
 */
@Configuration
public class config {

    public static final String EXCHANGE_NAME = "escolar";

    public enum LlavesRuteadoras {
        ROUTING_KEY("guardarCalificaciones"),
        ROUTING_KEY2("consultarAsignacion"),
        ROUTING_KEY3("elpepe");

        private final String valor;

        LlavesRuteadoras(String valor) {
            this.valor = valor;
        }

        public String obtenerValor() {
            return valor;
        }
    }

@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    // Configuración del RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(EXCHANGE_NAME);
        SimpleMessageConverter messageConverter = new SimpleMessageConverter();
        messageConverter.addAllowedListPatterns("edu.itson.dominioescolar.*");
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    // Configuración de las colas
    @Bean
    public Queue queueGuardarCalificaciones() {
        return new Queue("guardarCalificaciones", false);
    }

    @Bean
    public Queue queueConsultarAsignacion() {
        return new Queue("consultarAsignacion", false);
    }

    // Configuración del exchange
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME, false, false);
    }

    // Configuración de los bindings entre las colas y el exchange
    @Bean
    public Binding bindingGuardarCalificaciones(Queue queueGuardarCalificaciones, DirectExchange exchange) {
        return BindingBuilder.bind(queueGuardarCalificaciones).to(exchange).with(LlavesRuteadoras.ROUTING_KEY.obtenerValor());
    }

    @Bean
    public Binding bindingConsultarAsignacion(Queue queueConsultarAsignacion, DirectExchange exchange) {
        return BindingBuilder.bind(queueConsultarAsignacion).to(exchange).with(LlavesRuteadoras.ROUTING_KEY2 .obtenerValor());
    }
}
