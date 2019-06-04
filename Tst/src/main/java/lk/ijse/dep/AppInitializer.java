package lk.ijse.dep;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.dep.Business.impl.CustomerBOImpl;
import lk.ijse.dep.Business.impl.ItemBOImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class AppInitializer extends Application {

    public static AnnotationConfigApplicationContext ctx;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        ctx= new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
//        ItemBOImpl bean = ctx.getBean(ItemBOImpl.class);
//        System.out.println(bean);

        Parent root = FXMLLoader.load(this.getClass().getResource("/View/Dashboard.fxml"));
        Scene mainScreen = new Scene(root);
        primaryStage.setScene(mainScreen);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
