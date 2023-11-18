package co.com.bancolombia.usecase.note;

import co.com.bancolombia.model.note.Note;
import co.com.bancolombia.model.note.gateways.NoteRepository;
import co.com.bancolombia.model.shared.exception.AppException;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class NoteUseCase {

    private final NoteRepository noteRepository;

    public Note create(Note parNote) {
        return noteRepository.create(parNote);
    }

    public void update(Long id, Note parNote) {
        if (noteRepository.get(id) != null) {
            parNote.setUpdateDate(Date.from(Instant.now()));
            noteRepository.update(id, parNote);
        } else {
            throw new AppException("La nota no existe");
        }
    }

    public Note get(Long id) {
        Note note = noteRepository.get(id);
        if (note == null) {
            throw new AppException("La nota no existe");
        }
        return note;
    }

    public List<Note> getAll() {
        return noteRepository.getAll();
    }


}
