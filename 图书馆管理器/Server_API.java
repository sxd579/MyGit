import sun.reflect.generics.tree.Tree;

import javax.lang.model.element.NestingKind;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

public class Server_API {
    static InputStreamReader inr;
    static BufferedReader bfr;
    static OutputStreamWriter ouw;
    static BufferedWriter bfw;
    //注册方法
    public static String Register  (String name,String cardNumber,String psw){
        String account = "";
           try {
               File fileAccount = new File(Config.accountsPath);
               if (fileAccount.isFile()&&fileAccount.exists()){

                   //读取账号表
                   inr =  new InputStreamReader(new FileInputStream(fileAccount),"gbk");
                   bfr = new BufferedReader(inr);
                   String inputText ="";
                   while ((inputText=bfr.readLine())!=null){
                       account = inputText;
                       System.out.println(account);
                   }

                   //注册写入新的账号
                   account =""+(Integer.parseInt(account)+1);
                   ouw = new OutputStreamWriter(new FileOutputStream(fileAccount,true));
                   bfw = new BufferedWriter(ouw);
                   bfw.write(account+"\r\n"); // \r\n即为换行
                   bfw.flush();//写入文件

                   //创建新账户的文件夹
                   String newAccountInfoPath = Config.userinfoPath+account;
                   File fileCreateFolder = new File(newAccountInfoPath);
                   fileCreateFolder.mkdir();

                   //写入姓名,校园卡号,密码
                   File fileCreateName = new File(newAccountInfoPath+"/Name.txt");
                   ouw = new OutputStreamWriter(new FileOutputStream(fileCreateName));
                   bfw = new BufferedWriter(ouw);
                   bfw.write(name);
                   bfw.flush();
                   File fileCreatePassword = new File(newAccountInfoPath+"/Password.txt");
                   ouw = new OutputStreamWriter(new FileOutputStream(fileCreatePassword));
                   bfw = new BufferedWriter(ouw);
                   bfw.write(psw);
                   bfw.flush();
                   File fileCreateCardNumber = new File(newAccountInfoPath+"/CardNumber.txt");
                   ouw = new OutputStreamWriter(new FileOutputStream(fileCreateCardNumber));
                   bfw = new BufferedWriter(ouw);
                   bfw.write(cardNumber);
                   bfw.flush();
                   File fileCreateBooksBorrowed = new File(newAccountInfoPath+"/booksBorrowed.txt");
                   ouw = new OutputStreamWriter(new FileOutputStream(fileCreateBooksBorrowed));
                   bfw = new BufferedWriter(ouw);
                   bfw.write("");
                   bfw.flush();
                   File fileCreateBooksBorrowedTime = new File(newAccountInfoPath+"/booksBorrowedTime.txt");
                   ouw = new OutputStreamWriter(new FileOutputStream(fileCreateBooksBorrowedTime));
                   bfw = new BufferedWriter(ouw);
                   bfw.write("");
                   bfw.flush();
                   File fileCreateBooksReturnedTime = new File(newAccountInfoPath+"/booksReturnedTime.txt");
                   ouw = new OutputStreamWriter(new FileOutputStream(fileCreateBooksReturnedTime));
                   bfw = new BufferedWriter(ouw);
                   bfw.write("");
                   bfw.flush();


                   return account;
               }
               else {
                   System.out.println("找不到指定文件");
               }

           }catch (Exception e){
               System.out.println("读取文件内容出错");
               e.printStackTrace();

           }
           finally {
               System.out.println("新注册的账户为"+account);
               closeAllStream();
           }
           return null;
    }

