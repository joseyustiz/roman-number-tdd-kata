package com.joseyustiz.katas;

import java.util.HashMap;
import java.util.Map;

public class RomanToDecimalConverter {
    Map<Character, Value> romanToDecimalValues;

    public RomanToDecimalConverter() {
        this.romanToDecimalValues = new HashMap<>();
        romanToDecimalValues.put('I', new Value(true, 1));
        romanToDecimalValues.put('V', new Value(false, 5));
        romanToDecimalValues.put('X', new Value(true, 10));
        romanToDecimalValues.put('L', new Value(false, 50));
        romanToDecimalValues.put('C', new Value(true, 100));
        romanToDecimalValues.put('D', new Value(false, 500));
        romanToDecimalValues.put('M', new Value(true, 1000));
    }

    public int convert(String romanNumber) {
        int decimal = 0;
        char repeated = '\0';
        byte repeatedCount = 0;
        for (byte i = 0; i < romanNumber.length(); i++) {
            char current = romanNumber.charAt(i);
            if (repeated == current) {
                verifyRomanNumberFormat(romanNumber, repeatedCount, current);
                repeatedCount++;
            } else {
                if (isSubtraction(repeated, i, current))
                    decimal *= -1;

                repeated = current;
                repeatedCount = 1;

            }
            decimal = sum(romanNumber, decimal, repeated);
        }
        return decimal;
    }

    private boolean isSubtraction(char repeated, byte index, char current) {
        return index > 0 && romanToDecimalValues.get(repeated).getDecimal() < romanToDecimalValues.get(current).getDecimal();
    }

    private void verifyRomanNumberFormat(String romanNumber, int repeatedCount, char current) {
        if (repeatedCount > 2)
            throw new IllegalArgumentException(romanNumber + " has more than 3 repeated digits");
        if (!romanToDecimalValues.get(current).isRepeatable())
            throw new IllegalArgumentException(romanNumber + " has invalid repeated roman digits");
    }

    private int sum(String romanNumber, int decimal, char repeated) {
        if (romanToDecimalValues.get(repeated) == null)
            throw new IllegalArgumentException(romanNumber + " is not a roman number");
        else
            decimal += romanToDecimalValues.get(repeated).getDecimal();
        return decimal;
    }
}
