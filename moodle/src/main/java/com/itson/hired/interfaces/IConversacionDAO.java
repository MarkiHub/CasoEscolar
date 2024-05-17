/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.itson.hired.interfaces;

import edu.itson.dominioescolar.Conversacion;
import java.util.Optional;

/**
 *
 * @author ildex
 */
public interface IConversacionDAO {

    Conversacion crearConversacion(Conversacion conversacion);

    Optional<Conversacion> obtenerConversacionPorProfesorYPadre(Long idProfesor, Long idPadre);
}
