package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    Faker faker = new Faker();

    @Value
    public static class Cards {
        String card;
    }
    public static Cards getVerificationCard () {
        return new Cards("4444 4444 4444 4441");
    }
    public static Cards getInvalidCard () {
        return new Cards("4444 4444 4444 4442");
    }

    @Value
    public static class OwnerName {
    String ownerCardName;
    }

    public static OwnerName getValidOwnerCardName () {
        return new OwnerName("Ruslan");
    }
    public static OwnerName getInvalidOwnerCardName () {
        return new OwnerName("%?*(12123");
    }

    @Value
    public static class MonthCardExpiration {
        String month;
    }

    public static MonthCardExpiration getValidMonth () {
        return new MonthCardExpiration("08");
    }
    public static MonthCardExpiration getInvalidMonth () {
        return new MonthCardExpiration("RA");
    }


    @Value
    public static class YearCardExpiration {
        String year;
    }

    public static YearCardExpiration getValidYearCardExpiration () {
        return new YearCardExpiration("22");
    }
    public static YearCardExpiration getInvalidYearCardExpiration () {
        return new YearCardExpiration("RA");
    }

    @Value
    public static class CvcCvvCard {
        String cvc;
    }

    public static YearCardExpiration getValidYearCvcCvvCard () {
        return new YearCardExpiration("999");
    }
    public static YearCardExpiration getInvalidYearCvcCvvCard () {
        return new YearCardExpiration("RRA");
    }



}