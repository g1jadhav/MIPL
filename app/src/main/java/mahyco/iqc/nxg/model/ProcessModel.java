package mahyco.iqc.nxg.model;

import com.google.gson.annotations.SerializedName;

public class ProcessModel {


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

    @SerializedName("ProcessId")
    int id;//": 1,

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

    @SerializedName("ProcessTitle")
    String name;//": "Fresh Seed Receipt",
    String IsDelete;//": false,
    String CreatedBy;//": "",
    String CreatedDt;//": null,
    String ModifiedBy;//": "",
    String ModifiedDt;//": null

    @Override
    public String toString() {
        return name;
    }
}
