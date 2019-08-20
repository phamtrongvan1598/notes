package com.strongman.smm.service.impl;

import com.strongman.smm.model.Note;
import com.strongman.smm.repository.NoteRepository;
import com.strongman.smm.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository notesRepository;
    @Override
    public List<Note > findAll(){
        return notesRepository.findAll();
    }
    @Override
    public Note findById(Long id){
        return notesRepository.findById(id);
    }
    @Override
    public void save(Note note){
            notesRepository.save(note);
    }
}
