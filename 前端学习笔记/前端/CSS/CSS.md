## CSS

基础：

- 选择器

   - 引入方式

     - 外部样式表 link  优先级最低00
     - 内嵌样式表 <style>  优先级次之
     - 内联样式表  优先级最高

   - 选择器对相同属性的优先级，组合

     - important 10000 如 color:red!important
     - 内联样式 1000
     - ID选择器 100 (#xxx {})
     - 类选择器 10  (.xxx {})
     - 伪类选择器 10  .xxx:hover{}
     - 属性选择器  10 如 [name="xx"] {}
     - 伪对象(元素)选择器1  .xxxxx:before {}  .xxx:after {}q
     - 标签选择器 1 div {}  p {}
     - 通配符选择器 0 * {}
     - 计算最终权重以后决定使用哪个0 0 0 0 0

   - 子选择器  如 .xxx>div  子选择器只对儿子有效，对孙子无效

   - 后代选择器   .xxx div  中间是空格，对祖祖代代都有效

   - 相邻选择器   E+F 选择E下一个邻居，只是下一个且只有一个

     ```
     <div class="E"></div>>
     <p>1<p>
     <p>2<p>
     
     .E+p{
     
     }
     只对p1有效
     ```

   - 兄弟选择器  E~F 选择E下面所有邻居

   ```
   <div class="E"></div>>
   <p>1<p>
   <p>2<p>
   
   .E+p{
   
   }
   p1 p2都有效
   ```

   

- position

  - static  默认，会随着HTML的排版移动 没有	 top left right bottom
  - absolute   固定在所设定的位置，不会随着HTML的排版移动 ，出现滚动条也会随着滚动条移动， top lefrt right bottom 有效，嵌套使用时是根据外层元素去偏移位置的
  - relative    会随着HTML排行移动，使用top left right bottom来设置一些偏移，重要应用是与嵌套absolute使用，使得absolute 根据外层relative去定位(ps:static嵌套absolute无效)
  - fixed   用bottom top left right始终根据页面即body定位，无论是滚动还是放到relative里面都不能改变他的位置
  - sticky  开始类似static然后用top属性定义当于顶部距离小于设定的top时，变成类似fixed，固定在页面上

1.画一条0.5px的线

因为web浏览器无法识别1px以下，所以

- 使用css3的属性transform:scaleY(倍数); //元素尺寸按给定的参数比例减少

   设置为1px，缩放为0.5倍

  ```
  </div>
  <div style="
    width: 100%;
    height: 1px;
    background: black;
    transform: scaleY(0.5);
  "></div>
  ```

2.页面加载时，link标签会被同时加载，@import引用css只能等到页面加载结束加载

3.水平垂直居中

-  flex justify-content:center; align-items:center  

-  外层position:relative 内层 position:absolute top:50% left:50% transform:translate(-50%,-50%)

-  外层position:relative 内层 position:absolute top:50% left:50% margin:-1/2height 0 0 -1/2width

- 外层position:relative 内层 position:absolute top:0 bottom:0 left:0 right:0

  margin:auto;

- 外层display:table-cell;text-align:center;vertical_align:middle 内层是文字，内联元素，行内块

4.移动端1px问题，移动显示屏的分辨率始终是普通屏幕的2倍

   通过translate:scale(1/2,1/2);

5.css盒模型

-   标准盒模型(W3C)

    box-sizing : content-box;

    width为元素的宽度

    盒子的宽度为 width+2padding+2border

-   IE盒模型 怪异盒模型

    box-sizing:border-box

     width就是盒子的宽度

     width = 元素宽度+2padding+2border

