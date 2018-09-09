package idgenerator;

public class SequenceEntry {

    // 主键
    private String entityType;

    private String entityCode;

    private String dataCenterCode;

    private String idSegmentStartCode;

    private Integer stepSize;

    // 乐观锁控制
    private Integer version;

    public SequenceEntry() {

    }

    public SequenceEntry(String entityType, String entityCode, String dataCenterCode, String idSegmentStartCode, Integer stepSize, Integer version) {
        this.entityType = entityType;
        this.entityCode = entityCode;
        this.dataCenterCode = dataCenterCode;
        this.idSegmentStartCode = idSegmentStartCode;
        this.stepSize = stepSize;
        this.version = version;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getDataCenterCode() {
        return dataCenterCode;
    }

    public void setDataCenterCode(String dataCenterCode) {
        this.dataCenterCode = dataCenterCode;
    }

    public String getIdSegmentStartCode() {
        return idSegmentStartCode;
    }

    public void setIdSegmentStartCode(String idSegmentStartCode) {
        this.idSegmentStartCode = idSegmentStartCode;
    }

    public Integer getStepSize() {
        return stepSize;
    }

    public void setStepSize(Integer stepSize) {
        this.stepSize = stepSize;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SequenceEntry{" +
                "entityType='" + entityType + '\'' +
                ", entityCode='" + entityCode + '\'' +
                ", dataCenterCode='" + dataCenterCode + '\'' +
                ", idSegmentStartCode='" + idSegmentStartCode + '\'' +
                ", stepSize=" + stepSize +
                ", version=" + version +
                '}';
    }
}
