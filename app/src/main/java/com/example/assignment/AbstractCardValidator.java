package com.example.assignment;

import java.util.Calendar;

public class AbstractCardValidator {


    protected String cardName;
    protected String cardNumber;
    protected int expiryDateMonth;
    protected int expiryDateYear;
    protected String cvv;

    public AbstractCardValidator(String cardName, String cardNumber, int expiryDateMonth,
                                 int expiryDateYear, String cvv) {

        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiryDateMonth = expiryDateMonth;
        this.expiryDateYear = expiryDateYear;
        this.cvv = cvv;
    }

    public boolean validate() {

        boolean cardNameValidated = validateCardName();

        if (cardNameValidated) {

            boolean expiryDateValidated = validateExpiryDate();

            if (expiryDateValidated) {

                boolean cvvValidated = validateCvv();

                if (cvvValidated) {

                    boolean cardNumberLengthValidated = validateCardNumberLength();

                    if (cardNumberLengthValidated) {

                        boolean cardNumberFormatValidated = validateCardNumberFormat();

                        if (cardNameValidated && expiryDateValidated && cardNumberLengthValidated
                                && cardNumberFormatValidated)
                            return true;
                        else
                            return false;

                    }
                }
            }
        }

        return false;

    }

    protected boolean validateCardName() {

        boolean errorInName = false;

        if (cardName.length() == 0) {
            errorInName = true;
        }

        return !errorInName;

    }

    protected boolean validateCvv() {

        boolean errorInCvv = false;

        if (cvv.length() != 3 && cvv.length() != 4) {
            errorInCvv = true;
        }

        return !errorInCvv;

    }

    protected boolean validateExpiryDate() {

        boolean errorInDate = false;

        Calendar rightNow = Calendar.getInstance();

        int thisMonth = rightNow.get(Calendar.MONTH) + 1;

        int thisYear = rightNow.get(Calendar.YEAR);

        if (expiryDateYear < thisYear || (expiryDateYear == thisYear && expiryDateMonth < thisMonth)) {

            errorInDate = true;

        }

        return !errorInDate;

    }

    protected boolean validateCardNumberLength() {

        boolean errorInNumber = false;

        if (cardNumber.length() != 16) {

            errorInNumber = true;

        } else {

            for (int i = 0; i < 16; i++) {

                if (cardNumber.charAt(i) > '9' || cardNumber.charAt(i) < '0') {


                    errorInNumber = true;

                }
            }
        }

        return !errorInNumber;

    }

    protected boolean validateCardNumberFormat() {

        return false;

    }
}
