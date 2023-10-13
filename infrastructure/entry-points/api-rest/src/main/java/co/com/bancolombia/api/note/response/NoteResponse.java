package co.com.bancolombia.api.note.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NoteResponse {
    private String message;
    private Long id;
    private Boolean status;
    private Date updateDate;
    private Date creationDate;
}
