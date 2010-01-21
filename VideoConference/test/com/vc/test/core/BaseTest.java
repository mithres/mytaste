package com.vc.test.core;

import org.junit.runner.RunWith;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jpa.AbstractAspectjJpaTests;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:/applicationContext-test.xml",
})
@Transactional
public abstract class BaseTest extends AbstractAspectjJpaTests {
    
	private final Logger log = Red5LoggerFactory.getLogger(getClass(), "VideoConference");
    
}
