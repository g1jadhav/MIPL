package mahyco.mipl.nxg;

import java.util.List;

import mahyco.mipl.nxg.model.CategoryModel;

public interface MainActivityListListener {
    public void onResult(String result);

    public void onListResponce(List<CategoryModel> result);

}
