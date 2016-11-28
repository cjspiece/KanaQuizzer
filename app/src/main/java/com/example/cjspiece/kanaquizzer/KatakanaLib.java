package com.example.cjspiece.kanaquizzer;

// This class is used to generate a list of katakana and return a random one to the requesting
// activity

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;
import java.util.Random;

public class KatakanaLib {

    private String[] alphabet;
    private String[] romanization;
    private String currentKana;
    private String currentAnswer;
    private int currentIndex;
    private Random rand = new Random();

    KatakanaLib() {
        alphabet = new String[]{
                "ア", "イ", "エ", "オ", "ウ",
                "カ", "キ", "ケ", "コ", "ク",
                "ガ", "ギ", "ゲ", "ゴ", "グ",
                "サ", "シ", "セ", "ソ", "ス",
                "ザ", "ジ", "ゼ", "ゾ", "ズ",
                "タ", "チ", "テ", "ト", "ツ",
                "ダ", "ヂ", "デ", "ド", "ヅ",
                "ナ", "ニ", "ネ", "ノ", "ヌ",
                "ハ", "ヒ", " ヘ", "ホ", "フ",
                "バ", "ビ", "ベ", "ボ", "ブ",
                "パ", "ピ", "ペ", "ポ", "プ",
                "マ", "ミ", "メ", "モ", "ム",
                "ヤ", "ヨ", "ユ",
                "ラ", "リ", "レ", "ロ", "ル",
                "ワ", "ヲ", "ン",
                "キャ", "キョ", "キュ",
                "ギャ", "ギョ", "ギュ",
                "シャ", "ショ", "シュ",
                "ジャ", "ジョ", "ジュ",
                "チャ", "チョ", "チュ",
                "ニャ", "ニョ", "ニュ",
                "ヒャ", "ヒョ", "ヒュ",
                "ビャ", "ビョ", "ビュ",
                "ピャ", "ピョ", "ピュ",
                "ミャ", "ミョ", "ミュ",
                "リャ", "リョ", "リュ"
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
        currentIndex = n;
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

    public String getCurrentAnswer() {
        return currentAnswer;
    }

    public String getAnswerAt(int n) {
        return romanization[n];
    }
}
