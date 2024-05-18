/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.hired.interfaces;

import edu.itson.dominioescolar.Conversacion;
import edu.itson.dominioescolar.Mensaje;
import java.util.List;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ildex
 */
@Repository
@AutoConfiguration
public interface IMensajeDAO {

    public void insert(Mensaje mensaje);

    public List<Mensaje> getMensajesPorConversacion(Long idConversacion);

    public Mensaje getMensajePorId(Long idMensaje);

    public void crearConversacion(Conversacion conversacion);

    public void deleteMensaje(Long idMensaje);
}

