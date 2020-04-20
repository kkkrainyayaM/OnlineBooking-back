package by.project.onlinebooking.helpers;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.Arrays;
import java.util.List;

public class RandomPasswordGenerator {

    public static String generateRandomPassword() {
        List<CharacterRule> rules = Arrays.asList( new CharacterRule( EnglishCharacterData.UpperCase, 1 ),
                new CharacterRule( EnglishCharacterData.LowerCase, 1 ),
                new CharacterRule( EnglishCharacterData.Digit, 1 ),
                new CharacterRule( EnglishCharacterData.Special, 1 ) );

        PasswordGenerator generator = new PasswordGenerator();
        return generator.generatePassword( 8, rules );
    }
}
