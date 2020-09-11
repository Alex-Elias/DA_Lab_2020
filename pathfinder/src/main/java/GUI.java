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
 * @author alex
 */
public class GUI extends Application{
    
    private Tuple origin;
    private boolean origin_set_already = false;
    private boolean dest_set_already = false;
    private Tuple destination;
    private Pane maze;
    
    //the multiplicular amout to stretch the maze, only in int right now
    private int maze_stretch = 2;
    

    @Override
    public void start(Stage stage) throws Exception {
        
        
        //Create new Filereaderclass
        //it takes string input of the location of a .map maze file
        Filereader f = new Filereader("//home/alex/Pathfinder/Mazes/maze512-1-0.map");
        //Filereader returns a 2d array of the maze
        int[][] maze_array_read_from_file = f.returnArray();
        // sets the class variable maze
        createMaze(maze_array_read_from_file);
         
        BFS bfs = new BFS(maze_array_read_from_file);
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
        stats_vbox.getChildren().addAll(stats_title,stats_BFS,stats_BFS_Length,stats_BFS_Time);
        
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
        //UNFINISHED
        String maze_options_list_array[] = {"one pixel corridor", "four pixel corridor", "16 pixel corridor","32 pixel corridor"};
        
        ComboBox maze_options = new ComboBox(FXCollections.observableArrayList(maze_options_list_array));
        Button maze_select_button = new Button("select");
        HBox maze_options_select = new HBox();
        
        maze_select_button.setOnAction((event) -> {
            
        });
                
                
        maze_options_select.getChildren().addAll(maze_options,maze_select_button);
        borderpane.setBottom(maze_options_select);
        
        //adds the top left vbox nodes
        top_left_vbox.getChildren().add(Direction_Origin_pane);
        top_left_vbox.getChildren().add(Destination_coordinates_label);
        
        top_left_vbox.getChildren().add(coordinate_hbox);
        top_left_vbox.getChildren().add(confirmOrigin);
        top_left_vbox.getChildren().add(new Label("Press the button below to run BFS algorithm"));
        top_left_vbox.getChildren().add(runBFS_Button);
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
