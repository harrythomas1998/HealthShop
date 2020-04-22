package com.example.assignment;

public class VisaValidation extends AbstractCardValidator {

    public VisaValidation(String cardName, String cardNumber, int expiryDateMonth,
                          int expiryDateYear, String cvv) {

        super(cardName, cardNumber, expiryDateMonth, expiryDateYear, cvv);

    }

    protected boolean validateCardNumberFormat() {


        boolean errorInFormat = false;

        if (cardNumber.charAt(0) != '4') {

            errorInFormat = true;

        }
        else {


        }

        return !errorInFormat;

    }

}
