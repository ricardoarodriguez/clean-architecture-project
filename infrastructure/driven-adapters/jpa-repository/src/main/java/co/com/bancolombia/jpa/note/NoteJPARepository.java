package co.com.bancolombia.jpa.note;

import co.com.bancolombia.jpa.note.model.NoteDAO;
import co.com.bancolombia.model.note.Note;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import javax.transaction.Transactional;

@Transactional
public interface NoteJPARepository extends CrudRepository<NoteDAO, Long>, QueryByExampleExecutor<NoteDAO> {

    @Modifying
    @Query("UPDATE NoteDAO " +
            "SET not_message = :message, " +
            "not_status = :status " +
            "WHERE not_id = :id")
    Integer updateById(@Param("id") Long id, @Param("message") String message, @Param("status") String status);
}
