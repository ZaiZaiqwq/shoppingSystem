import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registrationUtils {
    //判断邮箱是否合法函数
    //输入：邮箱字符串
    //输出：true表示合法，false表示不合法
    public static boolean isEmail(String email) {
        String regEx1 = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern;
        Matcher matcher;

        if (email == null)
            return false;
        pattern = Pattern.compile(regEx1);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //创建文件函数
    //输入：文件名
    //输出：true表示创建成功，false表示创建失败
    public static boolean createFile(File fileName){
        boolean flag=false;

        try{
            //判断文件是否存在，若不存在则创建文件
            if(!fileName.exists()){
                fileName.createNewFile();
                flag=true;
            }
        }catch(Exception e){
            System.out.println("File creation failed.");
            e.printStackTrace();
        }
        return flag;
    }
}
