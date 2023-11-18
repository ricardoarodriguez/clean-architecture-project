package co.com.bancolombia.api.note;

import co.com.bancolombia.api.note.mapper.NoteMapper;
import co.com.bancolombia.api.note.request.NoteRequest;
import co.com.bancolombia.api.note.response.NoteResponse;
import co.com.bancolombia.model.note.Note;
import co.com.bancolombia.usecase.note.NoteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(path = "/getAll")
    public List<NoteResponse> getAll() {
        List<NoteResponse> noteResponseList = new ArrayList<>();
        List<Note> noteList = noteUseCase.getAll();
        for (Note note: noteList) {
            noteResponseList.add(NoteMapper.toResponse(note));
        }
        return noteResponseList;
    }

    @GetMapping(path = "/get")
    public ResponseEntity<NoteResponse> getById(@RequestParam Long id) {
        Note note = noteUseCase.get(id);
        if(note == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(NoteMapper.toResponse(note));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateById(@RequestParam Long id, @RequestBody NoteRequest request) {
        Note noteToBeUpdated = NoteMapper.toModel(request);
        noteUseCase.update(id, noteToBeUpdated);
        return ResponseEntity.ok("Updated");
    }
}
