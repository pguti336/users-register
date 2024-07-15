package com.challenge.users_register.utils;

import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class CreateUserRequestTestUtil {
    public static CreateUserRequest getDummyCreateUserRequest(String email) {
        CreateUserRequest newUserRequest = new CreateUserRequest();
        newUserRequest.setEmail(email);
        newUserRequest.setPassword("P4s$word");

        List<Phone> phones = getPhonesList();
        newUserRequest.setPhones(phones);

        return newUserRequest;
    }

    private static List<Phone> getPhonesList() {
        Phone phone1 = new Phone();
        phone1.setNumber("11122233");
        phone1.setCitycode("1");
        phone1.setCountrycode("55");

        Phone phone2 = new Phone();
        phone2.setNumber("22233344");
        phone2.setCitycode("1");
        phone2.setCountrycode("55");

        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);

        return phones;
    }
}
