package mahyco.mipl.nxg.model;

public class CropModel {

    private  int CropId;
    private  String CropCode;
    private  String CropName;
    private  String CreatedBy;
    private  String CreatedDt;
    private  String ModifiedBy;
    private  String ModifiedDt;

    public CropModel(int cropId, String cropCode, String cropName, String createdBy, String createdDt, String modifiedBy, String modifiedDt) {
        CropId = cropId;
        CropCode = cropCode;
        CropName = cropName;
        CreatedBy = createdBy;
        CreatedDt = createdDt;
        ModifiedBy = modifiedBy;
        ModifiedDt = modifiedDt;
    }

    public int getCropId() {
        return CropId;
    }

    public void setCropId(int cropId) {
        CropId = cropId;
    }

    public String getCropCode() {
        return CropCode;
    }

    public void setCropCode(String cropCode) {
        CropCode = cropCode;
    }

    public String getCropName() {
        return CropName;
    }

    public void setCropName(String cropName) {
        CropName = cropName;
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
        return CropName;
    }
}
