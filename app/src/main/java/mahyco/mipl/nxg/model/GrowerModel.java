package mahyco.mipl.nxg.model;

public class GrowerModel {
    int LoginId;//": 0,
    int CountryId;//": 0,
    int CountryMasterId;//": 0,
    String UniqueId;//": "string",
    String UserType;//": "string",
    int IsSync = 0;
    String StaffNameAndId;
    int TempId;

    public GrowerModel() {
    }

    public GrowerModel(int countryId, int countryMasterId, String uploadPhoto, String landMark,
                       String growerName, String gender, String dob, String mobileNo, String uniqueCode, String regDate,
                       String staffNameAndId, String idProofFrontCopy,
                       int isSync, String createdBy, String userType, String idProofBackCopy,
                       int loginId,String uniqueId, int tempId) {
        UploadPhoto = uploadPhoto;
        CountryId = countryId;
        CountryMasterId = countryMasterId;
        LandMark = landMark;
        FullName = growerName;
        Gender = gender;
        DOB = dob;
        MobileNo = mobileNo;
        UniqueCode = uniqueCode;
        RegDt = regDate;
        StaffNameAndId = staffNameAndId;
        IdProofFrontCopy = idProofFrontCopy;
        IsSync = isSync;
        CreatedBy = createdBy;
        UserType = userType;
        IdProofBackCopy = idProofBackCopy;
        LoginId = loginId;
        UniqueId = uniqueId;
        TempId = tempId;
    }

    public GrowerModel(int countryId, int countryMasterId, String uploadPhoto, String landMark,
                       String growerName, String gender, String dob, String mobileNo, String uniqueCode, String regDate,
                       String staffNameAndId, String idProofFrontCopy, String createdBy, String userType, String idProofBackCopy,
                       int loginId,String uniqueId ) {
        UploadPhoto = uploadPhoto;
        CountryId = countryId;
        CountryMasterId = countryMasterId;
        LandMark = landMark;
        FullName = growerName;
        Gender = gender;
        DOB = dob;
        MobileNo = mobileNo;
        UniqueCode = uniqueCode;
        RegDt = regDate;
        StaffNameAndId = staffNameAndId;
        IdProofFrontCopy = idProofFrontCopy;
        CreatedBy = createdBy;
        UserType = userType;
        IdProofBackCopy = idProofBackCopy;
        LoginId = loginId;
        UniqueId = uniqueId;
    }

    public int getTempId() {
        return TempId;
    }

    public void setTempId(int tempId) {
        TempId = TempId;
    }

    public String getStaffNameAndId() {
        return StaffNameAndId;
    }

    public void setStaffNameAndId(String staffNameAndId) {
        StaffNameAndId = staffNameAndId;
    }

    public int getIsSync() {
        return IsSync;
    }

    public void setIsSync(int isSync) {
        IsSync = isSync;
    }

    public int getLoginId() {
        return LoginId;
    }

    public void setLoginId(int loginId) {
        LoginId = loginId;
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

    public String getIdProofFrontCopy() {
        return IdProofFrontCopy;
    }

    public void setIdProofFrontCopy(String idProofFrontCopy) {
        IdProofFrontCopy = idProofFrontCopy;
    }

    public String getIdProofBackCopy() {
        return IdProofBackCopy;
    }

    public void setIdProofBackCopy(String idProofBackCopy) {
        IdProofBackCopy = idProofBackCopy;
    }

    public String getUploadPhoto() {
        return UploadPhoto;
    }

    public void setUploadPhoto(String uploadPhoto) {
        UploadPhoto = uploadPhoto;
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

    String LandMark;//": "string",
    String FullName;//": "string",
    String DOB;//": "2022-10-11T04:44:57.051Z",
    String Gender;//": "string",
    String MobileNo;//": "string",
    String UniqueCode;//": "string",
    String IdProofFrontCopy;//": "string",
    String IdProofBackCopy;//": "string",
    String UploadPhoto;//": "string",
    String RegDt;//": "2022-10-11T04:44:57.051Z",
    String CreatedBy;//": "string"
}
