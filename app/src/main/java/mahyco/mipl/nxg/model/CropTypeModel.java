package mahyco.mipl.nxg.model;

public class CropTypeModel {

    private int CropTypeId;
    private String CropType;
//    private String IsDelete;
    private String CreatedBy;
    private String CreatedDt;
    private String ModifiedBy;
    private String ModifiedDt;

    public CropTypeModel(){}
    public CropTypeModel(int cropTypeId, String cropType, String createdBy, String createdDt, String modifiedBy, String modifiedDt) {
        CropTypeId = cropTypeId;
        CropType = cropType;
        CreatedBy = createdBy;
        CreatedDt = createdDt;
        ModifiedBy = modifiedBy;
        ModifiedDt = modifiedDt;
    }

    public int getCropTypeId() {
        return CropTypeId;
    }

    public void setCropTypeId(int cropTypeId) {
        CropTypeId = cropTypeId;
    }

    public String getCropType() {
        return CropType;
    }

    public void setCropType(String cropType) {
        CropType = cropType;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDt() {
        return CreatedDt;
    }

    public void setCreatedDt(String createdDt) {
        CreatedDt = createdDt;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public String getModifiedDt() {
        return ModifiedDt;
    }

    public void setModifiedDt(String modifiedDt) {
        ModifiedDt = modifiedDt;
    }

    public String toString() {
        return CropType;
    }
}
