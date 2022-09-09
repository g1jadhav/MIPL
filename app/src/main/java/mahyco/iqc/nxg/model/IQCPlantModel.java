package mahyco.iqc.nxg.model;

public class IQCPlantModel {
int IQCPlantId;//": 1,

    public int getIQCPlantId() {
        return IQCPlantId;
    }

    public void setIQCPlantId(int IQCPlantId) {
        this.IQCPlantId = IQCPlantId;
    }

    public String getPlantTitle() {
        return PlantTitle;
    }

    public void setPlantTitle(String plantTitle) {
        PlantTitle = plantTitle;
    }

    public String getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(String isDelete) {
        IsDelete = isDelete;
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

    String PlantTitle;//": "Saha",
    String IsDelete;//": false,
    String CreatedBy;//": "",
    String CreatedDt;//": null,
    String ModifiedBy;//": "55000066",
    String ModifiedDt;//": "2022-06-03T09:49:10.443"
}
