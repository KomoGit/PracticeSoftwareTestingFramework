package com.toolshop.framework.utils;

import com.github.javafaker.Faker;

public class DataUtils {
    private static final Faker faker = new Faker();

    private DataUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getRandomAddress(){
        return faker.address().fullAddress();
    }

    public static String getRandomFirstName(){
        return faker.name().firstName();
    }

    public static String getRandomLastName(){
        return faker.name().lastName();
    }

    public static String getRandomFullName(){
        return faker.name().fullName();
    }

    public static String getRandomEmail(){
        return faker.internet().emailAddress();
    }

    public static String getRandomPhoneNumber(){
        return faker.phoneNumber().phoneNumber();
    }

    public static String getRandomPostalCode(){
        return faker.address().zipCode();
    }

    public static String getRandomPostalCode(String state){
        return faker.address().zipCodeByState(state);
    }

    public static String getRandomCity(){
        return faker.address().city();
    }
}
