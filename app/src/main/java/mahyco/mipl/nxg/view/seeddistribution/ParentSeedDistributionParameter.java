package mahyco.mipl.nxg.view.seeddistribution;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ParentSeedDistributionParameter {
    @SerializedName("createParentSeedDistributionModel")
    ArrayList<DistributionParamItem> list = new ArrayList<>();

    class DistributionParamItem {

        @SerializedName("CountryId")
        int countryId;

        @SerializedName("PlantingYear")
        String plantingYear;

        @SerializedName("SeasonId")
        int seasonId;

        @SerializedName("CropId")
        int cropId;

        @SerializedName("ProductionClusterId")
        int productionClusterId;

        @SerializedName("OrganizerId")
        int organizerId;

        @SerializedName("ParentSeedReceiptId")
        int productionCode;

        @SerializedName("CreatedBy")
        int createdBy;

        @SerializedName("FemaleParentSeedBatchId")
        int femaleParentSeedBatchId;

        @SerializedName("MaleParentSeedBatchId")
        int maleParentSeedBatchId;

        @SerializedName("IssueDt")
        String issueDt;

        @SerializedName("GrowerId")
        int growerId;

        @SerializedName("SeedProductionArea")
        float seedParentArea;
    }
}
