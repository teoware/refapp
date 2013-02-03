package com.teoware.refapp.test.util;

import org.joda.time.DateTime;

import com.teoware.refapp.dao.dto.CreateNoteDetailsInput;
import com.teoware.refapp.dao.dto.CreateNoteInput;
import com.teoware.refapp.dao.dto.CreateTaskDetailsInput;
import com.teoware.refapp.dao.dto.CreateTaskInput;
import com.teoware.refapp.dao.dto.CreateUserAddressInput;
import com.teoware.refapp.dao.dto.CreateUserDetailsInput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.DeleteNoteDetailsInput;
import com.teoware.refapp.dao.dto.DeleteNoteInput;
import com.teoware.refapp.dao.dto.DeleteNoteStatusInput;
import com.teoware.refapp.dao.dto.DeleteTaskDetailsInput;
import com.teoware.refapp.dao.dto.DeleteTaskInput;
import com.teoware.refapp.dao.dto.DeleteTaskStatusInput;
import com.teoware.refapp.dao.dto.DeleteUserAddressInput;
import com.teoware.refapp.dao.dto.DeleteUserDetailsInput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserPasswordInput;
import com.teoware.refapp.dao.dto.DeleteUserStatusInput;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.ReadNoteInput;
import com.teoware.refapp.dao.dto.ReadTaskInput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsInput;
import com.teoware.refapp.dao.dto.UpdateNoteInput;
import com.teoware.refapp.dao.dto.UpdateNoteStatusInput;
import com.teoware.refapp.dao.dto.UpdateTaskDetailsInput;
import com.teoware.refapp.dao.dto.UpdateTaskInput;
import com.teoware.refapp.dao.dto.UpdateTaskStatusInput;
import com.teoware.refapp.dao.dto.UpdateUserAddressInput;
import com.teoware.refapp.dao.dto.UpdateUserDetailsInput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordInput;
import com.teoware.refapp.dao.dto.UpdateUserStatusInput;
import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.note.Note;
import com.teoware.refapp.model.task.Task;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.service.dto.RegisterUserRequest;

public class TestDataFactory {

	public static User createUserJohn() {
		DateTime dateTime = DateTime.now();
		User bean = BeanFactory.createUserBean();
		bean.getUsername().setUsername("john.doe");
		bean.getUserStatus().setStatus(Status.ACTIVE);
		bean.getUserStatus().setCreated(dateTime);
		bean.getUserStatus().setModified(dateTime);
		bean.getUserDetails().setFirstName("John");
		bean.getUserDetails().setLastName("Doe");
		bean.getUserDetails().setBirthDate(new DateTime().withYear(1975).withMonthOfYear(1).withDayOfMonth(1));
		bean.getUserDetails().setGender(Gender.MALE);
		bean.getUserDetails().setEmail("john.doe@email.com");
		bean.getUserDetails().setPhone("+47 23456789");
		bean.getUserAddress().setAddress("Storgata 1");
		bean.getUserAddress().setPostalCode("1234");
		bean.getUserAddress().setMunicipality("Oslo");
		bean.getUserAddress().setRegion("Oslo");
		bean.getUserAddress().setCountry("Norway");
		return bean;
	}

	public static User createUserJane() {
		DateTime dateTime = DateTime.now();
		User bean = BeanFactory.createUserBean();
		bean.getUsername().setUsername("jane.doe");
		bean.getUserStatus().setStatus(Status.ACTIVE);
		bean.getUserStatus().setCreated(dateTime);
		bean.getUserStatus().setModified(dateTime);
		bean.getUserDetails().setFirstName("Jane");
		bean.getUserDetails().setLastName("Doe");
		bean.getUserDetails().setBirthDate(new DateTime().withYear(1970).withMonthOfYear(12).withDayOfMonth(30));
		bean.getUserDetails().setGender(Gender.FEMALE);
		bean.getUserDetails().setEmail("jane.doe@email.com");
		bean.getUserDetails().setPhone("+47 98765432");
		bean.getUserAddress().setAddress("Lillegata 1");
		bean.getUserAddress().setPostalCode("1010");
		bean.getUserAddress().setMunicipality("Oslo");
		bean.getUserAddress().setRegion("Oslo");
		bean.getUserAddress().setCountry("Norway");
		return bean;
	}

