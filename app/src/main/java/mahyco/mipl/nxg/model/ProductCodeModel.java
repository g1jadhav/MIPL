package mahyco.mipl.nxg.model;

public class ProductCodeModel {

    private  int ProductId;
    private  int CropId;
    private  String ProductType;
    private  String ProductCode;
    private  String ProductName;
    private  String CreatedBy;
    private  String CreatedDt;
    private  String ModifiedBy;
    private  String ModifiedDt;
    private  String CropCode;
    private  String CropName;

    public ProductCodeModel(){}
    public ProductCodeModel(int productId, int cropId, String productType, String productCode, String productName, String createdBy, String createdDt, String modifiedBy, String modifiedDt, String cropCode, String cropName) {
        ProductId = productId;
        CropId = cropId;
        ProductType = productType;
        ProductCode = productCode;
        ProductName = productName;
        CreatedBy = createdBy;
        CreatedDt = createdDt;
        ModifiedBy = modifiedBy;
        ModifiedDt = modifiedDt;
        CropCode = cropCode;
        CropName = cropName;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getCropId() {
        return CropId;
    }

    public void setCropId(int cropId) {
        CropId = cropId;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
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


    public String toString() {
        return ProductName;
    }
}
