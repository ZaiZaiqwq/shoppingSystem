import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//客户管理类
public class CustomerManagement {
    //列出所有客户信息函数
    public static void ListAll(){
        String line;
        String[] parts;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------Gene Knee Tie May Shopping system>All Customer------------------------------------------------");

        //读取所有用户信息
        try (BufferedReader reader = new BufferedReader(new FileReader("customerInformation.txt"))){
            if ((line = reader.readLine()) == null){
                System.out.println("No clients at the moment.");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Press <Enter> to return...");
                scanner.nextLine();
                return;
            }
            do {
                parts = line.split(" ");
                Date date =new Date(Long.parseLong(parts[0]));
                System.out.println("id:"+parts[0]+"\tusername:"+parts[1]+"\tEmail:"+parts[3]+"\tphone number:"+parts[4]+"\tlevel:"+parts[5]+"\tregistration date:"+date+"\tconsumption:"+parts[6]+"\tlogIn Attempts:"+parts[7]);
            }while ((line = reader.readLine()) != null);
        }catch (IOException e){
            System.out.println("An error occurred while reading the customer data file. Presentation failed.");
            e.printStackTrace();
            return;
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("Press <Enter> to return...");
        scanner.nextLine();
    }

    //删除客户信息函数
    public static void Delete(){
        String userName,line,choice;
        String[] parts;
        boolean isCustomerFound=false;
        ArrayList<String> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------Gene Knee Tie May Shopping system>Delete Customer-------------------------");

        //获取管理员输入的用户名
        System.out.print("customer username: ");
        userName=scanner.nextLine();

        //读取文件中的账号并进行一一比对,若不为要删除的用户信息则记录保存
        try (BufferedReader reader = new BufferedReader(new FileReader("customerInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                //若未匹配该用户，则直接记录
                if (parts[1].equals(userName)){
                    isCustomerFound=true;
                }else {
                    customers.add(line);
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the customer data file. Deleting the customer failed.");
            e.printStackTrace();
            return;
        }

        //若找到该用户，则再次询问后将不包含删除信息的记录信息重新写入文件；若未找到该用户，则输出提示管理员并直接返回
        if (isCustomerFound){
            //获取用户输入的选择
            System.out.println("The deletion cannot be restored. '1' for going on and '0' for cancel.");
            System.out.print("Your choice: ");
            choice=scanner.nextLine();

            //若继续删除操作，则将不包含删除信息的记录信息重新写入文件，否则提示取消删除操作后返回
            if (choice.equals("1")){
                //将不包含删除信息的记录信息重新写入文件
                try(FileWriter writer = new FileWriter("customerInformation.txt",false)){
                    for (String customer:customers){
                        writer.write(customer+"\n");
                    }
                }catch (IOException e){
                    System.out.println("An error occurred while writing customer data to file. Deleting the customer failed");
                    e.printStackTrace();
                    return;
                }

                //删除账户相关文件
                if (deleteFile(userName+"Cart.txt")&&deleteFile(userName+"ShoppingHistory.txt")){
                    System.out.println("Deleting the customer succeeded.");
                }else {
                    System.out.println("An error occurred while deleting file. The file does not exist.");
                }
            }else {
                System.out.println("The deletion operation has been cancelled.");
            }
        }else {
            System.out.println("The customer does not exist. Deleting the customer failed. Check if the username is incorrect.");
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    //查询客户信息函数
    public static void QueryCustomerInformation(){
        String choice;
        Scanner scanner = new Scanner(System.in);

        while (true){
            //打印客户信息查询界面及相关功能
            System.out.println("========================================================================");
            System.out.println("========================================================================");
            System.out.println("||    Gene Knee Tie May Shopping system>Query Customer Information    ||");
            System.out.println("||                      1.query by ID                                 ||");
            System.out.println("||                      2.query by username                           ||");
            System.out.println("||                      3.query all customer information              ||");
            System.out.println("||                      4.get back                                    ||");
            System.out.println("========================================================================");
            System.out.println("========================================================================");

            //获取用户输入的选择
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            //根据用户输入的不同选择进入不同分支，调用不同的函数
            System.out.println();
            switch (choice){
                case "1":
                    queryByID();
                    break;
                case "2":
                    queryByUserName();
                    break;
                case "3":
                    ListAll();
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

    //根据ID查询客户函数
    private static void queryByID(){
        String ID,line;
        String[] parts;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------Gene Knee Tie May Shopping system>Query By ID-------------------------");

        //获取用户输入的ID
        System.out.print("Customer ID: ");
        ID=scanner.nextLine();

        //读取文件中的账号并进行一一比对
        try (BufferedReader reader = new BufferedReader(new FileReader("customerInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                //若找到该客户，则将其信息输出
                if (parts[0].equals(ID)){
                    Date date =new Date(Long.parseLong(parts[0]));
                    System.out.println("id: "+parts[0]+"\tusername: "+parts[1]+"\tEmail: "+parts[3]+"\tphone number: "+parts[4]+"\tlevel: "+parts[5]+"\tregistration date: "+date+"\tconsumption: "+parts[6]+"\tlogIn attempts: "+parts[7]);
                    System.out.print("Press <Enter> to return...");
                    scanner.nextLine();
                    System.out.println("--------------------------------------------------------------------------------------------------");
                    return;
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the customer data file. Query failed.");
            e.printStackTrace();
            return;
        }

        //若对比完文件中所有账号仍未找到该用户，则用户不存在，输出提示管理员并返回
        System.out.println("The customer could not be found. Query failed. Check if the ID is incorrect.");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    //根据用户名查询客户函数
    private static void queryByUserName(){
        String userName,line;
        String[] parts;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------Gene Knee Tie May Shopping system>Query By Username-------------------------");

        //获取用户输入的用户名
        System.out.print("customer username: ");
        userName=scanner.nextLine();

        //读取文件中的账号并进行一一比对
        try (BufferedReader reader = new BufferedReader(new FileReader("customerInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                //若找到该客户，则将其信息输出
                if (parts[1].equals(userName)){
                    Date date =new Date(Long.parseLong(parts[0]));
                    System.out.println("id:"+parts[0]+"\tusername:"+parts[1]+"\tEmail:"+parts[3]+"\tphone number:"+parts[4]+"\tlevel:"+parts[5]+"\tregistration date:"+date+"\tconsumption:"+parts[6]+"\tlogIn attempts: "+parts[7]);
                    System.out.print("Press <Enter> to return...");
                    scanner.nextLine();
                    System.out.println("--------------------------------------------------------------------------------------------------");
                    return;
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the customer data file. Query failed.");
            e.printStackTrace();
            return;
        }

        //若对比完文件中所有账号仍未找到该用户，则用户不存在，输出提示管理员并返回
        System.out.println("The customer could not be found. Query failed. Check if the username is incorrect.");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    //删除文件函数
    //输入：filePath-被删除的文件的文件名
    //输出：成功true，否则false
    private static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);

        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }

        return flag;
    }
}
