package mahyco.mipl.nxg.view.seeddistribution;

import mahyco.mipl.nxg.model.SuccessModel;

public interface DistributionListener {

    public void onResult(String result);

    void onSeedDistributionResult(SuccessModel result);
}
