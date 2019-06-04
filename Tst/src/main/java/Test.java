import lk.ijse.dep.AppConfig;
import lk.ijse.dep.DAO.DAO.custom.CustomerDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();

        CustomerDAO customerDAO = ctx.getBean(CustomerDAO.class);



    }

}
