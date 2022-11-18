package mahyco.mipl.nxg.model;

public class StoreAreaModel {

    String plantingYear;
    String productionCode;
    String FemaleBatchNo;
    String MaleBatchNo;
    int FemaleParentSeedBatchId;
    int MaleParentSeedBatchId;
    int ParentSeedReceiptId;
    String ParentSeedReceiptType;
    float SeedProductionArea;
    int ClusterId;

    public StoreAreaModel(String plantingYear, String productionCode, String femaleBatchNo, String maleBatchNo,float seedProductionArea, int femaleParentSeedBatchId, int maleParentSeedBatchId, int parentSeedReceiptId, String parentSeedReceiptType, int clusterId) {
        this.plantingYear = plantingYear;
        this.productionCode = productionCode;
        FemaleBatchNo = femaleBatchNo;
        MaleBatchNo = maleBatchNo;
        FemaleParentSeedBatchId = femaleParentSeedBatchId;
        MaleParentSeedBatchId = maleParentSeedBatchId;
        ParentSeedReceiptId = parentSeedReceiptId;
        ParentSeedReceiptType = parentSeedReceiptType;
        SeedProductionArea = seedProductionArea;
        ClusterId = clusterId;
    }

    public String getPlantingYear() {
        return plantingYear;
    }

    public void setPlantingYear(String plantingYear) {
        this.plantingYear = plantingYear;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getFemaleBatchNo() {
        return FemaleBatchNo;
    }

    public void setFemaleBatchNo(String femaleBatchNo) {
        FemaleBatchNo = femaleBatchNo;
    }

    public String getMaleBatchNo() {
        return MaleBatchNo;
    }

    public void setMaleBatchNo(String maleBatchNo) {
        MaleBatchNo = maleBatchNo;
    }

    public int getFemaleParentSeedBatchId() {
        return FemaleParentSeedBatchId;
    }

    public void setFemaleParentSeedBatchId(int femaleParentSeedBatchId) {
        FemaleParentSeedBatchId = femaleParentSeedBatchId;
    }

    public int getMaleParentSeedBatchId() {
        return MaleParentSeedBatchId;
    }

    public void setMaleParentSeedBatchId(int maleParentSeedBatchId) {
        MaleParentSeedBatchId = maleParentSeedBatchId;
    }

    public int getParentSeedReceiptId() {
        return ParentSeedReceiptId;
    }

    public void setParentSeedReceiptId(int parentSeedReceiptId) {
        ParentSeedReceiptId = parentSeedReceiptId;
    }

    public String getParentSeedReceiptType() {
        return ParentSeedReceiptType;
    }

    public void setParentSeedReceiptType(String parentSeedReceiptType) {
        ParentSeedReceiptType = parentSeedReceiptType;
    }

    public float getSeedProductionArea() {
        return SeedProductionArea;
    }

    public void setSeedProductionArea(float seedProductionArea) {
        SeedProductionArea = seedProductionArea;
    }

    public int getClusterId() {
        return ClusterId;
    }

    public void setClusterId(int clusterId) {
        ClusterId = clusterId;
    }
}
