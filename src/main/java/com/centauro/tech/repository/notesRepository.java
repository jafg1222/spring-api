/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centauro.tech.repository;

import com.centauro.tech.model.notesModel;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jafg1222
 */
public interface notesRepository extends JpaRepository<notesModel, Long>{
    
}
