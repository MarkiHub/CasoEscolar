/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.itson.hired.demo.DAO.PadreDAO;
import edu.itson.dominioescolar.Padre;
import edu.itson.dominioescolar.Profesor;
import jakarta.servlet.ServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ildex
 */
@Controller
@RequestMapping("/padre")
@Repository
@AutoConfiguration
public class PadreController {

    @GetMapping("/Chat")
    public String chatProfes() {
        return "ChatProfes";
    }

    @GetMapping("/chatProfe")
    public String chat(@RequestParam Long conversacionId, Model model) {
        model.addAttribute("conversacionId", conversacionId);
        return "Chat";
    }
}
