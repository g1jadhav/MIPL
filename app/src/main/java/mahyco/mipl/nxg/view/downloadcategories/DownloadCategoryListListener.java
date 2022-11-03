package mahyco.mipl.nxg.view.downloadcategories;

import java.util.List;

import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CategoryModel;
import mahyco.mipl.nxg.model.CropModel;
import mahyco.mipl.nxg.model.DownloadGrowerModel;
import mahyco.mipl.nxg.model.ProductCodeModel;
import mahyco.mipl.nxg.model.ProductionClusterModel;
import mahyco.mipl.nxg.model.SeasonModel;
import mahyco.mipl.nxg.model.SeedBatchNoModel;
import mahyco.mipl.nxg.model.SeedReceiptModel;

public interface DownloadCategoryListListener {
    public void onResult(String result);
    public void onListCategoryMasterResponse(List<CategoryModel> result);
    public void onListLocationResponse(List<CategoryChildModel> result);
    public void onListSeasonMasterResponse(List<SeasonModel> result);
    public void onListGrowerResponse(List<DownloadGrowerModel> result);
    public void onListCropResponse(List<CropModel> result);
    public void onListProductionClusterResponse(List<ProductionClusterModel> result);
    public void onListProductCodeResponse(List<ProductCodeModel> result);
    public void onListSeedReceiptNoResponse(List<SeedReceiptModel> result);
    public void onListSeedBatchNoResponse(List<SeedBatchNoModel> result);
}
