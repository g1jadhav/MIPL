package mahyco.mipl.nxg.util;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Vector;

import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.model.GrowerModel;

import mahyco.mipl.nxg.model.OldGrowerSeedDistributionModel;

import mahyco.mipl.nxg.model.SeasonModel;


public class SqlightDatabase extends SQLiteOpenHelper {

    final static String DBName = "mipl";
    final static int version = 8;
    long count = 0;
    final String tbl_categorymaster = "tbl_categorymaster";
    final String tbl_locationmaster = "tbl_locationmaster";

    final String tbl_parentSeedDistribution = "tbl_parentseeddistribution";

    public SqlightDatabase(Context context) {
        super(context, DBName, null, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        String createCategoryMaster = "Create table tbl_categorymaster(\n" +
                "    TempId  INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "    CategoryId integer,\n" +
                "    CountryName text,\n" +
                "    Position integer,\n" +
                "    CategoryName text,\n" +
                "    DisplayTitle text,\n" +
                "    IsDelete text,\n" +
                "    CreatedBy text,\n" +
                "    CreatedDt text,\n" +
                "    ModifiedBy text,\n" +
                "    ModifiedDt text\n" +
                ")";
        db.execSQL(createCategoryMaster);

        String createlocationmaster = " Create table tbl_locationmaster(\n" +
                "    TempId  INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "    CountryMasterId INTEGER,\n" +
                "    CategoryId INTEGER,\n" +
                "    ParentId INTEGER,\n" +
                "    KeyValue Text,\n" +
                "    KeyCode text,\n" +
                "    IsDelete text,\n" +
                "    CreatedBy text,\n" +
                "    CreatedDt text,\n" +
                "    ModifiedBy text,\n" +
                "    ModifiedDt text,\n" +
                "    CountryName text,\n" +
                "    CategoryName text,\n" +
                "    DisplayTitle text\n" +
                ")";

        db.execSQL(createlocationmaster);

        String createseasonmaster = "Create table tbl_seasonmaster(" +
                "TempID Integer PRIMARY KEY AUTOINCREMENT,\n" +
                "SeasonId INTEGER,\n" +
                "    CountryId INTEGER,\n" +
                "    Season TEXT,\n" +
                "    IsDelete TEXT,\n" +
                "    CreatedBy TEXT,\n" +
                "    CreatedDt  TEXT,\n" +
                "    ModifiedBy  TEXT,\n" +
                "    ModifiedDt  TEXT,\n" +
                "    CountryName  TEXT)";

        db.execSQL(createseasonmaster);

        String creategrowermaster = " Create table tbl_growermaster(\n" +
                "    TempId  INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "    CountryMasterId INTEGER,\n" +
                "    CategoryId INTEGER,\n" +
                "    ParentId INTEGER,\n" +
                "    KeyValue Text,\n" +
                "    KeyCode text,\n" +
                "    IsDelete text,\n" +
                "    CreatedBy text,\n" +
                "    CreatedDt text,\n" +
                "    ModifiedBy text,\n" +
                "    ModifiedDt text,\n" +
                "    CountryName text,\n" +
                "    CategoryName text,\n" +
                "    DisplayTitle text\n" +
                ")";

        db.execSQL(creategrowermaster);

        String createRegistration = " Create table tbl_registrationmaster(\n" +
                "    TempId  INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "    CountryId INTEGER,\n" +
                "    CountryMasterId INTEGER,\n" +
                "    FarmerPhoto Text,\n" +
                "    Landmark text,\n" +
                "    GrowerName text,\n" +
                "    Gender text,\n" +
                "    DateOfBirth text,\n" +
                "    MobileNo text,\n" +
                "    UniqueCode text,\n" +
                "    DateOfRegistration text,\n" +
                "    StaffNameId text,\n" +
                "    FrontPhoto text,\n" +
                "    IsSync INTEGER,\n" +
                "    CreatedBy text,\n" +
                "    UserType text,\n" +
                "    BackPhoto text,\n" +
                "    LoginId INTEGER,\n" +
                "    UniqueId text,\n" +
                "    GrowerImageUpload INTEGER,\n" +
                "    FrontImageUpload INTEGER,\n" +
                "    BackImageUpload INTEGER\n" +
                ")";

        db.execSQL(createRegistration);


        String parentSeedDistribution = " Create table "+ tbl_parentSeedDistribution+"(\n" +
                "    TempId  INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "    CountryId INTEGER,\n" +
                "    CountryMasterId INTEGER,\n" +
                "    plantingYear Text,\n" +
                "    season text,\n" +
                "    uniqueCode text,\n" +
                "    staffName text,\n" +
                "    crop text,\n" +
                "    distributedBy text,\n" +
                "    productionCode text,\n" +
                "    seedProductionArea text,\n" +
                "    seedIssueLocation text,\n" +
                "    seedBatchNoFemale text,\n" +
                "    seedBatchNoMale text,\n" +
                "    growerName text,\n" +
                "    growerAddress text,\n" +
                "    loginId INTEGER,\n" +
                "    issueDate text\n" +
                ")";

        db.execSQL(parentSeedDistribution);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        droptable(db, "tbl_locationmaster");
        droptable(db, "tbl_categorymaster");
        droptable(db, "tbl_seasonmaster");
        droptable(db, "tbl_growermaster");
        droptable(db, "tbl_registrationmaster");
        droptable(db, tbl_parentSeedDistribution);
        onCreate(db);
    }

    private void droptable(SQLiteDatabase db, String s) {
        try {

            String tbl_order_terms = "drop table " + s;
            db.execSQL(tbl_order_terms);
            Log.i("Table Drop :", s);

        } catch (Exception e) {
            Log.i("Error is :", e.getMessage());
        }
    }

    public void trucateTable(String s) {
        try {
            SQLiteDatabase db = null;
            db = this.getReadableDatabase();
            String trucateStr = "delete from " + s;
            db.execSQL(trucateStr);
            Log.i("Table Trucated :", s);

        } catch (Exception e) {
            Log.i("Error is :", e.getMessage());
        }
    }

    public boolean addCategory(CategoryModel categoryModel) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "insert into tbl_categorymaster" +
                    "(" +
                    "" +
                    "CategoryId," +
                    "CountryName," +
                    "Position," +
                    "CategoryName," +
                    "DisplayTitle," +
                    "IsDelete," +
                    "CreatedBy," +
                    "CreatedDt," +
                    "ModifiedBy," +
                    "ModifiedDt" +
                    ") values" +
                    "('" + categoryModel.getCategoryId() + "'," +
                    "'" + categoryModel.getCountryName() + "'," +
                    "'" + categoryModel.getPosition() + "'," +
                    "'" + categoryModel.getCategoryName() + "'," +
                    "'" + categoryModel.getDisplayTitle() + "'," +
                    "'" + categoryModel.isDelete() + "'," +
                    "'" + categoryModel.getCreatedBy() + "'," +
                    "'" + categoryModel.getCreatedDt() + "'," +
                    "'" + categoryModel.getModifiedBy() + "'," +
                    "'" + categoryModel.getModifiedDt() + "')";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Product Added ", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean addLocation(CategoryChildModel categoryModel) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "insert into tbl_locationmaster" +
                    "(" +
                    "" +
                    "CountryMasterId," +
                    "CategoryId," +
                    "ParentId," +
                    "KeyValue," +
                    "KeyCode," +
                    "IsDelete," +
                    "CreatedBy," +
                    "CreatedDt," +
                    "ModifiedBy," +
                    "ModifiedDt," +
                    "CountryName," +
                    "CategoryName," +
                    "DisplayTitle" +
                    ") values" +
                    "('" + categoryModel.getCountryMasterId() + "'," +
                    "'" + categoryModel.getCategoryId() + "'," +
                    "'" + categoryModel.getParentId() + "'," +
                    "'" + categoryModel.getKeyValue() + "'," +
                    "'" + categoryModel.getKeyCode() + "'," +
                    "'" + categoryModel.isDelete() + "'," +
                    "'" + categoryModel.getCreatedBy() + "'," +
                    "'" + categoryModel.getCreatedDt() + "'," +
                    "'" + categoryModel.getModifiedBy() + "'," +
                    "'" + categoryModel.getModifiedDt() + "'," +
                    "'" + categoryModel.getCountryName() + "'," +
                    "'" + categoryModel.getCategoryName() + "'," +
                    "'" + categoryModel.getDisplayTitle() + "')";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Product Added ", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean addRegistration(GrowerModel growerModel) {
        /*Log.e("temporary","farmer photo "+growerModel.getUploadPhoto() + "\n country id " + growerModel.getCountryId() +
                "\n CountryMasterId() " + growerModel.getCountryMasterId() +
                "\nLandMark()" + growerModel.getLandMark() +
                "\nLandFullName()" + growerModel.getFullName() +
                "\nLandGender()" + growerModel.getGender() +
                "\nLandDOB()()" + growerModel.getDOB() +
                "\nLandMobileNo()" + growerModel.getMobileNo() +
                "\nLandUniqueCode()" + growerModel.getUniqueCode() +
                "\nLandRegDt()" + growerModel.getRegDt() +
                "\nLandStaffNameAndI()" + growerModel.getStaffNameAndId() +
                "\nLandFrontCopy()" + growerModel.getIdProofFrontCopy() +
                "\nIsSync()" + growerModel.getIsSync() +
                "\nreatedBy()" + growerModel.getCreatedBy() +
                "\nUserType()" + growerModel.getUserType()+
                "\nBackCopy()" + growerModel.getIdProofBackCopy());*/
        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "insert into tbl_registrationmaster" +
                    "(" +
                    "" +
                    "CountryId," +
                    "CountryMasterId," +
                    "FarmerPhoto," +
                    "Landmark," +
                    "GrowerName," +
                    "Gender," +
                    "DateOfBirth," +
                    "MobileNo," +
                    "UniqueCode," +
                    "DateOfRegistration," +
                    "StaffNameId," +
                    "FrontPhoto," +
                    "IsSync," +
                    "CreatedBy," +
                    "UserType," +
                    "BackPhoto," +
                    "LoginId," +
                    "UniqueId," +
                    "GrowerImageUpload," +
                    "FrontImageUpload," +
                    "BackImageUpload" +
                    ") values" +
                    "('" + growerModel.getCountryId() + "'," +
                    "'" + growerModel.getCountryMasterId() + "'," +
                    "'" + growerModel.getUploadPhoto() + "'," +
                    "'" + growerModel.getLandMark() + "'," +
                    "'" + growerModel.getFullName() + "'," +
                    "'" + growerModel.getGender() + "'," +
                    "'" + growerModel.getDOB() + "'," +
                    "'" + growerModel.getMobileNo() + "'," +
                    "'" + growerModel.getUniqueCode() + "'," +
                    "'" + growerModel.getRegDt() + "'," +
                    "'" + growerModel.getStaffNameAndId() + "'," +
                    "'" + growerModel.getIdProofFrontCopy() + "'," +
                    "'" + growerModel.getIsSync() + "'," +
                    "'" + growerModel.getCreatedBy() + "'," +
                    "'" + growerModel.getUserType() + "'," +
                    "'" + growerModel.getIdProofBackCopy() + "'," +
                    "'" + growerModel.getLoginId() + "'," +
                    "'" + growerModel.getUniqueId() + "'," +
                    "'" + growerModel.getGrowerImageUpload() + "'," +
                    "'" + growerModel.getFrontImageUpload() + "'," +
                    "'" + growerModel.getBackImageUpload() + "')";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Product Added ", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean parentSeedDistribution(OldGrowerSeedDistributionModel seedDistributionModel) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "insert into " + tbl_parentSeedDistribution +
                    "(" +
                    "" +
                    "CountryId," +
                    "CountryMasterId," +
                    "plantingYear," +
                    "season," +
                    "uniqueCode," +
                    "staffName," +
                    "crop," +
                    "distributedBy," +
                    "productionCode," +
                    "seedProductionArea," +
                    "seedIssueLocation," +
                    "seedBatchNoFemale," +
                    "seedBatchNoMale," +
                    "growerName," +
                    "growerAddress," +
                    "loginId," +
                    "issueDate" +
                    ") values" +
                    "('" + seedDistributionModel.getCountryId() + "'," +
                    "'" + seedDistributionModel.getCountryMasterId() + "'," +
                    "'" + seedDistributionModel.getPlantingYear() + "'," +
                    "'" + seedDistributionModel.getSeason() + "'," +
                    "'" + seedDistributionModel.getUniqueCode() + "'," +
                    "'" + seedDistributionModel.getStaffName() + "'," +
                    "'" + seedDistributionModel.getCrop() + "'," +
                    "'" + seedDistributionModel.getDistributedBy() + "'," +
                    "'" + seedDistributionModel.getProductionCode() + "'," +
                    "'" + seedDistributionModel.getSeedProductionArea()+ "'," +
                    "'" + seedDistributionModel.getSeedIssueLocation() + "'," +
                    "'" + seedDistributionModel.getSeedBatchNoFemale()+ "'," +
                    "'" + seedDistributionModel.getSeedBatchNoMale()+ "'," +
                    "'" + seedDistributionModel.getGrowerName()+ "'," +
                    "'" + seedDistributionModel.getGrowerAddress()+ "'," +
                    "'" + seedDistributionModel.getLoginId()+ "'," +
                    "'" + seedDistributionModel.getIssueDate() + "')";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Product Added ", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public ArrayList<CategoryModel> getAllCategories() {
        SQLiteDatabase myDb = null;
        try {
            myDb = this.getReadableDatabase();
            String q = "SELECT  * FROM tbl_categorymaster";
            Cursor cursorCourses = myDb.rawQuery(q, null);
            ArrayList<CategoryModel> courseModalArrayList = new ArrayList<>();
            if (cursorCourses.moveToFirst()) {
                do {
                    courseModalArrayList.add(new CategoryModel(cursorCourses.getInt(1),
                            cursorCourses.getString(2),
                            cursorCourses.getInt(3),
                            cursorCourses.getString(4),
                            cursorCourses.getString(5),
                            cursorCourses.getString(7),
                            cursorCourses.getString(8),
                            cursorCourses.getString(9),
                            cursorCourses.getString(10)));
                } while (cursorCourses.moveToNext());
            }
            return courseModalArrayList;
        } catch (Exception e) {
            return null;
        } finally {
            myDb.close();
        }
    }