	public static User createUserJonah() {
		DateTime dateTime = DateTime.now();
		User bean = BeanFactory.createUserBean();
		bean.getUsername().setUsername("jonah.doe");
		bean.getUserStatus().setStatus(Status.ACTIVE);
		bean.getUserStatus().setCreated(dateTime);
		bean.getUserStatus().setModified(dateTime);
		bean.getUserDetails().setFirstName("Jonah");
		bean.getUserDetails().setLastName("Doe");
		bean.getUserDetails().setBirthDate(new DateTime().withYear(1975).withMonthOfYear(1).withDayOfMonth(1));
		bean.getUserDetails().setGender(Gender.MALE);
		bean.getUserDetails().setEmail("jonah.doe@email.com");
		bean.getUserDetails().setPhone("+47 19283746");
		bean.getUserAddress().setAddress("Mellomgata 1");
		bean.getUserAddress().setPostalCode("1221");
		bean.getUserAddress().setMunicipality("Oslo");
		bean.getUserAddress().setRegion("Oslo");
		bean.getUserAddress().setCountry("Norway");
		return bean;
	}

	public static UserPassword createUserPasswordJohn() {
		UserPassword bean = new UserPassword();
		bean.setPassword("johnsPassword");
		bean.setSalt("johnsPasswordSalt");
		return bean;
	}

	public static UserPassword createUserPasswordJane() {
		UserPassword bean = new UserPassword();
		bean.setPassword("janesPassword");
		bean.setSalt("janesPasswordSalt");
		return bean;
	}

	public static UserPassword createUserPasswordJonah() {
		UserPassword bean = new UserPassword();
		bean.setPassword("jonahsPassword");
		bean.setSalt("jonahsPasswordSalt");
		return bean;
	}

	public static Username createUsername(String username) {
		Username bean = new Username();
		bean.setUsername(username);
		return bean;
	}

	public static RegisterUserRequest createRegisterUserRequestJohn() {
		return new RegisterUserRequest(createUserJohn(), createUserPasswordJohn());
	}

	public static RegisterUserRequest createRegisterUserRequestJane() {
		return new RegisterUserRequest(createUserJane(), createUserPasswordJane());
	}

	public static RegisterUserRequest createRegisterUserRequestJonah() {
		return new RegisterUserRequest(createUserJonah(), createUserPasswordJonah());
	}

	public static CreateUserInput createCreateUserInputJohn() {
		return new CreateUserInput(createUserJohn().getUsername());
	}

	public static CreateUserDetailsInput createCreateUserDetailsInputJohn() {
		return createCreateUserDetailsInputJohn(new Id(0L));
	}

	public static CreateUserDetailsInput createCreateUserDetailsInputJohn(Id userId) {
		return new CreateUserDetailsInput(userId, createUserJohn().getUserDetails());
	}

	public static CreateUserAddressInput createCreateUserAddressInputJohn() {
		return createCreateUserAddressInputJohn(new Id(0L));
	}

	public static CreateUserAddressInput createCreateUserAddressInputJohn(Id userId) {
		return new CreateUserAddressInput(userId, createUserJohn().getUserAddress());
	}

	public static CreateUserInput createCreateUserInputJane() {
		return new CreateUserInput(createUserJane().getUsername());
	}

	public static CreateUserDetailsInput createCreateUserDetailsInputJane() {
		return createCreateUserDetailsInputJane(new Id(0L));
	}

	public static CreateUserDetailsInput createCreateUserDetailsInputJane(Id userId) {
		return new CreateUserDetailsInput(userId, createUserJane().getUserDetails());
	}

	public static CreateUserAddressInput createCreateUserAddressInputJane() {
		return createCreateUserAddressInputJane(new Id(0L));
	}

	public static CreateUserAddressInput createCreateUserAddressInputJane(Id userId) {
		return new CreateUserAddressInput(userId, createUserJane().getUserAddress());
	}

	public static CreateUserInput createCreateUserInputJonah() {
		return new CreateUserInput(createUserJonah().getUsername());
	}

	public static CreateUserDetailsInput createCreateUserDetailsInputJonah() {
		return createCreateUserDetailsInputJonah(new Id(0L));
	}

	public static CreateUserDetailsInput createCreateUserDetailsInputJonah(Id userId) {
		return new CreateUserDetailsInput(userId, createUserJonah().getUserDetails());
	}

