package idgenerator;

public class EntityIDRecord {

    private String entityType;

    private String entityCode;

    private String dataCenterCode;

    private String idSegmentStartCode;

    private Integer stepSize;

    private Integer currentIndex;

    public EntityIDRecord() {

    }

    public EntityIDRecord(String entityType, String entityCode, String dataCenterCode, String idSegmentStartCode, Integer stepSize, Integer currentIndex) {
        this.entityType = entityType;
        this.entityCode = entityCode;
        this.dataCenterCode = dataCenterCode;
        this.idSegmentStartCode = idSegmentStartCode;
        this.stepSize = stepSize;
        this.currentIndex = currentIndex;
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

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    @Override
    public String toString() {
        return "EntityIDRecord{" +
                "entityType='" + entityType + '\'' +
                ", entityCode='" + entityCode + '\'' +
                ", dataCenterCode='" + dataCenterCode + '\'' +
                ", idSegmentStartCode='" + idSegmentStartCode + '\'' +
                ", stepSize=" + stepSize +
                ", currentIndex=" + currentIndex +
                '}';
    }
}
