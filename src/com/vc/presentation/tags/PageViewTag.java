package com.vc.presentation.tags;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageViewTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String action = null;

    private int pageCount;
    private int totalCount;
    private int currentPage;

    private String theme = null;
    private String render = null;
    private String callback;

    private String className;

    private String innerStyle;

    private static final String SPACE = "&nbsp;";

    private static final String HREF_START = "<a href='";
    private static final String HREF_AJAX_START = "<a  href='javascript:void(0);' onclick='retrieveURL(\"";

    private static final String PAGE_DIV_START = "<div class=\"{0}\" style=\"{1}\">";

    private static final String PAGE_DIV_END = "</div>";

    private static final String HREF_END = "</a>" + SPACE;

    @SuppressWarnings("unchecked")
    @Override
    public int doStartTag() throws JspException {

        StringBuffer sb = new StringBuffer();
        StringBuffer paraBuffer = new StringBuffer();

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String path = request.getScheme() + ":" + "//" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
        action = path + action;

        Enumeration<String> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String parameterName = e.nextElement();
            if (!"page".equals(parameterName) && !"count".equals(parameterName)) {
                paraBuffer.append("&" + parameterName + "=" + request.getParameter(parameterName));
            }
        }

        int totalPages = totalCount / pageCount;
        if (totalCount % pageCount > 0) {
            totalPages += 1;
        }

        String linkStr = null;
        if ("ajax".equals(theme) && render != null) {
            linkStr = createAjaxLink(totalPages, sb, paraBuffer, render);
        } else {
            linkStr = createLink(totalPages, sb, paraBuffer);
        }

        try {
            JspWriter writer = pageContext.getOut();
            if (linkStr != null && linkStr.length() > 0) {
                linkStr = MessageFormat.format(PAGE_DIV_START, this.getClassName() == null ? "" : this.getClassName(),
                        this.getInnerStyle() == null ? "" : this.getInnerStyle()).concat(linkStr).concat(PAGE_DIV_END);
            }
            writer.write(linkStr);
        } catch (IOException e1) {
        }

        return EVAL_PAGE;
    }

    private String createAjaxLink(int totalPages, StringBuffer sb, StringBuffer paraBuffer, String render) {

        StringBuffer callbackBuffer = new StringBuffer();
        callbackBuffer.append("function(){");
        callbackBuffer.append(this.getCallback() == null ? "" : this.getCallback().replaceAll("'", "\""));
        callbackBuffer.append("}");

        if (totalPages > 1) {

            if (currentPage > 1) {
                sb.append(HREF_AJAX_START + action + "?page=" + (currentPage - 1) + "&count=" + pageCount
                        + paraBuffer.toString() + "\",\"" + render + "\"," + callbackBuffer.toString() + ")'>◄"
                        + HREF_END);
            } else {
                sb.append("<span class=\"disabled\">◄</span>");
            }

            if (totalPages > 8) {

                if (currentPage < 8) {
                    for (int i = 1; i <= 8; i++) {
                        if (i == currentPage) {
                            sb.append("<span class=\"current\">" + i + "</span>");
                        } else {
                            sb.append(HREF_AJAX_START + action + "?page=" + i + "&count=" + pageCount
                                    + paraBuffer.toString() + "\",\"" + render + "\"," + callbackBuffer.toString()
                                    + ")'>" + i + HREF_END);
                        }
                    }
                } else {
                    for (int i = 1; i <= 2; i++) {
                        sb.append(HREF_AJAX_START + action + "?page=" + i + "&count=" + pageCount
                                + paraBuffer.toString() + "\",\"" + render + "\"," + callbackBuffer.toString() + ")'>"
                                + i + HREF_END);
                    }
                    sb.append("...&nbsp;");
                    for (int i = currentPage - 3; i < currentPage; i++) {
                        sb.append(HREF_AJAX_START + action + "?page=" + i + "&count=" + pageCount
                                + paraBuffer.toString() + "\",\"" + render + "\"," + callbackBuffer.toString() + ")'>"
                                + i + HREF_END);
                    }
                    sb.append(currentPage + "&nbsp;");

                    if (totalPages > currentPage + 3) {
                        for (int i = currentPage + 1; i <= currentPage + 3; i++) {
                            sb.append(HREF_AJAX_START + action + "?page=" + i + "&count=" + pageCount
                                    + paraBuffer.toString() + "\",\"" + render + "\"," + callbackBuffer.toString()
                                    + ")'>" + i + HREF_END);
                        }
                    } else {
                        for (int i = currentPage + 1; i <= totalPages; i++) {
                            sb.append(HREF_AJAX_START + action + "?page=" + i + "&count=" + pageCount
                                    + paraBuffer.toString() + "\",\"" + render + "\"," + callbackBuffer.toString()
                                    + ")'>" + i + HREF_END);
                        }
                    }

                }

            } else if (totalPages > 1 && totalPages <= 7) {
                for (int i = 1; i <= totalPages; i++) {
                    String url = null;
                    if (i == currentPage) {
                        url = "<span class=\"current\">" + i + "</span>";
                    } else {
                        url = HREF_AJAX_START + action + "?page=" + i + "&count=" + pageCount + paraBuffer.toString()
                                + "\",\"" + render + "\"," + callbackBuffer.toString() + ")'>" + i + HREF_END;
                    }
                    sb.append(url);
                }
            }

            if (currentPage < totalPages) {
                sb.append(HREF_AJAX_START + action + "?page=" + (currentPage + 1) + "&count=" + pageCount
                        + paraBuffer.toString() + "\",\"" + render + "\"," + callbackBuffer.toString() + ")'>►"
                        + HREF_END);
            } else {
                sb.append("<span class=\"disabled\">►</span>");
            }

        }
        return sb.toString();
    }

    private String createLink(int totalPages, StringBuffer sb, StringBuffer paraBuffer) {

        if (totalPages > 1) {

            if (currentPage > 1) {
                sb.append(HREF_START + action + "?page=" + (currentPage - 1) + "&count=" + pageCount
                        + paraBuffer.toString() + "'>◄" + HREF_END);
            } else {
                sb.append("<span class=\"disabled\">◄</span>");
            }

            if (totalPages > 8) {

                if (currentPage < 8) {
                    for (int i = 1; i <= 8; i++) {
                        if (i == currentPage) {
                            sb.append("<span class=\"current\">" + i + "</span>");
                        } else {
                            sb.append(HREF_START + action + "?page=" + i + "&count=" + pageCount
                                    + paraBuffer.toString() + "'>" + i + HREF_END);
                        }
                    }
                } else {
                    for (int i = 1; i <= 2; i++) {
                        sb.append(HREF_START + action + "?page=" + i + "&count=" + pageCount + paraBuffer.toString()
                                + "'>" + i + HREF_END);
                    }
                    sb.append("...&nbsp;");
                    for (int i = currentPage - 3; i < currentPage; i++) {
                        sb.append(HREF_START + action + "?page=" + i + "&count=" + pageCount + paraBuffer.toString()
                                + "'>" + i + HREF_END);
                    }
                    sb.append(currentPage + "&nbsp;");

                    if (totalPages > currentPage + 3) {
                        for (int i = currentPage + 1; i <= currentPage + 3; i++) {
                            sb.append(HREF_START + action + "?page=" + i + "&count=" + pageCount
                                    + paraBuffer.toString() + "'>" + i + HREF_END);
                        }
                    } else {
                        for (int i = currentPage + 1; i <= totalPages; i++) {
                            sb.append(HREF_START + action + "?page=" + i + "&count=" + pageCount
                                    + paraBuffer.toString() + "'>" + i + HREF_END);
                        }
                    }
                }

            } else if (totalPages > 1 && totalPages <= 7) {
                for (int i = 1; i <= totalPages; i++) {
                    String url = null;
                    if (i == currentPage) {
                        url = "<span class=\"current\">" + i + "</span>";
                    } else {
                        url = HREF_START + action + "?page=" + i + "&count=" + pageCount + paraBuffer.toString() + "'>"
                                + i + HREF_END;
                    }
                    sb.append(url);
                }
            }

            if (currentPage < totalPages) {
                sb.append(HREF_START + action + "?page=" + (currentPage + 1) + "&count=" + pageCount
                        + paraBuffer.toString() + "'>►" + HREF_END);
            } else {
                sb.append("<span class=\"disabled\">►</span>");
            }
        }
        return sb.toString();
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInnerStyle() {
        return innerStyle;
    }

    public void setInnerStyle(String innerStyle) {
        this.innerStyle = innerStyle;
    }
}
