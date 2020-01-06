package com.joseyustiz.katas

import spock.lang.Specification

class RomanToDecimalConverterSpec extends Specification {
    def converter = new RomanToDecimalConverter()

    def "convert one digit roman number to decimal"() {
        when:
        def result = converter.convert(romanNumber)

        then:
        result == decimalNumber

        where:
        romanNumber | decimalNumber
        "I"         | 1
        "V"         | 5
        "X"         | 10
        "L"         | 50
        "C"         | 100
        "D"         | 500
        "M"         | 1000
    }

    def "thrown an exception if it is passed a no roman number"() {
        when:
        converter.convert("NoRoman")

        then:
        thrown(IllegalArgumentException)
    }


    def "thrown an exception if repeating a numeral more than three times"() {
        when:
        converter.convert("IIII")

        then:
        thrown(IllegalArgumentException)
    }

    def "thrown an exception if it is repeated different character than I, X, C, and M"() {
        when:
        converter.convert(romanNumber)

        then:
        thrown(IllegalArgumentException)

        where:
        romanNumber << ["VV", "LL", "DD"]
    }

    def "sum repeating a numeral up to three times"() {
        when:
        def result = converter.convert(romanNumber)

        then:
        result == decimalNumber

        where:
        romanNumber      | decimalNumber
        "III"            | 3
        "VIII"           | 8
        "XXX"            | 30
        "XXXVIII"        | 38
        "CCC"            | 300
        "CCCLXXXVIII"    | 388
        "MMMCCCLXXXVIII" | 3388
    }

    def "Writing a smaller numeral to the left of a larger numeral represents subtraction"() {
        when:
        def result = converter.convert(romanNumber)

        then:
        result == decimalNumber

        where:
        romanNumber | decimalNumber
        "IV"        | 4
        "IX"        | 9
        "XL"        | 40
        "XC"        | 90
        "CD"        | 400
        "CM"        | 900
    }
}
