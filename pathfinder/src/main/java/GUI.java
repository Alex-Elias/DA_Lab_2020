import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUI extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        
        
        //Create new Filereaderclass
        Filereader f = new Filereader("/home/alex/Mazes/maze512-32-0.map");
        //Filereader returns a 2d array of the maze
        Pane maze = createMaze(f.returnArray());
        BorderPane borderpane = new BorderPane();
        borderpane.setRight(maze);
        
        VBox vbox = new VBox();
        vbox.getChildren().add(new Button("click here"));
        
        borderpane.setLeft(vbox);
        
        Scene scene = new Scene(borderpane);
        stage.setScene(scene);
        stage.setTitle("Pathfinding");
        
        stage.show();
        
    }
    public Pane createMaze(int[][] mazeArray){
        
        Pane maze = new Pane();
        maze.setPrefSize(1026, 1026);
        for( int i = 0; i < mazeArray.length; i++){
            for(int j = 0; j < mazeArray[1].length; j++){
                if(mazeArray[i][j]==1){
                    maze.getChildren().add(new Circle(2*i,2*j,1));
                }else{
                    
                }
            }
        }
        return maze;
    }
    
    public void launchGUI(){
        launch(GUI.class);
    }
    
    
    
}
