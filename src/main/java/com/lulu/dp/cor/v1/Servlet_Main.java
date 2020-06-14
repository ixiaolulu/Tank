package com.lulu.dp.cor.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-13 14:34
 */
public class Servlet_Main {

    public static void main(String[] args) {
        Request request = new Request();
        request.str = "大家好:)，<script>，各位程序员，大家都是996 ";
        Response response = new Response();
        response.str = "";

        FilterChain chain = new FilterChain();
        chain.add(new HTMLFilter()).add(new SensitiveFilter());
        chain.doFilter(request, response);
        System.out.println(request.str);

    }
}

interface Filter {
    boolean doFilter(Request request, Response response);
}

class Request {
    String str;
}

class Response {
    String str;
}

class SensitiveFilter implements Filter {

    @Override
    public boolean doFilter(Request request, Response response) {
        request.str = request.str.replace("996", "995");
        return true;
    }
}

class HTMLFilter implements Filter {
    @Override
    public boolean doFilter(Request request, Response response) {
        request.str = request.str.replaceAll("<", "[").replaceAll(">", "]");
        return true;
    }
}

class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<>();

    @Override
    public boolean doFilter(Request request, Response response) {

        for (Filter filter : filters) {
            filter.doFilter(request, response);
        }
        return false;
    }

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }
}