package GUI;

import filereader.Filereader;
import algorithms.AStar;
import algorithms.Dijkstra;
import algorithms.JPS;
import datastructures.Node;
import datastructures.NodeList;
import datastructures.Tuple;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * the graphical user interface class
 * this class uses Javafx libraries to implement a graphical user interface
 * this class displays the maze as well as the shortest path
 * How to use:
 *           click on the desired starting location in the maze or input manually into the text field and click the confirm button to confirm the choice
 *           a red dot will appear to confirming the location as well as the location coordinates will appear in the upper left of the page
 *           repeat the process to confirm the destination
 *           click on the button 'run Dijkstra' to run the Dijkstra algorithm, the shortest path will be displayed in red
 *           click on the button 'run A*' to run the A Star algorithm, the shortest path will be displayed in green
 *           click on the button 'run JPS' to run the Jump Point Search algorithm, the shortest path will be displayed in green
 *           the button 'reset' on the bottom left of the window will reset the map, sometimes it takes a while so be patient
 *           to change the map click on the drop down menu and select a map then click on the 'select' button
 * NOTE:
 *     
 *      
 *      
 *      
 * 
 * 
 * 
 * @author alex
 */
public class GUI extends Application{
    
    private Tuple origin;
    private boolean originSetAlready = false;
    private boolean destinationSetAlready = false;
    private Tuple destination;
    private Pane maze;
    private Dijkstra dijk;
    private AStar Astar;
    private JPS jps;
    
    
    private int[][] mazeArrayReadFromFile;
    
    //the multiplicular amout to stretch the maze, only in int right now
    private int mazeStretch = 2;
    
    //maps
    private String m1 = "Mazes/maze512-1-0.map";
    private String m8 = "Mazes/maze512-8-0.map";
    private String m32 = "Mazes/maze512-32-0.map";
    private String r10 = "Mazes/random512-10-0.map";
    private String r40 = "Mazes/random512-40-7.map";
    
    private Filereader file;
    
    
    // stat Labels
    Label statsTitle;
    
    Label statsDijk;
    Label statsDijkLength;
    Label statsDijkTime;
    Label statsAStar;
    Label statsAStarLength;
    Label statsAStarTime;
    Label statsJPS;
    Label statsJPSLength;
    Label statsJPSTime;
    
    // coordinanation labels
    Label OriginCoordinatesLabel;
    Label DestinationCoordinatesLabel;

    // Buttons
    Button confirmOrigin;
    
    Button runDijkstraButton;
    Button runAStarButton;
    Button runJPSButton;
    Button mazeSelectButton;
    Button mazeResetButton;
    
    TextField XCoordinate;
    TextField YCoordinate;
    

    

