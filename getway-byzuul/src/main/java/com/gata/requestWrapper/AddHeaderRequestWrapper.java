package com.gata.requestWrapper;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public final class AddHeaderRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String> customHeaders;

    public AddHeaderRequestWrapper(HttpServletRequest request){
        super(request);
        this.customHeaders = new HashMap<String, String>();
    }

    public void putHeader(String name, String value){
        this.customHeaders.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        return this.customHeaders.get(name);
    }

    public Map<String, String> getCustomHeaders() {
        return customHeaders;
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        if (null != name && name.equals("userid")) {
            return new Enumeration<String>() {
                private boolean hasGetted = false;

                @Override
                public boolean hasMoreElements() {
                    return !hasGetted;
                }

                @Override
                public String nextElement() {
                    if (hasGetted) {
                        throw new NoSuchElementException();
                    } else {
                        hasGetted = true;
                        return "dalong";
                    }
                }
            };
        }
        return super.getHeaders(name);

    }
}