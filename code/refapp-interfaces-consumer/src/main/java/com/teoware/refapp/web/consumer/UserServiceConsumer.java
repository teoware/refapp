package com.teoware.refapp.web.consumer;

import com.teoware.refapp.web.consumer.vo.FindUserRequestVO;
import com.teoware.refapp.web.consumer.vo.RegisterUserRequestVO;
import com.teoware.refapp.web.consumer.vo.RegisterUserResponseVO;
import com.teoware.refapp.web.consumer.vo.UserListVO;
import com.teoware.refapp.web.consumer.vo.UserVO;

public interface UserServiceConsumer {

	public RegisterUserResponseVO registerUser(RegisterUserRequestVO vo);

	public UserVO findUser(FindUserRequestVO vo);

	public UserListVO listUsers();
}
