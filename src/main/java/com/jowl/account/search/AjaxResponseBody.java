package com.jowl.account.search;

import java.util.List;

/**
 * The Ajax Response Body class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
public class AjaxResponseBody<Clazz> {

    String msg;
    List<Clazz> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Clazz> getResult() {
        return result;
    }

    public void setResult(List<Clazz> result) {
        this.result = result;
    }
}
