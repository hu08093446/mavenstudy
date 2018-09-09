package idgenerator.mybatis.mybatismapper;

import idgenerator.SequenceEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SequenceEntryMapper{

    @Select("select * from sequenceentry where entitytype = #{entitytype}")
    SequenceEntry getEntry(@Param("entitytype") String entitytype);

    @Insert("INSERT INTO sequenceEntry(EntityType) VALUES(#{entitytype})")
    void save(@Param("entitytype") String entitytype);

    @Update("update sequenceentry set idSegmentStartCode = #{idSegmentStartCode}, " +
            "version = #{version} + 1 where entityType = #{entityType} and version = #{version}")
    int update(SequenceEntry entry);

    @Select("select * from sequenceentry")
    List<SequenceEntry> getAll();
}
