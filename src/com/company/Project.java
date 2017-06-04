package com.company;
import java.util.ArrayList;

/**
 * Created by Steffen on 28.05.2017.
 */
public class Project {

    private ArrayList<NextStep> nList;

    public Project(ArrayList<NextStep> nList) {
        this.nList = nList;
    }

    public void addItem(NextStep nextStep){
        if(this.nList != null){
            this.nList.add(nextStep);
        }
    }

}

