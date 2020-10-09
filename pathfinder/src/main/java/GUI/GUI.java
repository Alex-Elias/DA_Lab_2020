package GUI;

import filereader.Filereader;
import algorithms.AStar;
import algorithms.Dijkstra;
import algorithms.JPS;
import algorithms.BFS;
import datastructures.Node;
import datastructures.NodeList;
import datastructures.Tuple;
import datastructures.TupleList;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * the graphical user interface class
 * this class uses Javafx libraries to implement a graphical user interface
 * this class displays the maze as well as the shortest path
 * How to use:
 *           click on the desired starting location in the maze or input manually into the text field and click the confirm button to confirm the choice
 *           a red dot will appear to confirming the location as well as the location coordinates will appear in the upper left of the page
 *           repeat the process to confirm the destination
 *           click on the button 'run BFS' to run the BFS algorithm,the algorithm will run and the shortest path will be displayed in blue
 *           click on the button 'run Dijkstra' to run the Dijkstra algorithm, the shortest path will be displayed in red
 *           click on the button 'run A*' to run the A Star algorithm, the shortest path will be displayed in green
 *           click on the button 'run JPS' to run the Jump Point Search algorithm, the shortest path will be displayed in green
 *           the button 'reset' on the bottom left of the window will reset the map, sometimes it takes a while so be patient
 *           to change the map click on the drop down menu and select a map then click on the 'select' button
 * NOTE:
 *     
 *      this is a work in progress, the shortest path will only be displayed if the path is reachable
 *      a wall can be selected as a coordinate, there is no shortest path and thus the algorithm will not work
 *      
 *      BFS algorithm is for non-weighted graphs so there is only 4 directions of movement so the path will be longer than the other algorithms
 * 
 * 
 * 
 * @author alex
 */
public class GUI extends Application{
    
    private Tuple origin;
    private boolean origin_set_already = false;
    private boolean dest_set_already = false;
    private Tuple destination;
    private Pane maze;
    private Dijkstra dijk;
    private AStar Astar;
    private JPS jps;
    private BFS bfs;
    
    private int[][] maze_array_read_from_file;
    
    //the multiplicular amout to stretch the maze, only in int right now
    private int maze_stretch = 2;
    
    //maps
    private String m1 = "Mazes/maze512-1-0.map";
    private String m8 = "Mazes/maze512-8-0.map";
    private String m32 = "Mazes/maze512-32-0.map";
    private String r10 = "Mazes/random512-10-0.map";
    private String r40 = "Mazes/random512-40-7.map";
    
    private Filereader file;
    

