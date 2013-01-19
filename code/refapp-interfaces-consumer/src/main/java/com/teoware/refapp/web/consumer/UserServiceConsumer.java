package com.teoware.refapp.web.consumer;

import com.teoware.refapp.web.consumer.vo.ActivateUserRequestVO;
import com.teoware.refapp.web.consumer.vo.ActivateUserResponseVO;
import com.teoware.refapp.web.consumer.vo.ChangeUserPasswordRequestVO;
import com.teoware.refapp.web.consumer.vo.ChangeUserPasswordResponseVO;
import com.teoware.refapp.web.consumer.vo.ChangeUserRequestVO;
import com.teoware.refapp.web.consumer.vo.ChangeUserResponseVO;
import com.teoware.refapp.web.consumer.vo.DeleteUserRequestVO;
import com.teoware.refapp.web.consumer.vo.DeleteUserResponseVO;
import com.teoware.refapp.web.consumer.vo.FindUserRequestVO;
import com.teoware.refapp.web.consumer.vo.FindUserResponseVO;
import com.teoware.refapp.web.consumer.vo.ListUsersVO;
import com.teoware.refapp.web.consumer.vo.RegisterUserRequestVO;
import com.teoware.refapp.web.consumer.vo.RegisterUserResponseVO;
import com.teoware.refapp.web.consumer.vo.SuspendUserRequestVO;
import com.teoware.refapp.web.consumer.vo.SuspendUserResponseVO;

public interface UserServiceConsumer {

	public RegisterUserResponseVO registerUser(RegisterUserRequestVO vo);

	public ActivateUserResponseVO activateUser(ActivateUserRequestVO vo);

	public SuspendUserResponseVO suspendUser(SuspendUserRequestVO vo);

	public FindUserResponseVO findUser(FindUserRequestVO vo);

	public ListUsersVO listUsers();

	public ChangeUserResponseVO changeUser(ChangeUserRequestVO vo);

	public ChangeUserPasswordResponseVO changeUserPassword(ChangeUserPasswordRequestVO vo);

	public DeleteUserResponseVO deleteUser(DeleteUserRequestVO vo);
}
