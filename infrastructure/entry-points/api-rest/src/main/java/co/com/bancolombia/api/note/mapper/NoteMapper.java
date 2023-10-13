package co.com.bancolombia.api.note.mapper;

import co.com.bancolombia.api.note.request.NoteRequest;
import co.com.bancolombia.api.note.response.NoteResponse;
import co.com.bancolombia.model.note.Note;

public class NoteMapper {

    public static Note toModel(NoteRequest noteRequest) {
        return Note.builder().message(noteRequest.getMessage()).status(noteRequest.getStatus()).build();
    }

    public static NoteResponse toResponse(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .message(note.getMessage())
                .status(note.getStatus())
                .creationDate(note.getCreationDate())
                .updateDate(note.getUpdateDate())
                .build();
    }
}
