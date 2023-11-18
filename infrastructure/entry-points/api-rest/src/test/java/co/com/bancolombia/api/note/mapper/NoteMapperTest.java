package co.com.bancolombia.api.note.mapper;

import co.com.bancolombia.api.note.request.NoteRequest;
import co.com.bancolombia.api.note.response.NoteResponse;
import co.com.bancolombia.model.note.Note;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteMapperTest {

    @Test
    void testToModel() {
        // Given
        NoteRequest noteRequest = new NoteRequest("Test message", "Pending");

        // When
        Note note = NoteMapper.toModel(noteRequest);

        // Then
        assertEquals(noteRequest.getMessage(), note.getMessage());
        assertEquals(noteRequest.getStatus(), note.getStatus());
    }

    @Test
    void testToResponse() {
        // Given
        Note note = new Note();
        note.setId(1L);
        note.setMessage("Test message");
        note.setStatus("Pending");
        note.setCreationDate(Date.from(Instant.now()));
        note.setUpdateDate(Date.from(Instant.now()));

        // When
        NoteResponse noteResponse = NoteMapper.toResponse(note);

        // Then
        assertEquals(note.getId(), noteResponse.getId());
        assertEquals(note.getMessage(), noteResponse.getMessage());
        assertEquals(note.getStatus(), noteResponse.getStatus());
        assertEquals(note.getCreationDate(), noteResponse.getCreationDate());
        assertEquals(note.getUpdateDate(), noteResponse.getUpdateDate());
    }
}
