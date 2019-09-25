public class Node_Bookinfo {
     private  int id;
     private  int row;
     private  String name;
     private  String author;
     private  int  booksNum;
     private  int  allBooksNum;
     private  Node_Bookinfo leftNode = null ;
     private  Node_Bookinfo rightNode = null;
     public int getRow() {
        return row;
    }

     public void setRow(int row) {
        this.row = row;
    }
    public Node_Bookinfo getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node_Bookinfo leftNode) {
        this.leftNode = leftNode;
    }

    public Node_Bookinfo getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node_Bookinfo rightNode) {
        this.rightNode = rightNode;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBooksNum() {
        return booksNum;
    }

    public void setBooksNum(int booksNum) {
        this.booksNum = booksNum;
    }

    public int getAllBooksNum() {
        return allBooksNum;
    }

    public void setAllBooksNum(int allBooksNum) {
        this.allBooksNum = allBooksNum;
    }

    public Node_Bookinfo(String id,String name,String author,String booksNum,String allBooksNum,int row){
        setId(Integer.parseInt(id));
        setName(name);
        setAuthor(author);
        setBooksNum(Integer.parseInt(booksNum));
        setAllBooksNum(Integer.parseInt(allBooksNum));
        setRow(row);

    }
    public String output(){
        return id+"-"+name+"-"+author+"-"+booksNum+"-"+allBooksNum;
    }
    public String outputUser(){
        return id+"-"+name+"-"+author;
    }


}