	public static CreateUserAddressInput createCreateUserAddressInputJonah() {
		return createCreateUserAddressInputJonah(new Id(0L));
	}

	public static CreateUserAddressInput createCreateUserAddressInputJonah(Id userId) {
		return new CreateUserAddressInput(userId, createUserJonah().getUserAddress());
	}

	public static UpdateUserInput createUpdateUserInputJohn() {
		return createUpdateUserInputJohn(new Id(0L));
	}

	public static UpdateUserInput createUpdateUserInputJohn(Id userId) {
		return new UpdateUserInput(userId, createUserJohn().getUsername());
	}

	public static UpdateUserDetailsInput createUpdateUserDetailsInputJohn() {
		return createUpdateUserDetailsInputJohn(new Id(0L));
	}

	public static UpdateUserDetailsInput createUpdateUserDetailsInputJohn(Id userId) {
		return new UpdateUserDetailsInput(userId, createUserJohn().getUserDetails());
	}

	public static UpdateUserStatusInput createUpdateUserStatusInputJohn() {
		return createUpdateUserStatusInputJohn(new Id(0L));
	}

	public static UpdateUserStatusInput createUpdateUserStatusInputJohn(Id userId) {
		return new UpdateUserStatusInput(userId, createUserJohn().getUserStatus());
	}

	public static UpdateUserAddressInput createUpdateUserAddressInputJohn() {
		return createUpdateUserAddressInputJohn(new Id(0L));
	}

	public static UpdateUserAddressInput createUpdateUserAddressInputJohn(Id userId) {
		return new UpdateUserAddressInput(userId, createUserJohn().getUserAddress());
	}

	public static UpdateUserPasswordInput createUpdateUserPasswordInputJohn() {
		return createUpdateUserPasswordInputJohn(new Id(0L));
	}

	public static UpdateUserPasswordInput createUpdateUserPasswordInputJohn(Id userId) {
		return new UpdateUserPasswordInput(userId, createUserPasswordJohn());
	}

	public static UpdateUserInput createUpdateUserInputJane() {
		return createUpdateUserInputJane(new Id(0L));
	}

	public static UpdateUserInput createUpdateUserInputJane(Id userId) {
		return new UpdateUserInput(userId, createUserJane().getUsername());
	}

	public static UpdateUserDetailsInput createUpdateUserDetailsInputJane() {
		return createUpdateUserDetailsInputJane(new Id(0L));
	}

	public static UpdateUserDetailsInput createUpdateUserDetailsInputJane(Id userId) {
		return new UpdateUserDetailsInput(userId, createUserJane().getUserDetails());
	}

	public static UpdateUserStatusInput createUpdateUserStatusInputJane() {
		return createUpdateUserStatusInputJane(new Id(0L));
	}

	public static UpdateUserStatusInput createUpdateUserStatusInputJane(Id userId) {
		return new UpdateUserStatusInput(userId, createUserJane().getUserStatus());
	}

	public static UpdateUserAddressInput createUpdateUserAddressInputJane() {
		return createUpdateUserAddressInputJane(new Id(0L));
	}

	public static UpdateUserAddressInput createUpdateUserAddressInputJane(Id userId) {
		return new UpdateUserAddressInput(userId, createUserJane().getUserAddress());
	}

	public static UpdateUserPasswordInput createUpdateUserPasswordInputJane() {
		return createUpdateUserPasswordInputJane(new Id(0L));
	}

	public static UpdateUserPasswordInput createUpdateUserPasswordInputJane(Id userId) {
		return new UpdateUserPasswordInput(userId, createUserPasswordJane());
	}

	public static UpdateUserInput createUpdateUserInputJonah() {
		return createUpdateUserInputJonah(new Id(0L));
	}

	public static UpdateUserInput createUpdateUserInputJonah(Id userId) {
		return new UpdateUserInput(userId, createUserJonah().getUsername());
	}

	public static UpdateUserDetailsInput createUpdateUserDetailsInputJonah() {
		return createUpdateUserDetailsInputJonah(new Id(0L));
	}

	public static UpdateUserDetailsInput createUpdateUserDetailsInputJonah(Id userId) {
		return new UpdateUserDetailsInput(userId, createUserJonah().getUserDetails());
	}

	public static UpdateUserStatusInput createUpdateUserStatusInputJonah() {
		return createUpdateUserStatusInputJonah(new Id(0L));
	}

