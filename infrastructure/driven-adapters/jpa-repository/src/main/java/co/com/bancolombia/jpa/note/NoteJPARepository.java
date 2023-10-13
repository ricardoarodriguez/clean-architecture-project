package co.com.bancolombia.jpa.note;

import co.com.bancolombia.jpa.note.model.NoteDAO;
import co.com.bancolombia.model.note.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import javax.transaction.Transactional;

@Transactional
public interface NoteJPARepository extends CrudRepository<NoteDAO, Long>, QueryByExampleExecutor<NoteDAO> {

    @Query("UPDATE notes " +
            "SET not_message = :x " +
            "WHERE not_id =:id")
    NoteDAO updateById(@Param("id") Long id, @Param("x") String status);
}
