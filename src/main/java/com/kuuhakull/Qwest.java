package com.kuuhakull;

import java.util.ArrayList;
import java.util.HashSet;

public class Qwest {
    private int idQwest;
    private String text;
    private HashSet<String> correctAnswer;
    private ArrayList<String> allAnswer;
    private byte typeQwest;

    Qwest(int id, String title, ArrayList<String> answer, ArrayList<String> distract, byte type){
        idQwest = id;
        text = title;
        correctAnswer = new HashSet<>();
        allAnswer = new ArrayList<>();
        correctAnswer.addAll(answer);
        allAnswer.addAll(distract);
        typeQwest = type;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getAllAnswer() {
        return allAnswer;
    }

    public byte getTypeQwest() {
        return typeQwest;
    }

    public boolean chekAnswer(ArrayList<String> answer) {
        if (correctAnswer.size()!=answer.size()){
            return false;
        }
        for (String item: answer){
            if (!correctAnswer.contains(item)){
                return false;
            }
        }
        return true;
    }
}