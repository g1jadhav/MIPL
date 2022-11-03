package mahyco.mipl.nxg.model;

public class SeasonModel {

    public SeasonModel(int seasonId, int countryId, String season, String createdBy, String createdDt, String modifiedBy, String modifiedDt, String countryName) {
        SeasonId = seasonId;
        CountryId = countryId;
        Season = season;
        CreatedBy = createdBy;
        CreatedDt = createdDt;
        ModifiedBy = modifiedBy;
        ModifiedDt = modifiedDt;
        CountryName = countryName;
    }

    public int getSeasonId() {
        return SeasonId;
    }

    public void setSeasonId(int seasonId) {
        SeasonId = seasonId;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String season) {
        Season = season;
    }

    public boolean isDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
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

    public String toString() {
        return Season;
    }

    int SeasonId;//": 1,
            int CountryId;//": 1,
            String Season;//": "KHARIF",
            boolean IsDelete;//": false,
            String CreatedBy;//": "55000066",
            String CreatedDt;//": null,
            String ModifiedBy;//": "",
            String ModifiedDt;//": null,
            String CountryName;//": "Malawi"

}
