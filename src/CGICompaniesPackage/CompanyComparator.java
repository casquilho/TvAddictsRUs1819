package CGICompaniesPackage;

import java.util.Comparator;


public class CompanyComparator implements Comparator<CGICompany> {

    public CompanyComparator(){
        super();
    }



    @Override
    public int compare(CGICompany m, CGICompany n) throws ClassCastException {

        int relation =  m.getProfit() - n.getProfit();

        if(relation == 0) {
            relation = m.getNumberChars() - n.getNumberChars();
            if(relation == 0)
                return m.getName().compareTo(n.getName());
        }
        return relation;
    }



}
