package mahyco.mipl.nxg.model;

public class OldGrowerSeedDistributionModel {

    private int CountryId;
    private String PlantingYear;
    private int SeasonId;
    private int CropId;
    private int ProductionClusterId;
    private int OrganizerId;
    private int GrowerId;
    private int ProductionCode;
    private String CreatedBy;
    private int FemaleParentSeedBatchId;
    private int MaleParentSeedBatchId;
    private String IssueDt;
    private float SeedParentArea;

    public OldGrowerSeedDistributionModel(int countryId,
                                          String plantingYear,
                                          int seasonId,
                                          int cropId,
                                          int productionClusterId,
                                          int organizerId,
                                          int growerId,
                                          int productionCode,
                                          int femaleParentSeedBatchId,
                                          int maleParentSeedBatchId,
                                          String issueDt,
                                          float seedParentArea,
                                          String createdBy) {

        CountryId = countryId;
        PlantingYear = plantingYear;
        SeasonId = seasonId;
        CropId = cropId;
        IssueDt = issueDt;
        FemaleParentSeedBatchId = femaleParentSeedBatchId;
        ProductionClusterId = productionClusterId;
        OrganizerId = organizerId;
        ProductionCode = productionCode;
        MaleParentSeedBatchId = maleParentSeedBatchId;
        CreatedBy = createdBy;
        GrowerId = growerId;
        SeedParentArea = seedParentArea;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
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

    public int getCropId() {
        return CropId;
    }

    public void setCropId(int cropId) {
        CropId = cropId;
    }

    public int getProductionClusterId() {
        return ProductionClusterId;
    }

    public void setProductionClusterId(int productionClusterId) {
        ProductionClusterId = productionClusterId;
    }

    public int getOrganizerId() {
        return OrganizerId;
    }

    public void setOrganizerId(int organizerId) {
        OrganizerId = organizerId;
    }

    public int getProductionCode() {
        return ProductionCode;
    }

    public void setProductionCode(int productionCode) {
        ProductionCode = productionCode;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
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

    public String getIssueDt() {
        return IssueDt;
    }

    public void setIssueDt(String issueDt) {
        IssueDt = issueDt;
    }

    public int getGrowerId() {
        return GrowerId;
    }

    public void setGrowerId(int growerId) {
        GrowerId = growerId;
    }

    public float getSeedParentArea() {
        return SeedParentArea;
    }

    public void setSeedParentArea(float seedParentArea) {
        SeedParentArea = seedParentArea;
    }


    public OldGrowerSeedDistributionModel() {
    }
}
