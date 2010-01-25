package com.vc.util.photo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.util.Base64;
import org.apache.jackrabbit.webdav.MultiStatus;
import org.apache.jackrabbit.webdav.client.methods.MkColMethod;
import org.apache.jackrabbit.webdav.client.methods.PropFindMethod;
import org.apache.jackrabbit.webdav.client.methods.PutMethod;

/**
 * Uploader by WebDAV protocol
 * 
 * 
 * 
 */
public class DavFileUploader implements IFileUploader {

    private static Log log = LogFactory.getLog(DavFileUploader.class);

    private static final int SC_MULTISTATUS = 207;

    private static final String ROOT_PATH = "/";

    private String username;

    private String password;

    @Override
    public boolean exists(String uri, String authString) throws IOException {
        uri = normalizeUri(uri);
        PropFindMethod method = new PropFindMethod(uri);
        method.setRequestHeader(new Header("Depth", "0"));
        method.setRequestEntity(new StringRequestEntity(getPropfindBody(), " text/xml", null));

        int response = executeMethod(method, authString);

        if (response != SC_MULTISTATUS) {
            if (response == HttpServletResponse.SC_METHOD_NOT_ALLOWED) {
                /*
                 * Resource available but PROPFIND NOT allowed
                 */
                return true;
            }
            if (response == HttpServletResponse.SC_NOT_FOUND) {
                return false;
            }

            throw new IOException("Not valid HTTP  response code:" + response);
        }
        try {
            MultiStatus multiStatus = method.getResponseBodyAsMultiStatus();
            return multiStatus.getResponses()[0].getStatus()[0].getStatusCode() == HttpServletResponse.SC_OK;
        } catch (Exception e) {

            throw new IOException("Not valid multistatus response", e);
        }
    }

    private String normalizeUri(String uri) {

        return uri.replace("\\", "/");

    }

    /**
     * @param method
     * @param authString
     * @return
     * @throws IOException
     * @throws HttpException
     */
    private int executeMethod(HttpMethod method, String authString) throws IOException, HttpException {
        prepareMethod(method, authString);
        int response = getHttpClient().executeMethod(method);

        return response;
    }

    private String getPropfindBody() {

        return "<?xml version=\"1.0\" ?>" + "<D:propfind xmlns:D=\"DAV:\">" + "<D:allprop/>" + "</D:propfind>";
    }

    /**
     * Adds additional headers to method
     * 
     * @param method
     * @param authString
     * @throws IOException
     */
    private void prepareMethod(HttpMethod method, String authString) throws IOException {

        StringWriter writer = new StringWriter();
        Base64.encode(new ByteArrayInputStream((authString == null || authString.length() == 0 ? username + ":"
                + password : authString).getBytes()), writer);
        String authStr = "Basic " + writer.toString();
        method.setRequestHeader("Authorization", authStr);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.comodo.hopsurf.toolbar.image.FileUploader#preparePath(java.lang.String)
     */
    @Override
    public boolean preparePath(String uri, String authString) throws IOException {
        uri = normalizeUri(uri);
        if (log.isDebugEnabled()) {
            log.debug("Prepare URI:" + uri);
        }

        URI parent;
        try {
            parent = new URI(uri);
            parent = buildURI(parent, getParentURI(parent.getPath()));
        } catch (URISyntaxException e) {
            throw new IOException("Not valid URI:" + uri);
        }

        if (log.isDebugEnabled()) {
            log.debug("Check parent URI:" + parent);
        }
        return prepareCurrent(parent, authString);
    }

    private boolean prepareCurrent(URI u, String authString) throws IOException, HttpException {

        if (exists(u.toString(), authString)) {
            return true;
        }

        if (log.isDebugEnabled()) {
            log.debug("Create parent URI:" + u);
        }
        String path = u.getPath();

        if (path == ROOT_PATH) {
            return exists(u.toString(), authString);
        }

        int response = mkCol(u.toString(), authString);

        if (isSuccessfullResponse(response)) {
            return true;
        }

        if (response == HttpServletResponse.SC_CONFLICT) {
            if (exists(u.toString(), authString)) {
                return true;
            }

            String parent = getParentURI(path);

            log.debug("Returned SC_CONFLICT try to create parent collection");

            URI parrent;
            try {
                parrent = buildURI(u, parent);
            } catch (URISyntaxException e) {

                throw new IOException("Not valid parent URI");

            }

            if (prepareCurrent(parrent, authString)) {
                return isSuccessfullResponse(mkCol(u.toString(), authString));
            }

        }
        return false;
    }

    private URI buildURI(URI uri, String newPath) throws URISyntaxException {
        uri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), newPath, uri.getQuery(), uri
                .getFragment());
        return uri;
    }

    /**
     * @param path
     * @return
     */
    private String getParentURI(String path) {
        String[] pathItems = path.split("/");
        String parent = "/";
        String last = "";
        for (String item : pathItems) {
            if (item.length() != 0) {
                parent += last;
                last = item + "/";
            }
        }
        return parent;
    }

    /**
     * @param response
     * @return
     */
    private boolean isSuccessfullResponse(int response) {
        boolean result = response >= HttpServletResponse.SC_CREATED
                && response < HttpServletResponse.SC_MULTIPLE_CHOICES;
        if (!result) {
            log.debug("Not suucessfull response code:" + response);
        }
        return result;
    }

    private int mkCol(String uri, String authString) throws IOException, HttpException {
        MkColMethod method = new MkColMethod(uri);
        int response = executeMethod(method, authString);

        return response;
    }

    @Override
    public void upload(InputStream is, int contentLength, String uri, String authString) throws IOException {
        uri = normalizeUri(uri);
        if (log.isDebugEnabled()) {
            log.debug("Before upload:" + uri);
        }

        PutMethod method = new PutMethod(uri);
        method.setRequestEntity(new InputStreamRequestEntity(is, contentLength));

        int response = executeMethod(method, authString);

        if (!isSuccessfullResponse(response)) {
            throw new IOException("Upload error code:" + response);
        }
        if (log.isDebugEnabled()) {
            log.debug("Seccesfull upload:" + uri);
        }

    }

    /**
     * @return
     */
    HttpClient getHttpClient() {
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setIntParameter("http.protocol.max-redirects", 10);
        return httpClient;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public byte[] download(String uri, String authString) throws IOException {
        GetMethod getMethod = new GetMethod(uri);
        int statusCode = executeMethod(getMethod, authString);
        byte[] bytes = null;
        if (isSuccessfullResponse(statusCode)) {
            bytes = getMethod.getResponseBody();
        }
        return bytes;
    }

}
