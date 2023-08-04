import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//密码管理类，用于实现管理员和客户密码管理相关操作
public class PasswordManagement {
    //管理员修改密码函数
    public static void changePassword(){
        String newMD5;
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------Gene Knee Tie May Shopping system>Change Password----------");

        //获取用户输入的新密码并将其转换为MD5值
        System.out.print("new password: ");
        newMD5=MD5Util.md5(scanner.nextLine());

        //将新密码存入文件中保存
        try(FileWriter writer = new FileWriter("adminPassword.txt",false)){
            writer.write(newMD5);
        }catch (IOException e){
            System.out.println("An error occurred while writing new password to file. Password change failed.");
            e.printStackTrace();
            return;
        }
        System.out.println("Password changed successfully.");
        System.out.println("---------------------------------------------------------------------");
    }

    //管理员重置客户密码函数
    public static void resetCustomerPassword(){
        String userName,line,resetPassword="52Cxk!789",resetMD5=MD5Util.md5(resetPassword);
        String[] parts;
        boolean isCustomerFound=false;
        ArrayList<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------Gene Knee Tie May Shopping system>Customer Password Reset------------");

        //获取管理员输入的客户用户名
        System.out.print("customer username: ");
        userName=scanner.nextLine();

        //读取文件中的账号并进行一一比对和记录
        try (BufferedReader reader = new BufferedReader(new FileReader("customerInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                Date date = new Date(Long.parseLong(parts[0]));
                //若匹配到该用户，则将其密码重置并记录，若未匹配该用户，则直接记录
                if (parts[1].equals(userName)){
                    isCustomerFound=true;
                    Customer customer = new Customer(parts[0],parts[1],resetMD5,parts[3],parts[4],parts[5],date,Double.parseDouble(parts[6]),Integer.parseInt(parts[7]));
                    customers.add(customer);
                }else {
                    Customer customer = new Customer(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],date,Double.parseDouble(parts[6]),Integer.parseInt(parts[7]));
                    customers.add(customer);
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the customer data file. Password reset failed");
            e.printStackTrace();
            return;
        }

        //若找到该用户，则将记录的信息重新写入文件；若未找到该用户，则输出提示管理员并直接返回
        if (isCustomerFound){
            try(FileWriter writer = new FileWriter("customerInformation.txt",false)){
                for (Customer customer:customers){
                    writer.write(customer.ID+" "+customer.UserName+" "+customer.Password+" "+customer.Email+" "+customer.PhoneNum+" "+customer.Level+" "+customer.Consumption+" "+customer.LogInAttempts+"\n");
                }
            }catch (IOException e){
                System.out.println("An error occurred while writing customer data to file. Password reset failed.");
                e.printStackTrace();
                return;
            }
            System.out.println("Password reset successfully.");
        }else {
            System.out.println("The customer could not be found. Password reset failed. Check if the username is incorrect.");
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    //客户修改密码函数
    //输入：进行相关操作的客户
    public static void changePassword(Customer customer){
        String line,newPassword,newMD5;
        String[] parts;
        ArrayList<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------Gene Knee Tie May Shopping system>Change Password------------------------------------");

        //获取用户输入的新密码并判断是否大于8个字符，是否是大小写字母，数字和标点符号的组合
        System.out.print("new password(More than 8 characters and contain uppercase letters, lowercase letters, numbers and punctuation): ");
        newPassword=scanner.nextLine();
        if (newPassword.length()<=8){
            System.out.println("The password is no more than 8 characters. Password change failed");
            return;
        }
        if(!(newPassword.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\p{Punct}).+"))){
            System.out.println("The password does not meet the format requirements. Password change failed.");
            return;
        }
        if(newPassword.contains(" ")){
            System.out.println("The username contains spaces. Registration failed.");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            return;
        }

        //密码符合要求，进行修改
        //将用户输入的密码转换成对应的MD5值
        newMD5=MD5Util.md5(newPassword);

        //读取并记录文件中的所有账号信息并进行一一比对和记录
        try (BufferedReader reader = new BufferedReader(new FileReader("customerInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                Date date = new Date(Long.parseLong(parts[0]));
                //若匹配到该用户，则将其密码改为新输入的密码并记录；若未匹配该用户，则直接记录
                if (parts[1].equals(customer.UserName)){
                    customer.Password=newMD5;
                    Customer cus = new Customer(parts[0],parts[1],newMD5,parts[3],parts[4],parts[5],date,Double.parseDouble(parts[6]),Integer.parseInt(parts[7]));
                    customers.add(cus);
                }else {
                    Customer cus = new Customer(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],date,Double.parseDouble(parts[6]),Integer.parseInt(parts[7]));
                    customers.add(cus);
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the customer data file. Password change failed.");
            e.printStackTrace();
            return;
        }

        //将修改后的信息写入文件
        try(FileWriter writer = new FileWriter("customerInformation.txt",false)){
            for (Customer cus:customers){
                writer.write(cus.ID+" "+cus.UserName+" "+cus.Password+" "+cus.Email+" "+cus.PhoneNum+" "+cus.Level+" "+cus.Consumption+" "+customer.LogInAttempts+"\n");
            }
        }catch (IOException e){
            System.out.println("An error occurred while writing customer data to file. Password change failed");
            e.printStackTrace();
            return;
        }

        System.out.println("Password changed successfully.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    }

    //客户重置密码函数
    //输入：进行相关操作的客户
    public static void resetPassword(Customer customer){
        String line,id,email,randomPassword,randomMD5;
        String[] parts;
        int newPasswordLen = 10;//新随机生成密码的长度
        ArrayList<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------Gene Knee Tie May Shopping system>Reset Password--------------------------------");

        //获取用户输入的用户名并判断是否正确
        System.out.print("Your username: ");
        id=scanner.nextLine();
        if (!id.equals(customer.UserName)){
            System.out.println("The user name is incorrect. Password reset failed.");
            return;
        }

        //获取用户输入的邮箱并判断是否正确
        System.out.print("Your email address: ");
        email=scanner.nextLine();
        if (!email.equals(customer.Email)){
            System.out.println("The email address does not match the one entered during registration. Password reset failed.");
            return;
        }

        //输入的用户名和邮箱均正确，重置密码
        System.out.println("Resetting the password...");

        //生成随机密码
        do {
            randomPassword = createRandomPassword(newPasswordLen);
        } while (!(randomPassword.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\p{Punct}).+")));

        //将随机密码转换成对应的MD5值
        randomMD5=MD5Util.md5(randomPassword);

        //读取并记录文件中的所有账号信息
        try (BufferedReader reader = new BufferedReader(new FileReader("customerInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                Date date = new Date(Long.parseLong(parts[0]));
                //若匹配到该用户，则将其密码改为新随机生成的密码并记录；若未匹配该用户，则直接记录
                if (parts[1].equals(customer.UserName)){
                    customer.Password=randomMD5;
                    Customer cus = new Customer(parts[0],parts[1],randomMD5,parts[3],parts[4],parts[5],date,Double.parseDouble(parts[6]),Integer.parseInt(parts[7]));
                    customers.add(cus);
                }else {
                    Customer cus = new Customer(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],date,Double.parseDouble(parts[6]),Integer.parseInt(parts[7]));
                    customers.add(cus);
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the customer data file. Password reset failed.");
            e.printStackTrace();
            return;
        }

        //将修改后的信息写入文件
        try(FileWriter writer = new FileWriter("customerInformation.txt",false)){
            for (Customer cus:customers){
                writer.write(cus.ID+" "+cus.UserName+" "+cus.Password+" "+cus.Email+" "+cus.PhoneNum+" "+cus.Level+" "+cus.Consumption+" "+customer.LogInAttempts+"\n");
            }
        }catch (IOException e){
            System.out.println("An error occurred while writing customer data to file. Password reset failed");
            e.printStackTrace();
            return;
        }

        //将新生成的随机密码以邮件的形式发送给用户
        SendMailUtils.sendEmail(customer.Email,randomPassword);
        System.out.println("Succeeded in resetting the password. The newly generated random password has been sent to your email address.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    }

    //生成随机密码函数
    //输入：len-生成密码的长度
    //输出：字符串形式的随机密码
    private static String createRandomPassword(int len){
        int randNum;
        //密码的取值范围
        String valueRange="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789！@＃$％^＆*（）_+";
        Random random = new Random();
        StringBuilder randomPassword = new StringBuilder();

        //生成随机密码
        for(int i=0;i<len;i++){
            randNum=random.nextInt(valueRange.length());
            randomPassword.append(valueRange.charAt(randNum));
        }

        return randomPassword.toString();
    }
}
