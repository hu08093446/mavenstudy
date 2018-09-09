package idgenerator.mybatis.impl;

import idgenerator.SequenceEntry;
import idgenerator.SequenceStore;
import idgenerator.mybatis.mybatismapper.SequenceEntryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MybatisSequenceStoreImpl implements SequenceStore {
    @Autowired
    private SequenceEntryMapper mapper;

    private static final String FAST = "fast";

    @Override
    public List<SequenceEntry> getAllSequence() {
        return mapper.getAll();
    }

    @Override
    public SequenceEntry getSequence(final String type) {
        SequenceEntry entry = mapper.getEntry(type);
        return entry;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SequenceEntry entry) {
        mapper.save(entry.getEntityType());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SequenceEntry entry) {
        return mapper.update(entry);
    }

}
