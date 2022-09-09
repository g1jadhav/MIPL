package mahyco.iqc.nxg;

import java.util.List;

import mahyco.iqc.nxg.model.ActionModel;

public interface MainActivityListListener {
    public void onResult(String result);

    public void onListResponce(List result);
    public void onListResponce(ActionModel result);


    void onDataReset(String result);
}
