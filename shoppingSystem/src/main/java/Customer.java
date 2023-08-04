import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

//客户类
public class Customer extends User{
    public String ID;//客户ID
    public String Email;//用户邮箱
    public String PhoneNum;//用户手机号
    public String Level;//用户级别
    public Date RegistrationDate;//用户注册时间
    public double Consumption;//客户累计消费金额
    public int LogInAttempts;

    public Customer(String id,String userName,String password,String email,String phoneNum,String level,Date registrationDate,double consumption,int logInAttempts){
        ID=id;
        UserName=userName;
        Password=password;
        Email=email;
        PhoneNum=phoneNum;
        Level=level;
        RegistrationDate=registrationDate;
        Consumption=consumption;
        LogInAttempts=logInAttempts;
    }

    public Customer(){

    }

    //客户登录函数
    public void logIn(){
        String userName,inputMD5,line;
        String[] parts;
        boolean isAttemptsAlerted=false,isFound=false;
        final int MAX_LOGIN_ATTEMPTS=5;
        ArrayList<String> customerInfo = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------Gene Knee Tie May Shopping system>Customer Login----------");

        //获取用户输入的用户名和密码并将其转换成MD5值
        System.out.print("username: ");
        userName = scanner.nextLine();
        System.out.print("password: ");
        inputMD5=MD5Util.md5(scanner.nextLine());

        //读取文件中的账号密码并进行一一比对
        try (BufferedReader reader = new BufferedReader(new FileReader("customerInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                //判断输入的用户名是否匹配，若匹配则判断密码是否正确，若不匹配则直接记录信息
                if (parts[1].equals(userName)){
                    isFound=true;
                    if (Integer.parseInt(parts[7])>=MAX_LOGIN_ATTEMPTS){
                        System.out.println("The number of password errors reached the upper limit. The account is locked. Login failed.");
                        return;
                    }
                    //判断输入的密码是否匹配，如果匹配成功，则将已尝试输入密码错误次数清零，将该用户的信息赋值并登录到菜单；若匹配不成功则将尝试登录次数+1
                    if (parts[2].equals(inputMD5)){
                        if (Integer.parseInt(parts[7])!=0){
                            isAttemptsAlerted=true;
                        }
                        LogInAttempts=0;
                        ID=parts[0];
                        RegistrationDate=new Date(Long.parseLong(ID));
                        UserName=parts[1];
                        Password=parts[2];
                        Email=parts[3];
                        PhoneNum=parts[4];
                        Level=parts[5];
                        Consumption=Double.parseDouble(parts[6]);
                        System.out.println("Login successfully.");
                        Menu.customerMenu(this);
                        customerInfo.add(parts[0]+" "+parts[1]+" "+parts[2]+" "+parts[3]+" "+parts[4]+" "+parts[5]+" "+parts[6]+" "+LogInAttempts);
                    }else {
                        if (Integer.parseInt(parts[7])+1>=MAX_LOGIN_ATTEMPTS){
                            System.out.println("The number of password errors reached the upper limit. The account is locked.");
                        }else {
                            System.out.println("Username and password do not match. Login failed. You only have "+(MAX_LOGIN_ATTEMPTS-(Integer.parseInt(parts[7])+1))+" chances to try again.");
                        }
                        customerInfo.add(parts[0]+" "+parts[1]+" "+parts[2]+" "+parts[3]+" "+parts[4]+" "+parts[5]+" "+parts[6]+" "+(Integer.parseInt(parts[7])+1));
                        isAttemptsAlerted=true;
                    }
                }else {
                    customerInfo.add(line);
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the customer data file. Login failed.");
            e.printStackTrace();
            return;
        }

        //若遍历完整个文件仍未找到匹配的账号，则用户不存在，打印输出提示用户
        if (!isFound){
            System.out.println("The username does not exist. Check if the username is incorrect.");
        }

        //如果用户信息有改动，则将新的用户信息写入文件
        if(isAttemptsAlerted){
            try(FileWriter writer = new FileWriter("customerInformation.txt",false)){
                for (String customer:customerInfo){
                    writer.write(customer+"\n");
                }
            }catch (IOException e){
                System.out.println("An error occurred while writing customer data to file.");
                e.printStackTrace();
            }
        }
    }

    //客户注册函数
    public void registration(){
        String password;
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------Gene Knee Tie May Shopping system>Customer Registration------------------------------");

        //获取用户输入的用户名并判断是否不少于5个字符，是否包含空格
        System.out.print("username(No less than 5 characters. Cannot contain Spaces.): ");
        UserName = scanner.nextLine();
        if (UserName.length()<5){
            System.out.println("The username is less than 5 characters. Registration failed.");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            return;
        }
        if(UserName.contains(" ")){
            System.out.println("The username contains spaces. Registration failed.");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            return;
        }

        //获取用户输入的密码并判断是否大于8个字符，是否是大小写字母，数字和标点符号的组合，是否包含空格
        System.out.print("password(More than 8 characters and contain uppercase letters, lowercase letters, numbers and punctuation.Cannot contain Spaces.): ");
        password = scanner.nextLine();
        if (password.length()<=8){
            System.out.println("The password is no more than 8 characters. Registration failed.");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            return;
        }
        if(!(password.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\p{Punct}).+"))){
            System.out.println("The password does not meet the format requirements. Registration failed.");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            return;
        }
        if(password.contains(" ")){
            System.out.println("The username contains spaces. Registration failed.");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            return;
        }

        //获取用户输入的邮箱并判断邮箱是否合法
        System.out.print("Email address: ");
        Email = scanner.nextLine();
        if (!registrationUtils.isEmail(Email)){
            System.out.println("The email address is in the wrong format. Registration failed.");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            return;
        }

        //获取用户输入的手机号并判断手机号是否合法
        System.out.print("phone number: ");
        PhoneNum=scanner.nextLine();
        //用户手机号码校验
        if(!Pattern.matches("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$",PhoneNum)){
            //手机号码格式错误
            System.out.println("The phone number is in the wrong format. Registration failed.");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            return;
        }

        //用户输入的信息全部符合要求，注册账户
        //将用户输入的密码转换为MD5值
        Password=MD5Util.md5(password);
        Level="bronze";
        Consumption=0.0;
        LogInAttempts=0;

        //创建保存该客户购物车和购物记录的文件
        File cartFile = new File(UserName+"Cart.txt");
        File shoppingHistoryFile = new File(UserName+"ShoppingHistory.txt");
        if (!(registrationUtils.createFile(cartFile)&&registrationUtils.createFile(shoppingHistoryFile))){
            //创建失败，说明注册的用户名与已有的客户的用户名相同
            System.out.println("The username already exists. Registration failed.");
            return;
        }

        //将新注册的用户信息存入文件中
        RegistrationDate = new Date();
        ID=Long.toString(RegistrationDate.getTime());
        try(FileWriter writer = new FileWriter("customerInformation.txt",true)){
            writer.write(ID+" "+UserName+" "+Password +" "+Email +" "+PhoneNum+" "+Level+" "+Consumption+" "+LogInAttempts+"\n");
        }catch (IOException e){
            System.out.println("An error occurred while writing customer data to file. Registration failed.");
            e.printStackTrace();
            return;
        }
        System.out.println("Registered successfully.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    //客户密码管理显示函数，用于显示密码管理界面及相关功能，根据用户不同的输入调用不同的功能函数
    //输入：进行相关操作的客户
    public void managePassword(Customer customer){
        String choice;
        Scanner scanner = new Scanner(System.in);

        while (true){
            //打印密码管理操作界面及相关功能操作
            System.out.println("================================================================");
            System.out.println("================================================================");
            System.out.println("||    Gene Knee Tie May Shopping system>Password Management   ||");
            System.out.println("||                   1.change password                        ||");
            System.out.println("||                   2.reset password                         ||");
            System.out.println("||                   3.get back                               ||");
            System.out.println("================================================================");
            System.out.println("================================================================");

            //获取用户输入的选择
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            //根据用户输入的不同选择进入不同分支，调用不同的函数
            System.out.println();
            switch (choice){
                case "1":
                    PasswordManagement.changePassword(customer);
                    break;
                case "2":
                    PasswordManagement.resetPassword(customer);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            System.out.println();
        }
    }

    //购物管理显示函数，用于显示购物管理界面及相关功能，根据用户不同的输入调用不同的功能函数
    //输入：进行相关操作的客户
    public void shopping(Customer customer){
        String choice;
        Scanner scanner = new Scanner(System.in);

        while (true){
            //打印购物管理操作界面及相关功能操作
            ShoppingManagement.ShowCart(customer);
            System.out.println("=================================================================");
            System.out.println("=================================================================");
            System.out.println("||    Gene Knee Tie May Shopping system>Shopping Management    ||");
            System.out.println("||            1.Add items to cart                              ||");
            System.out.println("||            2.Remove the item from the cart                  ||");
            System.out.println("||            3.Modify the purchase quantity of the item       ||");
            System.out.println("||            4.Pay                                            ||");
            System.out.println("||            5.View shopping history                          ||");
            System.out.println("||            6.View Hot products                              ||");
            System.out.println("||            7.get back                                       ||");
            System.out.println("=================================================================");
            System.out.println("=================================================================");

            //获取用户输入的选择
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            //根据用户输入的不同选择进入不同分支，调用不同的函数
            System.out.println();
            switch (choice){
                case "1":
                    ShoppingManagement.AddToCart(customer);
                    break;
                case "2":
                    ShoppingManagement.RemoveFromCart(customer);
                    break;
                case "3":
                    ShoppingManagement.ModifyItemPurchaseQuantity(customer);
                    break;
                case "4":
                    ShoppingManagement.Pay(customer);
                    break;
                case "5":
                    ShoppingManagement.ShoppingHistory(customer);
                    break;
                case "6":
                    ShoppingManagement.hotProducts();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            System.out.println();
        }
    }
}