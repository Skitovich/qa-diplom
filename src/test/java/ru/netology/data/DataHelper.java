package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class Cards {
        private String card;
    }
    public static Cards getVerificationCard () {
        return new Cards("4444 4444 4444 4441");
    }
    public static Cards getInvalidCard () {
        return new Cards("4444 4444 4444 4442");
    }

    @Value
    public static class Name {

    }

}