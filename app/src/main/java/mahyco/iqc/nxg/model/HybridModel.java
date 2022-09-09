package mahyco.iqc.nxg.model;

import com.google.gson.annotations.SerializedName;

public class HybridModel {
    @SerializedName("HybridId")
    int id;//": 3,

            String CropId;//": 1,
    @SerializedName("HybridTitle")
    String name;//": "CHILLI MHP-1 HY",
    String IsDelete;//": false,
    String CreatedBy;//": "55000066",
    String CreatedDt;//": "2022-06-06T10:52:22.01",
    String ModifiedBy;//": "",
    String ModifiedDt;//": null,

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCropId() {
        return CropId;
    }

    public void setCropId(String cropId) {
        CropId = cropId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCropTitle() {
        return CropTitle;
    }

    public void setCropTitle(String cropTitle) {
        CropTitle = cropTitle;
    }

    public String getCropCode() {
        return CropCode;
    }

    public void setCropCode(String cropCode) {
        CropCode = cropCode;
    }

    String CropTitle;//": "Chilli",
    String CropCode;//": "MAHY 456"
    @Override
    public String toString() {
        return name;
    }
}
