package mahyco.mipl.nxg.model;

public class OldGrowerSeedDistributionModel {

    int CountryId;//": 0,
    int CountryMasterId;//": 0,
    String DistributedBy;//": "string",
    String Season;//": "string",
    String UniqueCode;//": "string",
    String Crop;//": "string",
    String ProductionCode;//": "string",
    String PlantingYear;//": "string",
    String StaffName;
    String SeedProductionArea;
    String SeedIssueLocation;
    String SeedBatchNoFemale;
    String SeedBatchNoMale;
    String GrowerName;
    String GrowerAddress;
    int TempId;
    int LoginId;
    String IssueDate;
    public OldGrowerSeedDistributionModel() {
    }

    public OldGrowerSeedDistributionModel(int countryId, int countryMasterId, String plantingYear, String season,
                                          String uniqueCode, String staffName, String crop,
                                          String distributedBy, String productionCode, String seedProductionArea,
                                          String seedIssueLocation, String seedBatchNoFemale, String seedBatchNoMale,
                                          String growerName, String growerAddress, int loginId, int tempId, String issueDate) {

        GrowerName = growerName;
        GrowerAddress = growerAddress;
        SeedBatchNoFemale = seedBatchNoFemale;
        SeedBatchNoMale = seedBatchNoMale;
        SeedIssueLocation = seedIssueLocation;
        SeedProductionArea = seedProductionArea;
        PlantingYear = plantingYear;
        CountryId = countryId;
        CountryMasterId = countryMasterId;
        Season = season;
        UniqueCode = uniqueCode;
        StaffName = staffName;
        Crop = crop;
        DistributedBy = distributedBy;
        ProductionCode = productionCode;
        TempId = tempId;
        LoginId = loginId;
        IssueDate = issueDate;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }

    public int getLoginId() {
        return LoginId;
    }

    public void setLoginId(int loginId) {
        LoginId = loginId;
    }

    public String getGrowerName() {
        return GrowerName;
    }

    public void setGrowerName(String growerName) {
        GrowerName = growerName;
    }

    public String getGrowerAddress() {
        return GrowerAddress;
    }

    public void setGrowerAddress(String growerAddress) {
        GrowerAddress = growerAddress;
    }

    public String getSeedBatchNoFemale() {
        return SeedBatchNoFemale;
    }

    public void setSeedBatchNoFemale(String seedBatchNoFemale) {
        SeedBatchNoFemale = seedBatchNoFemale;
    }

    public String getSeedBatchNoMale() {
        return SeedBatchNoMale;
    }

    public void setSeedBatchNoMale(String seedBatchNoMale) {
        SeedBatchNoMale = seedBatchNoMale;
    }

    public String getSeedIssueLocation() {
        return SeedIssueLocation;
    }

    public void setSeedIssueLocation(String seedIssueLocation) {
        SeedIssueLocation = seedIssueLocation;
    }

    public String getSeedProductionArea() {
        return SeedProductionArea;
    }

    public void setSeedProductionArea(String seedProductionArea) {
        SeedProductionArea = seedProductionArea;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String staffName) {
        StaffName = staffName;
    }

    public int getTempId() {
        return TempId;
    }

    public void setTempId(int tempId) {
        TempId = TempId;
    }


    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public int getCountryMasterId() {
        return CountryMasterId;
    }

    public void setCountryMasterId(int countryMasterId) {
        CountryMasterId = countryMasterId;
    }

    public String getDistributedBy() {
        return DistributedBy;
    }

    public void setDistributedBy(String distributedBy) {
        DistributedBy = distributedBy;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String season) {
        Season = season;
    }

    public String getUniqueCode() {
        return UniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        UniqueCode = uniqueCode;
    }

    public String getCrop() {
        return Crop;
    }

    public void setCrop(String crop) {
        Crop = crop;
    }

    public String getProductionCode() {
        return ProductionCode;
    }

    public void setProductionCode(String productionCode) {
        ProductionCode = productionCode;
    }

    public String getPlantingYear() {
        return PlantingYear;
    }

    public void setPlantingYear(String uploadPhoto) {
        PlantingYear = uploadPhoto;
    }
}
