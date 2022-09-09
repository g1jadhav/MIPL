package mahyco.iqc.nxg.model;

public class OwnerModel {


    int ActivityOwnerId;//": 62,

    public int getActivityOwnerId() {
        return ActivityOwnerId;
    }

    public void setActivityOwnerId(int activityOwnerId) {
        ActivityOwnerId = activityOwnerId;
    }

    public int getIQCPlant() {
        return IQCPlant;
    }

    public void setIQCPlant(int IQCPlant) {
        this.IQCPlant = IQCPlant;
    }

    public int getContractPlantId() {
        return ContractPlantId;
    }

    public void setContractPlantId(int contractPlantId) {
        ContractPlantId = contractPlantId;
    }

    public String getActivityOwner() {
        return ActivityOwner;
    }

    public void setActivityOwner(String activityOwner) {
        ActivityOwner = activityOwner;
    }

    public String getPlantTitle() {
        return PlantTitle;
    }

    public void setPlantTitle(String plantTitle) {
        PlantTitle = plantTitle;
    }

    public String getContractPlantTitle() {
        return ContractPlantTitle;
    }

    public void setContractPlantTitle(String contractPlantTitle) {
        ContractPlantTitle = contractPlantTitle;
    }

    public boolean isDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
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

    int IQCPlant;//": 4,
    int ContractPlantId;//": 2,
    String ActivityOwner;//": "Junaid Siddiqui",
    String PlantTitle;//": "Contract Plants",
    String ContractPlantTitle;//": "XYZ2",
    boolean IsDelete;//": false,
    String CreatedBy;//": "55000066",
    String CreatedDt;//": "2022-06-15T10:26:17.023",
    String ModifiedBy;//": "55000066",
    String ModifiedDt;//": "2022-06-15T10:26:45.613"


    @Override
    public String toString() {
        return ActivityOwner;
    }
}
