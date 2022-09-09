package mahyco.iqc.nxg.model;

import java.util.List;

public class NCModel {

    int PlantId;//": 1,
    int ActivityOwnerId;//": 1,
    int ProcessId;//": 1,
    int CropId;//": 1,
    int HybridId;//": 1,
    String BatchNo;//": "1236548",
    boolean IsHavingNCType;//": true,
    String Remark;//": "",
    String CreatedBy;//": "55000066",
    List<CheckPointModel> checkPointModels;//": [

    List<NCTypeModel> nCTypeModels;//": [

    List<UploadImageModel> uploadImageModels;//": [

    List<ObservationforNCTypeModel> observationforNCTypeModels;//": [
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

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public List<CheckPointModel> getCheckPointModels() {
        return checkPointModels;
    }

    public void setCheckPointModels(List<CheckPointModel> checkPointModels) {
        this.checkPointModels = checkPointModels;
    }

    public List<NCTypeModel> getnCTypeModels() {
        return nCTypeModels;
    }

    public void setnCTypeModels(List<NCTypeModel> nCTypeModels) {
        this.nCTypeModels = nCTypeModels;
    }

    public List<UploadImageModel> getUploadImageModels() {
        return uploadImageModels;
    }

    public void setUploadImageModels(List<UploadImageModel> uploadImageModels) {
        this.uploadImageModels = uploadImageModels;
    }

    public List<ObservationforNCTypeModel> getObservationforNCTypeModels() {
        return observationforNCTypeModels;
    }

    public void setObservationforNCTypeModels(List<ObservationforNCTypeModel> observationforNCTypeModels) {
        this.observationforNCTypeModels = observationforNCTypeModels;
    }




}
