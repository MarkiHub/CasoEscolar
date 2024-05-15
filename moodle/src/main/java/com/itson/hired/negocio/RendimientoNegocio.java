/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.negocio;

import com.itson.hired.DAO.EntregasDAO;
import edu.itson.dominioescolar.DTO.ReviewDTO;
import java.util.List;

/**
 *
 * @author Elkur
 */
public class RendimientoNegocio {

    public void revisarRendimiento(long idAlumno, long idAsignacion, EntregasDAO entDao) {
        List<ReviewDTO> reviews = entDao.getReviews(idAlumno, idAsignacion);
        
        float numAsignaciones = entDao.getNumAsignaciones(idAsignacion);
        float numAsignacionesBajas = 0;
        
        for(ReviewDTO review : reviews){
            int calificacion = review.getCalificacion();
            if(calificacion<= 79){
                numAsignacionesBajas++;
            }
        }
        float rendimiento = 100 - (numAsignacionesBajas/numAsignaciones)*100;
        if(rendimiento<80){
            //NOTIFICA
        }
        
    }
    
}
