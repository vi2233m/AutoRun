package com.zot.autorun.moudules.apitest.dto.cases;

import java.util.List;

public class CasesRequestBody {

    private List<Cases> cases;

    @Override
    public String toString() {
        return "CasesRequestBody{" +
                "cases=" + cases +
                '}';
    }

    public List<Cases> getCases() {
        return cases;
    }

    public void setCases(List<Cases> cases) {
        this.cases = cases;
    }
}
