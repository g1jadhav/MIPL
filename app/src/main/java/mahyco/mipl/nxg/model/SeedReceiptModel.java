package mahyco.mipl.nxg.model;

public class SeedReceiptModel {

    private int ParentSeedReceiptId;
    private int SeedProductionTargetId;
    private String ProductionCode;
    private String ParentSeedReceiptType;
    private int CountryId;
    private int ProductionClusterId;
    private int UserId;
    private String PlantingYear;
    private int SeasonId;
    private int CropTypeId;
    private int CropId;
    private int ProductId;
    private float PlannedArea;
    private float PlannedProcessedQty;
    private float PlannedUnprocessedQty;
    private float NoofFemalePkts;
    private float NoofMalePkts;
    private float FemaleSeedRate;
    private float FemaleSeedPacking;
    private float MaleSeedRate;
    private float MaleSeedPacking;
    private float FemaleParentSeedsArea;
    private float MaleParentSeedArea;
    private float TotalFemaleParentSeeds;
    private float TotalMaleParentSeeds;
    private String STONo_DeliveryChallanNo;
    private String ParentSeedReceiptDt;
    /*    IsDelete: false,*/
    private String CreatedBy;
    private String CreatedDt;
    private String ModifiedBy;
    private String ModifiedDt;
    private String CountryName;
    private String Season;
    private String CropCode;
    private String CropName;
    private String CropType;
    private String ProductionCluster;
    private String OrganizerName;
    private String ProductName;

    public SeedReceiptModel(int parentSeedReceiptId, int seedProductionTargetId, String productionCode, String parentSeedReceiptType, int countryId, int productionClusterId, int userId, String plantingYear, int seasonId, int cropTypeId, int cropId, int productId, float plannedArea, float plannedProcessedQty, float plannedUnprocessedQty, float noofFemalePkts, float noofMalePkts, float femaleSeedRate, float femaleSeedPacking, float maleSeedRate, float maleSeedPacking, float femaleParentSeedsArea, float maleParentSeedArea, float totalFemaleParentSeeds, float totalMaleParentSeeds, String sTONo_DeliveryChallanNo, String parentSeedReceiptDt, String createdBy, String createdDt, String modifiedBy, String modifiedDt, String countryName, String season, String cropCode, String cropName, String cropType, String productionCluster, String organizerName, String productName, String fullName) {
        ParentSeedReceiptId = parentSeedReceiptId;
        SeedProductionTargetId = seedProductionTargetId;
        ProductionCode = productionCode;
        ParentSeedReceiptType = parentSeedReceiptType;
        CountryId = countryId;
        ProductionClusterId = productionClusterId;
        UserId = userId;
        PlantingYear = plantingYear;
        SeasonId = seasonId;
        CropTypeId = cropTypeId;
        CropId = cropId;
        ProductId = productId;
        PlannedArea = plannedArea;
        PlannedProcessedQty = plannedProcessedQty;
        PlannedUnprocessedQty = plannedUnprocessedQty;
        NoofFemalePkts = noofFemalePkts;
        NoofMalePkts = noofMalePkts;
        FemaleSeedRate = femaleSeedRate;
        FemaleSeedPacking = femaleSeedPacking;
        MaleSeedRate = maleSeedRate;
        MaleSeedPacking = maleSeedPacking;
        FemaleParentSeedsArea = femaleParentSeedsArea;
        MaleParentSeedArea = maleParentSeedArea;
        TotalFemaleParentSeeds = totalFemaleParentSeeds;
        TotalMaleParentSeeds = totalMaleParentSeeds;
        STONo_DeliveryChallanNo = sTONo_DeliveryChallanNo;
        ParentSeedReceiptDt = parentSeedReceiptDt;
        CreatedBy = createdBy;
        CreatedDt = createdDt;
        ModifiedBy = modifiedBy;
        ModifiedDt = modifiedDt;
        CountryName = countryName;
        Season = season;
        CropCode = cropCode;
        CropName = cropName;
        CropType = cropType;
        ProductionCluster = productionCluster;
        OrganizerName = organizerName;
        ProductName = productName;
        FullName = fullName;
    }

    public int getParentSeedReceiptId() {
        return ParentSeedReceiptId;
    }

    public void setParentSeedReceiptId(int parentSeedReceiptId) {
        ParentSeedReceiptId = parentSeedReceiptId;
    }

    public int getSeedProductionTargetId() {
        return SeedProductionTargetId;
    }

