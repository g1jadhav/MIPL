package mahyco.mipl.nxg.view.registration;

import java.util.List;

import mahyco.mipl.nxg.model.CategoryModel;

public interface RegistrationListener {
    public void onResult(String result);

    void onListResponce(List<CategoryModel> result);
}
