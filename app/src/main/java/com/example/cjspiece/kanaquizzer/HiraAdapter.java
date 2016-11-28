package com.example.cjspiece.kanaquizzer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class HiraAdapter extends BaseAdapter {
    private HiraganaLib myLib = new HiraganaLib();
    private Context mContext;
    private String[] alphabet = new String[]{
        "あ", "い", "え", "お", "う", "か", "き", "け", "こ", "く", "が", "ぎ", "げ", "ご", "ぐ",
                "さ","し", "せ", "そ", "す", "ざ", "じ", "ぜ", "ぞ", "ず", "た", "ち", "て", "と", "つ",
                "だ", "ぢ", "で","ど", "づ", "な", "に", "ね", "の", "ぬ", "は", "ひ", "へ", "ほ", "ふ",
                "ば", "び", "べ", "ぼ", "ぶ","ぱ", "ぴ", "ぺ", "ぽ", "ぷ", "ま", "み", "め", "も", "む",
                "や", "よ", "ゆ", "ら", "り", "れ", "ろ","る", "わ", "を", "ん", "きゃ", "きょ", "きゅ",
                "ぎゃ", "ぎょ", "ぎゅ", "しゃ", "しょ", "しゅ", "じゃ", "じょ", "じゅ", "ちゃ", "ちょ", "ちゅ",
                "にゃ", "にょ", "にゅ", "ひゃ", "ひょ", "ひゅ", "びゃ", "びょ", "びゅ", "ぴゃ","ぴょ", "ぴゅ",
                "みゃ", "みょ", "みゅ", "りゃ", "りょ", "りゅ"
    };

    private String[] romanization = new String[] {
        "a", "i", "e", "o", "u", "ka", "ki", "ke", "ko", "ku", "ga", "gi", "ge", "go", "gu",
                "sa","shi", "se", "so", "su", "za", "ji", "ze", "zo", "zu", "ta", "chi", "te", "to", "tsu",
                "da", "ji", "de", "do", "zu", "na", "ni", "ne", "no", "nu", "ha", "hi", "he", "ho", "fu",
                "ba", "bi", "be", "bo", "bu", "pa", "pi", "pe", "po", "pu", "ma", "mi", "me", "mo", "mu",
                "ya", "yo", "yu", "ra", "ri", "re", "ro", "ru", "wa", "wo", "n", "kya", "kyo", "kyu",
                "gya", "gyo", "gyu", "sha", "sho", "shu", "ja", "jo", "ju", "cha", "cho", "chu",
                "nya", "nyo", "nyu", "hya", "hyo", "hyu", "bya", "byo", "byu", "pya", "pyo", "pyu",
                "mya", "myo", "myu", "rya", "ryo", "ryu"
    };

    // Constructor that accepts a context
    public HiraAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return alphabet.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // Creates a new TextView for each value in the array and places them into a GridView grid slot
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView myText;
        if (convertView == null) {
            myText = new TextView(mContext);
            myText.setLayoutParams(new GridView.LayoutParams(85, 85));
        }
        else {
            myText = (TextView) convertView;
        }

        myText.setText(alphabet[position]);
        return myText;
    }
}