    public void setSeedProductionTargetId(int seedProductionTargetId) {
        SeedProductionTargetId = seedProductionTargetId;
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

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public int getProductionClusterId() {
        return ProductionClusterId;
    }

    public void setProductionClusterId(int productionClusterId) {
        ProductionClusterId = productionClusterId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getPlantingYear() {
        return PlantingYear;
    }

    public void setPlantingYear(String plantingYear) {
        PlantingYear = plantingYear;
    }

    public int getSeasonId() {
        return SeasonId;
    }

    public void setSeasonId(int seasonId) {
        SeasonId = seasonId;
    }

    public int getCropTypeId() {
        return CropTypeId;
    }

    public void setCropTypeId(int cropTypeId) {
        CropTypeId = cropTypeId;
    }

    public int getCropId() {
        return CropId;
    }

    public void setCropId(int cropId) {
        CropId = cropId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public float getPlannedArea() {
        return PlannedArea;
    }

    public void setPlannedArea(float plannedArea) {
        PlannedArea = plannedArea;
    }

    public float getPlannedProcessedQty() {
        return PlannedProcessedQty;
    }

    public void setPlannedProcessedQty(float plannedProcessedQty) {
        PlannedProcessedQty = plannedProcessedQty;
    }

    public float getPlannedUnprocessedQty() {
        return PlannedUnprocessedQty;
    }

    public void setPlannedUnprocessedQty(float plannedUnprocessedQty) {
        PlannedUnprocessedQty = plannedUnprocessedQty;
    }

    public float getNoofFemalePkts() {
        return NoofFemalePkts;
    }

    public void setNoofFemalePkts(float noofFemalePkts) {
        NoofFemalePkts = noofFemalePkts;
    }

    public float getNoofMalePkts() {
        return NoofMalePkts;
    }

    public void setNoofMalePkts(float noofMalePkts) {
        NoofMalePkts = noofMalePkts;
    }

    public float getFemaleSeedRate() {
        return FemaleSeedRate;
    }

    public void setFemaleSeedRate(float femaleSeedRate) {
        FemaleSeedRate = femaleSeedRate;
    }

    public float getFemaleSeedPacking() {
        return FemaleSeedPacking;
    }

    public void setFemaleSeedPacking(float femaleSeedPacking) {
        FemaleSeedPacking = femaleSeedPacking;
    }

    public float getMaleSeedRate() {
        return MaleSeedRate;
    }

    public void setMaleSeedRate(int maleSeedRate) {
        MaleSeedRate = maleSeedRate;
    }

    public float getMaleSeedPacking() {
        return MaleSeedPacking;
    }

    public void setMaleSeedPacking(float maleSeedPacking) {
        MaleSeedPacking = maleSeedPacking;
    }

    public float getFemaleParentSeedsArea() {
        return FemaleParentSeedsArea;
    }

    public void setFemaleParentSeedsArea(float femaleParentSeedsArea) {
        FemaleParentSeedsArea = femaleParentSeedsArea;
    }

    public float getMaleParentSeedArea() {
        return MaleParentSeedArea;
    }

    public void setMaleParentSeedArea(float maleParentSeedArea) {
        MaleParentSeedArea = maleParentSeedArea;
    }

    public float getTotalFemaleParentSeeds() {
        return TotalFemaleParentSeeds;
    }

    public void setTotalFemaleParentSeeds(float totalFemaleParentSeeds) {
        TotalFemaleParentSeeds = totalFemaleParentSeeds;
    }

    public float getTotalMaleParentSeeds() {
        return TotalMaleParentSeeds;
    }

    public void setTotalMaleParentSeeds(float totalMaleParentSeeds) {
        TotalMaleParentSeeds = totalMaleParentSeeds;
    }

    public String getSTONo_DeliveryChallanNo() {
        return STONo_DeliveryChallanNo;
    }

    public void setSTONo_DeliveryChallanNo(String STONo_DeliveryChallanNo) {
        this.STONo_DeliveryChallanNo = STONo_DeliveryChallanNo;
    }

    public String getParentSeedReceiptDt() {
        return ParentSeedReceiptDt;
    }

    public void setParentSeedReceiptDt(String parentSeedReceiptDt) {
        ParentSeedReceiptDt = parentSeedReceiptDt;
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

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String season) {
        Season = season;
    }

    public String getCropCode() {
        return CropCode;
    }

    public void setCropCode(String cropCode) {
        CropCode = cropCode;
    }

    public String getCropName() {
        return CropName;
    }

    public void setCropName(String cropName) {
        CropName = cropName;
    }

    public String getCropType() {
        return CropType;
    }

    public void setCropType(String cropType) {
        CropType = cropType;
    }

    public String getProductionCluster() {
        return ProductionCluster;
    }

    public void setProductionCluster(String productionCluster) {
        ProductionCluster = productionCluster;
    }

    public String getOrganizerName() {
        return OrganizerName;
    }

    public void setOrganizerName(String organizerName) {
        OrganizerName = organizerName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    private String FullName;

    public String toString() {
        return ProductionCode;
    }
}
