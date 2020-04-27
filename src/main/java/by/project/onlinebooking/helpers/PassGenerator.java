package by.project.onlinebooking.helpers;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.Arrays;
import java.util.List;

public class PassGenerator {

    private static final PasswordGenerator generator = new PasswordGenerator();
    private static final CharacterRule rule1 = new CharacterRule( EnglishCharacterData.UpperCase, 1 );
    private static final CharacterRule rule2 = new CharacterRule( EnglishCharacterData.LowerCase, 1 );
    private static final CharacterRule rule3 = new CharacterRule( EnglishCharacterData.Digit, 1 );
    private static final CharacterRule rule4 = new CharacterRule( EnglishCharacterData.Special, 1 );

    public static String generateRandomPassword() {
        List<CharacterRule> rules = Arrays.asList( rule1, rule2, rule3, rule4 );
        return generator.generatePassword( 8, rules );
    }
}
