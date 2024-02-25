package Spring_HW.sem8.notes.repository;

import Spring_HW.sem8.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Репозиторий заметок
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findNoteById(Long id);
}
