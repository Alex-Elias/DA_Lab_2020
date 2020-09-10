import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class GUI extends Application{
    
    private Tuple origin;
    private boolean origin_set_already = false;
    private boolean dest_set_already = false;
    private Tuple destination;
    private Pane maze;
    private int maze_stretch = 2;
    

    @Override
    public void start(Stage stage) throws Exception {
        
        
        //Create new Filereaderclass
        Filereader f = new Filereader("/home/alex/Mazes/maze512-1-0.map");
        //Filereader returns a 2d array of the maze
        int[][] array = f.returnArray();
        createMaze(array);
        BFS bfs = new BFS(array);
        BorderPane borderpane = new BorderPane();
        borderpane.setRight(this.maze);
        
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        
        //HBox for origin
        HBox hbox = new HBox();
        TextField XOrigin = new TextField("X =");
        TextField YOrigin = new TextField("Y =");
        
        hbox.getChildren().add(XOrigin);
        hbox.getChildren().add(YOrigin);
        hbox.setSpacing(20);
        
        
        
        
        FlowPane pane = new FlowPane();
        FlowPane pane2 = new FlowPane();
        
        Label firstLabel = new Label("click on the maze to set a starting location then press accept!");
        Label OriginCord = new Label("Origin: X=  , Y=  ");
        Label DestCord = new Label("Destination: X=  , Y=  ");
        pane.getChildren().add(firstLabel);
        
        this.maze.setOnMouseClicked(new EventHandler<MouseEvent>() {
        
            @Override
            public void handle(MouseEvent event){
                
                XOrigin.setText("" + event.getX());
                YOrigin.setText("" + event.getY());
            }
        });
        Button confirmOrigin = new Button("confirm");
        confirmOrigin.setOnAction((event) -> {
        
            if(!this.origin_set_already){
                setOrigin(new Tuple((int)Double.parseDouble(YOrigin.getText()) / this.maze_stretch,(int) Double.parseDouble(XOrigin.getText())/this.maze_stretch));
                this.origin_set_already = true;
                OriginCord.setText("Origin: X= " + this.origin.y*this.maze_stretch + ", Y= " + this.origin.x*this.maze_stretch);
                
            }else if(!this.dest_set_already){
                this.destination = new Tuple((int)Double.parseDouble(YOrigin.getText())/this.maze_stretch, (int)Double.parseDouble(XOrigin.getText())/this.maze_stretch);
                DestCord.setText("Destination: X= "+ this.destination.y*this.maze_stretch + ", Y= " + this.destination.x*this.maze_stretch);
                this.dest_set_already = true;
            }
             
                
            
        });
        
        pane.getChildren().add(OriginCord);
        pane2.getChildren().add(DestCord);
        
        Button runBFS_Button = new Button("Run BFS");
        
        runBFS_Button.setOnAction((event) -> {
            bfs.runBFS(this.origin);
            bfs.shortestpath(this.destination);
            
            for(Tuple t: bfs.path){
                Circle blueCircle = new Circle(this.maze_stretch *t.y,this.maze_stretch * t.x,1);
                blueCircle.setFill(Color.BLUE);
                this.maze.getChildren().add(blueCircle);
            }
            
        });
        
        
        
        //VBOX addition
        vbox.getChildren().add(pane);
        vbox.getChildren().add(DestCord);
        
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(confirmOrigin);
        vbox.getChildren().add(new Label("Press the button below to run BFS algorithm"));
        vbox.getChildren().add(runBFS_Button);
        borderpane.setLeft(vbox);
        
        Scene scene = new Scene(borderpane);
        stage.setScene(scene);
        stage.setTitle("Pathfinding");
        
        stage.show();
        
    }
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
    private int getStretch(){
        return this.maze_stretch;
    }
    
    
    
}
