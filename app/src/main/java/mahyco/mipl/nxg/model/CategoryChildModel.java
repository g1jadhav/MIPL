package mahyco.mipl.nxg.model;

public class CategoryChildModel {

    public CategoryChildModel(int CountryMasterId, int CategoryId, int ParentId, String KeyValue,
                              String KeyCode, String CreatedBy, String CreatedDt, String ModifiedBy, String ModifiedDt,
                              String CountryName, String CategoryName, String DisplayTitle) {
        this.CountryMasterId = CountryMasterId;
        this.CategoryId = CategoryId;
        this.ParentId = ParentId;
        this.KeyValue = KeyValue;
        this.KeyCode = KeyCode;
        this.CreatedBy = CreatedBy;
        this.CreatedDt = CreatedDt;
        this.ModifiedBy = ModifiedBy;
        this.ModifiedDt = ModifiedDt;
        this.CountryName = CountryName;
        this.CategoryName = CategoryName;
        this.DisplayTitle = DisplayTitle;
    }

    int CountryMasterId;//": 21,

    public int getCountryMasterId() {
        return CountryMasterId;
    }

    public void setCountryMasterId(int countryMasterId) {
        CountryMasterId = countryMasterId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public String getKeyValue() {
        return KeyValue;
    }

    public void setKeyValue(String keyValue) {
        KeyValue = keyValue;
    }

    public String getKeyCode() {
        return KeyCode;
    }

    public void setKeyCode(String keyCode) {
        KeyCode = keyCode;
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

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getDisplayTitle() {
        return DisplayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        DisplayTitle = displayTitle;
    }

    int CategoryId;//": 3,
    int ParentId;//": 1,
    String KeyValue;//": "Balaka",
    String KeyCode;//": "MB01",
    boolean IsDelete;//": false,
    String CreatedBy;//": "Junaid Siddiqui",
    String CreatedDt;//": "2022-10-07T13:53:40.37",
    String ModifiedBy;//": "",
    String ModifiedDt;//": null,
    String CountryName;//": "Malawi",
    String CategoryName;//": "EPA",
    String DisplayTitle;//": "EPA"

    public String toString() {
        return KeyValue;
    }

}