    public ArrayList<GrowerModel> getAllRegistration() {
        SQLiteDatabase myDb = null;
        try {
            myDb = this.getReadableDatabase();
            String q = "SELECT * FROM tbl_registrationmaster";
            Cursor cursorCourses = myDb.rawQuery(q, null);
            ArrayList<GrowerModel> courseModalArrayList = new ArrayList<>();
            if (cursorCourses.moveToFirst()) {
                do {
                    courseModalArrayList.add(new GrowerModel(cursorCourses.getInt(1),
                            cursorCourses.getInt(2),
                            cursorCourses.getString(3),
                            cursorCourses.getString(4),
                            cursorCourses.getString(5),
                            cursorCourses.getString(6),
                            cursorCourses.getString(7),
                            cursorCourses.getString(8),
                            cursorCourses.getString(9),
                            cursorCourses.getString(10),
                            cursorCourses.getString(11),
                            cursorCourses.getString(12),
                            cursorCourses.getInt(13),
                            cursorCourses.getString(14),
                            cursorCourses.getString(15),
                            cursorCourses.getString(16),
                            cursorCourses.getInt(17),
                            cursorCourses.getString(18),
                            cursorCourses.getInt(0),
                            cursorCourses.getInt(19),
                            cursorCourses.getInt(20),
                            cursorCourses.getInt(21)
                            ));
                } while (cursorCourses.moveToNext());
            }
            return courseModalArrayList;
        } catch (Exception e) {
            return null;
        } finally {
            myDb.close();
        }
    }

