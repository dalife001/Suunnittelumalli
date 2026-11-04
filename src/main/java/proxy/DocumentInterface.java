package proxy;

import java.time.LocalDate;

public interface DocumentInterface {
    String getContent(User user);

    LocalDate getCreationDate();
}
