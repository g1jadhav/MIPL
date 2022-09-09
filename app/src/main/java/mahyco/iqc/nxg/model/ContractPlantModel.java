package mahyco.iqc.nxg.model;

import com.google.gson.annotations.SerializedName;

public class ContractPlantModel {
    @SerializedName("ContractPlantId")
    int id;//": 1,



    public int getPlantId() {
        return PlantId;
    }

    public void setPlantId(int plantId) {
        PlantId = plantId;
    }


    public String getPlantTitle() {
        return PlantTitle;
    }

    public void setPlantTitle(String plantTitle) {
        PlantTitle = plantTitle;
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

    int PlantId;//": 4,

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("ContractPlantTitle")
    String name;//": "XYZ1",
    String PlantTitle;//": "Contract Plants",
    boolean IsDelete;//": false,
    String CreatedBy;//": "55000066",
    String CreatedDt;//": "2022-06-14T10:55:47.7",
    String ModifiedBy;//": "55000066",
    String ModifiedDt;//": "2022-06-14T11:02:19.257"
    @Override
    public String toString() {
        return name;
    }

}
