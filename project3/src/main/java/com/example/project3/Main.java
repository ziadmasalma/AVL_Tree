package com.example.project3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;


public class Main extends Application {

    TawjihiDS sci = new TawjihiDS();
    TawjihiDS lit = new TawjihiDS();
    Tawjihi tawjihi;
    @Override
    public void start(Stage stage){
        stage.setTitle("Tawjihi");
        interfaseStart(stage);
        stage.show();
    }

    public void interfaseStart(Stage stage) {//the first interfase in project
        VBox vStart = new VBox(30);
        vStart.setAlignment(Pos.CENTER);

        Label lbStart = new Label("Welcome to the programme");
        lbStart.setTextFill(Color.GOLD);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35);
        lbStart.setFont(font);

        Button btStart = new Button("Start");
        btStart.setFont(new Font(30));
        btStart.setMinSize(300, 100);
        Font font1 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35);
        btStart.setFont(font1);
        btStart.setTextFill(Color.BLACK);

        btStart.setOnAction(e -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose West Bank.txt");
            File file = fileChooser.showOpenDialog(stage);
            if (file!=null) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String in = scanner.nextLine();
                    String[] sp = in.split(",");
                    tawjihi = new Tawjihi(Long.parseLong(sp[0]), sp[1], Double.parseDouble(sp[2]));
                    if (tawjihi.getBranch().compareTo("Scientific") == 0) {
                        sci.add(tawjihi);
                    } else
                        lit.add(tawjihi);
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            branchInterfase(stage);}
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("error dont choose a file");
                alert.setContentText("please select a file to read it ");
                alert.showAndWait();
            }
        });

        Button btClose = new Button("Close");
        btClose.setFont(new Font(30));
        btClose.setMinSize(300, 100);
        btClose.setTextFill(Color.BLACK);
        Font font2 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35);
        btClose.setFont(font2);
        btClose.setOnAction(e -> {
            stage.close();
        });

        vStart.getChildren().addAll(lbStart, btStart, btClose);
        Scene sceneStart = new Scene(vStart, 1200, 700);
        stage.setScene(sceneStart);
    }

    public void branchInterfase(Stage stage) {
        Font font1 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35);
        Label lbranch = new Label("choose branch");
        lbranch.setFont(font1);
        lbranch.setTextFill(Color.GOLD);

        CheckBox sciBox=new CheckBox("Scientific");
        sciBox.setSelected(false);
        sciBox.setFont(font1);

        CheckBox litBox=new CheckBox("Literary");
        litBox.setSelected(false);
        litBox.setFont(font1);

        HBox hBox=new HBox(sciBox,litBox);
        hBox.setTranslateX(350);
        hBox.setSpacing(40);
        VBox vBox1=new VBox(lbranch,hBox);
        vBox1.setSpacing(100);
        vBox1.setAlignment(Pos.CENTER);
        Button btStart = new Button("Select");
        btStart.setFont(new Font(30));
        btStart.setMinSize(300, 100);
        btStart.setFont(font1);
        btStart.setTextFill(Color.BLACK);
        btStart.setOnAction(e->{
            if(litBox.isSelected()&&sciBox.isSelected())
                operationInterFase(stage,2);
            else if (litBox.isSelected())
                operationInterFase(stage,0);
            else if (sciBox.isSelected())
                operationInterFase(stage,1);
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("error dont choose a branch");
                alert.setContentText("please select a branch ");
                alert.showAndWait();
            }
        });

        Button btClose = new Button("Close");
        btClose.setFont(new Font(30));
        btClose.setMinSize(300, 100);
        btClose.setTextFill(Color.BLACK);
        Font font2 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35);
        btClose.setFont(font2);
        btClose.setOnAction(e -> {
            stage.close();
        });
        VBox vBox=new VBox(vBox1,btStart,btClose);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(100);
        Scene sceneStart = new Scene(vBox, 1200, 700);
        stage.setScene(sceneStart);
    }

    public void operationInterFase(Stage stage,int branch){
        VBox vStart = new VBox(30);
        vStart.setAlignment(Pos.CENTER);

        Label lbStart = new Label("Choose what you want");
        lbStart.setTextFill(Color.GOLD);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35);
        lbStart.setFont(font);
        vStart.getChildren().add(lbStart);

        HBox hStart = new HBox(40);
        hStart.setAlignment(Pos.CENTER);

        Button btDelete = new Button("Delete Students");
        btDelete.setFont(new Font(30));
        btDelete.setMinSize(300, 100);
        Font font1 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 25);
        btDelete.setFont(font1);
        btDelete.setTextFill(Color.BLACK);
        btDelete.setOnAction(e -> {
            deleatInterFace(stage, branch);
        });

        Button btSearch = new Button("Search Students");
        btSearch.setFont(new Font(30));
        btSearch.setMinSize(300, 100);
        btSearch.setTextFill(Color.BLACK);
        Font font2 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 25);
        btSearch.setFont(font2);
        btSearch.setTextFill(Color.BLACK);
        btSearch.setOnAction(e -> {
            searchInterFase(stage,branch );
        });

        hStart.getChildren().addAll(btDelete, btSearch);

        HBox hStart2 = new HBox(40);
        hStart2.setAlignment(Pos.CENTER);

        Button btPrint = new Button("print");
        btPrint.setFont(new Font(30));
        btPrint.setMinSize(300, 100);
        btPrint.setTextFill(Color.BLACK);
        Font font4 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20);
        btPrint.setFont(font4);
        btPrint.setTextFill(Color.BLACK);
        btPrint.setOnAction(e -> {
            printInterface(stage, branch);
        });
        Button btUpdate = new Button("Update Students");
        btUpdate.setFont(new Font(30));
        btUpdate.setMinSize(300, 100);
        btUpdate.setFont(font1);
        btUpdate.setTextFill(Color.BLACK);
        btUpdate.setOnAction(e -> {
            updateInterface(stage, branch);
        });

        Button bthight = new Button("print height");
        bthight.setFont(new Font(30));
        bthight.setMinSize(300, 100);
        bthight.setTextFill(Color.BLACK);
        bthight.setFont(font4);
        bthight.setTextFill(Color.BLACK);
        bthight.setOnAction(e -> {
            heightInterFase(stage, branch);
        });
        hStart2.getChildren().addAll(btUpdate,btPrint);

        HBox hStart4 = new HBox(500);
        hStart4.setAlignment(Pos.CENTER);

        Button btBack = new Button("Back");
        btBack.setFont(new Font(30));
        btBack.setMinSize(300, 100);
        btBack.setTextFill(Color.BLACK);
        Font font7 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35);
        btBack.setFont(font7);
        btBack.setTextFill(Color.BLACK);

        btBack.setOnAction(e -> {
            branchInterfase(stage);
        });

        Button btAdd = new Button("Add Students");
        btAdd.setFont(new Font(30));
        btAdd.setMinSize(300, 100);
        btAdd.setTextFill(Color.BLACK);
        Font font8 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35);
        btAdd.setFont(font8);
        btAdd.setTextFill(Color.BLACK);
        btAdd.setOnAction(e -> {
            addInterFace(stage, branch);
        });

        hStart4.getChildren().addAll(btBack, btAdd);

        vStart.getChildren().addAll(hStart, hStart2,bthight, hStart4);
        Scene sceneStart = new Scene(vStart, 1200, 700);
        stage.setScene(sceneStart);
    }

    public void deleatInterFace(Stage stage, int branch) {//interfase for delete student from list

        HBox hStart = new HBox(30);
        hStart.setAlignment(Pos.CENTER);

        Label l1 = new Label("Seat Number:");
        l1.setFont(new Font(14));
        l1.setTextFill(Color.GOLD);

        TextField t1 = new TextField();

        hStart.getChildren().addAll(l1, t1);

        Label lb = new Label("Branch:");
        lb.setFont(new Font(14));
        lb.setTextFill(Color.GOLD);
        ComboBox<String> branchs=new ComboBox<>();
        branchs.getItems().addAll("Scientific","Literary");
        HBox hBranch = new HBox(30);
        hBranch.setAlignment(Pos.CENTER);
        hBranch.getChildren().addAll(lb,branchs);
        if(branch==1){
            branchs.setDisable(true);
            branchs.setValue("Scientific");
        }
        else if(branch==0){
            branchs.setDisable(true);
            branchs.setValue("Literary");
        }

        HBox hStart1 = new HBox(30);
        hStart1.setAlignment(Pos.CENTER);

        Label l2 = new Label("Average:");
        l2.setFont(new Font(20));
        l2.setTextFill(Color.GOLD);

        TextField t2 = new TextField();
        t2.setDisable(true);

        hStart1.getChildren().addAll(l2, t2);

        HBox hStart2 = new HBox(30);
        hStart2.setAlignment(Pos.CENTER);

        Button btBack = new Button("Back");
        btBack.setFont(new Font(30));
        btBack.setMinSize(100, 50);
        btBack.setTextFill(Color.BLACK);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btBack.setFont(font);
        btBack.setTextFill(Color.BLACK);

        btBack.setOnAction(e -> {
            operationInterFase(stage, branch);
        });

        Button btDelete = new Button("Delete");
        btDelete.setFont(new Font(30));
        btDelete.setMinSize(100, 50);
        Font font1 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btDelete.setFont(font1);
        btDelete.setTextFill(Color.BLACK);
        btDelete.setDisable(true);
        btDelete.setOnAction(e -> {
            if(branchs.getValue()=="Literary")
                lit.delete(Integer.parseInt(t1.getText()));
            else if (branchs.getValue()=="Scientific")
                sci.delete(Integer.parseInt(t1.getText()));
            btDelete.setDisable(true);
            t1.setText("done");
        });

        Button btSearch = new Button("Search");
        btSearch.setFont(new Font(30));
        btSearch.setMinSize(100, 50);
        Font font2 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btSearch.setFont(font2);
        btSearch.setTextFill(Color.BLACK);
        btSearch.setOnAction(e->{
            Tawjihi search = null;
            if(branchs.getValue()=="Literary")
                search=lit.searchBySeatNum(Integer.parseInt(t1.getText()));
            else if (branchs.getValue()=="Scientific")
                search=sci.searchBySeatNum(Integer.parseInt(t1.getText()));
            else
                t1.setText("enter the branche");
            if(search==null){
                t1.setText("not found");
            }
            else {
                t2.setText(String.valueOf(search.getAverage()));
                btDelete.setDisable(false);
            }
        });

        hStart2.getChildren().addAll(btBack, btDelete, btSearch);

        VBox vStart = new VBox(30);
        vStart.setAlignment(Pos.CENTER);
        vStart.getChildren().addAll(hStart, hBranch,hStart1, hStart2);

        Scene scene = new Scene(vStart, 1200, 700);
        stage.setScene(scene);


    }

    public void addInterFace(Stage stage, int branch) {//interfase for add a student node to list
        HBox hStart = new HBox(30);
        hStart.setAlignment(Pos.CENTER);

        Label l1 = new Label("Seat Number:");
        l1.setFont(new Font(14));
        l1.setTextFill(Color.GOLD);

        TextField t1 = new TextField();

        hStart.getChildren().addAll(l1, t1);
        Label lb = new Label("Branch:");
        lb.setFont(new Font(14));
        lb.setTextFill(Color.GOLD);
        ComboBox<String> branchs=new ComboBox<>();
        branchs.getItems().addAll("Scientific","Literary");
        HBox hBranch = new HBox(30);
        hBranch.setAlignment(Pos.CENTER);
        hBranch.getChildren().addAll(lb,branchs);
        if(branch==1){
            branchs.setDisable(true);
            branchs.setValue("Scientific");
        }
        else if(branch==0){
            branchs.setDisable(true);
            branchs.setValue("Literary");
        }

        HBox hStart1 = new HBox(30);
        hStart1.setAlignment(Pos.CENTER);

        Label l2 = new Label("Average:");
        l2.setFont(new Font(20));
        l2.setTextFill(Color.GOLD);

        TextField t2 = new TextField();
        t2.setDisable(true);
        hStart1.getChildren().addAll(l2, t2);

        HBox hStart2 = new HBox(30);
        hStart2.setAlignment(Pos.CENTER);

        Button btBack = new Button("Back");
        btBack.setFont(new Font(30));
        btBack.setMinSize(100, 50);
        btBack.setTextFill(Color.BLACK);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btBack.setFont(font);
        btBack.setTextFill(Color.BLACK);

        btBack.setOnAction(e -> {
            operationInterFase(stage,branch);
        });

        Button btAdd = new Button("Add");
        btAdd.setFont(new Font(30));
        btAdd.setMinSize(100, 50);
        Font font1 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btAdd.setFont(font1);
        btAdd.setTextFill(Color.BLACK);
        btAdd.setDisable(true);
        btAdd.setOnAction(e -> {
            if (Double.parseDouble(t2.getText())>100||Double.parseDouble(t2.getText())<0){
                t2.setText("error enter a coorect avg");
            }else{
            tawjihi = new Tawjihi(Long.parseLong(t1.getText()), branchs.getValue(), Double.parseDouble(t2.getText()));
            if(branchs.getValue()=="Literary")
                lit.add(tawjihi);
            else
                sci.add(tawjihi);
            btAdd.setDisable(true);
            t2.setDisable(true);
            t2.setText("done");}
        });

        Button btsearch = new Button("Search");
        btsearch.setFont(new Font(30));
        btsearch.setMinSize(100, 50);
        Font font2 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btsearch.setFont(font2);
        btsearch.setTextFill(Color.BLACK);
        btsearch.setOnAction(e->{
            Tawjihi search = null;
            if(branchs.getValue()=="Literary")
                search=lit.searchBySeatNum(Integer.parseInt(t1.getText()));
            else if (branchs.getValue()=="Scientific")
                search=sci.searchBySeatNum(Integer.parseInt(t1.getText()));
            else
                t1.setText("enter the branche");
            if(search==null){
                t2.setDisable(false);
                btAdd.setDisable(false);
            }
            else {
                t1.setText("Student number exists");
            }
        });

        hStart2.getChildren().addAll(btBack, btAdd, btsearch);

        VBox vStart = new VBox(30);
        vStart.setAlignment(Pos.CENTER);
        vStart.getChildren().addAll(hStart, hBranch,hStart1, hStart2);

        Scene scene = new Scene(vStart, 1200, 700);
        stage.setScene(scene);


    }

    public void searchInterFase(Stage stage, int branch) {//search for a student by his seat number
        HBox hStart = new HBox(30);
        hStart.setAlignment(Pos.CENTER);

        Label l1 = new Label("Seat Number:");
        l1.setFont(new Font(14));
        l1.setTextFill(Color.GOLD);

        TextField t1 = new TextField();

        hStart.getChildren().addAll(l1, t1);
        Label lb = new Label("Branch:");
        lb.setFont(new Font(14));
        lb.setTextFill(Color.GOLD);
        ComboBox<String> branchs=new ComboBox<>();
        branchs.getItems().addAll("Scientific","Literary");
        HBox hBranch = new HBox(30);
        hBranch.setAlignment(Pos.CENTER);
        hBranch.getChildren().addAll(lb,branchs);
        if(branch==1){
            branchs.setDisable(true);
            branchs.setValue("Scientific");
        }
        else if(branch==0){
            branchs.setDisable(true);
            branchs.setValue("Literary");
        }
        HBox hStart1 = new HBox(30);
        hStart1.setAlignment(Pos.CENTER);

        Label l2 = new Label("Average:");
        l2.setFont(new Font(20));
        l2.setTextFill(Color.GOLD);

        TextField t2 = new TextField();
        t2.setDisable(true);

        hStart1.getChildren().addAll(l2, t2);

        HBox hStart2 = new HBox(30);
        hStart2.setAlignment(Pos.CENTER);

        Button btBack = new Button("Back");
        btBack.setFont(new Font(30));
        btBack.setMinSize(100, 50);
        btBack.setTextFill(Color.BLACK);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btBack.setFont(font);
        btBack.setTextFill(Color.BLACK);

        btBack.setOnAction(e -> {
            operationInterFase(stage,branch);
        });

        Button btSearch = new Button("Search");
        btSearch.setFont(new Font(30));
        btSearch.setMinSize(100, 50);
        Font font1 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btSearch.setFont(font1);
        btSearch.setTextFill(Color.BLACK);
        btSearch.setOnAction(e->{
            Tawjihi search = null;
            if(branchs.getValue()=="Literary")
                search=lit.searchBySeatNum(Integer.parseInt(t1.getText()));
            else if (branchs.getValue()=="Scientific")
                search=sci.searchBySeatNum(Integer.parseInt(t1.getText()));
            else
                t1.setText("enter the branche");
            if(search==null){
                t1.setText("not found");
            }
            else
                t2.setText(String.valueOf(search.getAverage()));
        });

        hStart2.getChildren().addAll(btBack, btSearch);

        VBox vStart = new VBox(30);
        vStart.setAlignment(Pos.CENTER);
        vStart.getChildren().addAll(hStart, hBranch,hStart1, hStart2);

        Scene scene = new Scene(vStart, 1200, 700);
        stage.setScene(scene);


    }

    public void printInterface(Stage stage, int branch) {//get the number and per for a specific grade
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        TextArea t1 = new TextArea();
        t1.setEditable(false);
        t1.setMinSize(200, 400);
        pane.add(t1, 0, 0);

        Label lb = new Label("Branch:");
        lb.setFont(new Font(14));
        lb.setTextFill(Color.GOLD);
        ComboBox<String> branchs=new ComboBox<>();
        branchs.getItems().addAll("Scientific","Literary");
        HBox hBranch = new HBox(30);
        hBranch.setAlignment(Pos.CENTER);
        hBranch.getChildren().addAll(lb,branchs);
        if(branch==1){
            branchs.setDisable(true);
            branchs.setValue("Scientific");
        }
        else if(branch==0){
            branchs.setDisable(true);
            branchs.setValue("Literary");
        }

        // Create buttons
        Button button1 = new Button("print list");
        button1.setFont(new Font(30));
        button1.setMinSize(100, 50);
        button1.setTextFill(Color.BLACK);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        button1.setFont(font);
        button1.setTextFill(Color.BLACK);
        button1.setOnAction(e->{
            if(branchs.getValue()=="Literary")
                t1.setText(lit.getTawjihiList());
            else if (branchs.getValue()=="Scientific")
                t1.setText(sci.getTawjihiList());
            else
                t1.setText("enter the branche");
        });
        Button button2 = new Button("print 1st avl");
        button2.setFont(new Font(30));
        button2.setMinSize(100, 50);
        button2.setTextFill(Color.BLACK);
        button2.setFont(font);
        button2.setTextFill(Color.BLACK);
        button2.setOnAction(e->{
            if(branchs.getValue()=="Literary")
                t1.setText(lit.getTawjihiTree_SeatNum());
            else if (branchs.getValue()=="Scientific")
                t1.setText(sci.getTawjihiTree_SeatNum());
            else
                t1.setText("enter the branche");
        });
        Button button3 = new Button("print 2nd avl");
        button3.setFont(new Font(30));
        button3.setMinSize(100, 50);
        button3.setTextFill(Color.BLACK);
        button3.setFont(font);
        button3.setTextFill(Color.BLACK);
        button3.setOnAction(e->{
            if(branchs.getValue()=="Literary")
                t1.setText(lit.getTawjihiTree_Avg());
            else if (branchs.getValue()=="Scientific")
                t1.setText(sci.getTawjihiTree_Avg());
            else
                t1.setText("enter the branche");
        });
        Button button4 = new Button("Back");
        button4.setFont(new Font(30));
        button4.setMinSize(100, 50);
        button4.setTextFill(Color.BLACK);
        button4.setFont(font);
        button4.setTextFill(Color.BLACK);
        button4.setOnAction(e->{
            operationInterFase(stage,branch);
        });
        // Create horizontal box for buttons
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(button1, button2, button3, button4);

        // Create vertical box for text area and button box
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(hBranch,pane, buttonBox);

        // Create scene and set it as the stage's scene
        Scene scene = new Scene(root,1200,700);
        stage.setScene(scene);

    }

    public void updateInterface(Stage stage, int branch){
        HBox hStart = new HBox(30);
        hStart.setAlignment(Pos.CENTER);

        Label l1 = new Label("Seat Number:");
        l1.setFont(new Font(14));
        l1.setTextFill(Color.GOLD);

        TextField t1 = new TextField();

        hStart.getChildren().addAll(l1, t1);

        Label lb = new Label("Branch:");
        lb.setFont(new Font(14));
        lb.setTextFill(Color.GOLD);
        ComboBox<String> branchs=new ComboBox<>();
        branchs.getItems().addAll("Scientific","Literary");
        HBox hBranch = new HBox(30);
        hBranch.setAlignment(Pos.CENTER);
        hBranch.getChildren().addAll(lb,branchs);
        if(branch==1){
            branchs.setDisable(true);
            branchs.setValue("Scientific");
        }
        else if(branch==0){
            branchs.setDisable(true);
            branchs.setValue("Literary");
        }

        HBox hStart1 = new HBox(30);
        hStart1.setAlignment(Pos.CENTER);

        Label l2 = new Label("Average:");
        l2.setFont(new Font(20));
        l2.setTextFill(Color.GOLD);

        TextField t2 = new TextField();
        t2.setDisable(true);
        hStart1.getChildren().addAll(l2, t2);


        HBox hStart3 = new HBox(30);
        hStart3.setAlignment(Pos.CENTER);

        Label l3 = new Label("New Average:");
        l3.setFont(new Font(20));
        l3.setTextFill(Color.GOLD);

        TextField t3 = new TextField();
        t3.setDisable(true);
        hStart1.getChildren().addAll(l3, t3);

        HBox hStart2 = new HBox(30);
        hStart2.setAlignment(Pos.CENTER);

        Button btBack = new Button("Back");
        btBack.setFont(new Font(30));
        btBack.setMinSize(100, 50);
        btBack.setTextFill(Color.BLACK);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btBack.setFont(font);
        btBack.setTextFill(Color.BLACK);

        btBack.setOnAction(e -> {
            operationInterFase(stage,branch);
        });

        Button btUpdate = new Button("Update");
        btUpdate.setFont(new Font(30));
        btUpdate.setMinSize(100, 50);
        Font font1 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btUpdate.setFont(font1);
        btUpdate.setTextFill(Color.BLACK);
        btUpdate.setDisable(true);
        btUpdate.setOnAction(e -> {
            if (Double.parseDouble(t3.getText())>100||Double.parseDouble(t3.getText())<0){
                t3.setText("error enter a coorect avg");
            }
            else {
            tawjihi = new Tawjihi(Long.parseLong(t1.getText()), branchs.getValue(), Double.parseDouble(t3.getText()));
            if (branchs.getValue()=="Literary")
                lit.update(Long.parseLong(t1.getText()),Long.parseLong(t1.getText()), branchs.getValue(), Double.parseDouble(t3.getText()));
            else
                sci.update(Long.parseLong(t1.getText()),Long.parseLong(t1.getText()), branchs.getValue(), Double.parseDouble(t3.getText()));
            btUpdate.setDisable(true);
            t3.setDisable(true);}
        });

        Button btsearch = new Button("Search");
        btsearch.setFont(new Font(30));
        btsearch.setMinSize(100, 50);
        Font font2 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        btsearch.setFont(font2);
        btsearch.setTextFill(Color.BLACK);
        btsearch.setOnAction(e->{
            Tawjihi search = null;
            if(branchs.getValue()=="Literary")
                search=lit.searchBySeatNum(Integer.parseInt(t1.getText()));
            else if (branchs.getValue()=="Scientific")
                search=sci.searchBySeatNum(Integer.parseInt(t1.getText()));
            else
                t1.setText("enter the branche");
            if(search==null){
                t1.setText("not found");
            }
            else {
                t2.setText(String.valueOf(search.getAverage()));
                t3.setDisable(false);
                btUpdate.setDisable(false);
            }
        });
        hStart2.getChildren().addAll(btBack, btUpdate, btsearch);

        VBox vStart = new VBox(30);
        vStart.setAlignment(Pos.CENTER);
        vStart.getChildren().addAll(hStart,hBranch, hStart1,hStart3, hStart2);

        Scene scene = new Scene(vStart, 1200, 700);
        stage.setScene(scene);

    }

    public void heightInterFase(Stage stage,int branch){
        // Create text area
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        TextArea t1 = new TextArea();
        t1.setEditable(false);
        t1.setMinSize(200, 400);
        pane.add(t1, 0, 0);

        Label lb = new Label("Branch:");
        lb.setFont(new Font(14));
        lb.setTextFill(Color.GOLD);
        ComboBox<String> branchs=new ComboBox<>();
        branchs.getItems().addAll("Scientific","Literary");
        HBox hBranch = new HBox(30);
        hBranch.setAlignment(Pos.CENTER);
        hBranch.getChildren().addAll(lb,branchs);
        if(branch==1){
            branchs.setDisable(true);
            branchs.setValue("Scientific");
        }
        else if(branch==0){
            branchs.setDisable(true);
            branchs.setValue("Literary");
        }

        // Create buttons

        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);

        Button button2 = new Button("print 1st avl");
        button2.setFont(new Font(30));
        button2.setMinSize(100, 50);
        button2.setTextFill(Color.BLACK);
        button2.setFont(font);
        button2.setTextFill(Color.BLACK);
        button2.setOnAction(e->{
            if(branchs.getValue()=="Literary")
                t1.setText(String.valueOf(lit.getTawjihiTree_SeatNumheight()));
            else if (branchs.getValue()=="Scientific")
                t1.setText(String.valueOf(sci.getTawjihiTree_SeatNumheight()));
            else
                t1.setText("enter branch");
        });
        Button button3 = new Button("print 2nd avl");
        button3.setFont(new Font(30));
        button3.setMinSize(100, 50);
        button3.setTextFill(Color.BLACK);
        button3.setFont(font);
        button3.setTextFill(Color.BLACK);
        button3.setOnAction(e->{
            if(branchs.getValue()=="Literary")
                t1.setText(String.valueOf(lit.getTawjihiTree_Avgheight()));
            else if (branchs.getValue()=="Scientific")
                t1.setText(String.valueOf(sci.getTawjihiTree_Avgheight()));
            else
                t1.setText("enter branch");
        });
        Button button4 = new Button("Back");
        button4.setFont(new Font(30));
        button4.setMinSize(100, 50);
        button4.setTextFill(Color.BLACK);
        button4.setFont(font);
        button4.setTextFill(Color.BLACK);
        button4.setOnAction(e->{
            operationInterFase(stage,branch);
        });
        // Create horizontal box for buttons
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll( button2, button3, button4);

        // Create vertical box for text area and button box
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(hBranch,pane, buttonBox);

        // Create scene and set it as the stage's scene
        Scene scene = new Scene(root,1200,700);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}