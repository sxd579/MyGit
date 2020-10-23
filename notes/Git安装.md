## Git安装和使用

1. https://git-scm.com/download/win

2. 将Git/Bin配置为环境变量Path

3. $ git config --global user.name "your name"
   $ git config --global user.email "your_email@youremail.com"  设置名字 邮箱

4. Git操作  untracked(工作区)-->git add->暂存区->git commit -m""->仓库

   - 文件首先实在untracked

   - 通过git add  文件名/.(全部添加)，将对应文件添加到暂存区（清缓存or重启就没了）

     可以通过git status查看状态
     
   - git commit -m"说明" 将文件从暂存区转移到仓库

5. 可以通过git log

   ​             git log --oneline来查看日志，最前面是文件的哈希

6. git checkout . 来撤销工作区里面的操作

7. git reset  来撤销暂存区到工作区即 git add .之前

8. 删除提交记录  git rm 文件   仓库和暂存区和工作区的文件都会删除掉

   ​       git r --cached 文件  只会删除仓库和暂存区里的该文件不会删除工作区的该文件

9. git commit --amend -m    覆盖上次提交  git log查不到上次，已经被覆盖，git reflog可以找到

10. git reflog  查看全面的记录 

11. git reset --hard hash值    回退到对应版本（git log中获得hash值）

12. 比较差异:git diff  比较本地工作区和暂存区的区别

13. git diff --cached 比较缓存区和仓库的区别

14. git diff   版本号1  版本号2 的区别

15.  分支 --->解决冲突

    - 查看分支 git branch

    - 新建一个分支，将当前分支复制一份到新分支，用于一些实验性代码

      git branch 分支名  创建一个新分支，

    - 切换分支，先要提交当前分支内容 git checkout 分支名 切换到对应分支，当两个分支md5值相同时，就会把对这个分支的操作带到另一个分支，每个分支在修改完毕以后commit一次就可以避免带的其他分支了

    - 合并分支 git merge 分支名 

      合并分支如果对同一行有修改，则会由冲突会显示

      两个分支冲突地方会显示如下，删除掉

      ```
      <<<<<<< HEAD
          console.log("master的修改内容，等待冲突")
      =======
          console.log("b的修改");
      >>>>>>> a
      ```

      手动删除解决冲突以后重新提交

    - git branch -d(-D:强制删除)安全删除  分支名，

      即合并之后无用分支可以删除

    - git tag 版本号  在提交以后可以设置大版本号

      git tag 即可查看版本列表

      git checkout 版本号 查看某个版本

16. 绑定在线仓库  

    - 生成密钥  ssh-keygen -t rsa -C "1239652039@qq.com"

    - 生成时会 由 密钥储存路径 大概 公钥pub文件 复制内容，添加到github的 ssh里面

    - 测试是否成功 ssh  -T  git@github.com

      ​					 ssh  -T  git@gitee.com

    - git remote add origin  git@gitee.com:sxd579/test.git  (地址从仓库拿)

    - 首次推送 git push -u origin master 之后可以不用-u

    - 不可以在v2.0 v1.0上面提交，只能在master上提交

    - 提交之前 git pull拉取一下线上仓库的

    - push 分支 则可以 git push origin 分支名 /版本名

    - 可以添加多个remote仓库  改origin为其他名字

    - 查看当前git的地址内容 git remote -v

      

    
    
    
    
    

