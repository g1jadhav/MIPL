package mahyco.iqc.nxg.view.createcorrection;

import java.util.List;

import mahyco.iqc.nxg.model.ActionModel;
import mahyco.iqc.nxg.model.CheckPoint;
import mahyco.iqc.nxg.model.ContractPlantModel;
import mahyco.iqc.nxg.model.CropModel;
import mahyco.iqc.nxg.model.HybridModel;
import mahyco.iqc.nxg.model.InspectionModel;
import mahyco.iqc.nxg.model.NCTypeModel;
import mahyco.iqc.nxg.model.ObservationResponseModel;
import mahyco.iqc.nxg.model.OwnerModel;
import mahyco.iqc.nxg.model.ProcessModel;

public interface CreateCorrectionListener {
    public void onResult(String result);
    public void onListResponce(List result);
    public void onListResponce_ProcessList(List<ProcessModel> result);
    public void onListResponce(ActionModel result);

    void onListResponce_CropList(List<CropModel> result);

    void onListResponce_HybridList(List<HybridModel> result);

    void onListResponce_CheckPointList(List<CheckPoint> result);

    void onListResponce_NCTypeList(List<NCTypeModel> result);

    void onListResponce_OwnerList(List<OwnerModel> result);

    void onObservationResponse(ObservationResponseModel result);

    void onListResponce_ContactPlant(List<ContractPlantModel> result);

    void onGetInspectionDetails(InspectionModel result);

}
