package co.com.bancolombia.model.note;

import co.com.bancolombia.model.shared.exception.AppException;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class Note {

    private Long id;
    private String message;
    private Boolean status;
    private Date updateDate;
    private Date creationDate;

    @Builder(toBuilder = true)
    public Note(String message, Boolean status) {

        if (message != null && !message.isEmpty() && message.length() < 255) {
            this.message = message.trim();
        } else {
            throw new AppException("El mensaje debe contener un valor que se encuentre entre 0 y 255 caracteres");
        }

        if (status != null) {
            this.status = status;
        } else {
            throw new AppException("El estado debe contener un valor");
        }

        Date now = Date.from(Instant.now());
        this.updateDate = now;
        this.creationDate = now;
    }
}
