package com.vc.util.photo;

import java.io.IOException;
import java.io.InputStream;

/**
 * Uploading data using various protocols
 * <ul>
 * <li>File System</li>
 * <li>WebDAV</li>
 * <li>SCP</li>
 * </ul>
 * 
 * 
 */
public interface IFileUploader {

	/**
	 * Download content
	 * 
	 * @param uri
	 * @param authString
	 * @return
	 * @throws IOException
	 */
	byte[] download(String uri, String authString) throws IOException;

	/**
	 * Creates directories for <code>fileName</code> if they not exists before
	 * uploading.
	 * 
	 * @param uri
	 * @param authString
	 *            TODO
	 * @return <code>true</code> if successful.
	 * @throws IOException
	 */
	boolean preparePath(String uri, String authString) throws IOException;

	/**
	 * Upload content with given length to scecified <code>uri</code>.
	 * 
	 * @param is
	 *            content
	 * @param contentLength
	 * @param uri
	 *            destination URI
	 * @param authString
	 *            TODO
	 * @return
	 * @throws IOException
	 */
	void upload(InputStream is, int contentLength, String uri, String authString) throws IOException;

	/**
	 * Checks whether or not resource exists
	 * 
	 * @param uri
	 * @param authString
	 *            TODO
	 * @return
	 * @throws IOException
	 */
	boolean exists(String uri, String authString) throws IOException;
}