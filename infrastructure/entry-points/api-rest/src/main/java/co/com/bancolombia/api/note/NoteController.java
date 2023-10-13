package co.com.bancolombia.api.note;

import co.com.bancolombia.api.note.mapper.NoteMapper;
import co.com.bancolombia.api.note.request.NoteRequest;
import co.com.bancolombia.api.note.response.NoteResponse;
import co.com.bancolombia.model.note.Note;
import co.com.bancolombia.usecase.note.NoteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class NoteController {
    private final NoteUseCase noteUseCase;

    @PostMapping(path = "/create")
    public NoteResponse create(@RequestBody NoteRequest noteRequest) {
        Note noteToBeCreated = NoteMapper.toModel(noteRequest);
        Note noteCreated = noteUseCase.create(noteToBeCreated);
        return NoteMapper.toResponse(noteCreated);
    }
}
