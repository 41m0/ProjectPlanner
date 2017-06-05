package com.company;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

//to use FX stuff, main must extend Application

public class Main extends Application implements EventHandler<ActionEvent> {

    private Stage window;
    private Button addTask, delTask;
    private TextField t_addTask;
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

        addTask = new Button("Add Task");
        addTask.setOnAction((event) -> addTask());



        //Add task button, not really necessary?

        t_addTask = new TextField();
        // if(t_addTask.getText().isEmpty()) addTask.setDisable(true);
        t_addTask.setPromptText("Define task");
        t_addTask.setOnKeyPressed((event) -> {
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


        //TitledPane tp = new TitledPane("My Titled Pane", new Button("Button"));
        Label projectLabel = new Label("Projects");
        //Boderpane
        BorderPane borderPane = new BorderPane();
        //ToolBar toolbar = new ToolBar();
        HBox topBar = new HBox();
        HBox statusBar = new HBox();
        VBox leftList = new VBox();
        VBox rightList = new VBox();

        topBar.getChildren().addAll(new Label("TopBar"));
        leftList.getChildren().addAll(t_addTask,tasks, delTask);
        rightList.getChildren().addAll(projects);
        statusBar.getChildren().addAll(new Label("StatusBar"));

        borderPane.setTop(topBar);
        borderPane.setCenter(projectLabel);
        borderPane.setBottom(statusBar);
        borderPane.setRight(rightList);
        borderPane.setLeft(leftList);
        borderPane.setPrefSize(500,400);

        //Gridpane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        grid.getColumnConstraints().add(new ColumnConstraints(500));
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

    @Override
    public void handle(ActionEvent event){
        if(event.getSource()==addTask){
            addTask();
        }
    }
    private void addTask(){
        String text = t_addTask.getText();
        if(!tasks.getItems().contains(text)){
            NextStep nextStep = new NextStep(text);
            tasks.getItems().add(nextStep.toString());
            t_addTask.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notice");
            alert.setHeaderText(null);
            alert.setContentText("Task already exists.");

            alert.showAndWait();
        }

    }

    private void delTask(){
        //Allows for multi-select delete
        ObservableList<String> list = tasks.getSelectionModel().getSelectedItems();
        tasks.getItems().removeAll(list);
    }
}
