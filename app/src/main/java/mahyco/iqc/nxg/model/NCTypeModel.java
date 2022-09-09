package mahyco.iqc.nxg.model;

import com.google.gson.annotations.SerializedName;

public class NCTypeModel {


    public int getProcessId() {
        return ProcessId;
    }

    public void setProcessId(int processId) {
        ProcessId = processId;
    }

    public int getCheckPointId() {
        return CheckPointId;
    }

    public void setCheckPointId(int checkPointId) {
        CheckPointId = checkPointId;
    }



    public boolean isDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
    }

    public boolean isRequired() {
        return IsRequired;
    }

    public void setRequired(boolean required) {
        IsRequired = required;
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

    public String getCheckPointTitle() {
        return CheckPointTitle;
    }

    public void setCheckPointTitle(String checkPointTitle) {
        CheckPointTitle = checkPointTitle;
    }


    int ProcessId;//": 1,
    int CheckPointId;//": 1,

    boolean IsDelete;//": false,
    boolean IsRequired;//": true,
    String CreatedBy;//": "55000066",
    String CreatedDt;//": "2022-05-30T14:14:43.16",
    String ModifiedBy;//": "",
    String ModifiedDt;//": null,
    String ProcessTitle;//": "Fresh Seed Receipt",
    String CheckPointTitle;//": "Verification of documents"

    @SerializedName("NCTypeId")
    int id;

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

    @SerializedName("NCType")
    String  name;
    @Override
    public String toString() {
        return name;
    }
}
