package com.HIM.client;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class MyDefaultTreeCellRenderer extends DefaultTreeCellRenderer {
    /**
       * ID
    */
   private static final long   serialVersionUID    = 1L;

    /**
   * 重写父类DefaultTreeCellRenderer的方法
    */
    @Override
   public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                 boolean sel, boolean expanded, boolean leaf, int row,
                                                 boolean hasFocus)
   {

      //执行父类原型操作
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                                row, hasFocus);

      setText(value.toString());

      if (sel)
        {
                  setForeground(getTextSelectionColor());
        }
      else
     {
          setForeground(getTextNonSelectionColor());
       }

         //得到每个节点的TreeNode
       MyTreeNode node = (MyTreeNode) value;
      if (node.getIndex()!=0) {
          if (FriendInfoManager.getFriendInfo(node.getIndex())!=null) {
              ImageIcon sourceImage = new ImageIcon("src/com/HIM/user_photos/" + FriendInfoManager.getFriendInfo(node.getIndex()).getphotoindex());
              Image photo_user = sourceImage.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
              this.setIcon(new ImageIcon(photo_user));
          }
      }
       //得到每个节点的text
       return  this;
          }

}
