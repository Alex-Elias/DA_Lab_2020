import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUI extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        
        
        
        Filereader f = new Filereader("/home/alex/Downloads/maze512-32-0.map");
        Pane maze = createMaze(f.returnArray());
        
        Scene scene = new Scene(maze);
        stage.setScene(scene);
        stage.show();
        
    }
    public Pane createMaze(int[][] mazeArray){
        
        Pane maze = new Pane();
        for( int i = 0; i < mazeArray.length; i++){
            for(int j = 0; j < mazeArray[1].length; i++){
                if(mazeArray[i][j]==1){
                    maze.getChildren().add(new Circle(i,j,1));
                }
            }
        }
        return maze;
    }
    
    public void launchGUI(){
        launch(GUI.class);
    }
    
    
    
}
