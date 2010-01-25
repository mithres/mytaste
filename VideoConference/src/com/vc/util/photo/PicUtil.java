package com.vc.util.photo;

import ij.ImagePlus;
import ij.plugin.JpegWriter;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vc.core.constants.Constants;
import com.vc.entity.UserInfo;
import com.vc.util.configuration.ServerConfiguration;

public class PicUtil {

	private static final Log logger = LogFactory.getLog(PicUtil.class);

	public static final long DEFAULT_AVATOR_SIZE = 2097152;

	private static MultiProtocolFileSaver createFileSaver(InputStream is, int maxWidth, int maxHeight, String fileName) throws IOException {
		ImageProcessor ip = scaleImage(is, maxWidth, maxHeight);
		ImagePlus imp = new ImagePlus("sm", ip);
		JpegWriter.setQuality(90);
		MultiProtocolFileSaver fs = new MultiProtocolFileSaver(imp);
		return fs;
	}

	public static ImageProcessor scaleImage(InputStream is, int maxWidth, int maxHeight) throws IOException {
		BufferedImage bi = ImageIO.read(is);
		int width = bi.getWidth();
		int height = bi.getHeight();

		double scale = cScale(width, height, maxWidth, maxHeight);

		ImageProcessor ip = new ColorProcessor(bi);
		return ip.resize((int) (width * scale), (int) (height * scale));
	}

	/**
	 * save image as PNG type
	 * 
	 * @param is
	 * @param maxWidth
	 * @param maxHeight
	 * @param fileName
	 * @throws IOException
	 */
	public static void makePhotoPNG(InputStream is, int maxWidth, int maxHeight, String fileName) throws IOException {
		createFileSaver(is, maxWidth, maxHeight, fileName).saveAsPng(fileName);
	}

	/**
	 * save image as JPEG type
	 * 
	 * @param is
	 * @param maxWidth
	 * @param maxHeight
	 * @param fileName
	 * @throws IOException
	 */
	public static void makePhotoJPEG(InputStream is, int maxWidth, int maxHeight, String fileName) throws IOException {
		createFileSaver(is, maxWidth, maxHeight, fileName).saveAsJpeg(fileName);
	}

	/**
	 * save image as GIF type
	 * 
	 * @param is
	 * @param maxWidth
	 * @param maxHeight
	 * @param fileName
	 * @throws IOException
	 */
	public static void makePhotoGIF(InputStream is, int maxWidth, int maxHeight, String fileName) throws IOException {
		createFileSaver(is, maxWidth, maxHeight, fileName).saveAsGif(fileName);
	}

	public static double cScale(int w, int h, int maxWidth, int maxHeight) {
		double scale = 1.0D;
		scale = (double) maxWidth / (double) w >= (double) maxHeight / (double) h ? (double) maxHeight / (double) h : (double) maxWidth
				/ (double) w;
		return scale;
	}

	/**
	 * @param groupID
	 * @param photoType
	 * @return
	 */
	public static final String loadPhotoPath(long groupID, PhotoType photoType) {
		int pathID = (int) groupID / 10000;
		String photoPath = ServerConfiguration.getPhotoPath(photoType);
		StringBuffer sb = new StringBuffer(photoPath);
		sb.append(String.valueOf(pathID));
		sb.append(File.separator);
		return sb.toString();
	}

	public static final String loadPhotoUrl(long groupID, PhotoType photoType) {
		
		if (photoType.equals(PhotoType.FilmScreenShot)) {
			return ServerConfiguration.getPhotoUrl(photoType);
		} else {
			int pathID = (int) groupID / 10000;
			String photoPath = ServerConfiguration.getPhotoUrl(photoType);
			StringBuffer sb = new StringBuffer(photoPath);
			sb.append(String.valueOf(pathID));
			sb.append(File.separator);
			return sb.toString();
		}

	}

	public static final String createImageTag(String imageSrc, String id, String css, String defaultImage, int width, int height, int border) {

		StringBuffer sb = new StringBuffer("<img src='" + imageSrc + "' ");
		if (id != null) {
			sb.append(" id='" + id + "' ");
		}
		if (height != 0 && width != 0) {
			sb.append(" width='" + width + "' height='" + height + "' ");
		}
		if (css != null) {
			sb.append(" class='" + css + "' ");
		}
		sb.append(" border='" + border + "' ");
		sb.append(" onerror='this.src=\"" + defaultImage + "\"' ");
		sb.append(" />");
		return sb.toString();

	}

	public static final boolean uploadImage(File file, UserInfo info) {

		InputStream is;
		try {
			is = new FileInputStream(file);
			return uploadImage(is, info);
		} catch (FileNotFoundException e1) {
			logger.warn("Image upload error", e1);
			throw new RuntimeException("Save user photo fail.", e1);
		}
	}

	public static final boolean uploadImage(InputStream is, UserInfo info) {

		try {
			String filePath = PicUtil.loadPhotoPath(info.getUserIndex(), PhotoType.UserPhoto);
			StringBuffer sb = new StringBuffer(filePath);
			sb.append(String.valueOf(info.getUserIndex()));
			sb.append(".jpg");
			PicUtil.makePhotoJPEG(is, Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT, sb.toString());
			return Boolean.TRUE;
		} catch (IOException e) {
			logger.warn("Image upload error", e);
			throw new RuntimeException("Save user photo fail.", e);
		} catch (RuntimeException e) {
			logger.warn("Image upload error", e);
			return Boolean.FALSE;
		}

	}

	public static String getUserAvatarTag(Long profileID) {

		String imageName = profileID.toString() + ".jpg";
		String filePath = loadPhotoUrl(profileID, PhotoType.UserPhoto);
		filePath += imageName;
		return filePath;
	}

}