    @Override
    public void start(Stage stage) throws Exception {
        this.initialize();
        
        
        //Create new Filereaderclass
        //it takes string input of the location of a .map maze file
        /**
         * the tested mazes are:
         *  Mazes/maze512-1-0.map 
         *  Mazes/maze512-32-0.map 
         *  Mazes/maze512-8-0.map
         *  Mazes/random512-10-0.map  
         *  Mazes/brc101d.map - 
         *  Mazes/random512-40-7.map 
        
        */
        //-------------------------------------------FILE PATH HERE--------------------------------------------------------------------
        this.file = new Filereader("Mazes/maze512-8-0.map");
        //------------------------------------------ABOVE HERE-------------------------------------------------------------------------
        

        //Filereader returns a 2d array of the maze
        this.mazeArrayReadFromFile= file.returnArray();
        // sets the class variable maze
        createMaze(mazeArrayReadFromFile);
        
        this.dijk = new Dijkstra(mazeArrayReadFromFile);
        
        this.Astar = new AStar(mazeArrayReadFromFile);
        
        this.jps = new JPS(mazeArrayReadFromFile);
        
        
        
        
        
        
        
        //the main borderpane where all of the nodes are set
        BorderPane borderpane = new BorderPane();
        borderpane.setRight(this.maze);
        // the top left VBox containing the text
        VBox topLeftVbox = new VBox();
        topLeftVbox.setSpacing(10);
        
        //the hbox containing the two textfields for the coordinates
        HBox coordinateHbox = new HBox();
        
        
        coordinateHbox.getChildren().add(this.XCoordinate);
        coordinateHbox.getChildren().add(this.YCoordinate);
        coordinateHbox.setSpacing(20);
        
        //the vbox containing all of the stats from the algoritm performance and distance of shortest path
        VBox statsVbox = new VBox();
        
        statsVbox.getChildren().addAll(statsTitle, statsDijk, statsDijkLength, statsDijkTime, statsAStar, statsAStarLength, statsAStarTime, statsJPS, statsJPSLength, statsJPSTime);
        
        FlowPane Direction_Origin_pane = new FlowPane();
        FlowPane Destination_pane = new FlowPane();
        
        Label Directions_label = new Label("click on the maze to set a starting location then press accept!");
        
        Direction_Origin_pane.getChildren().add(Directions_label);
        
        
        
        Label error_location_label = new Label();
        
        
        
        
        Direction_Origin_pane.getChildren().add(this.OriginCoordinatesLabel);
        Destination_pane.getChildren().add(this.OriginCoordinatesLabel);
        
        /**
         * event handler which sets the coordinates of the mouse on the maze to the X and Y coordinate TextField
         */
        this.maze.setOnMouseClicked(new EventHandler<MouseEvent>() {
        
            @Override
            public void handle(MouseEvent event){
                
                XCoordinate.setText("" + event.getX());
                YCoordinate.setText("" + event.getY());
            }
        });
        
        
        /**
         * the button on activation first sets the class variable, Origin, with the values in the X, Y textfield 
         * the coordinate text updates with the confirmed values
         * a red circle gets set at the chosen location
         */
        confirmOrigin.setOnAction((event) -> {
            // mind the counterintuitive x, y values for the circle coordinates
            int x = (int)Double.parseDouble(YCoordinate.getText()) / this.mazeStretch;
            int y = (int) Double.parseDouble(XCoordinate.getText())/this.mazeStretch;
            if(this.isValidLocation(x, y)){
                
                error_location_label.setText("");
            
            
                if(!this.originSetAlready){
                    setOrigin(new Tuple(x,y));
                    this.originSetAlready = true;
                    OriginCoordinatesLabel.setText("Origin: X= " + this.origin.getY()*this.mazeStretch + ", Y= " + this.origin.getX()*this.mazeStretch);
                    this.maze.getChildren().add(new Circle(this.origin.getY()*this.mazeStretch,this.origin.getX()*this.mazeStretch,4,Color.RED));
                }else if(!this.destinationSetAlready){
                    this.destination = new Tuple(x,y);
                    DestinationCoordinatesLabel.setText("Destination: X= "+ this.destination.getY()*this.mazeStretch + ", Y= " + this.destination.getX()*this.mazeStretch);
                    this.destinationSetAlready = true;
                    this.maze.getChildren().add(new Circle(this.destination.getY()*this.mazeStretch,this.destination.getX()*this.mazeStretch,4,Color.RED));
                }
            }else{
                error_location_label.setText("Invalid location! Make sure you are not selecting a wall");
                error_location_label.setTextFill(Color.RED);
            }
            
            if(this.originSetAlready && this.destinationSetAlready){
                this.setAlgorithmButtonsEnabled();
                confirmOrigin.setDisable(true);
            }
             
                
            
        });
        
        
        
        
        
        
        
        /**
         * Runs Dijkstra algorithm
         */
        
        
        runDijkstraButton.setOnAction((event) -> {
           
            long time_mil = this.runDijkstra();
            double distance = dijk.getDistance();
            statsDijkLength.setText("Length of path: " + distance);
            statsDijkTime.setText("Length of Dijkstra in ms: " + time_mil);
        });
        //button on press runs the A* algoithm
        
        /**
         * runs the A* algorithm
         */
        runAStarButton.setOnAction((event) -> {
            
            long time_mil = this.runAStar();
            double distance = Astar.distance[this.destination.getX()][this.destination.getY()];
            statsAStarLength.setText("Length of path: " + distance);
            statsAStarTime.setText("Length of A* in ms: " + time_mil);
            
        });
        
        
        
        runJPSButton.setOnAction((event) ->{
            
            
            long time_mil = this.runJPS();
            statsJPSLength.setText("Length of path: " + jps.weight);
            statsJPSTime.setText("Length of JPS in ms: " + time_mil);
        });
        
        //Bottom left buttons and drop down menu
        String maze_options_list_array[] = {"one pixel corridor", "eight pixel corridor", "32 pixel corridor", "random map 40%", "random map 10%"};
        
        ComboBox maze_options = new ComboBox(FXCollections.observableArrayList(maze_options_list_array));
        
        HBox maze_options_select = new HBox();
        
        mazeSelectButton.setOnAction((event) -> {
            String map = maze_options.getValue().toString();
            if(map.equals("one pixel corridor")){
                this.file = new Filereader(this.m1);
            }else if(map.equals("eight pixel corridor")){
                this.file = new Filereader(this.m8);
            }else if(map.equals("32 pixel corridor")){
                this.file = new Filereader(this.m32);
            }else if(map.equals("random map 40%")){
                this.file = new Filereader(this.r40);
            }else{
                this.file = new Filereader(this.r10);
            }
            this.mazeArrayReadFromFile = this.file.returnArray();
            this.resetMap();
            
            
            
            
        });
        mazeResetButton.setOnAction((event) -> {
            this.resetMap();
        
        });
                
                
        maze_options_select.getChildren().addAll(maze_options,mazeSelectButton, mazeResetButton);
        borderpane.setBottom(maze_options_select);
        
        //adds the top left vbox nodes
        topLeftVbox.getChildren().add(Direction_Origin_pane);
        topLeftVbox.getChildren().add(OriginCoordinatesLabel);
        topLeftVbox.getChildren().add(DestinationCoordinatesLabel);
        topLeftVbox.getChildren().add(error_location_label);
        topLeftVbox.getChildren().add(coordinateHbox);
        
        topLeftVbox.getChildren().add(confirmOrigin);
        topLeftVbox.getChildren().add(runDijkstraButton);
        topLeftVbox.getChildren().add(runAStarButton);
        
        topLeftVbox.getChildren().add(runJPSButton);
        topLeftVbox.getChildren().add(statsVbox);
        
        
        
        
        this.setAlgorithmButtonsDisabled();
        
        Stage instructionStage = new Stage();
        instructionStage.initModality(Modality.APPLICATION_MODAL);
        instructionStage.initOwner(stage);
        Scene instructionScene = new Scene(this.instructions());
        instructionStage.setScene(instructionScene);
        instructionStage.setTitle("User Instructions");
        
        
        
        borderpane.setLeft(topLeftVbox);
        Scene scene = new Scene(borderpane);
        stage.setScene(scene);
        stage.setTitle("Pathfinding");
        
        stage.show();
        instructionStage.show();
        
    }
    