	public static UpdateUserStatusInput createUpdateUserStatusInputJonah(Id userId) {
		return new UpdateUserStatusInput(userId, createUserJonah().getUserStatus());
	}

	public static UpdateUserAddressInput createUpdateUserAddressInputJonah() {
		return createUpdateUserAddressInputJonah(new Id(0L));
	}

	public static UpdateUserAddressInput createUpdateUserAddressInputJonah(Id userId) {
		return new UpdateUserAddressInput(userId, createUserJonah().getUserAddress());
	}

	public static UpdateUserPasswordInput createUpdateUserPasswordInputJonah() {
		return createUpdateUserPasswordInputJonah(new Id(0L));
	}

	public static UpdateUserPasswordInput createUpdateUserPasswordInputJonah(Id userId) {
		return new UpdateUserPasswordInput(userId, createUserPasswordJonah());
	}

	public static ReadUserInput createReadUserInputJohn() {
		return new ReadUserInput(createUsername("john.doe"));
	}

	public static ReadUserInput createReadUserInputJane() {
		return new ReadUserInput(createUsername("jane.doe"));
	}

	public static ReadUserInput createReadUserInputJonah() {
		return new ReadUserInput(createUsername("jonah.doe"));
	}

	public static DeleteUserInput createDeleteUserInput() {
		return createDeleteUserInput(new Id(0L));
	}

	public static DeleteUserInput createDeleteUserInput(Id id) {
		return new DeleteUserInput(id);
	}

	public static DeleteUserDetailsInput createDeleteUserDetailsInput() {
		return createDeleteUserDetailsInput(new Id(0L));
	}

	public static DeleteUserDetailsInput createDeleteUserDetailsInput(Id id) {
		return new DeleteUserDetailsInput(id);
	}

	public static DeleteUserStatusInput createDeleteUserStatusInput() {
		return createDeleteUserStatusInput(new Id(0L));
	}

	public static DeleteUserStatusInput createDeleteUserStatusInput(Id id) {
		return new DeleteUserStatusInput(id);
	}

	public static DeleteUserAddressInput createDeleteUserAddressInput() {
		return createDeleteUserAddressInput(new Id(0L));
	}

	public static DeleteUserAddressInput createDeleteUserAddressInput(Id id) {
		return new DeleteUserAddressInput(id);
	}

	public static DeleteUserPasswordInput createDeleteUserPasswordInput() {
		return createDeleteUserPasswordInput(new Id(0L));
	}

	public static DeleteUserPasswordInput createDeleteUserPasswordInput(Id id) {
		return new DeleteUserPasswordInput(id);
	}

	public static PurgeUsersInput createPurgeUsersInput() {
		return new PurgeUsersInput();
	}

	public static Note createNote1() {
		DateTime dateTime = DateTime.now();
		Note note = BeanFactory.createNote();
		note.getTitle().setTitle("Note 1");
		note.getNoteDetails().setDescription("This is note 1");
		note.getNoteStatus().setStatus(Status.NEW);
		note.getNoteStatus().setCreated(dateTime);
		note.getNoteStatus().setModified(dateTime);
		return note;
	}

	public static CreateNoteInput createCreateNoteInput1() {
		return createCreateNoteInput1(new Id(0L));
	}

	public static CreateNoteInput createCreateNoteInput1(Id userId) {
		return new CreateNoteInput(userId, createNote1().getTitle());
	}

	public static CreateNoteDetailsInput createCreateNoteDetailsInput1() {
		return createCreateNoteDetailsInput1(new Id(0L));
	}

	public static CreateNoteDetailsInput createCreateNoteDetailsInput1(Id id) {
		return new CreateNoteDetailsInput(id, createNote1().getNoteDetails());
	}

	public static ReadNoteInput createReadNoteInput1() {
		return new ReadNoteInput(createUsername("john.doe"));
	}

	public static UpdateNoteInput createUpdateNoteInput1() {
		return createUpdateNoteInput1(new Id(0L));
	}

	public static UpdateNoteInput createUpdateNoteInput1(Id id) {
		return new UpdateNoteInput(id, createNote1().getTitle());
	}

	public static UpdateNoteDetailsInput createUpdateNoteDetailsInput1() {
		return createUpdateNoteDetailsInput1(new Id(0L));
	}

