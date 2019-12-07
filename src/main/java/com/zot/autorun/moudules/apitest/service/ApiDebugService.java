package com.zot.autorun.moudules.apitest.service;

import com.zot.autorun.moudules.apitest.dto.RequestApiBody;
import com.zot.autorun.moudules.apitest.dto.ResponseBody;

public interface ApiDebugService {

    /**
     *
     * @param requestApiBody
     * @return
     */
    ResponseBody apiPost(RequestApiBody requestApiBody);

}
