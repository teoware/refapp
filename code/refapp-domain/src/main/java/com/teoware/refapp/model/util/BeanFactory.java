package com.teoware.refapp.model.util;

import com.teoware.refapp.model.common.Title;
import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.model.note.Note;
import com.teoware.refapp.model.note.NoteDetails;
import com.teoware.refapp.model.note.NoteStatus;
import com.teoware.refapp.model.task.Task;
import com.teoware.refapp.model.task.TaskDetails;
import com.teoware.refapp.model.task.TaskStatus;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserAddress;
import com.teoware.refapp.model.user.UserDetails;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.UserStatus;

public class BeanFactory {

	public static Username createUsernameBean() {
		Username username = new Username();
		return username;
	}

	public static UserDetails createUserInfoBean() {
		UserDetails userInfo = new UserDetails();
		return userInfo;
	}

	public static UserAddress createUserAddressBean() {
		UserAddress userAddress = new UserAddress();
		return userAddress;
	}

	public static UserStatus createUserStatusBean() {
		UserStatus userStatus = new UserStatus();
		return userStatus;
	}

	public static User createUserBean() {
		User user = new User();
		user.setUsername(createUsernameBean());
		user.setUserDetails(createUserInfoBean());
		user.setUserAddress(createUserAddressBean());
		user.setUserStatus(createUserStatusBean());
		return user;
	}

	public static UserPassword createUserPasswordBean() {
		return new UserPassword();
	}

	public static Note createNote() {
		return new Note(new Title(), new NoteDetails(), new NoteStatus());
	}

	public static Task createTask() {
		return new Task(new Title(), new TaskDetails(), new TaskStatus());
	}
}
