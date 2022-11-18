package mahyco.mipl.nxg.model;

public class ProductionClusterModel {

    int ProductionClusterId;
    int CountryId;
    String ProductionClusterCode;
    String ProductionCluster;
    //    boolean IsDelete;
    String CreatedBy;
    String CreatedDt;
    String ModifiedBy;
    String ModifiedDt;
    String CountryName;

    public ProductionClusterModel(){}

    public ProductionClusterModel(int productionClusterId, int countryId, String productionClusterCode, String productionCluster, String createdBy, String createdDt, String modifiedBy, String modifiedDt, String countryName) {
        ProductionClusterId = productionClusterId;
        CountryId = countryId;
        ProductionClusterCode = productionClusterCode;
        ProductionCluster = productionCluster;
        CreatedBy = createdBy;
        CreatedDt = createdDt;
        ModifiedBy = modifiedBy;
        ModifiedDt = modifiedDt;
        CountryName = countryName;
    }

    public int getProductionClusterId() {
        return ProductionClusterId;
    }

    public void setProductionClusterId(int productionClusterId) {
        ProductionClusterId = productionClusterId;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public String getProductionClusterCode() {
        return ProductionClusterCode;
    }

    public void setProductionClusterCode(String productionClusterCode) {
        ProductionClusterCode = productionClusterCode;
    }

    public String getProductionCluster() {
        return ProductionCluster;
    }

    public void setProductionCluster(String productionCluster) {
        ProductionCluster = productionCluster;
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
        return ProductionCluster;
    }
}
