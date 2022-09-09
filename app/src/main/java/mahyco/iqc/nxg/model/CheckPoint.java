package mahyco.iqc.nxg.model;

import com.google.gson.annotations.SerializedName;

public class CheckPoint {

    @SerializedName("CheckPointId")
    int id;//": 1,

    String ProcessId;//": "1",

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcessId() {
        return ProcessId;
    }

    public void setProcessId(String processId) {
        ProcessId = processId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProcessTitle() {
        return ProcessTitle;
    }

    public void setProcessTitle(String processTitle) {
        ProcessTitle = processTitle;
    }

    @SerializedName("CheckPointTitle")
    String name;//": "Verification of documents",

    public boolean isRequired() {
        return IsRequired;
    }

    public void setRequired(boolean required) {
        IsRequired = required;
    }

    public boolean isDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
    }

    boolean IsRequired;//": true,
    boolean IsDelete;//": false,
    String CreatedBy;//": "",
    String CreatedDt;//": null,
    String ModifiedBy;//": "55000066",
    String ModifiedDt;//": "2022-06-03T10:33:26.777",
    String ProcessTitle;//": "Fresh Seed Receipt"

    @Override
    public String toString() {
        return name;
    }
}
