package mahyco.mipl.nxg.model;

public class DownloadGrowerModel {

    private  int UserId;//": 0,
    private  int LoginId;//": 0,
    private  int CountryId;//": 0,
    private  int CountryMasterId;//": 0,
    private  String UniqueId;//": "string",
    private  String UserType;//": "string",
    private  String LandMark;//": "string",
    private  String FullName;//": "string",
    private  String DOB;//": "2022-10-11T04:44:57.051Z",
    private String Gender;//": "string",
    private String MobileNo;//": "string",
    private  String UniqueCode;//": "string",
    private  String RegDt;//": "2022-10-11T04:44:57.051Z",
    private  String CreatedBy;//": "string"
    private  String CreatedDt;
    /*private  boolean IsDelete;*/
    private String ModifiedBy;
    private  String ModifiedDt;
    private  String CountryName;
    private String CategoryName;
    private  String KeyValue;
    private String KeyCode;
    private String UserName;
    private String UserCode;


    public DownloadGrowerModel() {
    }

    public DownloadGrowerModel(int userId, int loginId, int countryId, int countryMasterId, String uniqueId, String userType,
                               String landMark, String fullName, String dob, String gender, String mobileNo,
                               String uniqueCode, String regDate, /*boolean isDelete, */String createdBy,
                               String createdDt, String modifiedBy,
                               String modifiedDt, String countryName, String categoryName,
                               String keyValue, String keyCode, String userName, String userCode) {
        UserId = userId;
        LoginId = loginId;
        KeyValue = keyValue;
        KeyCode = keyCode;
        UserName = userName;
        UserCode = userCode;
        CategoryName = categoryName;
        CountryName = countryName;
        ModifiedDt = modifiedDt;
        ModifiedBy = modifiedBy;
       /* IsDelete = isDelete;*/
        CreatedDt = createdDt;
        LandMark = landMark;
        CountryId = countryId;
        CountryMasterId = countryMasterId;
        FullName = fullName;
        Gender = gender;
        DOB = dob;
        MobileNo = mobileNo;
        UniqueCode = uniqueCode;
        RegDt = regDate;
        CreatedBy = createdBy;
        UserType = userType;
        UniqueId = uniqueId;
    }

    public int getLoginId() {
        return LoginId;
    }

    public void setLoginId(int loginId) {
        LoginId = loginId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
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

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getLandMark() {
        return LandMark;
    }

    public void setLandMark(String landMark) {
        LandMark = landMark;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getUniqueCode() {
        return UniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        UniqueCode = uniqueCode;
    }

    public String getRegDt() {
        return RegDt;
    }

    public void setRegDt(String regDt) {
        RegDt = regDt;
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

   /* public boolean isDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
    }*/

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

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String toString() {
        return FullName +" ("+ UniqueCode+")";
    }
}
