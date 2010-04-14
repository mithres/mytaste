package com.vc.presentation.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.vc.core.constants.Constants;

public class EncodingFilter implements Filter {

    /**
     * The default character encoding to set for requests that pass through this
     * filter.
     */
    private static String DEFAULT_ENCODING = Constants.UTF8;

    private String encoding = null;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        // Pass control on to the next filter
        chain.doFilter(request, response);

    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        
    }

}
