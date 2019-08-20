package com.strongman.smm.contrloller;


import com.strongman.smm.model.Note;
import com.strongman.smm.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;
    //-------------------Retrieve All Notes--------------------------------------------------------
    @RequestMapping(value = "/notes/", method = RequestMethod.GET)
    public ResponseEntity<List<Note>> listAllCustomers() {
        List<Note> customers = noteService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<List<Note>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Note>>(customers, HttpStatus.OK);
    }
    //-------------------Retrieve Single Note--------------------------------------------------------
    @RequestMapping(value = "/notes/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> getNote(@PathVariable("id") long id){
        System.out.println(("Fetching Note with id \" + id"));
        Note note = noteService.findById(id);
        if (note ==null){
            System.out.println("note with id \" + id + \" not found");
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }return new ResponseEntity<Note>(note , HttpStatus.OK);
    }
    //-------------------Create a Note--------------------------------------------------------
    @RequestMapping(value = "/notes/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Note note, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Note " + note.getTitle());
        noteService.save(note);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/notes/{id}").buildAndExpand(note.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Note --------------------------------------------------------

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Note> updateNote(@PathVariable("id") long id, @RequestBody Note note) {
        System.out.println("Updating Note " + id);

      Note currentNote = noteService.findById(id);

        if (currentNote == null) {
            System.out.println("Note with id " + id + " not found");
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }

        currentNote.setTitle(note.getTitle());
        currentNote.setContent(note.getContent());
        currentNote.setId(note.getId());

        noteService.save(currentNote);
        return new ResponseEntity<Note>(currentNote, HttpStatus.OK);
    }

}
