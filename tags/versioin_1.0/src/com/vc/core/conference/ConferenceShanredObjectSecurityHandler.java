package com.vc.core.conference;

import java.util.List;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.IScope;
import org.red5.server.api.so.ISharedObject;
import org.red5.server.api.so.ISharedObjectSecurity;
import org.red5.server.api.so.ISharedObjectService;
import org.red5.server.so.SharedObjectService;
import org.slf4j.Logger;

public class ConferenceShanredObjectSecurityHandler implements ISharedObjectSecurity {

	private static final Logger log = Red5LoggerFactory.getLogger(ConferenceShanredObjectSecurityHandler.class,
			"VideoConference");

	@Override
	public boolean isConnectionAllowed(ISharedObject arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCreationAllowed(IScope scope, String name, boolean persistent) {
		if ("MessageSO".equals(name) || "UserListSO".equals(name)) {
			ISharedObjectService service = new SharedObjectService();
			if (service.getSharedObject(scope, name) == null) {
				if (service.createSharedObject(scope, name, persistent)) {
					ISharedObject so = service.getSharedObject(scope, name);
				//	if ("UserListSO".equals(name)) {
				//		so.addSharedObjectListener(new RoomUserListListener());
				//	}
				}
			}
		}
		return true;
	}

	@Override
	public boolean isDeleteAllowed(ISharedObject arg0, String arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isSendAllowed(ISharedObject arg0, String arg1, List<?> arg2) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isWriteAllowed(ISharedObject arg0, String arg1, Object arg2) {
		// TODO Auto-generated method stub
		return true;
	}

}
