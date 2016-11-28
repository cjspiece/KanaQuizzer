package com.example.cjspiece.kanaquizzer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class KataAdapter extends BaseAdapter {
    private KatakanaLib myLib = new KatakanaLib();
    private Context mContext;
    private String[] alphabet = new String[]{
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

    public KataAdapter(Context c) {
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