    @Override
    public void start(Stage stage) throws Exception {
        
        
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
        this.maze_array_read_from_file= file.returnArray();
        // sets the class variable maze
        createMaze(maze_array_read_from_file);
        
        this.dijk = new Dijkstra(maze_array_read_from_file);
        
        this.Astar = new AStar(maze_array_read_from_file);
        
        this.jps = new JPS(maze_array_read_from_file);
        
        this.bfs = new BFS(maze_array_read_from_file);
        //the main borderpane where all of the nodes are set
        BorderPane borderpane = new BorderPane();
        borderpane.setRight(this.maze);
        // the top left VBox containing the text
        VBox top_left_vbox = new VBox();
        top_left_vbox.setSpacing(10);
        
        //the hbox containing the two textfields for the coordinates
        HBox coordinate_hbox = new HBox();
        TextField X_coordinate = new TextField("X =");
        TextField Y_coordinate = new TextField("Y =");
        
        coordinate_hbox.getChildren().add(X_coordinate);
        coordinate_hbox.getChildren().add(Y_coordinate);
        coordinate_hbox.setSpacing(20);
        
        //the vbox containing all of the stats from the algoritm performance and distance of shortest path
        VBox stats_vbox = new VBox();
        Label stats_title = new Label("Stats:");
        Label stats_BFS = new Label("BFS:");
        Label stats_BFS_Length = new Label("Length of path:");
        Label stats_BFS_Time = new Label("Length of BFS in ms:");
        Label stats_dijk = new Label("Dijkstra: ");
        Label stats_dijk_Length = new Label("Length of path: ");
        Label stats_dijk_Time = new Label("Length of Dijkstra in ms:");
        Label stats_AStar = new Label("A*: ");
        Label stats_AStar_Length = new Label("Length of path: ");
        Label stats_AStar_Time = new Label("Length of A* in ms: ");
        Label stats_JPS = new Label("JPS: ");
        Label stats_JPS_Length = new Label("Length of path: ");
        Label stats_JPS_Time = new Label("Length of JPS in ms: ");
        stats_vbox.getChildren().addAll(stats_title,stats_BFS,stats_BFS_Length,stats_BFS_Time,stats_dijk,stats_dijk_Length,stats_dijk_Time);
        stats_vbox.getChildren().addAll(stats_AStar,stats_AStar_Length,stats_AStar_Time,stats_JPS,stats_JPS_Length,stats_JPS_Time);
        
        FlowPane Direction_Origin_pane = new FlowPane();
        FlowPane Destination_pane = new FlowPane();
        
        Label Directions_label = new Label("click on the maze to set a starting location then press accept!");
        Label Origin_coordinates_label = new Label("Origin: X=  , Y=  ");
        Label Destination_coordinates_label = new Label("Destination: X=  , Y=  ");
        Direction_Origin_pane.getChildren().add(Directions_label);
        
        Direction_Origin_pane.getChildren().add(Origin_coordinates_label);
        Destination_pane.getChildren().add(Destination_coordinates_label);
        /**
         * event handler which sets the coordinates of the mouse on the maze to the X and Y coordinate TextField
         */
        this.maze.setOnMouseClicked(new EventHandler<MouseEvent>() {
        
            @Override
            public void handle(MouseEvent event){
                
                X_coordinate.setText("" + event.getX());
                Y_coordinate.setText("" + event.getY());
            }
        });
        Button confirmOrigin = new Button("confirm");
        
        /**
         * the button on activation first sets the class variable, Origin, with the values in the X, Y textfield 
         * the coordinate text updates with the confirmed values
         * a red circle gets set at the chosen location
         */
        confirmOrigin.setOnAction((event) -> {
            // mind the counterintuitive x, y values for the circle coordinates
            if(!this.origin_set_already){
                setOrigin(new Tuple((int)Double.parseDouble(Y_coordinate.getText()) / this.maze_stretch,(int) Double.parseDouble(X_coordinate.getText())/this.maze_stretch));
                this.origin_set_already = true;
                Origin_coordinates_label.setText("Origin: X= " + this.origin.y*this.maze_stretch + ", Y= " + this.origin.x*this.maze_stretch);
                this.maze.getChildren().add(new Circle(this.origin.y*this.maze_stretch,this.origin.x*this.maze_stretch,4,Color.RED));
            }else if(!this.dest_set_already){
                this.destination = new Tuple((int)Double.parseDouble(Y_coordinate.getText())/this.maze_stretch, (int)Double.parseDouble(X_coordinate.getText())/this.maze_stretch);
                Destination_coordinates_label.setText("Destination: X= "+ this.destination.y*this.maze_stretch + ", Y= " + this.destination.x*this.maze_stretch);
                this.dest_set_already = true;
                this.maze.getChildren().add(new Circle(this.destination.y*this.maze_stretch,this.destination.x*this.maze_stretch,4,Color.RED));
            }
             
                
            
        });
        
        
        // button on press runs the BFS algorithm
        Button runBFS_Button = new Button("Run BFS");
        
        
        /**
         * runs the BFS algorithm and sets the shortest path in the maze in blue
         * records the length of time the algorithm takes
         * updates the text with the shortest path length and the execution time
         */
        runBFS_Button.setOnAction((event) -> {
            Long start_time = System.nanoTime();
            bfs.runBFS(this.origin);
            
            bfs.shortestpath(this.destination);
            Long end_time = System.nanoTime();
            for(Tuple t: bfs.path){
                Circle blueCircle = new Circle(this.maze_stretch *t.y,this.maze_stretch * t.x,1);
                blueCircle.setFill(Color.BLUE);
                this.maze.getChildren().add(blueCircle);
            }
            long time_mil = (end_time-start_time)/1000000;
            int temp = bfs.distance[this.destination.x][this.destination.y];
            stats_BFS_Length.setText("Length of path: " + temp);
            stats_BFS_Time.setText("Length of BFS in ms: " + time_mil);
            
        });
        /**
         * Runs Dijkstra algorithm
         */
        
        Button runDijkstra_Button = new Button("Run Dijkstra");
        runDijkstra_Button.setOnAction((event) -> {
           long start_time = System.nanoTime();
           dijk.runDijkstra(this.origin,this.destination);
           
           TupleList dijk_shortestPath = dijk.getShortestPath(this.destination);
           long end_time = System.nanoTime();
           while(!dijk_shortestPath.isEmpty()){
                Tuple t = dijk_shortestPath.remove();
                Circle redCircle = new Circle(this.maze_stretch *t.y,this.maze_stretch * t.x,1);
                redCircle.setFill(Color.RED);
                this.maze.getChildren().add(redCircle);
            }
            long time_mil = (end_time-start_time)/1000000;
            double distance = dijk.getDistance(this.destination);
            stats_dijk_Length.setText("Length of path: " + distance);
            stats_dijk_Time.setText("Length of Dijkstra in ms: " + time_mil);
        });
        //button on press runs the A* algoithm
        Button runAStar_Button = new Button("Run A*");
        /**
         * runs the A* algorithm
         */
        runAStar_Button.setOnAction((event) -> {
            long start_time = System.nanoTime();
            
            Astar.run_AStar(new Node(new Tuple(this.origin.x,this.origin.y)), new Node(new Tuple(this.destination.x,this.destination.y)));
            long end_time = System.nanoTime();
            NodeList AStar_shortestPath = Astar.get_shortest_path(new Node(new Tuple(this.destination.x,this.destination.y)));
            
            while(!AStar_shortestPath.isEmpty()){
                Node node = AStar_shortestPath.remove();
                Circle greenCircle = new Circle(this.maze_stretch * node.coordinates.y, this.maze_stretch * node.coordinates.x,1);
                greenCircle.setFill(Color.GREEN);
                this.maze.getChildren().add(greenCircle);
            }
            long time_mil = (end_time-start_time)/1000000;
            double distance = Astar.distance[this.destination.x][this.destination.y];
            stats_AStar_Length.setText("Length of path: " + distance);
            stats_AStar_Time.setText("Length of A* in ms: " + time_mil);
            
        });
        
        Button runJPS_button = new Button("Run JPS");
        
        runJPS_button.setOnAction((event) ->{
            long start_time = System.nanoTime();
            jps.run_JPS(new Node(new Tuple(this.origin.x,this.origin.y)), new Node(new Tuple(this.destination.x,this.destination.y)));
            long end_time = System.nanoTime();
            for(int i = 0; i < jps.maze.length; i++){
                for (int j = 0; j < jps.maze[0].length; j++){
                    if(jps.jump_point[i][j]==1){
                        Circle purpleCircle = new Circle(this.maze_stretch* j, this.maze_stretch * i, 1);
                        purpleCircle.setFill(Color.PURPLE);
                        this.maze.getChildren().add(purpleCircle);

                    }
                }
            }
            Node last = jps.goal;
            while(last.parent.coordinates != last.coordinates){
                Circle brownCircle = new Circle(this.maze_stretch * last.coordinates.y, this.maze_stretch * last.coordinates.x,1);
                brownCircle.setFill(Color.YELLOW);
                this.maze.getChildren().add(brownCircle);
                last = last.parent;
            }
            
            long time_mil = (end_time-start_time)/1000000;
            stats_JPS_Length.setText("Length of path: " + jps.weight);
            stats_JPS_Time.setText("Length of JPS in ms: " + time_mil);
        });
        
        //Bottom left buttons and drop down menu
        String maze_options_list_array[] = {"one pixel corridor", "eight pixel corridor", "32 pixel corridor", "random map 40%", "random map 10%"};
        
        ComboBox maze_options = new ComboBox(FXCollections.observableArrayList(maze_options_list_array));
        Button maze_select_button = new Button("select");
        Button maze_reset_button = new Button("reset maze");
        HBox maze_options_select = new HBox();
        
        maze_select_button.setOnAction((event) -> {
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
            this.maze_array_read_from_file = this.file.returnArray();
            this.maze.getChildren().clear();
            for( int i = 0; i < maze_array_read_from_file.length; i++){
                for(int j = 0; j < maze_array_read_from_file[1].length; j++){
                    if(maze_array_read_from_file[i][j]==1){
                    maze.getChildren().add(new Circle(this.maze_stretch*j,this.maze_stretch*i,1));
                    }else{
                    
                    }
                }
            }
            this.origin_set_already= false;
            this.dest_set_already=false;
            this.Astar = new AStar(maze_array_read_from_file);
            this.bfs = new BFS(maze_array_read_from_file);
            this.dijk = new Dijkstra(maze_array_read_from_file);
            this.jps = new JPS(maze_array_read_from_file);
            
            
            
            
        });
        maze_reset_button.setOnAction((event) -> {
            this.maze.getChildren().clear();
            for( int i = 0; i < maze_array_read_from_file.length; i++){
                for(int j = 0; j < maze_array_read_from_file[1].length; j++){
                    if(maze_array_read_from_file[i][j]==1){
                    maze.getChildren().add(new Circle(this.maze_stretch*j,this.maze_stretch*i,1));
                    }else{
                    
                    }
                }
            }
            this.origin_set_already= false;
            this.dest_set_already=false;
            this.Astar = new AStar(maze_array_read_from_file);
            this.bfs = new BFS(maze_array_read_from_file);
            this.dijk = new Dijkstra(maze_array_read_from_file);
            this.jps = new JPS(maze_array_read_from_file);
        
        });
                
                
        maze_options_select.getChildren().addAll(maze_options,maze_select_button, maze_reset_button);
        borderpane.setBottom(maze_options_select);
        
        //adds the top left vbox nodes
        top_left_vbox.getChildren().add(Direction_Origin_pane);
        top_left_vbox.getChildren().add(Destination_coordinates_label);
        
        top_left_vbox.getChildren().add(coordinate_hbox);
        top_left_vbox.getChildren().add(confirmOrigin);
        top_left_vbox.getChildren().add(new Label("Press the button below to run BFS algorithm"));
        top_left_vbox.getChildren().add(runBFS_Button);
        top_left_vbox.getChildren().add(runDijkstra_Button);
        top_left_vbox.getChildren().add(runAStar_Button);
        
        top_left_vbox.getChildren().add(runJPS_button);
        top_left_vbox.getChildren().add(stats_vbox);
        
        
        
        
        
        
        
        borderpane.setLeft(top_left_vbox);
        Scene scene = new Scene(borderpane);
        stage.setScene(scene);
        stage.setTitle("Pathfinding");
        
        stage.show();
        
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
                    maze.getChildren().add(new Circle(this.maze_stretch*j,this.maze_stretch*i,1));
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
    
    
    
    
}
