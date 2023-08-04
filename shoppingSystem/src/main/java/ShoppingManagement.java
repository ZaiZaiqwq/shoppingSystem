import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

//购物管理类
public class ShoppingManagement {
    //添加商品到购物车函数
    //输入：进行相关操作的客户
    public static void AddToCart(Customer customer){
        String id,line,amount;
        String[] parts;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------Gene Knee Tie May Shopping system>Add To Cart------------------------------------------------");

        //展示商品列表
        if (!ListAll()){
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            return;
        }

        //获取用户输入的id
        System.out.print("Product ID: ");
        id=scanner.nextLine();

        //读取购物车中的商品信息，判断购物车中是否已经存在该商品，若已存在则打印输出提示用户并返回
        try (BufferedReader reader = new BufferedReader(new FileReader(customer.UserName+"Cart.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                if (parts[0].equals(id)){
                    System.out.println("Already in the shopping cart. Failed to add to cart.");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                    return;
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the cart data file. Failed to add to cart.");
            e.printStackTrace();
            return;
        }

        //若购物车中不存在该商品，则继续添加操作，读取所有商品信息
        try (BufferedReader reader = new BufferedReader(new FileReader("commodityInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                //如果找到该商品，则获取用户输入的数量并将相关信息写入购物车文件中
                if (parts[0].equals(id)){
                    //获取用户输入的数量并判断其是否合法
                    System.out.print("amount ("+parts[7]+" remaining): ");
                    amount=scanner.nextLine();
                    if (!amount.matches("^[0-9]+$")){
                        System.out.println("Invalid input. Failed to add to cart.");
                        return;
                    }
                    if (Integer.parseInt(amount)>Integer.parseInt(parts[7])){
                        System.out.println("Greater than remaining. Failed to add to cart.");
                        return;
                    }

                    //输入的数量合法，将相关信息写入购物车文件
                    try(FileWriter writer = new FileWriter(customer.UserName+"Cart.txt",true)){
                        writer.write(id+" "+parts[1]+" "+amount+"\n");
                    }catch (IOException e){
                        System.out.println("An error occurred while writing commodity data to file. Failed to add to cart.");
                        e.printStackTrace();
                        return;
                    }

                    System.out.println("Add to cart successfully.");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                    return;
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the commodity data file. Failed to add to cart.");
            e.printStackTrace();
            return;
        }

        //对比完所有商品仍未找到，则该商品不存在，打印输出提示用户并返回
        System.out.println("The item does not exist. Failed to add to cart. Check if the id is incorrect.");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //将商品移出购物车函数
    //输入：进行相关操作的客户
    public static void RemoveFromCart(Customer customer){
        String id,line,choice;
        String[] parts;
        boolean isFound=false;
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------Gene Knee Tie May Shopping system>Remove From Cart----------------");

        //获取用户输入的id
        System.out.print("Product ID: ");
        id=scanner.nextLine();

        //读取购物车文件中所有的商品信息，若不匹配则将其记录
        try (BufferedReader reader = new BufferedReader(new FileReader(customer.UserName+"Cart.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                if (parts[0].equals(id)){
                    isFound=true;
                }else {
                    lines.add(line);
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the cart data file. Failed to remove from cart.");
            e.printStackTrace();
            return;
        }

        //如果没找到该商品，则打印输出提示用户并返回
        if (!isFound){
            System.out.println("The item is not in the shopping cart. Failed to remove from cart. Check if the id is incorrect.");
            return;
        }

        //如果找到该商品，则询问是否继续删除操作
        System.out.println("The deletion can not be restored. '1' for going on and '0' for cancel.");
        System.out.print("Your choice: ");
        choice=scanner.nextLine();

        //若继续删除操作，则将不包含删除信息的记录信息重新写入文件，否则提示取消删除操作后返回
        if (choice.equals("1")){
            //将不包含删除信息的记录信息重新写入文件
            try(FileWriter writer = new FileWriter(customer.UserName+"Cart.txt",false)){
                for (String info:lines) {
                    writer.write(info+"\n");
                }
            }catch (IOException e){
                System.out.println("An error occurred while writing commodity data to file. Failed to remove from cart.");
                e.printStackTrace();
                return;
            }

            System.out.println("Removed from cart successfully.");
        }else {
            System.out.println("The deletion operation has been cancelled.");
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    //修改商品购买数量函数
    //输入：进行相关操作的客户
    public static void ModifyItemPurchaseQuantity(Customer customer){
        String id,line,newQuantity,remainingQuantity="",name="";
        String[] parts;
        boolean cartIsFound=false,amountIsFound=false;
        HashMap<String,String> temp = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------Gene Knee Tie May Shopping system>Modify Cart----------------");

        //获取用户输入的id
        System.out.print("Please input the id of the item you want to modify the purchase quantity:");
        id=scanner.nextLine();

        //读取购物车中的商品信息并进行一一比对和记录，判断购物车中是否存在该商品
        try (BufferedReader reader = new BufferedReader(new FileReader(customer.UserName+"Cart.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                temp.put(parts[0]+" "+parts[1],parts[2]);
                if (parts[0].equals(id)){
                    cartIsFound=true;
                    name=parts[1];
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the cart data file. The modification failed");
            e.printStackTrace();
            return;
        }

        //如果未在购物车中找到该商品，则打印输出提示用户后返回
        if (!cartIsFound){
            System.out.println("The item does not exist. The modification failed. Please check that the id you entered is correct.");
            return;
        }

        //若购物车中该商品存在，则继续修改程序，读取商品信息文件，判断其是否仍然存在，若存在则记录其当前数量
        try (BufferedReader reader = new BufferedReader(new FileReader("commodityInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                if (parts[0].equals(id)){
                    remainingQuantity=parts[7];
                    amountIsFound=true;
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the commodity data file. The modification failed.");
            e.printStackTrace();
            return;
        }

        //若商品已不存在，打印输出提示用户并将其从购物车中去掉
        if (!amountIsFound){
            System.out.println("The product has been removed from the shelves. It has been removed from your shopping cart.");
            temp.remove(id+" "+name);
        }else {
            //若商品存在则获取用户输入的修改后的数量并判断其是否合法
            System.out.print("new amount ("+remainingQuantity+" remaining): ");
            newQuantity=scanner.nextLine();
            if (!newQuantity.matches("^[0-9]+$")){
                System.out.println("Invalid input. The modification failed.");
                return;
            }
            if (Integer.parseInt(newQuantity)>Integer.parseInt(remainingQuantity)){
                System.out.println("Greater than remaining. The modification failed.");
                return;
            }
            //如果数量小于或等于0，则将其从购物车中清除
            if (Integer.parseInt(newQuantity)<=0){
                temp.remove(id+" "+name);
            }else {
                //数量大于0，正常修改记录中的数据
                temp.replace(id+" "+name,newQuantity);
            }
        }

        //将修改的数据写回文件
        try(FileWriter writer = new FileWriter(customer.UserName+"Cart.txt",false)){
            temp.forEach((ID,info) -> {
                try {
                    writer.write(ID+" "+info + "\n");
                } catch (IOException e) {
                    System.out.println("An error occurred while writing commodity data to file. The modification failed.");
                    e.printStackTrace();
                }
            });
        }catch (IOException e){
            System.out.println("An error occurred while writing commodity data to file. The modification failed.");
            e.printStackTrace();
        }

        System.out.println("The modification was successful.");
        System.out.println("------------------------------------------------------------------------------");
    }

    //支付函数
    //输入：进行相关操作的客户
    public static void Pay(Customer customer){
        String line,choice,password;
        String[] parts;
        double sum=0;
        boolean isFound;
        int index=0;
        ArrayList<Commodity> commodities = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
        Date date = new Date();
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------Gene Knee Tie May Shopping system>Pay----------------");

        //读取并记录全部商品信息
        try (BufferedReader reader = new BufferedReader(new FileReader("commodityInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                Commodity commodity = new Commodity(parts[0],parts[1],parts[2],parts[3],parts[4],Double.parseDouble(parts[5]),Double.parseDouble(parts[6]),Integer.parseInt(parts[7]),Integer.parseInt(parts[8]));
                commodities.add(commodity);
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the commodity data file. The payment failed.");
            e.printStackTrace();
            return;
        }

        //读取购物车中全部商品信息
        try (BufferedReader reader = new BufferedReader(new FileReader(customer.UserName+"Cart.txt"))){
            //如果购物车为空，打印输出提示用户并返回
            if ((line = reader.readLine()) == null){
                System.out.println("Your shopping cart is empty. Payment failed.");
                System.out.println("------------------------------------------------------------------------------");
                return;
            }
            //如果购物车不为空，判断商品是否仍然有效
            do {
                parts = line.split(" ");
                isFound=false;
                //遍历全部商品
                for (Commodity commodity : commodities){
                    //如果找到该商品，则记录下其在动态数组中的下标
                    if (commodity.ID.equals(parts[0])){
                        isFound=true;
                        index=commodities.indexOf(commodity);
                        break;
                    }
                }
                //如果找到该商品则判断购物车中的数量是否大于存货
                if (isFound){
                    //如果购物车中的数量是否大于存货，打印输出提示用户并返回
                    if (Integer.parseInt(parts[2])>commodities.get(index).amount){
                        System.out.println("More items in the shopping cart than in stock. Payment failed. Modify and try again.");
                        System.out.println("----------------------------------------------------------------------------------------");
                        return;
                    }else {
                        //如果购物车中的数量不大于存货，则总金额加上该商品的单价*购买数量，修改商品的剩余数量，记录购买信息
                        sum+=commodities.get(index).retailPrice*Integer.parseInt(parts[2]);
                        commodities.get(index).amount-=Integer.parseInt(parts[2]);
                        commodities.get(index).Sales+=Integer.parseInt(parts[2]);
                        list.add(ft.format(date)+" "+parts[0]+" "+commodities.get(index).name+" "+commodities.get(index).manufacturer+" "+commodities.get(index).manufactureDate+" "+commodities.get(index).model+" "+commodities.get(index).retailPrice+" "+parts[2]);
                    }
                }else {
                    //如果未找到该商品，打印输出提示用户并返回
                    System.out.println("An invalid item exists in the shopping cart. Payment failed. Remove it and try again.");
                    System.out.println("----------------------------------------------------------------------------------------");
                    return;
                }
            } while ((line = reader.readLine()) != null);
        }catch (IOException e){
            System.out.println("An error occurred while reading the cart data file. The payment failed.");
            e.printStackTrace();
            return;
        }

        //打印支付界面及相关选择
        System.out.println("----------Gene Knee Tie May Shopping system>Mode Of Payment----------");
        System.out.println("Total price: "+sum+" yuan.");
        System.out.println();
        System.out.println("=============================================================");
        System.out.println("=============================================================");
        System.out.println("||    Gene Knee Tie May Shopping system>Mode Of Payment    ||");
        System.out.println("||                      1.Alipay                           ||");
        System.out.println("||                      2.WeChat Pay                       ||");
        System.out.println("||                      3.bank card                        ||");
        System.out.println("||                      4.Cancel payment                   ||");
        System.out.println("=============================================================");
        System.out.println("=============================================================");

        //获取用户输入的选择
        System.out.print("Your choice: ");
        choice = scanner.nextLine();

        //根据用户输入的不同选择调用不同的支付方式
        switch (choice){
            case "1":
                System.out.print("Please input your Alipay payment password: ");
                break;
            case "2":
                System.out.print("Please input your wechat Pay password: ");
                break;
            case "3":
                System.out.print("Please input your bank card password: ");
                break;
            case "4":
                System.out.println("The payment has been cancelled.");
                System.out.println("------------------------------------------------------------------------------");
                return;
            default:
                System.out.println("Invalid input. The payment failed.");
                System.out.println("------------------------------------------------------------------------------");
                return;
        }

        //获取用户输入的密码
        password=scanner.nextLine();

        //读取并记录所有用户信息并对支付用户的信息进行修改
        try (BufferedReader reader = new BufferedReader(new FileReader("customerInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                Date registrationDate = new Date(Long.parseLong(parts[0]));
                //若为支付用户，读取其信息并修改累计消费金额后记录
                if (parts[1].equals(customer.UserName)){
                    Customer cus = new Customer(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],registrationDate,Double.parseDouble(parts[6])+sum,Integer.parseInt(parts[7]));
                    customers.add(cus);
                }else {
                    //若不为支付用户，则直接记录其信息
                    Customer cus = new Customer(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],registrationDate,Double.parseDouble(parts[6]),Integer.parseInt(parts[7]));
                    customers.add(cus);
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the customer data file. The payment failed.");
            e.printStackTrace();
            return;
        }

        //将修改后的客户信息重新写入文件
        try(FileWriter writer = new FileWriter("customerInformation.txt",false)){
            for (Customer cus:customers){
                writer.write(cus.ID+" "+cus.UserName+" "+cus.Password+" "+cus.Email+" "+cus.PhoneNum+" "+cus.Level+" "+cus.Consumption+" "+cus.LogInAttempts+"\n");
            }
        }catch (IOException e){
            System.out.println("An error occurred while writing customer data to file. The payment failed.");
            e.printStackTrace();
            return;
        }

        //将修改后的商品信息重新写入文件
        try(FileWriter writer = new FileWriter("commodityInformation.txt",false)){
            for (Commodity commodity:commodities){
                writer.write(commodity.ID+" "+commodity.name+" "+commodity.manufacturer+" "+commodity.manufactureDate+" "+commodity.model+" "+commodity.purchasingCost+" "+commodity.retailPrice+" "+commodity.amount+" "+commodity.Sales+"\n");
            }
        }catch (IOException e){
            System.out.println("An error occurred while writing commodity data to file. The payment failed.");
            e.printStackTrace();
            return;
        }

        //将新的购物记录写入购物历史文件
        try(FileWriter writer = new FileWriter(customer.UserName+"ShoppingHistory.txt",true)){
            for (String information:list) {
                writer.write(information+"\n");
            }
        }catch (IOException e){
            System.out.println("An error occurred while writing shopping history to file. The payment failed.");
            e.printStackTrace();
            return;
        }

        //清空购物车文件
        try(FileWriter writer = new FileWriter(customer.UserName+"Cart.txt",false)){
            writer.write("");
        }catch (IOException e){
            System.out.println("An error occurred while Emptying the cart. The payment failed.");
            e.printStackTrace();
            return;
        }

        System.out.println("The payment was successful.");
        System.out.println("------------------------------------------------------------------------------");
    }

    //展示购物车函数
    //输入：进行相关操作的客户
    public static void ShowCart(Customer customer){
        String line;
        String[] parts;
        boolean isFound;
        double price=0.0;
        int remaining=0;
        HashMap<String,String> temp = new HashMap<>();

        //读取并记录所有商品信息
        try (BufferedReader reader = new BufferedReader(new FileReader("commodityInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                temp.put(parts[0],parts[6]+" "+parts[7]);
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the cart data file. Shopping cart loading failed.");
            e.printStackTrace();
            return;
        }

        System.out.println("---------------------------Your shopping cart----------------------------");
        //读取全部购物车中的商品信息
        try (BufferedReader reader = new BufferedReader(new FileReader(customer.UserName+"Cart.txt"))){
            //如果购物车为空，则输出打印提示用户
            if ((line = reader.readLine()) == null){
                System.out.println("Empty.");
                System.out.println("Why don't you go see what you want?");
            }else {
                //购物车非空，打印输出购物车中商品的信息
                do {
                    parts = line.split(" ");
                    isFound=false;
                    //遍历所有商品
                    for (String product : temp.keySet()){
                        //若找到该商品，则记录其剩余数量和价格
                        if (product.equals(parts[0])){
                            String[] portion=temp.get(product).split(" ");
                            isFound=true;
                            price=Double.parseDouble(portion[0]);
                            remaining=Integer.parseInt(portion[1]);
                            break;
                        }
                    }
                    //若找到该商品，则判断购物车中的数量是否大于剩余数量
                    if (isFound){
                        //若大于，则打印输出相关提示提醒用户
                        if (Integer.parseInt(parts[2])>remaining){
                            System.out.println("id: "+parts[0]+"\tproduct name: "+parts[1]+"\tamount: "+parts[2]+"(Greater than remaining. Please modify.)\tunit price: "+price);
                        }else {
                            //若不大于，则正常打印显示商品信息
                            System.out.println("id: "+parts[0]+"\tproduct name: "+parts[1]+"\tamount: "+parts[2]+"\tunit price: "+price);
                        }
                    }else {
                        //若未找到该商品，则打印输出相关提示提醒用户
                        System.out.println("id: "+parts[0]+"\tproduct name: "+parts[1]+"(Has been removed from sale. Remove it from the cart.)\tamount: "+parts[2]);
                    }
                }while ((line = reader.readLine()) != null);
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading user data. Shopping cart loading failed.");
            e.printStackTrace();
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    //查看购物历史函数
    //输入：进行相关操作的客户
    public static void ShoppingHistory(Customer customer){
        String line;
        String[] parts;
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------Gene Knee Tie May Shopping system>Shopping History-------------------------------------------------------");

        //购物历史文件中的数据
        try (BufferedReader reader = new BufferedReader(new FileReader(customer.UserName+"ShoppingHistory.txt"))){
            //若购物历史为空，打印输出提示用户；若非空，则打印输出全部购物历史信息
            if ((line = reader.readLine()) == null){
                System.out.println("Empty.");
                System.out.println("Why don't you go see what you want?");
            }else {
                do {
                    parts = line.split(" ");
                    System.out.println("time: "+parts[0]+" "+parts[1]+"\tid: "+parts[2]+"\tname: "+parts[3]+"\tmanufacturer: "+parts[4]+"\tdate of manufacture: "+parts[5]+"\tmodel: "+parts[6]+"\tunit price: "+parts[7]+"\tamount: "+parts[8]);
                }while ((line = reader.readLine()) != null);
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the shopping history data file. Presentation failed");
            e.printStackTrace();
            return;
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("Press <Enter> to return...");
        scanner.nextLine();
    }

    //查看热销商品函数
    //输入：进行相关操作的客户
    public static void hotProducts(){
        final int hotStandard=1000;
        String line;
        String[] parts;
        boolean isEmpty=true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------Gene Knee Tie May Shopping system>Hot Product------------------------------------------------");

        //读取所有商品信息，将销售量高于预定值的商品的信息打印输出
        try (BufferedReader reader = new BufferedReader(new FileReader("commodityInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                if (Integer.parseInt(parts[8])>=hotStandard){
                    isEmpty=false;
                    System.out.println("id:"+parts[0]+"\tname:"+parts[1]+"\tmanufacturer:"+parts[2]+"\tdate of manufacture:"+parts[3]+"\tmodel:"+parts[4]+"\tretail price:"+parts[6]+"\tamount:"+parts[7]+"\tsales:"+parts[8]);
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the commodity data file. Presentation failed.");
            e.printStackTrace();
            return;
        }

        //如果无热销商品，打印输出提示用户
        if (isEmpty){
            System.out.println("There are no hot items at the moment.");
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("Press <Enter> to return...");
        scanner.nextLine();
    }

    //展示所有商品信息函数（客户可见版）
    private static boolean ListAll(){
        String line;
        String[] parts;
        System.out.println("------------------------------------------------Gene Knee Tie May Shopping system>All Product------------------------------------------------");

        //读取并打印输出所有商品信息
        try (BufferedReader reader = new BufferedReader(new FileReader("commodityInformation.txt"))){
            if ((line = reader.readLine()) == null){
                System.out.println("I'm terribly sorry. No goods available.");
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                return false;
            }
            do {
                parts = line.split(" ");
                System.out.println("id:"+parts[0]+"\tname:"+parts[1]+"\tmanufacturer:"+parts[2]+"\tdate of manufacture:"+parts[3]+"\tmodel:"+parts[4]+"\tretail price:"+parts[6]+"\tamount:"+parts[7]+"\tsales:"+parts[8]);
            }while ((line = reader.readLine()) != null);
        }catch (IOException e){
            System.out.println("An error occurred while reading the commodity data file. Presentation failed.");
            e.printStackTrace();
            return false;
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        return true;
    }
}