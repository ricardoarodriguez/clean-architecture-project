package co.com.bancolombia.usecase.note;

import co.com.bancolombia.model.note.Note;
import co.com.bancolombia.model.note.gateways.NoteRepository;
import co.com.bancolombia.model.shared.exception.AppException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class NoteUseCase {

    private final NoteRepository noteRepository;

    public Note create(Note parNote) {
        if (noteRepository.get(parNote.getId()) == null) {
            return noteRepository.create(parNote);
        } else {
            throw new AppException("La nota ya existe");
        }
    }

    public Note update(Long id, Note parNote) {
        if (noteRepository.get(id) != null) {
            return noteRepository.update(id, parNote);
        } else {
            throw new AppException("La nota no existe");
        }
    }

    public Note get(Long id) {
        if (id != null) {
            return noteRepository.get(id);
        } else {
            throw new AppException("La nota no existe");
        }
    }

    public List<Note> getAll() {
        return noteRepository.getAll();
    }


}
