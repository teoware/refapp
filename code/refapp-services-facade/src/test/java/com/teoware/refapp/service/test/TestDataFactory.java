package com.teoware.refapp.service.test;

import org.joda.time.DateTime;
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

    public static Note createNote1() {
        DateTime dateTime = DateTime.now();
        Note note = BeanFactory.createNote();
        note.getUuid().setUuid("a");
        note.getNoteDetails().setTitle("Note 1");
        note.getNoteDetails().setDescription("This is note 1");
        note.getNoteStatus().setStatus(Status.NEW);
        note.getNoteStatus().setCreated(dateTime);
        note.getNoteStatus().setModified(dateTime);
        return note;
    }

    public static Task createTask1() {
        DateTime dateTime = DateTime.now();
        Task task = BeanFactory.createTask();
        task.getUuid().setUuid("a");
        task.getTaskDetails().setTitle("Task 1");
        task.getTaskDetails().setDescription("This is task 1");
        task.getTaskStatus().setStatus(Status.NEW);
        task.getTaskStatus().setCreated(dateTime);
        task.getTaskStatus().setModified(dateTime);
        return task;
    }
}
