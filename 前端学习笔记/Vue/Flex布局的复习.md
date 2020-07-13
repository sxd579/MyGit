## Flex布局的复习

  Flex布局首先一定要分清楚 **容器**  和 **项目**

   他们各自有各自的属性

### display: flex;

#### 容器属性

1. flex-direction  元素的排列方向

   **row**|row-reverse|column|column-reverse

2. flex-wrap     元素放不下了是否换行

   ##### flex布局横向布局遇到总宽度大于容器宽度时会自动按比例缩小

   **nowrap**|wrap|wrap-reverse

   

3. justify-content  定义了项目在**主轴上**的对齐方式  

   ![image-20200615184009557](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\image-20200615184009557.png)

   

4. align-items    定义项目在**交叉轴**上的对齐方式    

   ![image-20200615184241163](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\image-20200615184241163.png)

5. align-content

