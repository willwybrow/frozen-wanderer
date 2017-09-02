package uk.wangbot.fw.language;

import java.util.*;

/**
 * Created by wjw on 27/08/2017.
 */
public class Language {
    private HashMap<Phoneme, GraphemeMap> phonemeGraphemeMap;
    public Language() {
        this.phonemeGraphemeMap = new HashMap<>();
    }

    public void addGraphemeMap(Phoneme phoneme, GraphemeMap graphemeMap) {
        this.phonemeGraphemeMap.put(phoneme, graphemeMap);
    }

    public static Language createRandomLanguage() {
        Random random = new Random();
        // choose a random number of consonants and vowels
        List<Phoneme> consonants = Phoneme.getConsonants();
        List<Phoneme> vowels = Phoneme.getFullVowels();

        //choose between 80 and 100 percent of the full range
        long numberOfConsonants = Math.round((float) consonants.size() * 0.2 * random.nextFloat() + 0.8 * (float)consonants.size());
        long numberOfVowels = Math.round((float) vowels.size() * 0.2 * random.nextFloat() + 0.8 * (float)vowels.size());

        System.out.println(String.format("Creating a language with %d consonants and %d vowels", numberOfConsonants, numberOfVowels));

        Language randomLanguage = new Language();
        Collections.shuffle(consonants);
        Collections.shuffle(vowels);

        for (int i = 0; i < numberOfConsonants; i++) {
            Phoneme phoneme = consonants.get(i);
            randomLanguage.addGraphemeMap(phoneme, GraphemeMap.roughlyEnglish(phoneme));
        }
        for (int i = 0; i < numberOfVowels; i++) {
            Phoneme phoneme = vowels.get(i);
            randomLanguage.addGraphemeMap(phoneme, GraphemeMap.roughlyEnglish(phoneme));
        }

        return randomLanguage;
    }

    public String generateWord() {
        Random random = new Random();
        int vowelStep = random.nextInt(1);
        StringBuilder word = new StringBuilder();
        Phoneme randomPhoneme;
        GraphemeMap randomGrapheme;

        ArrayList<Phoneme> availableVowelPhonemes = new ArrayList<Phoneme>();
        ArrayList<Phoneme> availableConsonantPhonemes = new ArrayList<Phoneme>();

        Collections.sort(availableVowelPhonemes);
        Collections.sort(availableConsonantPhonemes);

        for (Map.Entry<Phoneme, GraphemeMap> entry : this.phonemeGraphemeMap.entrySet()) {
            Phoneme phoneme = entry.getKey();
            if (phoneme.getPhonemeClass() == PhonemeClass.CONSONANT) {
                availableConsonantPhonemes.add(phoneme);
            } else if (phoneme.getPhonemeClass() == PhonemeClass.FULL_VOWEL) {
                availableVowelPhonemes.add(phoneme);
            }
        }

        int syllables = random.nextInt(4) + 2;
        int wordSyllableCount = 0;

        Vector<Phoneme> phoneticWord = new Vector<>();

        while (wordSyllableCount < syllables) {
            if (vowelStep % 2 == 0) {
                // random vowel
                randomPhoneme = availableVowelPhonemes.get(random.nextInt(availableVowelPhonemes.size()));
                phoneticWord.add(randomPhoneme);
                System.out.println(String.format("Adding vowel phoneme %s", randomPhoneme.getIPA()));
                System.out.println(String.format("And completing syllable %d", wordSyllableCount));
                wordSyllableCount += 1;
            } else {
                randomPhoneme = availableConsonantPhonemes.get(random.nextInt(availableConsonantPhonemes.size()));
                phoneticWord.add(randomPhoneme);

                System.out.println(String.format("Adding consonant phoneme %s", randomPhoneme.getIPA()));
                System.out.println(String.format("And starting syllable %d", wordSyllableCount));
            }
            vowelStep += 1;
        }
        for (int i = 0; i < phoneticWord.size(); i++) {
            if (i == 0) {
                word.append(this.phonemeGraphemeMap.get(phoneticWord.get(i)).getValidInitials()[random.nextInt(this.phonemeGraphemeMap.get(phoneticWord.get(i)).getValidInitials().length)]);
            } else if (i == phoneticWord.size() - 1) {
                word.append(this.phonemeGraphemeMap.get(phoneticWord.get(i)).getValidFinals()[random.nextInt(this.phonemeGraphemeMap.get(phoneticWord.get(i)).getValidFinals().length)]);
            } else {
                word.append(this.phonemeGraphemeMap.get(phoneticWord.get(i)).getValidMiddles()[random.nextInt(this.phonemeGraphemeMap.get(phoneticWord.get(i)).getValidMiddles().length)]);
            }
        }
        return word.toString();
    }
}
