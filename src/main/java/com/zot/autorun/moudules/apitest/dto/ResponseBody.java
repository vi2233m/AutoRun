package com.zot.autorun.moudules.apitest.dto;

import org.springframework.stereotype.Component;


@Component
public class ResponseBody  {

    private String code;
    private String message;
    private Object body;

    @Override
    public String toString() {
        return "ResponseBody{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
