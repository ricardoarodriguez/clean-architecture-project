package co.com.bancolombia.api.note.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteRequest {
    private String message;
    private Boolean status;
}
