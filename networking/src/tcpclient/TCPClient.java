package tcpclient;
import java.io.*;
import java.net.*;
import javafx.application.Application;
import static javafx.application.ConditionalFeature.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class TCPClient extends Application
{
   
    public static String modifiedSentence;
    
    public static void main(String args[]) throws Exception
    {
        launch(args);
        
        String sentence;
        
        
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 12380);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while(true)
        {     
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            
            System.out.println("From Server : "+modifiedSentence);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("clientGUI.fxml"));
        
        Scene scene = new Scene(root);
       
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public void send(TextArea area,TextField field){
        field.clear();
        area.appendText(modifiedSentence);
        
    }
}