    public ArrayList<OldGrowerSeedDistributionModel> getParentSeedDistributionList() {
        SQLiteDatabase myDb = null;
        try {
            myDb = this.getReadableDatabase();
            String q = "SELECT * FROM "+tbl_parentSeedDistribution;
            Cursor cursorCourses = myDb.rawQuery(q, null);
            ArrayList<OldGrowerSeedDistributionModel> courseModalArrayList = new ArrayList<>();
            if (cursorCourses.moveToFirst()) {
                do {
                    courseModalArrayList.add(new OldGrowerSeedDistributionModel(cursorCourses.getInt(1),
                            cursorCourses.getInt(2),
                            cursorCourses.getString(3),
                            cursorCourses.getString(4),
                            cursorCourses.getString(5),
                            cursorCourses.getString(6),
                            cursorCourses.getString(7),
                            cursorCourses.getString(8),
                            cursorCourses.getString(9),
                            cursorCourses.getString(10),
                            cursorCourses.getString(11),
                            cursorCourses.getString(12),
                            cursorCourses.getString(13),
                            cursorCourses.getString(14),
                            cursorCourses.getString(15),
                            cursorCourses.getInt(16),
                            cursorCourses.getInt(0),
                            cursorCourses.getString(17)
                    ));
                } while (cursorCourses.moveToNext());
            }
            return courseModalArrayList;
        } catch (Exception e) {
            return null;
        } finally {
            myDb.close();
        }
    }

