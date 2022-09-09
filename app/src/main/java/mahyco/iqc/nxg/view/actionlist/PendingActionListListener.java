package mahyco.iqc.nxg.view.actionlist;

import java.util.List;

import mahyco.iqc.nxg.model.ActionModel;

public interface PendingActionListListener {
    public void onResult(String result);

    public void onListResponce(List result);
    public void onListResponce(ActionModel result);


}
