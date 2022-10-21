package mahyco.mipl.nxg.view.downloadcategories;

import java.util.List;

import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.CategoryModel;

public interface DownloadCategoryListListener {
    public void onResult(String result);
    public void onListCategoryMasterResponse(List<CategoryModel> result);
    public void onListLocationResponse(List<CategoryChildModel> result);
    public void onListSeasonMasterResponse(List<CategoryModel> result);
    public void onListGrowerResponse(List<CategoryModel> result);
}
