package com.company;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

//to use FX stuff, main must extend Application

public class Main extends Application {

    private Stage window;
    private Button delTask, delProject;
    private TextField addTask, addProject;
    private ListView<String> tasks = new ListView<>();
    private ListView<String> projects = new ListView<>();

    public static void main(String[] args) {
        //required for starting FX stuff
        launch(args);
    }

    // in here, the FX code is written
    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Working Title");

        /*
        Menu Bar
         */

        MenuBar menuBar = new MenuBar();

        //visible entry on the menu bar
        Menu menuFile = new Menu("File");

        //one Item of an entry
        MenuItem itmLoadProject = new MenuItem("Load Project");

        //Adding an item to an entry
        menuFile.getItems().addAll(itmLoadProject);

        //Adding the entries to the menu bar
        menuBar.getMenus().addAll(menuFile);
        /*
        Tasklist
         */

        addTask = new TextField();
        // if(addTask.getText().isEmpty()) addTask.setDisable(true);
        addTask.setPromptText("Define task");
        addTask.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                addTask(); }
        });

        //Multiselect
        tasks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tasks.setPrefWidth(800);

        tasks.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.DELETE){
                delTask(); }
        });

        delTask = new Button("Delete Task");

        delTask.setOnAction((ActionEvent e) -> delTask());

        /*
        Project list
         */
        addProject = new TextField();
        // if(addTask.getText().isEmpty()) addTask.setDisable(true);
        addProject.setPromptText("Define Project");
        addProject.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                addProject(); }
        });

        //Multiselect
        projects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        projects.setPrefWidth(400);

        projects.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.DELETE){
                delProject(); }
        });

        delProject = new Button("Delete Project");

        delProject.setOnAction((ActionEvent e) -> delProject());




        //TitledPane tp = new TitledPane("My Titled Pane", new Button("Button"));
        //Label projectLabel = new Label("Projects");
        //Boderpane
        BorderPane borderPane = new BorderPane();
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10,10,10,10));
        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(10,10,10,10));
        VBox leftList = new VBox();
        leftList.setPadding(new Insets(20,10,10,10));
        VBox rightList = new VBox();
        rightList.setPadding(new Insets(20,10,10,10));

        topBar.getChildren().addAll(menuBar);  //topbar currently not used
        rightList.getChildren().addAll(addTask,tasks, delTask);
        leftList.getChildren().addAll(addProject, projects, delProject);
        statusBar.getChildren().addAll(new Label("StatusBar"));

        borderPane.setTop(menuBar);
        borderPane.setCenter(rightList);
        borderPane.setBottom(statusBar);
        borderPane.setRight(null);
        borderPane.setLeft(leftList);
        borderPane.setPrefSize(500,400);
        //borderPane.setPadding(new Insets(0,0,10,10));

        //Gridpane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.getColumnConstraints().add(new ColumnConstraints(500));
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        grid.setVgap(8);
        grid.setHgap(10);

        //fill the grid
        //grid.getChildren().addAll(tp);
//        grid.add(projectLabel,0,0);
//        grid.add(addTask,1,0);
        //making Content for the stage
        Scene scene = new Scene(borderPane, 1000, 600);
        window.setScene(scene);

        //displaying the stage
        window.show();



        //The entire window of the program is called the Stage
        //Content inside the window: Scene
    }

    private void addTask(){
        String text = addTask.getText();
        if(!tasks.getItems().contains(text)){
            NextStep nextStep = new NextStep(text);
            tasks.getItems().add(nextStep.toString());
            addTask.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notice");
            alert.setHeaderText(null);
            alert.setContentText("Task already exists.");

            alert.showAndWait();
        }
    }

    private void addProject(){
        String text = addProject.getText();
        if(!projects.getItems().contains(text)){
            NextStep nextStep = new NextStep(text);
            projects.getItems().add(nextStep.toString());
            addProject.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notice");
            alert.setHeaderText(null);
            alert.setContentText("Project already exists.");

            alert.showAndWait();
        }
    }

    private void delTask(){
        //Allows for multi-select delete
        ObservableList<String> list = tasks.getSelectionModel().getSelectedItems();
        tasks.getItems().removeAll(list);
    }
    private void delProject(){
        //Allows for multi-select delete
        ObservableList<String> list = projects.getSelectionModel().getSelectedItems();
        projects.getItems().removeAll(list);
    }
}
