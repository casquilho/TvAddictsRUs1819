package CGICompaniesPackage;

import java.util.Comparator;


public class CompanyComparator implements Comparator<CGICompany> {

    public CompanyComparator(){
        super();
    }



    @Override
    public int compare(CGICompany m, CGICompany n) throws ClassCastException {

        return m.getProfit() - n.getProfit();
    }



}
