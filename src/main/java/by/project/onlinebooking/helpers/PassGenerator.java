package by.project.onlinebooking.helpers;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.Arrays;
import java.util.List;


public final class PassGenerator {

    private static final int LENGTH = 8;
    private static final PasswordGenerator GENERATOR = new PasswordGenerator();
    private static final CharacterRule RULE_1 = new CharacterRule(EnglishCharacterData.UpperCase, 1);
    private static final CharacterRule RULE_2 = new CharacterRule(EnglishCharacterData.LowerCase, 1);
    private static final CharacterRule RULE_3 = new CharacterRule(EnglishCharacterData.Digit, 1);
    private static final CharacterRule RULE_4 = new CharacterRule(EnglishCharacterData.Special, 1);

    private PassGenerator(){}

    public static String generateRandomPassword() {
        List<CharacterRule> rules = Arrays.asList(RULE_1, RULE_2, RULE_3, RULE_4);
        return GENERATOR.generatePassword(LENGTH, rules);
    }
}
