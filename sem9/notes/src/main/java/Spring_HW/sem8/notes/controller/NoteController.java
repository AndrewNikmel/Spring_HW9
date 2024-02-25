package Spring_HW.sem8.notes.controller;

import Spring_HW.sem8.notes.model.Note;
import Spring_HW.sem8.notes.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ms1")
public class NoteController {
    //Сервис
    private final NoteService noteService;
    //Добавление заметки
    @PostMapping
    public ResponseEntity<Void> addNote(@RequestBody Note note) {
        note.setCreationDate(LocalDateTime.now());
        noteService.save(note);
        return ResponseEntity.ok().build();
    }
    //Получение всех заметок
    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return new ResponseEntity<>(noteService.getAll(), HttpStatus.OK);
    }
    //Получение конкретной заметки
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        if(note == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(note, HttpStatus.FOUND);
    }
    //Изменение конкретной заметки
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNote(@PathVariable Long id, @RequestBody Note note) {
        Note oldNote = noteService.getNoteById(id);
        if(oldNote == null)
            return ResponseEntity.notFound().build();
        else {
            note.setId(id);
            note.setCreationDate(oldNote.getCreationDate());
            noteService.save(note);
            return ResponseEntity.ok().build();
        }
    }
    //Удаление заметки
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        if(note == null)
            return ResponseEntity.notFound().build();
        else {
            noteService.delete(id);
            return ResponseEntity.ok().build();
        }
    }
}