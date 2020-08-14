package com.elegion.courserafirstcourseprogrammingtest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class CharacterCreator extends Observable  implements Serializable{
    private String TAG = "DBG | Character Creator";
    public enum Specialization {
        WARRIOR, ARCHER, MAGE
    }

    public enum Race {
        HUMAN, ELF, ORC, DWARF
    }

    public enum Attribute {
        STRENGTH, AGILITY, INTELLECT, STAMINA, LUCK
    }

    public enum Perk {
        BERSERK, CALM, LIGHTWEIGHT, HEAVYARMORED, OBSERVANT, MEDITATIONS
    }



    private String mName;
    private Specialization mSpecialization;
    private Race mRace;
    private int mAvailablePoints;

    private Map<String, Integer> mAttributesMap = new HashMap<>();
    private Map<String, Boolean> mPerksMap = new HashMap<>();


    public CharacterCreator() {
        mRace = Race.HUMAN;
        mSpecialization = Specialization.WARRIOR;
        mAvailablePoints = 5;
        mAttributesMap.put(Attribute.STRENGTH.name(), 5);
        mAttributesMap.put(Attribute.AGILITY.name(), 5);
        mAttributesMap.put(Attribute.INTELLECT.name(), 5);
        mAttributesMap.put(Attribute.STAMINA.name(), 5);
        mAttributesMap.put(Attribute.LUCK.name(), 5);
    }


    public String[] getSpecializations() {
        String [] arraySpecialozation = new String[Specialization.values().length];
        Specialization [] allSpecialization = Specialization.values();
        String upperLowerCase = "";

        for (int i = 0; i < arraySpecialozation.length;i++){
            upperLowerCase = upperLower(allSpecialization[i].toString());
            arraySpecialozation[i] = upperLowerCase;
        }

        return arraySpecialozation;

    }

    public void setSpecialization(int position) {
        Specialization [] specializations = Specialization.values();

        if (position < 0){
            mSpecialization = specializations[0];
        }
        else if (position > specializations.length){
            mSpecialization = specializations[specializations.length - 1];
        }
        else{
            mSpecialization = specializations[position];
        }

    }

    public String[] getRaces() {
        String [] arrayRaces = new String[Race.values().length];
        Race [] allraces = Race.values();
        String upperLowerCase = "";

        for (int i = 0; i < allraces.length; i++){
            upperLowerCase = upperLower(allraces[i].toString());
            arrayRaces[i] = upperLowerCase;
        }


        return arrayRaces;
    }

    public void setRace(int position) {
        Race [] races = Race.values();

        if (position < 0){
            mRace = races[0];
        }
        else if (position > races.length){
            mRace = races[races.length - 1];
        }
        else {
            mRace = races[position];
        }

    }

    public String[] getAttributes() {
        String [] arrayAttributes = new String[Attribute.values().length];
        Attribute [] attributes = Attribute.values();
        String upperLowerCase = "";

        for (int i = 0; i < attributes.length; i++){
            upperLowerCase = upperLower(attributes[i].toString());
            arrayAttributes[i] = upperLowerCase;
        }

        return arrayAttributes;

    }

    public String[] getPerks() {
        String [] arrayPerks = new String [Perk.values().length];
        Perk [] perks = Perk.values();
        String upperLowerCase = "";

        for (int i = 0; i < perks.length;i++){
            upperLowerCase = upperLower(perks[i].toString());
            arrayPerks[i] = upperLowerCase;
        }
        return arrayPerks;

    }
    public void updateAttributeValue(int position, int updateTo) {
            Attribute [] attributes = Attribute.values();
           Attribute attr = attributes[position];

            int valueAttr = (mAttributesMap.get(attr.name()));

            if (updateTo > 0 && mAvailablePoints > 0 ){
                mAttributesMap.put(attr.name(),++valueAttr);
                --mAvailablePoints;
            }
            else if (updateTo < 0 && valueAttr  > 1){
                mAttributesMap.put(attr.name(),--valueAttr);
                ++mAvailablePoints;
            }

            setChanged();
            notifyObservers();
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAvailablePoints() {
        return String.valueOf(mAvailablePoints);
    }

    public Map<String, Integer> getAttributesMap() {
        return mAttributesMap;
    }

    public void checkPerk(String text, boolean isChecked) {
        mPerksMap.put(text, isChecked);
    }

    public Character create() {
        Character character = new Character();
        character.setName(mName);
        character.setRace(mRace);
        character.setSpecialization(mSpecialization);
        character.setAttributes(mAttributesMap);
        character.setPerks(mPerksMap);
        character.calculateParameters();
        return character;
    }

    public Specialization getSpecialization() {
        return mSpecialization;
    }

    public Race getRace() {
        return mRace;
    }

    public Map<String, Boolean> getPerksMap() {
        return mPerksMap;
    }

    public void setAvailablePoints(int availablePoints) {
        mAvailablePoints = availablePoints;
    }

    public int getRacePosition() {
        return mRace.ordinal();
    }

    public int getSpecializationPosition() {
        return mSpecialization.ordinal();
    }

    private String upperLower (String word){ ;
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }


}
