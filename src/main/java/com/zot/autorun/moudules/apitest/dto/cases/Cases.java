package com.zot.autorun.moudules.apitest.dto.cases;

import java.util.List;

public class Cases {

    private List<ApiInfoForCase> caseInfo;
//    private ApiInfoForCase caseInfo;


    @Override
    public String toString() {
        return "Cases{" +
                "caseInfo=" + caseInfo +
                '}';
    }

    public List<ApiInfoForCase> getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(List<ApiInfoForCase> caseInfo) {
        this.caseInfo = caseInfo;
    }
}
