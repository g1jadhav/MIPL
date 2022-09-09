package mahyco.iqc.nxg.model;

public class ProcessInspectionModel {
    public int getPlantId() {
        return PlantId;
    }

    public void setPlantId(int plantId) {
        PlantId = plantId;
    }

    public int getActivityOwnerId() {
        return ActivityOwnerId;
    }

    public void setActivityOwnerId(int activityOwnerId) {
        ActivityOwnerId = activityOwnerId;
    }

    public int getProcessId() {
        return ProcessId;
    }

    public void setProcessId(int processId) {
        ProcessId = processId;
    }

    public int getCropId() {
        return CropId;
    }

    public void setCropId(int cropId) {
        CropId = cropId;
    }

    public int getHybridId() {
        return HybridId;
    }

    public void setHybridId(int hybridId) {
        HybridId = hybridId;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public boolean isHavingNCType() {
        return IsHavingNCType;
    }

    public void setHavingNCType(boolean havingNCType) {
        IsHavingNCType = havingNCType;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getUploadImageNoNCType() {
        return UploadImageNoNCType;
    }

    public void setUploadImageNoNCType(String uploadImageNoNCType) {
        UploadImageNoNCType = uploadImageNoNCType;
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

    public String getPlantTitle() {
        return PlantTitle;
    }

    public void setPlantTitle(String plantTitle) {
        PlantTitle = plantTitle;
    }

    public String getProcessTitle() {
        return ProcessTitle;
    }

    public void setProcessTitle(String processTitle) {
        ProcessTitle = processTitle;
    }

    public String getCropTitle() {
        return CropTitle;
    }

    public void setCropTitle(String cropTitle) {
        CropTitle = cropTitle;
    }

    public String getHybridTitle() {
        return HybridTitle;
    }

    public void setHybridTitle(String hybridTitle) {
        HybridTitle = hybridTitle;
    }

    int PlantId;//": 1,
    int ActivityOwnerId;//": 1,
    int ProcessId;//": 1,
    int CropId;//": 1,
    int HybridId;//": 1,
    String BatchNo;//": "123456",
    boolean IsHavingNCType;//": true,
    String Remark;//": "string",
    String UploadImageNoNCType;//": null,
    String CreatedBy;//": "55000066",
    String CreatedDt;//": "2022-06-06T22:28:00.553",
    String PlantTitle;//": "Saha",
    String ProcessTitle;//": "Fresh Seed Receipt",
    String CropTitle;//": "Chilli",
    String HybridTitle;//": "MAHY 456"
}