    /**
     * visualizes the 2d array
     * @param mazeArray the 2d array which will be visualized
     */
    public void createMaze(int[][] mazeArray){
        
        Pane maze = new Pane();
        maze.setPrefSize(1026, 1026);
        for( int i = 0; i < mazeArray.length; i++){
            for(int j = 0; j < mazeArray[1].length; j++){
                if(mazeArray[i][j]==1){
                    maze.getChildren().add(new Circle(this.mazeStretch*j,this.mazeStretch*i,1));
                }else{
                    
                }
            }
        }
        this.maze = maze;
    }
    
    public void launchGUI(){
        launch(GUI.class);
    }
    private void setOrigin(Tuple T){
        this.origin = T;
    }
    private boolean isValidLocation(int x, int y){
        if(x<0 || x >= this.mazeArrayReadFromFile.length){
            return false;
        }
        if(y < 0 || y >= this.mazeArrayReadFromFile.length){
            return false;
        }
        if(this.mazeArrayReadFromFile[x][y]==1){
            return false;
        }
        return true;
    }
    private long runDijkstra(){
        long start_time = System.nanoTime();
        dijk.runDijkstra(this.origin,this.destination);

        NodeList dijk_shortestPath = dijk.getShortestPath();
        long end_time = System.nanoTime();
        while(!dijk_shortestPath.isEmpty()){
             Node n = dijk_shortestPath.remove();
             Circle redCircle = new Circle(this.mazeStretch *n.getY(),this.mazeStretch * n.getX(),1);
             redCircle.setFill(Color.RED);
             this.maze.getChildren().add(redCircle);
         }
         long time_mil = (end_time-start_time)/1000000;
         return time_mil;
    }
    private long runAStar(){
        long start_time = System.nanoTime();
            
        Astar.runAStar(new Node(new Tuple(this.origin.getX(),this.origin.getY())), new Node(new Tuple(this.destination.getX(),this.destination.getY())));
        long end_time = System.nanoTime();
        NodeList AStar_shortestPath = Astar.getShortestPath();

        while(!AStar_shortestPath.isEmpty()){
            Node node = AStar_shortestPath.remove();
            Circle greenCircle = new Circle(this.mazeStretch * node.getY(), this.mazeStretch * node.getX(),1);
            greenCircle.setFill(Color.GREEN);
            this.maze.getChildren().add(greenCircle);
        }
        long time_mil = (end_time-start_time)/1000000;
        return time_mil;
    }
    private long runJPS(){
        long start_time = System.nanoTime();
        jps.runJPS(new Node(new Tuple(this.origin.getX(),this.origin.getY())), new Node(new Tuple(this.destination.getX(),this.destination.getY())));
        long end_time = System.nanoTime();
        for(int i = 0; i < jps.maze.length; i++){
            for (int j = 0; j < jps.maze[0].length; j++){
                if(jps.jump_point[i][j]==1){
                    Circle purpleCircle = new Circle(this.mazeStretch* j, this.mazeStretch * i, 1);
                    purpleCircle.setFill(Color.PURPLE);
                    this.maze.getChildren().add(purpleCircle);

                }
            }
        }
        NodeList JPSshortestPath = jps.getShortestPath();
        
        while(!JPSshortestPath.isEmpty()){
            Node node = JPSshortestPath.remove();
            Circle yellowCircle = new Circle(this.mazeStretch * node.getY(), this.mazeStretch * node.getX(),1);
            yellowCircle.setFill(Color.YELLOW);
            this.maze.getChildren().add(yellowCircle);
            
        }

        long time_mil = (end_time-start_time)/1000000;
        return time_mil;
    }
    private void setAlgorithmButtonsDisabled(){
        
        runDijkstraButton.setDisable(true);
        runAStarButton.setDisable(true);
        runJPSButton.setDisable(true);
    }
    private void setAlgorithmButtonsEnabled(){
        runDijkstraButton.setDisable(false);
        runAStarButton.setDisable(false);
        runJPSButton.setDisable(false);
    }
    private void resetMap(){
        this.maze.getChildren().clear();
            for( int i = 0; i < mazeArrayReadFromFile.length; i++){
                for(int j = 0; j < mazeArrayReadFromFile[1].length; j++){
                    if(mazeArrayReadFromFile[i][j]==1){
                    maze.getChildren().add(new Circle(this.mazeStretch*j,this.mazeStretch*i,1));
                    }else{
                    
                    }
                }
            }
            this.originSetAlready= false;
            this.destinationSetAlready=false;
            this.Astar = new AStar(mazeArrayReadFromFile);
            
            this.dijk = new Dijkstra(mazeArrayReadFromFile);
            this.jps = new JPS(mazeArrayReadFromFile);
            
            this.setAlgorithmButtonsDisabled();
            this.confirmOrigin.setDisable(false);
            this.resetStats();
            
            
    }
    private void resetStats(){
        this.statsDijkLength.setText("Length of path: ");
        this.statsDijkTime.setText("Length of Dijkstra in ms:");
        this.statsAStarLength.setText("Length of path: ");
        this.statsAStarTime.setText("Length of A* in ms: ");
        this.statsJPSLength.setText("Length of path: ");
        this.statsJPSTime.setText("Length of JPS in ms: ");
        this.XCoordinate.setText("X =");
        this.YCoordinate.setText("Y =");
        
        this.OriginCoordinatesLabel.setText("Origin: X=  , Y=  ");
        this.DestinationCoordinatesLabel.setText("Destination: X=  , Y=  ");
    }
    private void initialize(){
        // stat Labels
        this.statsTitle = new Label("Stats:");
        this.statsDijk = new Label("Dijkstra: ");
        this.statsDijkLength = new Label("Length of path: ");
        this.statsDijkTime = new Label("Length of Dijkstra in ms:");
        this.statsAStar = new Label("A*: ");
        this.statsAStarLength = new Label("Length of path: ");
        this.statsAStarTime = new Label("Length of A* in ms: ");
        this.statsJPS = new Label("JPS: ");
        this.statsJPSLength = new Label("Length of path: ");
        this.statsJPSTime = new Label("Length of JPS in ms: ");
        
        this.OriginCoordinatesLabel = new Label("Origin: X=  , Y=  ");
        this.DestinationCoordinatesLabel = new Label("Destination: X=  , Y=  ");

        // Buttons
        this.confirmOrigin = new Button("confirm");
        this.runDijkstraButton = new Button("Run Dijkstra");
        this.runAStarButton = new Button("Run A*");
        this.runJPSButton = new Button("Run JPS");
        this.mazeSelectButton = new Button("select");
        this.mazeResetButton = new Button("reset maze");
        
        this.XCoordinate = new TextField("X =");
        this.YCoordinate = new TextField("Y =");
    }
    
