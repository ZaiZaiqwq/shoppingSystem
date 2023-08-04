import java.util.Scanner;

//菜单类，用于显示管理员和客户的功能菜单界面，根据用户不同的输入调用不同的功能函数
public class Menu {
    //管理员菜单函数
    public static void adminMenu(Administrator admin){
        String choice;
        Scanner scanner = new Scanner(System.in);

        while (true){
            //打印菜单界面及用户可选择的功能
            System.out.println("========================================================");
            System.out.println("========================================================");
            System.out.println("||    Gene Knee Tie May Shopping system>Admin Menu    ||");
            System.out.println("||               1.password management                ||");
            System.out.println("||               2.customer management                ||");
            System.out.println("||               3.commodity management               ||");
            System.out.println("||               4.log out                            ||");
            System.out.println("========================================================");
            System.out.println("========================================================");

            //获取用户输入的选择
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            //根据用户输入的不同选择进入不同分支，调用不同的函数
            System.out.println();
            switch (choice){
                case "1":
                    admin.managePassword();
                    break;
                case "2":
                    admin.manageCustomer();
                    break;
                case "3":
                    admin.manageCommodity();
                    break;
                case "4":
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            System.out.println();
        }
    }

    //客户菜单函数
    public static void customerMenu(Customer customer){
        String choice;
        Scanner scanner = new Scanner(System.in);

        while (true){
            //打印菜单界面及用户可选择的功能
            System.out.println("===========================================================");
            System.out.println("===========================================================");
            System.out.println("||    Gene Knee Tie May Shopping system>Customer Menu    ||");
            System.out.println("||                 1.password management                 ||");
            System.out.println("||                 2.shopping management                 ||");
            System.out.println("||                 3.log out                             ||");
            System.out.println("===========================================================");
            System.out.println("===========================================================");

            //获取用户输入的选择
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            //根据用户输入的不同选择进入不同分支，调用不同的函数
            System.out.println();
            switch (choice){
                case "1":
                    customer.managePassword(customer);
                    break;
                case "2":
                    customer.shopping(customer);
                    break;
                case "3":
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            System.out.println();
        }
    }

}
