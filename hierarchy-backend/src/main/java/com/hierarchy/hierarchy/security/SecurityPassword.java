package com.hierarchy.hierarchy.security;

import java.util.HashSet;
import java.util.Set;

public class SecurityPassword {
    public static int calculatePasswordStrength(String password) {
        int additions = calculateAdditions(password);
        int deductions = calculateDeductions(password);
        int passwordStrength = Math.max(0, Math.min(100, additions - deductions));

        return passwordStrength;
    }

    public static int calculateAdditions(String password) {
        int length = password.length();

        int additions = 0;
        additions += length * 4;
        additions += (length - countUppercaseLetters(password)) * 2;
        additions += (length - countLowercaseLetters(password)) * 2;
        additions += countNumbers(password) * 4;
        additions += countSymbols(password) * 6;
        additions += countMiddleNumbersOrSymbols(password) * 2;
        additions += countRequirements(password) * 2;

        return additions;
    }

    public static int calculateDeductions(String password) {
        int deductions = 0;
        int n = password.length();

        deductions += n;
        deductions += n;
        deductions -= countRepeatCharacters(password);
        deductions -= countConsecutiveUppercaseLetters(password) * 2;
        deductions -= countConsecutiveLowercaseLetters(password);
        deductions -= countConsecutiveNumbers(password);
        deductions -= countSequentialLetters(password) * 3;
        deductions -= countSequentialNumbers(password) * 3;
        deductions -= countSequentialSymbols(password) * 3;

        return deductions;
    }

    private static int countUppercaseLetters(String password) {
        return (int) password.chars().filter(Character::isUpperCase).count();
    }

    private static int countLowercaseLetters(String password) {
        return (int) password.chars().filter(Character::isLowerCase).count();
    }

    private static int countNumbers(String password) {
        return (int) password.chars().filter(Character::isDigit).count();
    }

    private static int countSymbols(String password) {
        String symbols = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

        return (int) password.chars().filter(ch -> symbols.indexOf(ch) != -1).count();
    }

    private static int countMiddleNumbersOrSymbols(String password) {
        if (password.length() <= 2) {
            return 0;
        }
        return countNumbers(password.substring(1, password.length() - 1))
                + countSymbols(password.substring(1, password.length() - 1));
    }

    private static int countRequirements(String password) {
        int requirements = 0;

        if (password.length() >= 8) {
            requirements++;
        }

        int typesCount = 0;
        if (containsUppercaseLetters(password)) {
            typesCount++;
        }
        if (containsLowercaseLetters(password)) {
            typesCount++;
        }
        if (containsNumbers(password)) {
            typesCount++;
        }
        if (containsSymbols(password)) {
            typesCount++;
        }

        if (typesCount >= 3) {
            requirements++;
        }

        return requirements;
    }

    private static int countRepeatCharacters(String password) {
        Set<Character> seenCharacters = new HashSet<>();
        int repeatCount = 0;

        for (char ch : password.toLowerCase().toCharArray()) {
            if (!seenCharacters.add(ch)) {
                repeatCount++;
            }
        }

        return repeatCount;
    }

    private static int countConsecutiveUppercaseLetters(String password) {
        int consecutiveCount = 0;
        char[] charArray = password.toCharArray();

        for (int i = 0; i < charArray.length - 1; i++) {
            if (Character.isUpperCase(charArray[i]) && Character.isUpperCase(charArray[i + 1])) {
                consecutiveCount++;
            }
        }

        return consecutiveCount;
    }

    private static int countConsecutiveLowercaseLetters(String password) {
        int consecutiveCount = 0;
        char[] charArray = password.toCharArray();

        for (int i = 0; i < charArray.length - 1; i++) {
            if (Character.isLowerCase(charArray[i]) && Character.isLowerCase(charArray[i + 1])) {
                consecutiveCount++;
            }
        }

        return consecutiveCount;
    }

    private static int countConsecutiveNumbers(String password) {
        int consecutiveCount = 0;
        char[] charArray = password.toCharArray();

        for (int i = 0; i < charArray.length - 1; i++) {
            if (Character.isDigit(charArray[i]) && Character.isDigit(charArray[i + 1])) {
                consecutiveCount++;
            }
        }

        return consecutiveCount;
    }

    private static int countSequentialLetters(String password) {
        int sequentialCount = 0;
        char[] charArray = password.toLowerCase().toCharArray();

        for (int i = 0; i < charArray.length - 2; i++) {
            if (charArray[i] + 1 == charArray[i + 1] && charArray[i] + 2 == charArray[i + 2] &&
                    Character.isLetter(charArray[i]) && Character.isLetter(charArray[i + 1]) && Character.isLetter(charArray[i + 2])) {
                sequentialCount++;
            }
        }

        return sequentialCount;
    }

    private static int countSequentialNumbers(String password) {
        int sequentialCount = 0;
        char[] charArray = password.toCharArray();

        for (int i = 0; i < charArray.length - 2; i++) {
            if (charArray[i] + 1 == charArray[i + 1] && charArray[i] + 2 == charArray[i + 2] &&
                    Character.isDigit(charArray[i]) && Character.isDigit(charArray[i + 1]) && Character.isDigit(charArray[i + 2])) {
                sequentialCount++;
            }
        }

        return sequentialCount;
    }

    private static int countSequentialSymbols(String password) {
        int sequentialCount = 0;
        char[] charArray = password.toCharArray();

        for (int i = 0; i < charArray.length - 2; i++) {
            if ((Character.isLetter(charArray[i]) && Character.isLetter(charArray[i + 1]) && Character.isLetter(charArray[i + 2]) &&
                    (charArray[i] + 1 == charArray[i + 1]) && (charArray[i + 1] + 1 == charArray[i + 2])) ||
                    (Character.isDigit(charArray[i]) && Character.isDigit(charArray[i + 1]) && Character.isDigit(charArray[i + 2]) &&
                            (charArray[i] + 1 == charArray[i + 1]) && (charArray[i + 1] + 1 == charArray[i + 2])) ||
                    (!Character.isLetterOrDigit(charArray[i]) && !Character.isLetterOrDigit(charArray[i + 1]) && !Character.isLetterOrDigit(charArray[i + 2]) &&
                            charArray[i] == charArray[i + 1] && charArray[i + 1] == charArray[i + 2])) {
                sequentialCount++;
            }
        }

        return sequentialCount;
    }

    private static boolean containsUppercaseLetters(String password) {
        return password.chars().anyMatch(Character::isUpperCase);
    }

    private static boolean containsLowercaseLetters(String password) {
        return password.chars().anyMatch(Character::isLowerCase);
    }

    private static boolean containsNumbers(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }

    private static boolean containsSymbols(String password) {
        return password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
    }
}
