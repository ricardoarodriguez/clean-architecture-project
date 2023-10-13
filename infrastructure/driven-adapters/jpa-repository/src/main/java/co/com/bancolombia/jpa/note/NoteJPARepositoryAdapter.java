package co.com.bancolombia.jpa.note;

import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.jpa.note.model.NoteDAO;
import co.com.bancolombia.model.note.Note;
import co.com.bancolombia.model.note.gateways.NoteRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteJPARepositoryAdapter extends AdapterOperations<Note, NoteDAO, Long, NoteJPARepository>
        implements NoteRepository {

    public NoteJPARepositoryAdapter(NoteJPARepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Note.class/* change for domain model */));
    }

    @Override
    public Note create(Note parNote) {
        return save(parNote);
    }

    @Override
    public Note update(Long id, Note parNote) {
//        NoteDAO noteDAO = toData(parNote);
//        return toEntity(repository.updateById(id, noteDAO));
        return save(parNote);
    }

    @Override
    public Note get(Long id) {
        return findById(id);
    }

    @Override
    public List<Note> getAll() {
        return findAll();
    }
}
