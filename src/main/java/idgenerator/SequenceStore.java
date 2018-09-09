package idgenerator;

import java.util.List;

public interface SequenceStore {
    List<SequenceEntry> getAllSequence();

    SequenceEntry getSequence(String type);

    void save(SequenceEntry entry);

    int update(SequenceEntry entry);
}