    public boolean deleteParentSeedDistribution(String uniqueCode) {
        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "DELETE from "+ tbl_parentSeedDistribution+ " where UniqueCode='" + uniqueCode+"'";
            Log.e("temporary"," deleted Query is -------> " + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.e("temporary"," deleted Error is Clear List " + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }
    }

    public boolean updateRegistrationStatus(String uniqueCode, int status) {
        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
           // String q = "update  tbl_registrationmaster set IsSync=" + status + " where MobileNo=" + mobileNumber;
            String q = "update  tbl_registrationmaster set IsSync='" + status + "' where UniqueCode='" + uniqueCode+"'";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is  Added ", "Order Details : " + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }
    }
    public boolean deleteRegistration(String uniqueCode) {
        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "DELETE from tbl_registrationmaster where UniqueCode='" + uniqueCode+"'";
            Log.e("temporary"," deleted Query is -------> " + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.e("temporary"," deleted Error is Clear List " + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }
    }

    /*public ArrayList<GrowerModel> deleteRegistration(String uniqueCode) {
         SQLiteDatabase myDb = null;
        try {
            myDb = this.getReadableDatabase();
            String q = "DELETE from tbl_registrationmaster where UniqueCode='" + uniqueCode+"'";
            Cursor cursorCourses = myDb.rawQuery(q, null);
            ArrayList<GrowerModel> courseModalArrayList = new ArrayList<>();
            if (cursorCourses.moveToFirst()) {
                do {
                    courseModalArrayList.add(new GrowerModel(cursorCourses.getInt(1),
                            cursorCourses.getInt(2),
                            cursorCourses.getString(3),
                            cursorCourses.getString(4),
                            cursorCourses.getString(5),
                            cursorCourses.getString(6),
                            cursorCourses.getString(7),
                            cursorCourses.getString(8),
                            cursorCourses.getString(9),
                            cursorCourses.getString(10),
                            cursorCourses.getString(11),
                            cursorCourses.getString(12),
                            cursorCourses.getInt(13),
                            cursorCourses.getString(14),
                            cursorCourses.getString(15),
                            cursorCourses.getString(16),
                            cursorCourses.getInt(17),
                            cursorCourses.getString(18),
                            cursorCourses.getInt(0)));
                } while (cursorCourses.moveToNext());
            }
            return courseModalArrayList;
        } catch (Exception e) {
            return null;
        } finally {
            myDb.close();
        }
    }*/

//    public ArrayList<GrowerModel> updateRegistrationImagePath(String mobileNumber, String imageType, String path) {
//        SQLiteDatabase myDb = null;
//        try {
//            myDb = this.getReadableDatabase();
//            String q;
//            if (imageType.equalsIgnoreCase("growerPhoto")) {
//                q = "update  tbl_registrationmaster set FarmerPhoto='" + path + "' where MobileNo='" + mobileNumber+"'";
//            } else if (imageType.equalsIgnoreCase("docBackPhoto")) {
//                q = "update  tbl_registrationmaster set BackPhoto='" + path + "' where MobileNo='" + mobileNumber+"'";
//            } else {
//                q = "update  tbl_registrationmaster set FrontPhoto='" + path + "' where MobileNo='" + mobileNumber+"'";
//            }
//            Cursor cursorCourses = myDb.rawQuery(q, null);
//            ArrayList<GrowerModel> courseModalArrayList = new ArrayList<>();
//            if (cursorCourses.moveToFirst()) {
//                do {
//                    courseModalArrayList.add(new GrowerModel(cursorCourses.getInt(1),
//                            cursorCourses.getInt(2),
//                            cursorCourses.getString(3),
//                            cursorCourses.getString(4),
//                            cursorCourses.getString(5),
//                            cursorCourses.getString(6),
//                            cursorCourses.getString(7),
//                            cursorCourses.getString(8),
//                            cursorCourses.getString(9),
//                            cursorCourses.getString(10),
//                            cursorCourses.getString(11),
//                            cursorCourses.getString(12),
//                            cursorCourses.getInt(13),
//                            cursorCourses.getString(14),
//                            cursorCourses.getString(15),
//                            cursorCourses.getString(16),
//                            cursorCourses.getInt(17),
//                            cursorCourses.getString(18),
//                            cursorCourses.getInt(0)));
//                } while (cursorCourses.moveToNext());
//            }
//            return courseModalArrayList;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            myDb.close();
//        }
//    }

    public boolean updateRegistrationImagePath(String uniqueCode, String imageType, String path,int isInsertedValue) {
        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q;
            if (imageType.equalsIgnoreCase("growerPhoto")) {
                q = "update  tbl_registrationmaster set FarmerPhoto='" + path + "',  GrowerImageUpload='" + isInsertedValue +"'where UniqueCode='" + uniqueCode+"'";
            } else if (imageType.equalsIgnoreCase("docBackPhoto")) {
                q = "update  tbl_registrationmaster set BackPhoto='" + path + "',  BackImageUpload='" + isInsertedValue +"'where UniqueCode='" + uniqueCode+"'";
                // q = "update  tbl_registrationmaster set BackPhoto='" + path + "' where UniqueCode='" + uniqueCode+"'";
            } else {
                q = "update  tbl_registrationmaster set FrontPhoto='" + path + "',  FrontImageUpload='" + isInsertedValue +"'where UniqueCode='" + uniqueCode+"'";
               // q = "update  tbl_registrationmaster set FrontPhoto='" + path + "' where UniqueCode='" + uniqueCode+"'";
            }
            Log.e("temporary","updateRegistrationImagePath Query is -------> " + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.e("temporary","updateRegistrationImagePath Error is Added Order Details : " + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }
    }

    public ArrayList<CategoryChildModel> getLocationCategories(int countryMasterId) {
        SQLiteDatabase myDb = null;
        try {
            myDb = this.getReadableDatabase();
            String q = "SELECT  * FROM tbl_locationmaster where ParentId=" + countryMasterId;
            Cursor cursorCourses = myDb.rawQuery(q, null);
            ArrayList<CategoryChildModel> courseModalArrayList = new ArrayList<>();
            if (cursorCourses.moveToFirst()) {
                do {
                    courseModalArrayList.add(new CategoryChildModel(cursorCourses.getInt(1),
                            cursorCourses.getInt(2),
                            cursorCourses.getInt(3),
                            cursorCourses.getString(4),
                            cursorCourses.getString(5),
                            cursorCourses.getString(7),
                            cursorCourses.getString(8),
                            cursorCourses.getString(9),
                            cursorCourses.getString(10),
                            cursorCourses.getString(11),
                            cursorCourses.getString(12),
                            cursorCourses.getString(13)));
                } while (cursorCourses.moveToNext());
            }
            return courseModalArrayList;
        } catch (Exception e) {
            return null;
        } finally {
            myDb.close();
        }
    }

    public boolean addSeason(SeasonModel seasonModel) {
        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "insert into tbl_seasonmaster" +
                    "(" +
                    "" +
                    "SeasonId," +
                    "CountryId," +
                    "Season," +
                    "IsDelete," +
                    "CreatedBy," +
                    "CreatedDt," +
                    "ModifiedBy," +
                    "ModifiedDt," +
                    "CountryName" +

                    ") values" +
                    "('" + seasonModel.getSeasonId() + "'," +
                    "'" + seasonModel.getCountryId() + "'," +
                    "'" + seasonModel.getSeason() + "'," +
                    "'" + seasonModel.isDelete() + "'," +
                    "'" + seasonModel.getCreatedBy() + "'," +
                    "'" + seasonModel.getCreatedDt() + "'," +
                    "'" + seasonModel.getModifiedBy() + "'," +
                    "'" + seasonModel.getModifiedDt() + "'," +
                    "'" + seasonModel.getCountryName() + "')" ;
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Product Added ", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }
    }

    public boolean addGrower(CategoryModel categoryModel) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "insert into tbl_growermaster" +
                    "(" +
                    "" +
                    "CategoryId," +
                    "CountryName," +
                    "Position," +
                    "CategoryName," +
                    "DisplayTitle," +
                    "IsDelete," +
                    "CreatedBy," +
                    "CreatedDt," +
                    "ModifiedBy," +
                    "ModifiedDt" +
                    ") values" +
                    "('" + categoryModel.getCategoryId() + "'," +
                    "'" + categoryModel.getCountryName() + "'," +
                    "'" + categoryModel.getPosition() + "'," +
                    "'" + categoryModel.getCategoryName() + "'," +
                    "'" + categoryModel.getDisplayTitle() + "'," +
                    "'" + categoryModel.isDelete() + "'," +
                    "'" + categoryModel.getCreatedBy() + "'," +
                    "'" + categoryModel.getCreatedDt() + "'," +
                    "'" + categoryModel.getModifiedBy() + "'," +
                    "'" + categoryModel.getModifiedDt() + "')";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Product Added ", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }
    }


    public boolean addOrderLocal(String id, String details, int status, String cname) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "insert into tbl_order_local(Details,Status,customername,createddate) values" +
                    "('" + details + "'," + status + ",'" + cname + "',datetime('now'))";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is  Added ", "Order Details : " + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean updateLocalOrerStatus(String id, int status) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "update  tbl_order_local set Status=" + status + " where id=" + id;
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is  Added ", "Order Details : " + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }


    public boolean clearProductList() {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "delete from order_details";
            //String q = "delete from tbl_customersatyam";

            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Clear List", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean clearProductList(int id) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "delete from order_details where productid=" + id + "";
            //String q = "delete from tbl_customersatyam";

            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Clear List", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }


    public boolean clearTermsList() {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "delete from tbl_order_terms";

            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Clear List", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }

    public boolean clearTermsList(int id) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "delete from tbl_order_terms where ParticularId='" + id + "'";

            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Clear List", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }

    }


    public Vector getProductDetailsById(String id) {
        SQLiteDatabase mydb = null;
        String k = "";
        Vector v;
        int i = 0;
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM tbl_cstatus where id=" + id;

            Cursor c = mydb.rawQuery(q, null);
            v = new Vector();
            if (c.moveToNext()) {
                //v[i]=new Vector();

                v.addElement(c.getInt(0));
                v.addElement(c.getString(1));
                v.addElement(c.getString(2));
                v.addElement(c.getString(3));
                v.addElement(c.getString(4));
                v.addElement(c.getString(5));
                v.addElement(c.getString(6));


                i++;
            }

            return v;
        } catch (Exception e) {

            return null;
        } finally {
            mydb.close();
        }
    }

    public Vector[] getAllProducts() {
        SQLiteDatabase mydb = null;
        String k = "";
        Vector v[];
        int i = 0;
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM order_details";

            Cursor c = mydb.rawQuery(q, null);
            v = new Vector[c.getCount()];
            while (c.moveToNext()) {
                v[i] = new Vector();

                v[i].addElement(c.getInt(0)); //id
                v[i].addElement(c.getString(1));//productid
                v[i].addElement(c.getInt(2));
                v[i].addElement(c.getString(3));
                v[i].addElement(c.getString(4));
                v[i].addElement(c.getString(5));
                v[i].addElement(c.getString(6));
                v[i].addElement(c.getString(7));
                v[i].addElement(c.getString(8));
                v[i].addElement(c.getString(9));

                i++;
            }

            return v;
        } catch (Exception e) {

            return null;
        } finally {
            mydb.close();
        }
    }

    public boolean addTerms(int termId, int srNo, int orderId, int particularId, String condition, boolean isRemoved, String name) {

        SQLiteDatabase mydb = null;
        try {
            mydb = this.getReadableDatabase();
            String q = "insert into tbl_order_terms(TermId,SrNo,OrderId,ParticularId,Condition,IsRemoved,name) values" +
                    "(" + termId + "," + srNo + "," + orderId + "," + particularId + ",'" + condition + "','" + isRemoved + "','" + name + "')";
            Log.i("Query is -------> ", "" + q);
            mydb.execSQL(q);
            return true;
        } catch (Exception e) {
            Log.i("Error is Product Added ", "" + e.getMessage());
            return false;
        } finally {
            mydb.close();
        }


    }

    public Vector[] getAllTerms() {
        SQLiteDatabase mydb = null;
        String k = "";
        Vector v[];
        int i = 0;
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM tbl_order_terms";

            Cursor c = mydb.rawQuery(q, null);
            v = new Vector[c.getCount()];
            while (c.moveToNext()) {
                v[i] = new Vector();

                v[i].addElement(c.getInt(0)); //id
                v[i].addElement(c.getInt(1));//productid
                v[i].addElement(c.getInt(2));
                v[i].addElement(c.getInt(3));
                v[i].addElement(c.getString(4));
                v[i].addElement(c.getString(5));
                v[i].addElement(c.getString(6));

                i++;
            }

            return v;
        } catch (Exception e) {

            return null;
        } finally {
            mydb.close();
        }
    }

