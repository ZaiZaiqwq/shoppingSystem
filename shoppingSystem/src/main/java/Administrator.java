import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//管理员类
public class Administrator extends User{
    public Administrator(){

    }

    //管理员登录函数
    public void logIn(){
        String userName,inputMD5,password,correctUsername="admin";
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------Gene Knee Tie May Shopping system>Administrator Login----------");

        //获取用户输入的用户名和密码，并将密码转换成MD5值
        System.out.print("username: ");
        userName = scanner.nextLine();
        System.out.print("password: ");
        inputMD5=MD5Util.md5(scanner.nextLine());

        //从文件中读取正确的密码
        try (BufferedReader reader = new BufferedReader(new FileReader("adminPassword.txt"))){
            password =reader.readLine();
        }catch (IOException e){
            System.out.println("An error occurred while reading the administrator password file. Login failed.");
            e.printStackTrace();
            return;
        }

        //判断输入的用户名和密码是否正确
        if (userName.equals(correctUsername)&&password.equals(inputMD5)){
            System.out.println("Login successfully.");
            UserName=correctUsername;
            Password=password;
            Menu.adminMenu(this);
        }else {
            System.out.println("Username and password do not match. Login failed.");
        }
    }

    //管理员密码管理显示函数，用于显示密码管理界面及相关功能，根据用户不同的输入调用不同的功能函数
    public void managePassword(){
        String choice;
        Scanner scanner = new Scanner(System.in);

        while (true){
            //打印密码管理操作界面及相关功能操作
            System.out.println("=================================================================");
            System.out.println("=================================================================");
            System.out.println("||    Gene Knee Tie May Shopping system>Password Management    ||");
            System.out.println("||                    1.change password                        ||");
            System.out.println("||                    2.reset customer password                ||");
            System.out.println("||                    3.get back                               ||");
            System.out.println("=================================================================");
            System.out.println("=================================================================");

            //获取用户输入的选择
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            //根据用户输入的不同选择进入不同分支，调用不同的函数
            System.out.println();
            switch (choice){
                case "1":
                    PasswordManagement.changePassword();
                    break;
                case "2":
                    PasswordManagement.resetCustomerPassword();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println();
        }
    }

    //客户管理显示函数，用于显示用户管理界面及相关功能，根据用户不同的输入调用不同的功能函数
    public void manageCustomer(){
        String choice;
        Scanner scanner = new Scanner(System.in);

        while (true){
            //打印客户管理操作界面及相关功能操作
            System.out.println("=================================================================");
            System.out.println("=================================================================");
            System.out.println("||    Gene Knee Tie May Shopping system>Customer Management    ||");
            System.out.println("||                1.List all customer information              ||");
            System.out.println("||                2.Delete customer information                ||");
            System.out.println("||                3.Query customer information                 ||");
            System.out.println("||                4.get back                                   ||");
            System.out.println("=================================================================");
            System.out.println("=================================================================");

            //获取用户输入的选择
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            //根据用户输入的不同选择进入不同分支，调用不同的函数
            System.out.println();
            switch (choice){
                case "1":
                    CustomerManagement.ListAll();
                    break;
                case "2":
                    CustomerManagement.Delete();
                    break;
                case "3":
                    CustomerManagement.QueryCustomerInformation();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            System.out.println();
        }
    }

    //商品管理显示函数，用于显示商品管理界面及相关功能，根据用户不同的输入调用不同的功能函数
    public void manageCommodity(){
        String choice;
        Scanner scanner = new Scanner(System.in);

        while (true){
            //打印商品管理操作界面及相关功能操作
            System.out.println("==================================================================");
            System.out.println("==================================================================");
            System.out.println("||    Gene Knee Tie May Shopping system>Commodity Management    ||");
            System.out.println("||                1.List all product information                ||");
            System.out.println("||                2.Add product information                     ||");
            System.out.println("||                3.Modify product information                  ||");
            System.out.println("||                4.Delete product information                  ||");
            System.out.println("||                5.Query product information                   ||");
            System.out.println("||                6.get back                                    ||");
            System.out.println("==================================================================");
            System.out.println("==================================================================");

            //获取用户输入的选择
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            //根据用户输入的不同选择进入不同分支，调用不同的函数
            System.out.println();
            switch (choice){
                case "1":
                    CommodityManagement.ListAll();
                    System.out.print("Press <Enter> to return...");
                    scanner.nextLine();
                    break;
                case "2":
                    CommodityManagement.Add();
                    break;
                case "3":
                    CommodityManagement.modify();
                    break;
                case "4":
                    CommodityManagement.delete();
                    break;
                case "5":
                    CommodityManagement.Query();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            System.out.println();
        }
    }
}