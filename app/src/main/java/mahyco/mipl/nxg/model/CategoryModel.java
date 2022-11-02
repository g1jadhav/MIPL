package mahyco.mipl.nxg.model;

public class CategoryModel {

    public CategoryModel(int CategoryId, String CountryName, int Position, String CategoryName,
                         String DisplayTitle,String CreatedBy,
                         String CreatedDt, String ModifiedBy, String ModifiedDt) {
        this.CategoryId = CategoryId;
        this.CountryName = CountryName;
        this.Position = Position;
        this.CategoryName = CategoryName;
        this.DisplayTitle = DisplayTitle;
        this.CreatedBy = CreatedBy;
        this.CreatedDt = CreatedDt;
        this.ModifiedBy = ModifiedBy;
        this.ModifiedDt = ModifiedDt;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
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

    int CategoryId;//": 1,
    String CountryName;//": "Malawi",
    int Position;//": 1,
    String CategoryName;//": "ADD",
    String DisplayTitle;//": "ADD",
    boolean IsDelete;//": false,
    String CreatedBy;//": "55000066",
    String CreatedDt;//": "2022-08-24T00:00:00",
    String ModifiedBy;//": "",
    String ModifiedDt;//": null

    public String toString() {
        return CountryName;
    }

}