    public Vector[] getAllTermsForAdd() {
        SQLiteDatabase mydb = null;
        String k = "";
        Vector v[];
        int i = 0;
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM tbl_order_terms";

            Cursor c = mydb.rawQuery(q, null);
            v = new Vector[c.getCount()];
            while (c.moveToNext()) {
                v[i] = new Vector();

                v[i].addElement(c.getInt(0)); //id
                v[i].addElement(c.getInt(1));//productid
                v[i].addElement(c.getInt(2));
                v[i].addElement(c.getInt(3));
                v[i].addElement(c.getString(4));
                v[i].addElement(c.getString(5));


                i++;
            }

            return v;
        } catch (Exception e) {

            return null;
        } finally {
            mydb.close();
        }
    }

    public Vector[] getAllOfflineOrders() {
        SQLiteDatabase mydb = null;
        String k = "";
        Vector v[];
        int i = 0;
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM tbl_order_local where status=0";

            Cursor c = mydb.rawQuery(q, null);
            v = new Vector[c.getCount()];
            while (c.moveToNext()) {
                v[i] = new Vector();

                v[i].addElement(c.getInt(0)); //id
                v[i].addElement(c.getString(1));//productid
                v[i].addElement(c.getInt(2));
                v[i].addElement(c.getString(3));
                v[i].addElement(c.getString(4));
                i++;
            }

            return v;
        } catch (Exception e) {

            return null;
        } finally {
            mydb.close();
        }
    }


    public Vector getAllOfflineOrdersById(int id) {
        SQLiteDatabase mydb = null;
        String k = "";
        Vector v = new Vector();
        ;
        int i = 0;
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  * FROM tbl_order_local where id=" + id + " and status=0";

            Cursor c = mydb.rawQuery(q, null);

            if (c.moveToNext()) {


                v.addElement(c.getInt(0)); //id
                v.addElement(c.getString(1).replace("\\\"", ""));//productid
                v.addElement(c.getInt(2));
                v.addElement(c.getString(3));
                v.addElement(c.getString(4));
                i++;
            }

            return v;
        } catch (Exception e) {

            return null;
        } finally {
            mydb.close();
        }
    }

    public int getMaxOfflineOrderID() {
        SQLiteDatabase mydb = null;
        String k = "";
        Vector v = new Vector();
        ;
        int i = 0;
        try {
            mydb = this.getReadableDatabase();
            String q = "SELECT  ifnull(max(id),0)+1 FROM tbl_order_local";

            Cursor c = mydb.rawQuery(q, null);

            if (c.moveToNext()) {
                i = c.getInt(0); //id
            }

            return i;
        } catch (Exception e) {

            return 0;
        } finally {
            mydb.close();
        }
    }


}
