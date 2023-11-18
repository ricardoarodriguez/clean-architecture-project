package co.com.bancolombia.jpa.note.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteDAO {

    @Id
    @GeneratedValue
    @Column(name = "not_id")
    private Long id;
    @Column(name = "not_message")
    private String message;
    @Column(name = "not_status")
    private String status;
    @Column(name = "not_updated_date")
    private Date updateDate;
    @Column(name = "not_creation_date")
    private Date creationDate;

}
