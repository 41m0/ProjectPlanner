package com.company;

import java.util.ArrayList;

/**
 * Created by Steffen on 18.06.2017.
 */
public class ProjectList {

    private ArrayList<Project> pList;
    private String name;


    public ArrayList<Project> getpList() {
        return pList;
    }

    public void setpList(ArrayList<Project> pList) {
        this.pList = pList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addNewProject(Project project) {
        //calling the private method findProject(String name)
        if (findProject(project.getName()) >= 0) {
            System.out.println("Project already exists.");
            return false;
        }
        //if the project is not yet on the list it is added here to the list
        pList.add(project);
        return true;
    }

    public boolean updateProject(Project oldProject, Project newProject) {
        int foundPosition = findProject(oldProject);
        if (foundPosition < 0) {
            System.out.println(oldProject.getName() + " was not found.");
            return false;
        }
        this.pList.set(foundPosition, newProject);
        System.out.println(oldProject.getName() + " was replaced with " + newProject.getName());
        return true;
    }

    public boolean removeProject(Project project) {
        int foundPosition = findProject(project);
        if (foundPosition < 0) {
            System.out.println(project.getName() + " was not found.");
            return false;
        }
        this.pList.remove(foundPosition);
        System.out.println(project.getName() + " was deleted.");
        return true;
    }

    private int findProject(Project project) {
        if (!(pList == null)) {
            System.out.println("No plist found.");
        } else {
            return this.pList.indexOf(project);
        }
        return -1;
    }

    private int findProject(String projectName) {
        if (!(pList == null)) {
            System.out.println("No plist found.");
        } else {
            for (int i = 0; i < this.pList.size(); i++) {
                Project project = this.pList.get(i);
                if (project.getName().equals(projectName)) {
                    return i;
                }
            }
        }
        return -1;
    }

}
