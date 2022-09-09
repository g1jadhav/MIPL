package mahyco.iqc.nxg.model;

import androidx.lifecycle.LifecycleCoroutineScope;

import java.util.List;

public class InspectionModel {


    Integer PlantInspectionId;//": 2,

    public Integer getPlantInspectionId() {
        return PlantInspectionId;
    }

    public void setPlantInspectionId(Integer plantInspectionId) {
        PlantInspectionId = plantInspectionId;
    }

    public Integer getPlantId() {
        return PlantId;
    }

    public void setPlantId(Integer plantId) {
        PlantId = plantId;
    }

    public Integer getActivityOwnerId() {
        return ActivityOwnerId;
    }

    public void setActivityOwnerId(Integer activityOwnerId) {
        ActivityOwnerId = activityOwnerId;
    }

    public Integer getProcessId() {
        return ProcessId;
    }

    public void setProcessId(Integer processId) {
        ProcessId = processId;
    }

    public Integer getCropId() {
        return CropId;
    }

    public void setCropId(Integer cropId) {
        CropId = cropId;
    }

    public Integer getHybridId() {
        return HybridId;
    }

    public void setHybridId(Integer hybridId) {
        HybridId = hybridId;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public String getIsHavingNCType() {
        return IsHavingNCType;
    }

    public void setIsHavingNCType(String isHavingNCType) {
        IsHavingNCType = isHavingNCType;
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

    Integer PlantId;//": 1,
    Integer ActivityOwnerId;//": 1,
    Integer ProcessId;//": 1,
    Integer CropId;//": 1,
    Integer HybridId;//": 1,
    String BatchNo;//": "1236548",
    String IsHavingNCType;//": true,
    String Remark;//": "",
    String CreatedBy;//": "55000066",
    String CreatedDt;//": "2022-06-10T11:03:27.383",
    String PlantTitle;//": "Saha",
    String ProcessTitle;//": "Fresh Seed Receipt",
    String CropTitle;//": "Chilli",
    String HybridTitle;//": "MAHY 456",
    List<CheckPointModel> checkPointModels;
    List<NCTypeModel> nCTypeModels;
    List<UploadImageModel> uploadImageModels;
    List<ObservationforNCTypeModel> observationforNCTypeModels;






    public class CheckPointModel {
        public Integer getInspectionCheckPointId() {
            return InspectionCheckPointId;
        }

        public void setInspectionCheckPointId(Integer inspectionCheckPointId) {
            InspectionCheckPointId = inspectionCheckPointId;
        }

        public Integer getPlantInspectionId() {
            return PlantInspectionId;
        }

        public void setPlantInspectionId(Integer plantInspectionId) {
            PlantInspectionId = plantInspectionId;
        }

        public Integer getCheckPointId() {
            return CheckPointId;
        }

        public void setCheckPointId(Integer checkPointId) {
            CheckPointId = checkPointId;
        }

        public String getCheckPointTitle() {
            return CheckPointTitle;
        }

        public void setCheckPointTitle(String checkPointTitle) {
            CheckPointTitle = checkPointTitle;
        }

        Integer InspectionCheckPointId;//": 2,
        Integer PlantInspectionId;//": 2,
        Integer CheckPointId;//": 1,
        String CheckPointTitle;//": "Verification of documents"
    }

    public class NCTypeModel {
        Integer InspectionNCTypeId;//": 3,
        Integer PlantInspectionId;//": 2,

        public Integer getInspectionNCTypeId() {
            return InspectionNCTypeId;
        }

        public void setInspectionNCTypeId(Integer inspectionNCTypeId) {
            InspectionNCTypeId = inspectionNCTypeId;
        }

        public Integer getPlantInspectionId() {
            return PlantInspectionId;
        }

        public void setPlantInspectionId(Integer plantInspectionId) {
            PlantInspectionId = plantInspectionId;
        }

        public Integer getNCTypeId() {
            return NCTypeId;
        }

        public void setNCTypeId(Integer NCTypeId) {
            this.NCTypeId = NCTypeId;
        }

        public String getNCType() {
            return NCType;
        }

        public void setNCType(String NCType) {
            this.NCType = NCType;
        }

        Integer NCTypeId;//": 1,
        String NCType;//": "Wrong details"

    }

    public class UploadImageModel {
        public Integer getUploadFilesId() {
            return UploadFilesId;
        }

        public void setUploadFilesId(Integer uploadFilesId) {
            UploadFilesId = uploadFilesId;
        }

        public Integer getPlantInspectionId() {
            return PlantInspectionId;
        }

        public void setPlantInspectionId(Integer plantInspectionId) {
            PlantInspectionId = plantInspectionId;
        }

        public Integer getInspectionCheckPointId() {
            return InspectionCheckPointId;
        }

        public void setInspectionCheckPointId(Integer inspectionCheckPointId) {
            InspectionCheckPointId = inspectionCheckPointId;
        }

        public String getFileType() {
            return FileType;
        }

        public void setFileType(String fileType) {
            FileType = fileType;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String fileName) {
            FileName = fileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }

        public String getCheckPointTitle() {
            return CheckPointTitle;
        }

        public void setCheckPointTitle(String checkPointTitle) {
            CheckPointTitle = checkPointTitle;
        }

        Integer UploadFilesId;//": 2,
        Integer PlantInspectionId;//": 2,
        Integer InspectionCheckPointId;//": 0,
        String FileType;//": "Image",
        String FileName;//": "Images_20220609112755736.jpeg",
        String FilePath;//": "IQCTest/UploadFiles/Images",
        String CheckPointTitle;//": ""

    }

    public class ObservationforNCTypeModel {
        Integer InspectionObservationId;//": 1,

        public Integer getInspectionObservationId() {
            return InspectionObservationId;
        }

        public void setInspectionObservationId(Integer inspectionObservationId) {
            InspectionObservationId = inspectionObservationId;
        }

        public Integer getPlantInspectionId() {
            return PlantInspectionId;
        }

        public void setPlantInspectionId(Integer plantInspectionId) {
            PlantInspectionId = plantInspectionId;
        }

        public String getObservation() {
            return Observation;
        }

        public void setObservation(String observation) {
            Observation = observation;
        }

        Integer PlantInspectionId;//": 2,
        String Observation;//": "string"
    }

}

