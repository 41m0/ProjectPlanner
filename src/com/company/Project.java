package com.company;

import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by Steffen on 28.05.2017.
 */
public class Project {

    private ArrayList<NextStep> nList;
    private String name;

    public Project(String name) {

        this.name = name;
        this.nList = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public ArrayList<NextStep> getnList() {
        return nList;
    }

    public boolean addNextStep(NextStep nextStep) {

        if (this.nList == null) {
            return false;
        } else {
            //calling the private method findContact(String name)
            if (findNextStep(nextStep.getDescription()) >= 0) {
                System.out.println("Next Step already defined");
                return false;
            }
            //if the next step is not yet on the list it is added here to the list
            nList.add(nextStep);
            return true;
        }
    }


//    public boolean updateContact(Contact oldContact, Contact newContact){
//        int foundPosition = findContact(oldContact);
//        if (foundPosition < 0){
//            System.out.println(oldContact.getName() + " was not found.");
//            return false;
//        }
//        this.myContacts.set(foundPosition, newContact);
//        System.out.println(oldContact.getName() + " was replaced with " + newContact.getName());
//        return true;
//    }
//    public boolean removeContact(Contact contact){
//        int foundPosition = findContact(contact);
//        if (foundPosition < 0){
//            System.out.println(contact.getName() + " was not found.");
//            return false;
//        }
//        this.myContacts.remove(foundPosition);
//        System.out.println(contact.getName() + " was deleted.");
//        return true;
//    }

    private void addAll(ObservableList<NextStep> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            this.nList.set(i, tasks.get(i));
        }
    }

    private int findNextStep(NextStep nextStep) {
        return this.nList.indexOf(nextStep);
    }


    private int findNextStep(String description) {
        for (int i = 0; i < this.nList.size(); i++) {
            NextStep nextStep = this.nList.get(i);
            if (nextStep.getDescription().equals(description)) {
                return i;
            }
        }
        return -1;
    }

    public String projectContentToString() {

        String text = "";
        for (NextStep aNList : nList) {
            text += aNList.getDescription() + ' ';
        }
        return text;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