	public static UpdateNoteDetailsInput createUpdateNoteDetailsInput1(Id id) {
		return new UpdateNoteDetailsInput(id, createNote1().getNoteDetails());
	}

	public static UpdateNoteStatusInput createUpdateNoteStatusInput1() {
		return createUpdateNoteStatusInput1(new Id(0L));
	}

	public static UpdateNoteStatusInput createUpdateNoteStatusInput1(Id id) {
		return new UpdateNoteStatusInput(id, createNote1().getNoteStatus());
	}

	public static DeleteNoteInput createDeleteNoteInput() {
		return createDeleteNoteInput(new Id(0L));
	}

	public static DeleteNoteInput createDeleteNoteInput(Id id) {
		return new DeleteNoteInput(id);
	}

	public static DeleteNoteDetailsInput createDeleteNoteDetailsInput() {
		return createDeleteNoteDetailsInput(new Id(0L));
	}

	public static DeleteNoteDetailsInput createDeleteNoteDetailsInput(Id id) {
		return new DeleteNoteDetailsInput(id);
	}

	public static DeleteNoteStatusInput createDeleteNoteStatusInput() {
		return createDeleteNoteStatusInput(new Id(0L));
	}

	public static DeleteNoteStatusInput createDeleteNoteStatusInput(Id id) {
		return new DeleteNoteStatusInput(id);
	}

	public static Task createTask1() {
		DateTime dateTime = DateTime.now();
		Task task = BeanFactory.createTask();
		task.getTitle().setTitle("Task 1");
		task.getTaskDetails().setDescription("This is task 1");
		task.getTaskStatus().setStatus(Status.NEW);
		task.getTaskStatus().setCreated(dateTime);
		task.getTaskStatus().setModified(dateTime);
		return task;
	}

	public static CreateTaskInput createCreateTaskInput1() {
		return createCreateTaskInput1(new Id(0L));
	}

	public static CreateTaskInput createCreateTaskInput1(Id userId) {
		return new CreateTaskInput(userId, createTask1().getTitle());
	}

	public static CreateTaskDetailsInput createCreateTaskDetailsInput1() {
		return createCreateTaskDetailsInput1(new Id(0L));
	}

	public static CreateTaskDetailsInput createCreateTaskDetailsInput1(Id id) {
		return new CreateTaskDetailsInput(id, createTask1().getTaskDetails());
	}

	public static ReadTaskInput createReadTaskInput1() {
		return new ReadTaskInput(createUsername("john.doe"));
	}

	public static UpdateTaskInput createUpdateTaskInput1() {
		return createUpdateTaskInput1(new Id(0L));
	}

	public static UpdateTaskInput createUpdateTaskInput1(Id id) {
		return new UpdateTaskInput(id, createTask1().getTitle());
	}

	public static UpdateTaskDetailsInput createUpdateTaskDetailsInput1() {
		return createUpdateTaskDetailsInput1(new Id(0L));
	}

	public static UpdateTaskDetailsInput createUpdateTaskDetailsInput1(Id id) {
		return new UpdateTaskDetailsInput(id, createTask1().getTaskDetails());
	}

	public static UpdateTaskStatusInput createUpdateTaskStatusInput1() {
		return createUpdateTaskStatusInput1(new Id(0L));
	}

	public static UpdateTaskStatusInput createUpdateTaskStatusInput1(Id id) {
		return new UpdateTaskStatusInput(id, createTask1().getTaskStatus());
	}

	public static DeleteTaskInput createDeleteTaskInput() {
		return createDeleteTaskInput(new Id(0L));
	}

	public static DeleteTaskInput createDeleteTaskInput(Id id) {
		return new DeleteTaskInput(id);
	}

	public static DeleteTaskDetailsInput createDeleteTaskDetailsInput() {
		return createDeleteTaskDetailsInput(new Id(0L));
	}

	public static DeleteTaskDetailsInput createDeleteTaskDetailsInput(Id id) {
		return new DeleteTaskDetailsInput(id);
	}

	public static DeleteTaskStatusInput createDeleteTaskStatusInput() {
		return createDeleteTaskStatusInput(new Id(0L));
	}

	public static DeleteTaskStatusInput createDeleteTaskStatusInput(Id id) {
		return new DeleteTaskStatusInput(id);
	}
}
