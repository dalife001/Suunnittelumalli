package memento;

import java.time.LocalDateTime;

public interface IMemento {
    LocalDateTime getSavedAt();

    String getDescription();
}
