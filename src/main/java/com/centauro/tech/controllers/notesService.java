/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centauro.tech.controllers;

import com.centauro.tech.model.notesModel;
import com.centauro.tech.repository.notesRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import net.minidev.json.JSONObject;

/**
 *
 * @author jafg1222
 */

@RestController
@RequestMapping("/api") // routing decorator 

public class notesService {
    
    @Autowired            
    notesRepository noteRepository;
    
    // Get Methods
    @CrossOrigin()
    @GetMapping("/allNotes")    
    public List<notesModel> getAllNotes(){
         return noteRepository.findAll();
    }
    
    @CrossOrigin()
    @GetMapping("/notes/{id}")
    public ResponseEntity<notesModel> getNoteById(@PathVariable(value = "id") Long noteId) {
        notesModel note = noteRepository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(note);
    }

    // Post Methods
    @CrossOrigin()
    @PostMapping("/Notes")
    public ResponseEntity<Object> insNotes(@Valid @RequestBody notesModel note){
        noteRepository.save(note);
        ArrayList<JSONObject> response = new ArrayList<JSONObject>();
        
        JSONObject json = new JSONObject();
        
        json.put("Status", "200");
        json.put("Response", "ok");
        
        response.add(json);
        
        return ResponseEntity.status(200).body(response);
    }
    
    // Delete Methods
    @CrossOrigin()
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<notesModel> deleteNote(@PathVariable(value = "id") Long noteId) {
        notesModel note = noteRepository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }

        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }
    
    // Put Methods
    @PutMapping("/notes/{id}")
    public ResponseEntity<notesModel> updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody notesModel noteDetails) {
        notesModel note = noteRepository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        note.setTitle(noteDetails.getTitle());
        note.setBody(noteDetails.getBody());

        notesModel updatedNote = noteRepository.save(note);
        return ResponseEntity.ok(updatedNote);
    }
    
    
}
