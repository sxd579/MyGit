import java.util.Stack;

public class Tree_Bookinfo {
    private int size;
    private Node_Bookinfo root;
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node_Bookinfo getRoot() {
        return root;
    }

    public void setRoot(Node_Bookinfo root) {
        this.root = root;
    }

    public boolean  addNode(Node_Bookinfo node_bookinfo,Node_Bookinfo currentNode){
        if (currentNode.getId()==node_bookinfo.getId()){
            return  false;
        }

        if (currentNode.getLeftNode()==null&&node_bookinfo.getId()<currentNode.getId()){
            currentNode.setLeftNode(node_bookinfo);
        }
        if (currentNode.getRightNode()==null&&node_bookinfo.getId()>currentNode.getId()){
            currentNode.setRightNode(node_bookinfo);

        }
        if (currentNode.getRightNode()!=null||currentNode.getLeftNode()!=null){
            if (node_bookinfo.getId()<currentNode.getId()){
                currentNode = currentNode.getLeftNode();
                addNode(node_bookinfo,currentNode);
            }
            if (node_bookinfo.getId()>currentNode.getId()){
                currentNode = currentNode.getRightNode();
                addNode(node_bookinfo,currentNode);
            }
        }

        return true;

    }


    //排序数组
    public Node_Bookinfo[] initializeTable(){
            int num = 0;
            String resort = "";
            Node_Bookinfo [] sortedArray = new Node_Bookinfo[size];
            Stack<Node_Bookinfo> stackNode = new Stack <Node_Bookinfo>();

            //中序遍历树
            Node_Bookinfo node_bookinfo = root;
            while (node_bookinfo!=null&&num<size){
                stackNode.push(node_bookinfo);
                node_bookinfo = node_bookinfo.getLeftNode();
                while (node_bookinfo==null&&!stackNode.empty()){
                    node_bookinfo = stackNode.pop();
                    sortedArray[num] = node_bookinfo;
                    resort +=node_bookinfo.output()+"\r\n";
                    num++;
//                    System.out.println(node_bookinfo.getRightNode()==null);
                    node_bookinfo =  node_bookinfo.getRightNode();

                }
            }

            Server_API.resortBooks(resort);
            return sortedArray;


    }

    //查找书
    public  Node_Bookinfo search(int id,Node_Bookinfo currentNode){
        Node_Bookinfo node_bookinfo = null;
        if (currentNode.getId()==id){
            node_bookinfo=currentNode;
            return node_bookinfo;
        }
        if (currentNode.getRightNode()!=null||currentNode.getLeftNode()!=null){
            if (id<currentNode.getId()){
                currentNode = currentNode.getLeftNode();
                node_bookinfo=search(id,currentNode);
            }
            if( id>currentNode.getId()){
                currentNode = currentNode.getRightNode();
                node_bookinfo=search(id,currentNode);
            }
        }
         return  node_bookinfo;


    }

}
