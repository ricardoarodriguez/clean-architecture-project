package co.com.bancolombia.usecase.note;

import co.com.bancolombia.model.note.Note;
import co.com.bancolombia.model.note.gateways.NoteRepository;
import co.com.bancolombia.model.shared.cipher.gateway.CipherRepository;
import co.com.bancolombia.model.shared.exception.AppException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class NoteUseCaseTest {
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private CipherRepository cipherRepository;

    @InjectMocks
    private NoteUseCase noteUseCase;

    @BeforeEach
    void setup(){
        openMocks(this);
    }

    @Test
    void testCreateNote() {
        // Given
        Note inputNote = Note.builder()
                .message("message")
                .status("activo")
                .build();

        when(cipherRepository.encrypt(anyString())).thenReturn("Encrypted message");
        when(noteRepository.create(inputNote)).thenReturn(inputNote);

        // When
        Note createdNote = noteUseCase.create(inputNote);

        // Then
        assertEquals(inputNote, createdNote);
        verify(cipherRepository, times(1)).encrypt(anyString());
        verify(noteRepository, times(1)).create(inputNote);
    }

    @Test
    void testUpdateNote() {
        // Given
        Long noteId = 1L;
        Note existingNote = new Note();
        existingNote.setId(noteId);
        when(noteRepository.get(noteId)).thenReturn(existingNote);

        Note updatedNote = Note.builder()
                .message("message")
                .status("activo")
                .build();

        // When
        when(cipherRepository.encrypt(anyString())).thenReturn("Encrypted updated message");
        noteUseCase.update(noteId, updatedNote);

        // Then
        verify(cipherRepository, times(1)).encrypt(anyString());
        verify(noteRepository, times(1)).update(noteId, updatedNote);
    }

    @Test
    void testUpdateNonExistingNote() {
        // Given
        Long noteId = 1L;
        when(noteRepository.get(noteId)).thenReturn(null);
        Note updatedNote = new Note();

        // When/Then
        assertThrows(AppException.class, () -> noteUseCase.update(noteId, updatedNote));
        verifyNoInteractions(cipherRepository);
    }

    @Test
    void testGetNote() {
        // Given
        Long noteId = 1L;
        Note expectedNote = new Note();
        expectedNote.setId(noteId);
        expectedNote.setMessage("Encrypted message");
        when(noteRepository.get(noteId)).thenReturn(expectedNote);
        when(cipherRepository.decrypt("Encrypted message")).thenReturn("Decrypted message");

        // When
        Note retrievedNote = noteUseCase.get(noteId);

        // Then
        assertEquals("Decrypted message", retrievedNote.getMessage());
        verify(cipherRepository, times(1)).decrypt("Encrypted message");
        verify(noteRepository, times(1)).get(noteId);
    }

    @Test
    void testGetNonExistingNote() {
        // Given
        Long noteId = 1L;
        when(noteRepository.get(noteId)).thenReturn(null);

        // When/Then
        assertThrows(AppException.class, () -> noteUseCase.get(noteId));
        verifyNoInteractions(cipherRepository);
        verify(noteRepository, times(1)).get(noteId);
    }

    @Test
    void testGetAllNotes() {
        // Given
        List<Note> expectedNotes = Arrays.asList(new Note(), new Note());
        when(noteRepository.getAll()).thenReturn(expectedNotes);

        // When
        List<Note> retrievedNotes = noteUseCase.getAll();

        // Then
        assertEquals(expectedNotes.size(), retrievedNotes.size());
        verify(noteRepository, times(1)).getAll();
    }
}
