package com.zot.autorun.moudules.apitest.dto.asserts;

import java.util.List;

public class Asserts {

    private List<AssertBase> body;
    private List<AssertBase> headers;

    @Override
    public String toString() {
        return "Asserts{" +
                "body=" + body +
                ", headers=" + headers +
                '}';
    }

    public List<AssertBase> getBody() {
        return body;
    }

    public void setBody(List<AssertBase> body) {
        this.body = body;
    }

    public List<AssertBase> getHeaders() {
        return headers;
    }

    public void setHeaders(List<AssertBase> headers) {
        this.headers = headers;
    }
}
