package mahyco.iqc.nxg.model;

import com.google.gson.annotations.SerializedName;

public class CropModel {
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

    public String getCropCode() {
        return CropCode;
    }

    public void setCropCode(String cropCode) {
        CropCode = cropCode;
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

    @SerializedName("CropId")
    int id;// 1,
    @SerializedName("CropTitle")
    String name;//": "Chilli",
    String CropCode;//": "MAHY 456",
    String IsDelete;//": false,
    String CreatedBy;//": "0",
    String CreatedDt;//": "2022-05-30T11:55:09.61",
    String ModifiedBy;//": "",
    String ModifiedDt;//": null
    @Override
    public String toString() {
        return name;
    }
}
