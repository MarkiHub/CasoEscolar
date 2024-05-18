/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.negocio;

import com.itson.hired.DAO.ConversacionDAO;
import com.itson.hired.DAO.EntregasDAO;
import com.itson.hired.DAO.PadreDAO;
import com.itson.hired.interfaces.IMensajeDAO;
import edu.itson.dominioescolar.DTO.MensajeDTO;
import edu.itson.dominioescolar.DTO.ReviewDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Elkur
 */
public class RendimientoNegocio {

    private final PadreDAO padreDAO;
    private final ConversacionDAO convDAO;
    private final IMensajeDAO mensajeDAO;

    @Autowired
    public RendimientoNegocio(PadreDAO padreDAO, ConversacionDAO convDAO, IMensajeDAO mensajeDAO) {
        this.padreDAO = padreDAO;
        this.convDAO = convDAO;
        this.mensajeDAO = mensajeDAO;
    }

    public void revisarRendimiento(long idAlumno, long idAsignacion, EntregasDAO entDao) {
        List<ReviewDTO> reviews = entDao.getReviews(idAlumno, idAsignacion);

        float numAsignaciones = entDao.getNumAsignaciones(idAsignacion);
        float numAsignacionesBajas = 0;

        for (ReviewDTO review : reviews) {
            int calificacion = review.getCalificacion();
            if (calificacion <= 79) {
                numAsignacionesBajas++;
            }
        }
        float rendimiento = 100 - (numAsignacionesBajas / numAsignaciones) * 100;
        if (rendimiento < 80) {
            // NOTIFICA
            String nombreAlumno = padreDAO.getNombreAlumnoById(idAlumno);
            String mensaje = nombreAlumno + " BAJÓ DE RENDIMIENTO EN LAS ASIGNACIONES";

            long idPadre = padreDAO.getIdPadreByIdAlumno(idAlumno);

            long idConversacion = convDAO.getConversacionIdByPadreAndProfesor(idPadre, 1L);

            MensajeDTO nuevoMensaje = new MensajeDTO();
            nuevoMensaje.setTexto(mensaje);
            nuevoMensaje.setConversacionId(idConversacion);
            nuevoMensaje.setIdSender(1L);

            mensajeDAO.insertDTO(nuevoMensaje);
        }
    }
}
