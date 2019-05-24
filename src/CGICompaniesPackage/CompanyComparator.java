package CGICompaniesPackage;

import java.util.Comparator;


public class CompanyComparator implements Comparator<CGICompany> {

    public CompanyComparator(){
        super();
    }



    @Override
    public int compare(CGICompany m, CGICompany n) throws ClassCastException {

        int relation =  n.getProfit() - m.getProfit();

        if(relation == 0) {
            relation = n.getNumberChars() - m.getNumberChars();
            if(relation == 0)
                return n.getName().compareTo(m.getName());
        }
        return relation;
    }



}