    //登入
    public static int Enter(String user,String psw){
        boolean userExist = false;
        boolean isMaster = false;
        try {
//            System.out.println(user);
            if (user.equals("10000")){isMaster = true;}
//            System.out.println(isMaster);
            File fileAccount = new File(Config.accountsPath);

            //读取账号表
            InputStreamReader inr =  new InputStreamReader(new FileInputStream(fileAccount),"gbk");
            BufferedReader bfr = new BufferedReader(inr);
            String inputText ="";
            while ((inputText=bfr.readLine())!=null){
               // System.out.println(user);
               // System.out.println(inputText);
                if (user.equals(inputText)){
                    userExist = true;
                }
//                System.out.println(userExist);
            }
            if (userExist){
                File filePsw = new File(Config.userinfoPath+user+"/Password.txt");
                InputStreamReader inr_Y  =  new InputStreamReader(new FileInputStream(filePsw),"gbk");
                BufferedReader bfr_Y = new BufferedReader(inr_Y);
                if (psw.equals(bfr_Y.readLine())) {
                    if (isMaster) {
                        return 1;
                    }
                    else {
                        return 2;
                    }
                }
                else {
//                    System.out.println(2);
                    return  0;}


            }
            else {
//                System.out.println(1);
                return  0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            closeAllStream();
        }

        return 0;
    }

    //查询用户名字
    public static String query_user_name(String user){
        String name = "";
        try {
            File fileName = new File(Config.userinfoPath+user+"/Name.txt");
            InputStreamReader inr  =  new InputStreamReader(new FileInputStream(fileName),"gbk");
            BufferedReader bfr = new BufferedReader(inr);
            name =bfr.readLine();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAllStream();
        }


        return name;
    }

    //查询校园卡号
    public static String query_user_cardNumber(String user){
        String cardNumber = "";
        try {
            File fileName = new File(Config.userinfoPath+user+"/CardNumber.txt");
            InputStreamReader inr  =  new InputStreamReader(new FileInputStream(fileName));
            BufferedReader bfr = new BufferedReader(inr);
            cardNumber =bfr.readLine();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAllStream();
        }


        return cardNumber;
    }
   //借书
    public static boolean borrowBook(String user,Node_Bookinfo node_bookinfo){
        try {
                ouw = new OutputStreamWriter(new FileOutputStream(Config.userinfoPath + user + "/booksBorrowed.txt", true));
                bfw = new BufferedWriter(ouw);
                bfw.write(node_bookinfo.outputUser()+"-"+getDate_Now()+"-"+"未归还"+"\r\n");
                bfw.flush();

                ouw = new OutputStreamWriter(new FileOutputStream(Config.brLogPath, true));
                bfw =new BufferedWriter(ouw);
                bfw.write(user+"-"+node_bookinfo.outputUser()+"-"+"借书:"+getDate_Now()+"\r\n");
                bfw.flush();

               return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            closeAllStream();
        }
    }

    //还书
    public  static  boolean  returnBook(String user,Node_Bookinfo node_bookinfo){
        try {
            inr = new InputStreamReader(new FileInputStream(Config.userinfoPath +user+"/booksBorrowed.txt"));
            bfr = new BufferedReader(inr);
            String inputText ="";
            String content = "";
            boolean returned = false;
            while ((inputText=bfr.readLine())!=null){
                String []info = inputText.split("-");
                System.out.println(Arrays.toString(info));
                System.out.println(inputText);
                System.out.println(info[0]);
                System.out.println(String.valueOf(node_bookinfo.getId()));
                System.out.println(Integer.parseInt(String.valueOf(info[0].charAt(info[0].length()-1)))==node_bookinfo.getId());
                System.out.println(info[4].equals(String.valueOf("未归还")));
                System.out.println(returned);
                if (Integer.parseInt(String.valueOf(info[0].charAt(info[0].length()-1)))==node_bookinfo.getId()&&info[4].equals(String.valueOf("未归还"))&&returned == false){
                    returned = true;
                    content += node_bookinfo.outputUser()+"-"+info[3]+"-"+getDate_Now()+"\r\n" ;
                }
                else {
                    content += inputText + "\r\n";
                }
            }
            if (!returned){
                return false;
            }
            ouw = new OutputStreamWriter(new FileOutputStream(Config.userinfoPath + user + "/booksBorrowed.txt"));
            bfw = new BufferedWriter(ouw);
            bfw.write(content);
            bfw.flush();

            ouw = new OutputStreamWriter(new FileOutputStream(Config.brLogPath, true));
            bfw =new BufferedWriter(ouw);
            bfw.write(user+"-"+node_bookinfo.outputUser()+"-"+"还书:"+getDate_Now()+"\r\n");
            bfw.flush();

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            closeAllStream();
        }

    }


    ////查询服务端日志
    public static ArrayList<String> get_Log_Server (){
        try {
            ArrayList <String> Logs = new ArrayList <String>();
            inr = new InputStreamReader(new FileInputStream(Config.brLogPath));
            bfr = new BufferedReader(inr);
            String  inpuText = null;
            while ((inpuText=bfr.readLine())!=null){
                Logs.add(inpuText);
            }
            return Logs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    ///查询用户端日志
    public static ArrayList<String> get_Log_Client (String user){
        try {
            ArrayList <String> Logs = new ArrayList <String>();
            inr = new InputStreamReader(new FileInputStream(Config.userinfoPath+user+"/booksBorrowed.txt"));
            bfr = new BufferedReader(inr);
            String  inpuText = null;
            while ((inpuText=bfr.readLine())!=null){
                Logs.add(inpuText);
            }
            return Logs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }









    //初始化BookTree
    public static Tree_Bookinfo initialize_BooksTree(){
        Tree_Bookinfo tree_bookinfo = new Tree_Bookinfo();
        int num = 0;
        try {
            File fileBooks = new File(Config.booksPath);
            if (fileBooks.isFile()&&fileBooks.exists()) {

                //读取账号表
                inr = new InputStreamReader(new FileInputStream(fileBooks),"gbk");
                bfr = new BufferedReader(inr);
                String inputText = "";
                int row = 1;
                while ((inputText = bfr.readLine()) != null) {
                    String [] characteristics = inputText.split("-");
                    Node_Bookinfo node_bookinfo  = new Node_Bookinfo(characteristics[0],characteristics[1],characteristics[2],characteristics[3],characteristics[4],row);
                    if (num == 0){
                        tree_bookinfo.setRoot(node_bookinfo);
                        num++;
                        System.out.println("添加成功");
                        row++;
                }
                    else{
                      if (tree_bookinfo.addNode(node_bookinfo,tree_bookinfo.getRoot())){
                          System.out.println("添加成功");
                          num++;
                          row++;
                      }
                      else {
                          System.out.println("添加失败");
                      };

                    }
                }
                tree_bookinfo.setSize(num);

            }
            else {
                System.out.println("文件不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            closeAllStream();
        }
                return tree_bookinfo;
    }


    //修改书籍数据
    public static boolean changeBookInfo(int rows,String info,int size){
        try {
            File fileBooks = new File(Config.booksPath);
//            System.out.println(1);
            if (fileBooks.exists()&&fileBooks.isFile())
            {
//                System.out.println(0);
                inr = new InputStreamReader(new FileInputStream(fileBooks),"gbk");
                bfr = new BufferedReader(inr);
                String readLine;
                String content="";
                boolean add = rows>size;
                while ((readLine = bfr.readLine())!=null){
                    rows--;
                    if (rows==0){
                        content+=info+"\r\n";
                        System.out.println("现存量修改成功");
//                        System.out.println(info);
                    }
                    else {
                        content+=readLine+"\r\n";
//                        System.out.println(readLine);
                    }
                }
                if (add){
                    content+=info+"\r\n";
                }
                ouw = new OutputStreamWriter(new FileOutputStream(fileBooks),"gbk");
                bfw = new BufferedWriter(ouw);
//                System.out.println(content);
                ouw.write(content);
                ouw.flush();

            }
            else {
                System.out.println("找不到文件");
            }

        }catch (Exception e) {

        }finally {
                   closeAllStream();
        }

        return  false;
    };

    //重排书库
    public  static  void resortBooks(String newBooks){
        try {
            ouw = new OutputStreamWriter(new FileOutputStream(Config.booksPath),"gbk");
            bfw = new BufferedWriter(ouw);
            ouw.write(newBooks);
//            ouw.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAllStream();
        }
    }


///关闭所有流
    public static void closeAllStream()  {
            try {
               if (ouw!=null)ouw.close();
               if (bfw!=null)bfw.close(); // 最后记得关闭文件
               if (inr!=null)inr.close();
               if (bfr!=null)bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//获取当前时间
    public static String getDate_Now(){
         String date_now = "";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
         date_now=sdf.format(d);
         return date_now;
    }

    public static void main(String[] args) {
        //Register("苏旭东","201700301098","793579");

//        if (Enter("10000","793579")==1){
//            System.out.println("登入成功");
//        }
//        else {
//            System.out.println("账号密码错误");
//        }

//        initialize_BooksTree();
        System.out.println(getDate_Now());
        System.out.print(Charset.defaultCharset());
    }
}
