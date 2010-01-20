package com.vc.util.security;

import java.util.UUID;

public class ServerSecurityUtil {

	public static final String generateVODKey() {
		return UUID.randomUUID().toString();
	}

}
