# hs

## 项目说明

项目常规以来库已经配置成功

框架支持：SpringBoot (Web框架支持) + MyBatis(ORM框架) + SpringSecurity(安全) + Swagger2(文档框架)

第三方主要jar

- hutool Hutool是一个Java工具包类库，对文件、流、加密解密、转码、正则、线程、XML等JDK方法进行封装，组成各种Util工具类 [官网](https://www.hutool.cn/)
- lombok 注解框架

目录结构

- hs-mbg 使用MyBatis 直接从数据库生成数据库基本访问类
- hs-security 安全访问配置模板
- hs-common 通用代码模板
- <span style="color:red">hs-main 主要开发的地方</span>
    - 里面的目录结构已经按照 controller, service, dao进行分开
    - 其中dao是对数据库访问的补充，在hs-mbg提供的基础查询不足的情况下，自己编码Mapper.xml和对应的类实现数据访问，具体样例可以查看hs-admin中的dao编写
- hs-admin 一个权限管理的样例代码：做权限管理那边可以参考

整个项目是从参考 [mall](https://github.com/macrozheng/mall-learning) 上面阉割后的代码，如果对基本的代码有疑问可以查看。

## 协作

项目在github上托管 其中 develop 分支为主线开发分支


```shell

```

克隆成功后，在本地新建自己的分支作为开发使用

```shell
git checkout -b '（gdx）自定义英文字母组合，建议使用个性的名字缩写'
```

现在就可以在你的分支上进行开发了

直接使用idea打开你的项目即可，idea会自动下载依赖库文件。如果下载失败，检查maven的配置镜像。

我使用的maven镜像配置，如果还有其他问题，百度或本组讨论

```xml

<mirrors>
    <mirror>
        <id>alimaven</id>
        <name>aliyun maven</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
        <mirrorOf>central</mirrorOf>
    </mirror>
    <mirror>
        <id>alimaven</id>
        <mirrorOf>central</mirrorOf>
        <name>aliyun maven</name>
        <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
    </mirror>
    <mirror>
        <id>repo2</id>
        <mirrorOf>central</mirrorOf>
        <name>Human Readable Name for this Mirror.</name>
        <url>http://repo2.maven.org/maven2/</url>
    </mirror>
    <mirror>
        <id>ibiblio</id>
        <mirrorOf>central</mirrorOf>
        <name>Human Readable Name for this Mirror.</name>
        <url>http://mirrors.ibiblio.org/pub/mirrors/maven2/</url>
    </mirror>
    <mirror>
        <id>jboss-public-repository-group</id>
        <mirrorOf>central</mirrorOf>
        <name>JBoss Public Repository Group</name>
        <url>http://repository.jboss.org/nexus/content/groups/public</url>
    </mirror>
    <mirror>
        <id>google-maven-central</id>
        <name>Google Maven Central</name>
        <url>https://maven-central.storage.googleapis.com
        </url>
        <mirrorOf>central</mirrorOf>
    </mirror>
    <!-- 中央仓库在中国的镜像 -->
    <mirror>
        <id>maven.net.cn</id>
        <name>oneof the central mirrors in china</name>
        <url>http://maven.net.cn/content/groups/public/</url>
        <mirrorOf>central</mirrorOf>
    </mirror>
</mirrors>
```

进入LtMainApplication直接运行，如果运行成功。

浏览器输入：http://localhost:8080/swagger-ui.html
能够访问说明启动成功。

控制会显示redis错误，不用管

### 同组协作

比如本地部分功能开发结束后，需要和前端进行对接口测试

**所有命令都可以用IDEA自带的功能鼠标进行操作**

**每个小组**自己新建个自己组的远程分支 比如 **xz1**

刚开始远程分支不存在，小组中的随便一个人本地新建 xz1 分支

```shell
git checkout -b xz1 # 本地新建分支并切换到分支
git push origin xz1 # 将本地分支提交到远程分支上
```

远程分支新建后

这个时候，同组的人只需要拉去远程xz1分支，就可以得到当前组的最新代码

每个人开发功能准备提交代码的时候

1. 切换到本地的xz1分支
   ```shell
   git checkout xz1
   ```
2. 拉取远程的xz1分支到本地
   ```shell
   git pull origin xz1
   ```
3. 合并自己开发的gdx分支的代码到xz1当中, 如果出现冲突，则解决冲突再提交
   ```shell
    git merge gdx
   ```
4. 再次提交xz1到远程，这样你本地开发的代码就更新到远程分支
   ```shell
   git push origin xz1
   ```
5. 其他人只需要执行1-2就可以得到当前小组的最新代码。

其他原则：

- 不要提交无关的文件，项目临时产生的文件到分支中



