package mahyco.iqc.nxg.model;

public class CorrectionModel {
    Integer Observation_CorrectionId;//": 1,

    public Integer getObservation_CorrectionId() {
        return Observation_CorrectionId;
    }

    public Integer getPlantInspectionId() {
        return PlantInspectionId;
    }

    public String getObservation_Correction() {
        return Observation_Correction;
    }

    public boolean isDelete() {
        return IsDelete;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public String getCreatedDt() {
        return CreatedDt;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public String getModifiedDt() {
        return ModifiedDt;
    }

    public String getPlantTitle() {
        return PlantTitle;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public String getProcessTitle() {
        return ProcessTitle;
    }

    public String getCropTitle() {
        return CropTitle;
    }

    public String getHybridTitle() {
        return HybridTitle;
    }

    Integer PlantInspectionId;//": 1008,
            String Observation_Correction;//": "Need to Add some more details",
            boolean IsDelete;//": false,
            String CreatedBy;//": "55000067",
            String CreatedDt;//": "2022-08-17T00:00:00",
            String ModifiedBy;//": "",
            String ModifiedDt;//": null,
            String PlantTitle;//": "Saha",
            String BatchNo;//": "258",
            String ProcessTitle;//": "Fresh Seed Receipt",
            String CropTitle;//": "Chilli",
            String HybridTitle;//": "CHILLI MHP-1 HY"
}
