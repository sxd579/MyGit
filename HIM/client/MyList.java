package com.HIM.client;

import javax.swing.*;
import java.util.ArrayList;

public class MyList extends JList {


    private ArrayList<String> index = new ArrayList <>();
    public MyList(){
        super();
    }
    public MyList(Object [] a){
        super(a);
    }
    public ArrayList <String > getIndex() {
        return index;
    }

    public void setIndex(ArrayList <String > index) {
        this.index = index;
    }
}
