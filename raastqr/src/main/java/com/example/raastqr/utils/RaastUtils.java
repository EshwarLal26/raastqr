package com.example.raastqr.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public final class RaastUtils {

    private static final Pattern MOBILE_PATTERN =
            Pattern.compile("\\+[0-9]{1,3}-[0-9]{6,15}");

    private static final Pattern MEMBER_ID_PATTERN =
            Pattern.compile("[0-9A-Za-z]{1,20}");

    private static final Pattern IBAN_PATTERN =
            Pattern.compile("[A-Z]{2}[0-9A-Z]{13,32}");

    private static final Pattern CURRENCY_PATTERN =
            Pattern.compile("[A-Z]{3}");

    private RaastUtils() {
    }

    public static String trimToNull(String value) {
        if (value == null) {
            return null;
        }

        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    public static String normalizeOptionalText(String value) {
        return trimToNull(value);
    }

    public static String requireText(String fieldName, String value) {
        String trimmed = trimToNull(value);
        if (trimmed == null) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return trimmed;
    }

    public static BigDecimal requireAmount(String fieldName, BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        if (value.signum() < 0) {
            throw new IllegalArgumentException(fieldName + " must not be negative");
        }
        return value;
    }

    public static String formatCurrency(String fieldName, String currency) {
        currency = requireText(fieldName, currency).toUpperCase();
        if (!CURRENCY_PATTERN.matcher(currency).matches()) {
            throw new IllegalArgumentException(fieldName + " must be 3 uppercase letters");
        }
        return currency;
    }

    public static String formatOptionalCurrency(String fieldName, String currency) {
        String trimmed = trimToNull(currency);
        return trimmed == null ? null : formatCurrency(fieldName, trimmed);
    }

    public static String formatMemberId(String fieldName, String memberId) {
        memberId = requireText(fieldName, memberId);
        if (!MEMBER_ID_PATTERN.matcher(memberId).matches()) {
            throw new IllegalArgumentException(fieldName + " is invalid");
        }
        return memberId;
    }

    public static String formatOptionalMemberId(String fieldName, String memberId) {
        String trimmed = trimToNull(memberId);
        return trimmed == null ? null : formatMemberId(fieldName, trimmed);
    }

    public static String formatIban(String fieldName, String iban) {
        iban = requireText(fieldName, iban).replaceAll("\\s+", "").toUpperCase();
        if (!IBAN_PATTERN.matcher(iban).matches()) {
            throw new IllegalArgumentException(fieldName + " is invalid");
        }
        return iban;
    }

    public static String formatOptionalIban(String fieldName, String iban) {
        String trimmed = trimToNull(iban);
        return trimmed == null ? null : formatIban(fieldName, trimmed);
    }

    public static String formatMobileNumber(String mobileNo) {
        if (mobileNo == null || mobileNo.trim().isEmpty()) {
            return mobileNo;
        }

        mobileNo = mobileNo.trim();

        if (MOBILE_PATTERN.matcher(mobileNo).matches()) {
            return mobileNo;
        }

        String digits = mobileNo.replaceAll("[^0-9]", "");

        if (digits.startsWith("92") && digits.length() >= 12) {
            return "+92-" + digits.substring(2);
        }

        if (digits.startsWith("0") && digits.length() == 11) {
            return "+92-" + digits.substring(1);
        }

        if (digits.length() == 10) {
            return "+92-" + digits;
        }

        return mobileNo;
    }

    public static String requireValidMobile(String fieldName, String mobileNo) {
        String formatted = formatMobileNumber(mobileNo);
        if (formatted == null || formatted.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        if (!MOBILE_PATTERN.matcher(formatted).matches()) {
            throw new IllegalArgumentException(fieldName + " is invalid");
        }
        return formatted;
    }

    public static String formatOptionalMobile(String fieldName, String mobileNo) {
        String trimmed = trimToNull(mobileNo);
        return trimmed == null ? null : requireValidMobile(fieldName, trimmed);
    }

    public static String formatIsoDate(String fieldName, String date) {
        date = requireText(fieldName, date);
        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception ex) {
            throw new IllegalArgumentException(fieldName + " must be in format yyyy-MM-dd");
        }
    }

    public static String formatIsoDateTime(String fieldName, String dateTime) {
        dateTime = requireText(fieldName, dateTime);
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception ex) {
            throw new IllegalArgumentException(fieldName + " must be in format yyyy-MM-dd'T'HH:mm:ss");
        }
    }

    public static String formatIsoOffsetDateTime(String fieldName, String dateTime) {
        dateTime = requireText(fieldName, dateTime);
        try {
            return OffsetDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch (Exception ex) {
            throw new IllegalArgumentException(fieldName + " must be in format yyyy-MM-dd'T'HH:mm:ssXXX");
        }
    }
}
