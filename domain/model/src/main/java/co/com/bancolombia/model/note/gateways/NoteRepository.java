package co.com.bancolombia.model.note.gateways;

import co.com.bancolombia.model.note.Note;

import java.util.List;

public interface NoteRepository {
    Note create(Note parNote);
    Note update(Long id, Note parNote);
    Note get(Long id);
    List<Note> getAll();
}