    private VBox instructions(){
        
        Label label1 = new Label("Click on the desired starting location in the maze or input manually into the text field and click the 'confirm' button to confirm your choice");
        label1.setFont(new Font(20));
        Label label2 = new Label("Repeat the process to confirm the destination");
        label2.setFont(new Font(20));
        
        Label label4 = new Label("Click on the button 'run Dijkstra' to run the Dijkstra algorithm, the shortest path will be displayed in red");
        label4.setFont(new Font(20));
        Label label5 = new Label("Click on the button 'run A*' to run the A Star algorithm, the shortest path will be displayed in green");
        label5.setFont(new Font(20));
        Label label6 = new Label("Click on the button 'run JPS' to run the Jump Point Search algorithm, the shortest path will be displayed in green");
        label6.setFont(new Font(20));
        Label label7 = new Label("The button 'reset' on the bottom left of the window will reset the map, sometimes it takes a while so be patient");
        label7.setFont(new Font(20));
        Label label8 = new Label("to change the map click on the drop down menu and select a map then click on the 'select' button");
        label8.setFont(new Font(20));
        VBox box = new VBox();
        box.setSpacing(10);
        box.getChildren().addAll(label1,label2,label4,label5,label6,label7,label8);
        return box;
    }
    
    
    
    
}
