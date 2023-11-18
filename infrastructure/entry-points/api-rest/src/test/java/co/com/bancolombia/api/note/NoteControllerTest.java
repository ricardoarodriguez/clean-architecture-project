package co.com.bancolombia.api.note;

import co.com.bancolombia.api.note.mapper.NoteMapper;
import co.com.bancolombia.api.note.request.NoteRequest;
import co.com.bancolombia.api.note.response.NoteResponse;
import co.com.bancolombia.model.note.Note;
import co.com.bancolombia.usecase.note.NoteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NoteControllerTest {

    @Mock
    private NoteUseCase noteUseCase;

    @InjectMocks
    private NoteController noteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNoteTest() {
        // Given
        NoteRequest request = new NoteRequest("Title", "Content");
        Note noteCreated = Note.builder()
                .message(request.getMessage())
                .status(request.getStatus())
                .build();

        // When
        when(noteUseCase.create(any(Note.class))).thenReturn(noteCreated);

        ResponseEntity<NoteResponse> response = noteController.create(request);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(noteCreated.getMessage(), Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals(noteCreated.getStatus(), response.getBody().getStatus());

        // Verify interaction with noteUseCase
        verify(noteUseCase, times(1)).create(any(Note.class));
    }

    @Test
    void getAllNotesTest() {
        // Given
        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note("Message", "ACTIVO"));
        noteList.add(new Note("Message", "ACTIVO"));

        when(noteUseCase.getAll()).thenReturn(noteList);

        // When
        ResponseEntity<List<NoteResponse>> response = noteController.getAll();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());

        verify(noteUseCase, times(1)).getAll();
    }

    @Test
    void getNoteByIdTest() {
        // Given
        Long noteId = 1L;
        Note note = new Note("Message", "ACTIVO");

        when(noteUseCase.get(noteId)).thenReturn(note);

        // When
        ResponseEntity<NoteResponse> response = noteController.getById(noteId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(noteUseCase, times(1)).get(noteId);
    }

    @Test
    void updateNoteByIdTest() {
        // Given
        Long noteId = 1L;
        NoteRequest request = new NoteRequest("Message Update", "Activo Update");

        // When
        ResponseEntity<String> response = noteController.updateById(noteId, request);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());


        verify(noteUseCase, times(1)).update(eq(noteId), any(Note.class));
    }

}
