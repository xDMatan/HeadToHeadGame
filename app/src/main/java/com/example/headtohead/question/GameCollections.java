package com.example.headtohead.question;

import java.util.ArrayList;
import java.util.HashMap;

public class GameCollections {
    private ArrayList<ClassicQuestion> classisCollection;
    private ArrayList<TFquestion> tFCollection;

    public GameCollections(){
        classisCollection = new ArrayList<ClassicQuestion>();
        tFCollection = new ArrayList<TFquestion>();


        HashMap<String, Boolean> ClassQ1 = new HashMap<String,Boolean>();
        ClassQ1.put("אוקטובר",true);
        ClassQ1.put("נובמבר",true);
        ClassQ1.put("דצמבר",true);
        ClassQ1.put("ינואר",true);
        ClassQ1.put("פברואר",true);
        ClassicQuestion q1 = new  ClassicQuestion("מהם חודשי השנה","General",ClassQ1,false);
        classisCollection.add(q1);


        TFquestion TFQ1 = new TFquestion("ניתן לראות את נוגה מכדא" ,"Space",true,false);
        tFCollection.add(TFQ1);
        // שאלות בנושא חלל
        TFquestion TFQ2 = new TFquestion("הירח הוא הלוויין הטבעי היחיד של כדור הארץ", "Space", true, false);
        tFCollection.add(TFQ2);
        TFquestion TFQ3 = new TFquestion("שבתאי הוא הכוכב הקרוב ביותר לשמש", "Space", false, false);
        tFCollection.add(TFQ3);
        TFquestion TFQ4 = new TFquestion("ישנם תשעה כוכבי לכת במערכת השמש", "Space", false, false);
        tFCollection.add(TFQ4);
        TFquestion TFQ5 = new TFquestion("היום על מאדים נמשך יותר מ-24 שעות", "Space", true, false);
        tFCollection.add(TFQ5);

// שאלות בנושא מדע
        TFquestion TFQ6 = new TFquestion("מים רותחים ב-100 מעלות צלזיוס בגובה פני הים", "Science", true, false);
        tFCollection.add(TFQ6);
        TFquestion TFQ7 = new TFquestion("אטום מורכב מגרעין שמכיל אלקטרונים", "Science", false, false);
        tFCollection.add(TFQ7);
        TFquestion TFQ8 = new TFquestion("דינוזאורים נכחדו לפני כ-65 מיליון שנה", "Science", true, false);
        tFCollection.add(TFQ8);
        TFquestion TFQ9 = new TFquestion("האור הוא צורה של אנרגיה", "Science", true, false);
        tFCollection.add(TFQ9);
        TFquestion TFQ10 = new TFquestion("שמן כבד יותר ממים ולכן הוא שוקע בהם", "Science", false, false);
        tFCollection.add(TFQ10);

// שאלות בנושא גיאוגרפיה
        TFquestion TFQ11 = new TFquestion("הים התיכון הוא הים הגדול בעולם", "Geography", false, false);
        tFCollection.add(TFQ11);
        TFquestion TFQ12 = new TFquestion("אפריקה היא היבשת הגדולה ביותר", "Geography", false, false);
        tFCollection.add(TFQ12);
        TFquestion TFQ13 = new TFquestion("הר האוורסט הוא ההר הגבוה בעולם", "Geography", true, false);
        tFCollection.add(TFQ13);
        TFquestion TFQ14 = new TFquestion("האמזונס הוא הנהר הארוך בעולם", "Geography", false, false);
        tFCollection.add(TFQ14);
        TFquestion TFQ15 = new TFquestion("הקטבים של כדור הארץ מכוסים בעיקר קרח", "Geography", true, false);
        tFCollection.add(TFQ15);

// שאלות בנושא היסטוריה
        TFquestion TFQ16 = new TFquestion("נפוליאון היה קיסר צרפת", "History", true, false);
        tFCollection.add(TFQ16);
        TFquestion TFQ17 = new TFquestion("מלחמת העולם הראשונה התחילה בשנת 1918", "History", false, false);
        tFCollection.add(TFQ17);
        TFquestion TFQ18 = new TFquestion("המהפכה התעשייתית החלה בבריטניה", "History", true, false);
        tFCollection.add(TFQ18);
        TFquestion TFQ19 = new TFquestion("מדינת ישראל הוקמה בשנת 1947", "History", false, false);
        tFCollection.add(TFQ19);
        TFquestion TFQ20 = new TFquestion("המגנה כרטה נחתמה בשנת 1215", "History", true, false);
        tFCollection.add(TFQ20);

// שאלות כלליות
        TFquestion TFQ21 = new TFquestion("חתולים רואים טוב יותר בלילה מאשר ביום", "General", true, false);
        tFCollection.add(TFQ21);
        TFquestion TFQ22 = new TFquestion("סוכר נמס במים", "General", true, false);
        tFCollection.add(TFQ22);
        TFquestion TFQ23 = new TFquestion("הדגל של בריטניה הוא בצבעים ירוק, לבן ושחור", "General", false, false);
        tFCollection.add(TFQ23);
        TFquestion TFQ24 = new TFquestion("רוחב הפס הוא מדד למהירות האינטרנט", "General", true, false);
        tFCollection.add(TFQ24);
        TFquestion TFQ25 = new TFquestion("ציפורים הן יונקים", "General", false, false);
        tFCollection.add(TFQ25);

    }

    public ArrayList<ClassicQuestion> getClassisCollection() {
        return classisCollection;
    }

    public ArrayList<TFquestion> getTFCollection() {
        return tFCollection;
    }
}

