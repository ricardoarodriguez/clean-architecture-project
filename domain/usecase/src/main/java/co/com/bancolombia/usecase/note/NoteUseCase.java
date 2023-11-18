package co.com.bancolombia.usecase.note;

import co.com.bancolombia.model.note.Note;
import co.com.bancolombia.model.note.gateways.NoteRepository;
import co.com.bancolombia.model.shared.cipher.gateway.CipherRepository;
import co.com.bancolombia.model.shared.exception.AppException;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class NoteUseCase {

    private final NoteRepository noteRepository;
    private final CipherRepository cipherRepository;

    public Note create(Note parNote) {
        parNote.setMessage(cipherRepository.encrypt(parNote.getMessage()));
        return noteRepository.create(parNote);
    }

    public void update(Long id, Note parNote) {
        if (noteRepository.get(id) != null) {
            parNote.setMessage(cipherRepository.encrypt(parNote.getMessage()));
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
        note.setMessage(cipherRepository.decrypt(note.getMessage()));
        return note;
    }

    public List<Note> getAll() {
        List<Note> noteList = noteRepository.getAll();
        for (Note note: noteList) {
            note.setMessage(cipherRepository.decrypt(note.getMessage()));
        }
        return noteList;
    }


}
