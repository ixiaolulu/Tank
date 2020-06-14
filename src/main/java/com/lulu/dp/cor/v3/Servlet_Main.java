package com.lulu.dp.cor.v3;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-13 15:56
 */
public class Servlet_Main {

    public static void main(String[] args) {
        Request request = new Request();
        request.str = "大家好:)，<script>，各位程序员，大家都是996 ";
        Response response = new Response();
        response.str = "";

        FilterChain chain = new FilterChain();
        chain.add(new HTMLFilter()).add(new SensitiveFilter());
        chain.doFilter(request, response, chain);
        System.out.println(request.str);
        System.out.println(response.str);

    }
}

class Request {
    String str;
}

class Response {
    String str;
}

interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}

class SensitiveFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str.replace("996", "995");
        chain.doFilter(request, response, chain);
        response.str += "SensitiveFilter()";
    }
}

class HTMLFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str.replaceAll("<", "[").replaceAll(">", "]");
        chain.doFilter(request, response, chain);
        response.str += "HTMLFilter()";
    }
}

class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<>();

    private int index = 0;

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        if (index == filters.size()) return;
        Filter filter = filters.get(index);
        index++;
        filter.doFilter(request, response, chain);
    }

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }
}