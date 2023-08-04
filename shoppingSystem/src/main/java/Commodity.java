//商品类
public class Commodity {
    public String ID;//商品编号
    public String name;//商品名称
    public String manufacturer;//生产厂家
    public String manufactureDate;//生产日期
    public String model;//型号
    public double purchasingCost;//进货价
    public double retailPrice;//零售价格
    public int amount;//数量
    public int Sales;//销量

    //构造方法
    public Commodity(String Id,String Name,String Manufacturer,String date,String Model,double cost,double price,int Amount,int sales){
        ID=Id;
        name=Name;
        manufacturer=Manufacturer;
        manufactureDate=date;
        model=Model;
        purchasingCost=cost;
        retailPrice=price;
        amount=Amount;
        Sales=sales;
    }

    public Commodity(){

    }
}