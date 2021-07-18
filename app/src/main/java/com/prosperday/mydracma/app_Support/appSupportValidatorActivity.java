package com.prosperday.mydracma.app_Support;
// ---------------------------------------------------------------
// Created by Rosemeire Deconti
// Created on 05/01/2018
// Created at 15:02
// Package is com.prosperday.mydracma
// Project is MyDracma
// ----------------------------------------------------------------

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

public class appSupportValidatorActivity {

    public static boolean NotNull(View pView, String pMessage) {

        EditText edText = (EditText) pView;
        Editable text = edText.getText();

        if (text.length() <= 0) {
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    ///---------------------------------------------------------------------------------------------
    ///---------------------------------------------------------------------------------------------
    ///---------------------------------------------------------------------------------------------
    public static boolean validationDate(View pView, String pMessage) {

        EditText edText = (EditText) pView;
        Editable text = edText.getText();

        //Check number of characters of date informed by user
        if (text.length() < 10) {
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }

        //Get day, month and year from date
        String dateString = edText.getText().toString();
        String dayString = dateString.substring(0, 2);
        String slash1String = dateString.substring(2, 3);
        String monthString = dateString.substring(3, 5);
        String slash2String = dateString.substring(5, 6);
        String yearString = dateString.substring(6, 10);
        String slashValue = "/";

        //Check slash1 and slash2
        if (!slash1String.equals(slashValue)) {
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }

        if (!slash2String.equals(slashValue)) {
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }

        //Convert string to iny
        int dayNumber = Integer.parseInt(dayString);
        int monthNumber = Integer.parseInt(monthString);
        int yearNumber = Integer.parseInt(yearString);

        //Check month
        if ((monthNumber <= 0) || (monthNumber >= 12)) {
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }

        //Check year
        if ((yearNumber <= 0) || (yearNumber >= 9999)) {
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }

        //Check if year is Leap
        String yearLeap = "N";

        if (yearNumber % 400 == 0) {
            yearLeap = "Y";
        } else if ((yearNumber % 4 == 0) && (yearNumber % 100 != 0)) {
            yearLeap = "Y";
        } else {
            yearLeap = "N";

        }

        //Check day (28 or 29) to February
        if (monthNumber == 2) {
            if (Objects.equals(yearLeap, "Y")) {
                if ((dayNumber <= 0) || (dayNumber >= 29)) {
                    edText.setError(pMessage);
                    edText.setFocusable(true);
                    edText.requestFocus();
                    return false;
                }
            }
        }

        if (monthNumber == 2) {
            if (Objects.equals(yearLeap, "N")) {
                if ((dayNumber <= 0) || (dayNumber >= 28)) {
                    edText.setError(pMessage);
                    edText.setFocusable(true);
                    edText.requestFocus();
                    return false;
                }
            }
        }

        //Check day (30) to April, June. September, November
        if ((monthNumber == 4) || (monthNumber == 6) || (monthNumber == 9) || (monthNumber == 11)) {
            if ((dayNumber <= 0) || (dayNumber >= 30)) {
                edText.setError(pMessage);
                edText.setFocusable(true);
                edText.requestFocus();
                return false;
            }
        }

        //Check day (31) to January, March, May, July, August, October, December
        if ((monthNumber == 1) || (monthNumber == 3) || (monthNumber == 5) || (monthNumber == 7) ||
                (monthNumber == 8) || (monthNumber == 10) || (monthNumber == 12)) {
            if ((dayNumber <= 0) || (dayNumber >= 31)) {
                edText.setError(pMessage);
                edText.setFocusable(true);
                edText.requestFocus();
                return false;
            }
        }
        return true;
    }

    ///---------------------------------------------------------------------------------------------
    ///---------------------------------------------------------------------------------------------
    ///---------------------------------------------------------------------------------------------
    public static boolean validateCPF(String CPF) {

        /// CPF = Mask.unmask(CPF);
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")) {
            return false;
        }
        char dig10, dig11;
        int sm, i, r, num, peso;
        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = CPF.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = CPF.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);
            return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
        } catch (Exception error) {
            return (false);
        }
    }
}
