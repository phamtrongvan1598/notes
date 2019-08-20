package com.strongman.smm.service;

import com.strongman.smm.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAll();
    Note findById(Long id);
    void save(Note note);
}
