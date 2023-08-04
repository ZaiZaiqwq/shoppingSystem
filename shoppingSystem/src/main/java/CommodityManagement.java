import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//商品管理类
public class CommodityManagement {
    //列出所有商品信息函数（管理员可见版）
    public static void ListAll(){
        String line;
        String[] parts;
        System.out.println("------------------------------------------------Gene Knee Tie May Shopping system>All Product------------------------------------------------");

        //读取并打印输出所有商品信息
        try (BufferedReader reader = new BufferedReader(new FileReader("commodityInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                System.out.println("id:"+parts[0]+"\tname:"+parts[1]+"\tmanufacturer:"+parts[2]+"\tdate of manufacture:"+parts[3]+"\tmodel:"+parts[4]+"\tpurchasing cost:"+parts[5]+"\tretail price:"+parts[6]+"\tamount:"+parts[7]+"\tsales:"+parts[8]);
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the commodity data file. Presentation failed.");
            e.printStackTrace();
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //增加商品函数
    public static void Add(){
        String input;
        Commodity newCommodity = new Commodity();
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------Gene Knee Tie May Shopping system>Add Product----------------");

        //获取用户输入的商品信息并判断是否合法
        System.out.print("ID: ");
        newCommodity.ID=scanner.nextLine();
        System.out.print("name: ");
        newCommodity.name=scanner.nextLine();
        System.out.print("manufacturer: ");
        newCommodity.manufacturer=scanner.nextLine();
        System.out.print("date of manufacture(yyyy.MM.dd): ");
        newCommodity.manufactureDate=scanner.nextLine();
        System.out.print("model: ");
        newCommodity.model=scanner.nextLine();
        System.out.print("purchasing cost: ");
        input=scanner.nextLine();
        while (!input.matches("^[0-9]*\\.?[0-9]+$")){
            System.out.print("Invalid input. Please re-input: ");
            input=scanner.nextLine();
        }
        newCommodity.purchasingCost=Double.parseDouble(input);
        System.out.print("retail price: ");
        input=scanner.nextLine();
        while (!input.matches("^[0-9]*\\.?[0-9]+$")){
            System.out.print("Invalid input. Please re-input: ");
            input=scanner.nextLine();
        }
        newCommodity.retailPrice=Double.parseDouble(input);
        System.out.print("amount: ");
        input=scanner.nextLine();
        while (!input.matches("^[0-9]+$")){
            System.out.print("Invalid input. Please re-input: ");
            input=scanner.nextLine();
        }
        newCommodity.amount=Integer.parseInt(input);
        System.out.print("sales: ");
        input=scanner.nextLine();
        while (!input.matches("^[0-9]+$")){
            System.out.print("Invalid input. Please re-input: ");
            input=scanner.nextLine();
        }
        newCommodity.Sales=Integer.parseInt(input);

        //将新商品的信息写入文件
        try(FileWriter writer = new FileWriter("commodityInformation.txt",true)){
            writer.write(newCommodity.ID+" "+newCommodity.name+" "+newCommodity.manufacturer +" "+newCommodity.manufactureDate +" "+newCommodity.model+" "+newCommodity.purchasingCost+" "+newCommodity.retailPrice+" "+newCommodity.amount+" "+newCommodity.Sales+"\n");
        }catch (IOException e){
            System.out.println("An error occurred while writing commodity data to file. Failed to add item.");
            e.printStackTrace();
            return;
        }

        System.out.println("The product information was successfully added.");
        System.out.println("-----------------------------------------------------------------------------");
    }

    //修改商品信息函数
    public static void modify(){
        String id,line,choice,modify;
        String[] parts;
        boolean isFound=false,isValid=false;
        int index=0;
        ArrayList<Commodity> commodities = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------Gene Knee Tie May Shopping system>Modify Product----------------");

        //获取用户输入的id
        System.out.print("id: ");
        id=scanner.nextLine();

        //读取文件中的商品信息并进行一一比对和记录
        try (BufferedReader reader = new BufferedReader(new FileReader("commodityInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                Commodity commodity = new Commodity(parts[0],parts[1],parts[2],parts[3],parts[4],Double.parseDouble(parts[5]),Double.parseDouble(parts[6]),Integer.parseInt(parts[7]),Integer.parseInt(parts[8]));
                commodities.add(commodity);
                if (parts[0].equals(id)){
                    isFound=true;
                    index=commodities.indexOf(commodity);
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the commodity data file. The modification failed.");
            e.printStackTrace();
            return;
        }

        //如果没有找到该商品，则打印输出提示管理员并返回
        if (!isFound){
            System.out.println("The item does not exist. The modification failed. Please check that the id you entered is correct.");
            return;
        }

        //如果找到该商品，则询问管理员修改商品的哪方面的信息
        while (true){
            //打印商品信息修改界面及其选项
            System.out.println("==========================================================================");
            System.out.println("==========================================================================");
            System.out.println("||    Gene Knee Tie May Shopping system>Modify commodity information    ||");
            System.out.println("||                        1.Modify Name                                 ||");
            System.out.println("||                        2.Modify Manufacturer                         ||");
            System.out.println("||                        3.Modify Date Of Manufacture                  ||");
            System.out.println("||                        4.Modify Model                                ||");
            System.out.println("||                        5.Modify Purchasing Cost                      ||");
            System.out.println("||                        6.Modify Retail Price                         ||");
            System.out.println("||                        7.Modify Amount                               ||");
            System.out.println("||                        8.Modify Sales                                ||");
            System.out.println("||                        9.Save                                        ||");
            System.out.println("||                        10.Get Back                                    ||");
            System.out.println("==========================================================================");
            System.out.println("==========================================================================");

            //获取用户输入的选择
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            //根据用户输入的不同选择进入不同分支，获取用户输入的新信息并进行替换
            switch (choice){
                case "1":
                    System.out.print("new name: ");
                    modify=scanner.nextLine();
                    commodities.get(index).name=modify;
                    isValid=true;
                    break;
                case "2":
                    System.out.print("new manufacturer: ");
                    modify=scanner.nextLine();
                    commodities.get(index).manufacturer=modify;
                    isValid=true;
                    break;
                case "3":
                    System.out.print("new date of manufacture: ");
                    modify=scanner.nextLine();
                    commodities.get(index).manufactureDate=modify;
                    isValid=true;
                    break;
                case "4":
                    System.out.print("new model: ");
                    modify=scanner.nextLine();
                    commodities.get(index).model=modify;
                    isValid=true;
                    break;
                case "5":
                    System.out.print("new purchasing cost: ");
                    modify=scanner.nextLine();
                    if (!String.valueOf(modify).matches("^[0-9]*\\.?[0-9]+$")){
                        System.out.println("Invalid input.");
                        break;
                    }
                    commodities.get(index).purchasingCost=Double.parseDouble(modify);
                    isValid=true;
                    break;
                case "6":
                    System.out.print("new retail price: ");
                    modify=scanner.nextLine();
                    if (!String.valueOf(modify).matches("^[0-9]*\\.?[0-9]+$")){
                        System.out.println("Invalid input.");
                        break;
                    }
                    commodities.get(index).retailPrice=Double.parseDouble(modify);
                    isValid=true;
                    break;
                case "7":
                    System.out.print("new amount: ");
                    modify=scanner.nextLine();
                    if (!String.valueOf(modify).matches("^[0-9]+$")){
                        System.out.println("Invalid input.");
                        break;
                    }
                    commodities.get(index).amount=Integer.parseInt(modify);
                    isValid=true;
                    break;
                case "8":
                    System.out.print("new sales: ");
                    modify=scanner.nextLine();
                    if (!String.valueOf(modify).matches("^[0-9]+$")){
                        System.out.println("Invalid input.");
                        break;
                    }
                    commodities.get(index).Sales=Integer.parseInt(modify);
                    isValid=true;
                    break;
                case "9":
                    //如果没有数据被修改，则直接返回;否则将修改后的数据重新写入再返回
                    if (isValid){
                        try(FileWriter writer = new FileWriter("commodityInformation.txt",false)){
                            for (Commodity commodity:commodities) {
                                writer.write(commodity.ID+" "+commodity.name+" "+commodity.manufacturer+" "+commodity.manufactureDate+" "+commodity.model+" "+commodity.purchasingCost+" "+commodity.retailPrice+" "+commodity.amount+" "+commodity.Sales+"\n");
                            }
                        }catch (IOException e){
                            System.out.println("An error occurred while writing commodity data to file. The modification failed.");
                            e.printStackTrace();
                            return;
                        }
                    }
                    System.out.println("Saved successfully.");
                    System.out.println("-----------------------------------------------------------------------------");
                    return;
                case "10":
                    //询问是否返回
                    System.out.println("Data that is not saved upon return will not be modified. '1' for going on and '0' for cancel.");
                    System.out.print("Your choice: ");
                    choice=scanner.nextLine();
                    if (choice.equals("1")){
                        return;
                    }else {
                        break;
                    }
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }

    //删除商品信息函数
    public static void delete(){
        String id,choice,line;
        String[] parts;
        ArrayList<Commodity> commodities = new ArrayList<>();
        boolean isFound=false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------Gene Knee Tie May Shopping system>Delete Product----------------");

        //获取用户输入的id
        System.out.print("id: ");
        id=scanner.nextLine();

        //读取文件中的商品并进行一一比对,若不为要删除的商品信息则记录保存
        try (BufferedReader reader = new BufferedReader(new FileReader("commodityInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                //若未匹配该商品，则直接记录
                if (parts[0].equals(id)){
                    isFound=true;
                }else {
                    Commodity commodity = new Commodity(parts[0],parts[1],parts[2],parts[3],parts[4],Double.parseDouble(parts[5]),Double.parseDouble(parts[6]),Integer.parseInt(parts[7]),Integer.parseInt(parts[8]));
                    commodities.add(commodity);
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the commodity data file. Delete failed.");
            e.printStackTrace();
            return;
        }

        //若找到该商品，则再次询问后将不包含删除信息的记录信息重新写入文件；若未找到该商品，则输出提示管理员并直接返回
        if (isFound){
            //获取用户输入的选择
            System.out.println("The deletion cannot be restored. '1' for going on and '0' for cancel.");
            System.out.print("Your choice: ");
            choice=scanner.nextLine();

            //若继续删除操作，则将不包含删除信息的记录信息重新写入文件，否则提示取消删除操作后返回
            if (choice.equals("1")){
                //将不包含删除信息的记录信息重新写入文件
                try(FileWriter writer = new FileWriter("commodityInformation.txt",false)){
                    for (Commodity commodity:commodities) {
                        writer.write(commodity.ID+" "+commodity.name+" "+commodity.manufacturer+" "+commodity.manufactureDate+" "+commodity.model+" "+commodity.purchasingCost+" "+commodity.retailPrice+" "+commodity.amount+" "+commodity.Sales+"\n");
                    }
                }catch (IOException e){
                    System.out.println("An error occurred while writing commodity data to file. Delete failed.");
                    e.printStackTrace();
                    return;
                }

                System.out.println("Deleted successfully.");
            }else {
                System.out.println("The deletion operation has been cancelled.");
            }
        }else {
            System.out.println("The item does not exist. Delete failed. Check if the id is incorrect.");
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    //查询商品信息函数
    public static void Query(){
        String line,id;
        String[] parts;
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------Gene Knee Tie May Shopping system>Query Product----------------");

        //获取用书输入的id
        System.out.print("id: ");
        id=scanner.nextLine();

        //读取文件中的商品并进行一一比对
        try (BufferedReader reader = new BufferedReader(new FileReader("commodityInformation.txt"))){
            while ((line = reader.readLine()) != null){
                parts = line.split(" ");
                //若找到该商品，则将其信息输出
                if (parts[0].equals(id)){
                    System.out.println("id:"+parts[0]+"\tname:"+parts[1]+"\tmanufacturer:"+parts[2]+"\tdate of manufacture:"+parts[3]+"\tmodel:"+parts[4]+"\tpurchasing cost:"+parts[5]+"\tretail price:"+parts[6]+"\tamount:"+parts[7]+"\tSales:"+parts[8]);
                    System.out.println("Press <Enter> to return...");
                    scanner.nextLine();
                    System.out.println("-----------------------------------------------------------------------------");
                    return;
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred while reading the commodity data file. Presentation failed.");
            e.printStackTrace();
            return;
        }

        //若对比玩文件中的所有商品后仍未找到与之匹配的，则商品不存在，输出提示管理员后返回
        System.out.println("The item could not be found. Query failed. Check if the id is incorrect.");
        System.out.println("-----------------------------------------------------------------------------");
    }
}
