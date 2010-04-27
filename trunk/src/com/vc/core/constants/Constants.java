package com.vc.core.constants;

/**
 * @author ammen
 * 
 */
public class Constants {
	
	public static final String LOCAL_IP = "127.0.0.1";
	
	public static final String USER_CREDENTIAL_AES_KEY = "UserCredential";
	
	
	//Scope name for vod and conference
	public static final String VOD_SCOPE_NAME = "vod";
	public static final String CONFERENCE_SCOPE_NAME = "conference";
	
	//Flash client shared object names
	public static final String MESSAGE_OBJECT = "MessageSO";
	public static final String USERLIST_OBJECT = "UserListSO";
	public static final String USERLISTUPDATE_OBJECT = "UserListUpdateSO";
	
	//Encoder name
	public static final String UTF8 = "UTF-8";
	public static final String GBK = "GBK";
	
	//Pagination 
	public static final int DEFAULT_START = 0;
	public static final int DEFAULT_COUNT = 5;
	
	// Photo properties
	public static final int DEFAULT_WIDTH = 128;
	public static final int DEFAULT_HEIGHT = 128;
	
	//Hibernate
	//Query hints
	public static final String ENABLE_QUERY_CACHE = "EnableQueryCache";
	
	//SessionID in Red5 Client
	public static final String SESSION_ID = "SessionId";
	
	//RBAC
	//Role names
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	//Captcha contants
	public static final String CAPTCHA_TICKET = "captcha.ticket";
	public static final String CAPTCHA_CODE_ERROR = "CaptchaCodeError";
	
	//Video and Record stream path
	public static final String VIDEO_STREAM_PATH = "/videoStreams/";
	public static final String RECORDED_STREAM_PATH = "/recordedStreams/";
	
	// URL Authorities that in serverlet context
	public static final String URL_AUTHORITIES = "urlAuthorities";
	
	//Current menu state
	public static final String MENU_STAT = "MenuStat";
	
	//Condition for search 
	public static final String SEARCH_CONDITION_ALL = "All";
	
	//Tag split express
	public static final String TAG_SPLIT_EXPRESSION = ",|:|;| ";
	
	//Constants for security handler
	public static String ALLOWED_HTML_DOMAINS = null;
	public static String ALLOWED_SWF_DOMAINS = null;
	
	//Photo configuration
	public static String PHOTO_PATH = null;
	public static String PHOTO_URL = null;
	public static String PHOTO_PATH_AUTH = null;
	
	//RTMP address
	public static String RTMP_IP = null;
	public static int RTMP_PORT;
	public static boolean IS_RTMP_SERVER = true;
	
	//FS_URI
	public static String FS_URI = null;
	
	
	
}
