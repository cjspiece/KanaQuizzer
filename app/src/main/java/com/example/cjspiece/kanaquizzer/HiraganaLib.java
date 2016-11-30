package com.example.cjspiece.kanaquizzer;

// This class is used to generate a list of hiragana and return a random one to the requesting
// activity

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;

public class HiraganaLib {
    private String[] alphabet;
    private String[] romanization;
    private String currentKana;
    private String currentAnswer;
    private Random rand = new Random();

    // Default class constructor that populate the arrays with values
    HiraganaLib() {
        alphabet = new String[]{
                "あ", "い", "え", "お", "う", "か", "き", "け", "こ", "く", "が", "ぎ", "げ", "ご", "ぐ",
                "さ","し", "せ", "そ", "す", "ざ", "じ", "ぜ", "ぞ", "ず", "た", "ち", "て", "と", "つ",
                "だ", "ぢ", "で","ど", "づ", "な", "に", "ね", "の", "ぬ", "は", "ひ", "へ", "ほ", "ふ",
                "ば", "び", "べ", "ぼ", "ぶ","ぱ", "ぴ", "ぺ", "ぽ", "ぷ", "ま", "み", "め", "も", "む",
                "や", "よ", "ゆ", "ら", "り", "れ", "ろ","る", "わ", "を", "ん", "きゃ", "きょ", "きゅ",
                "ぎゃ", "ぎょ", "ぎゅ", "しゃ", "しょ", "しゅ", "じゃ", "じょ", "じゅ", "ちゃ", "ちょ", "ちゅ",
                "にゃ", "にょ", "にゅ", "ひゃ", "ひょ", "ひゅ", "びゃ", "びょ", "びゅ", "ぴゃ","ぴょ", "ぴゅ",
                "みゃ", "みょ", "みゅ", "りゃ", "りょ", "りゅ"
        };

        romanization = new String[] {
                "a", "i", "e", "o", "u", "ka", "ki", "ke", "ko", "ku", "ga", "gi", "ge", "go", "gu",
                "sa","shi", "se", "so", "su", "za", "ji", "ze", "zo", "zu", "ta", "chi", "te", "to", "tsu",
                "da", "ji", "de", "do", "zu", "na", "ni", "ne", "no", "nu", "ha", "hi", "he", "ho", "fu",
                "ba", "bi", "be", "bo", "bu", "pa", "pi", "pe", "po", "pu", "ma", "mi", "me", "mo", "mu",
                "ya", "yo", "yu", "ra", "ri", "re", "ro", "ru", "wa", "wo", "n", "kya", "kyo", "kyu",
                "gya", "gyo", "gyu", "sha", "sho", "shu", "ja", "jo", "ju", "cha", "cho", "chu",
                "nya", "nyo", "nyu", "hya", "hyo", "hyu", "bya", "byo", "byu", "pya", "pyo", "pyu",
                "mya", "myo", "myu", "rya", "ryo", "ryu"
        };
    }
    /** Psuedorandomly  selects a new kana to quiz the user against
     * @return currentKana String value
     */
    public String getNewKana() {
        int n = rand.nextInt(alphabet.length);
        currentKana = alphabet[n];
        currentAnswer = romanization[n];
        return currentKana;
    }

    /**Checks the user input against the correct answer, if it is correct returns true, otherwise false
     * @param input for user input
     * @return boolean value
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean checkInput(String input) {
        return Objects.equals(input, currentAnswer);
    }

    /**Checks the user input against the correct answer, if it is correct returns true, otherwise false
     * @return boolean value
     */
    public String getCurrentAnswer() {
        return currentAnswer;
    }

    /**Getter for returning the answer for a specific index
     * @param  n
     * @return String value for the romanized version of the kana
     */
    public String getAnswerAt(int n) {
        return romanization[n];
    }
}
