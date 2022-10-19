package mahyco.mipl.nxg.view.growerregistration;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.List;

import mahyco.mipl.nxg.model.CategoryChildModel;
import mahyco.mipl.nxg.model.SuccessModel;

public interface Listener {
    public void onResult(String result);
    public void onListResponce(List result);

    void onSpinnerClick(int id,String categoryId);

    void loadChildSpinner(List<CategoryChildModel> result, SearchableSpinner spinner);

    void onGrowerRegister(SuccessModel result);
}
