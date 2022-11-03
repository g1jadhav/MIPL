package mahyco.mipl.nxg.model;

public class SeedBatchNoModel {
    private int ParentSeedBatchId;
    private int ParentSeedReceiptId;
    private int CountryId;
    private String ParentType;
    private String BatchNo;
    private int NoOfPackets;
    private int QTYInKG;
    private int SeedArea;
    // IsDelete: false,
    private String CreatedBy;
    private String CreatedDt;
    private String ModifiedBy;
    private String ModifiedDt;
    private String ProductionCode;
    private String ParentSeedReceiptType;

    public SeedBatchNoModel(int parentSeedBatchId, int parentSeedReceiptId, int countryId, String parentType, String batchNo, int noOfPackets, int QTYInKG, int seedArea, String createdBy, String createdDt, String modifiedBy, String modifiedDt, String productionCode, String parentSeedReceiptType) {
        ParentSeedBatchId = parentSeedBatchId;
        ParentSeedReceiptId = parentSeedReceiptId;
        CountryId = countryId;
        ParentType = parentType;
        BatchNo = batchNo;
        NoOfPackets = noOfPackets;
        this.QTYInKG = QTYInKG;
        SeedArea = seedArea;
        CreatedBy = createdBy;
        CreatedDt = createdDt;
        ModifiedBy = modifiedBy;
        ModifiedDt = modifiedDt;
        ProductionCode = productionCode;
        ParentSeedReceiptType = parentSeedReceiptType;
    }

    public int getParentSeedBatchId() {
        return ParentSeedBatchId;
    }

    public void setParentSeedBatchId(int parentSeedBatchId) {
        ParentSeedBatchId = parentSeedBatchId;
    }

    public int getParentSeedReceiptId() {
        return ParentSeedReceiptId;
    }

    public void setParentSeedReceiptId(int parentSeedReceiptId) {
        ParentSeedReceiptId = parentSeedReceiptId;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public String getParentType() {
        return ParentType;
    }

    public void setParentType(String parentType) {
        ParentType = parentType;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public int getNoOfPackets() {
        return NoOfPackets;
    }

    public void setNoOfPackets(int noOfPackets) {
        NoOfPackets = noOfPackets;
    }

    public int getQTYInKG() {
        return QTYInKG;
    }

    public void setQTYInKG(int QTYInKG) {
        this.QTYInKG = QTYInKG;
    }

    public int getSeedArea() {
        return SeedArea;
    }

    public void setSeedArea(int seedArea) {
        SeedArea = seedArea;
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

    public String getProductionCode() {
        return ProductionCode;
    }

    public void setProductionCode(String productionCode) {
        ProductionCode = productionCode;
    }

    public String getParentSeedReceiptType() {
        return ParentSeedReceiptType;
    }

    public void setParentSeedReceiptType(String parentSeedReceiptType) {
        ParentSeedReceiptType = parentSeedReceiptType;
    }

    public String toString() {
        return BatchNo + " " + NoOfPackets + "Pkt/s";
    }
}
