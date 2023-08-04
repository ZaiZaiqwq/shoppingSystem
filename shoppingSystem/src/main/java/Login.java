import java.util.Scanner;

//登录类，用于实现购物系统的登陆界面及相关功能
public class Login {
    //显示函数，用于显示登陆界面及相关功能，根据用户不同的输入调用不同的功能函数
    public static void showLogIn(){
        String choice;
        Scanner scanner = new Scanner(System.in);

        while (true){
            //打印登陆界面及相关功能
            System.out.println("======================================================");
            System.out.println("======================================================");
            System.out.println("||   Welcome to Gene Knee Tie May Shopping System   ||");
            System.out.println("||              1.Administrator Login               ||");
            System.out.println("||              2.Customer Login                    ||");
            System.out.println("||              3.Customer Registration             ||");
            System.out.println("||              4.Exit                              ||");
            System.out.println("======================================================");
            System.out.println("======================================================");

            //获取用户输入的选择
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            //根据用户输入的不同选择进入不同分支，调用不同的函数
            System.out.println();
            switch (choice) {
                case "1":
                    Administrator admin = new Administrator();
                    admin.logIn();
                    break;
                case "2":
                    Customer customer = new Customer();
                    customer.logIn();
                    break;
                case "3":
                    Customer newCustomer = new Customer();
                    newCustomer.registration();
                    break;
                case "4":
                    System.out.println("Looking forward to your next use.");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            System.out.println();
        }
    }

    //主函数
    public static void main(String[] args) {
        Login.showLogIn();
    }
}