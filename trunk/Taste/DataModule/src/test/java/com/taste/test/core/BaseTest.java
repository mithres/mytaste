package com.taste.test.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jpa.AbstractAspectjJpaTests;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:/applicationContext-test.xml",
		"classpath:/META-INF/applicationContext.xml",
		"classpath:/META-INF/applicationContext-service.xml",
		"classpath:/applicationContext-recommend.xml"
})
@Transactional
public abstract class BaseTest extends AbstractAspectjJpaTests {
    
    protected final Log logger = LogFactory.getLog(this.getClass());
    
	public static final int INITIAL_TAGS_COUNT = 278;
	public static final int INITIAL_URLINFO_COUNT = 88;
	public static final int INITIAL_URLEXTINFO_COUNT = 90;
	public static final int INITIAL_USERINFO_COUNT = 12;

	public static final String USERNAME_EMPTY = "emptyuser";
	public static final String USERNAME1 = "!#badodekar";
	public static final String USERNAME2 = "!#Batgar";
	public static final String USERNAME3 = "!#W4kk0";
	public static final String USERNAME4 = "!#b3nt3";
	
	public static final String TAG1 = "clock";
	public static final String TAG2 = "clothing";
	public static final String TAG3 = "cmd";

	public static final String URL1 = "http://convertpdftoword.net/";
	public static final String URL2 = "http://www.wambie.com/foto_br-272.html";
	public static final String URL3 = "http://www.drawergeeks.com/showTopic.php?id=83";
	
	
}
