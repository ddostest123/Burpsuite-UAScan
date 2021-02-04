# Burpsuite UAScan 插件
Burpsuite插件：被动方式进行未授权访问漏洞（越权）的扫描
+ 被动扫描经过Burpsuite的每一个请求
+ 通过修改Cookie头重新访问判断是否存在未授权访问（越权）问题
+ 如果扫描到未授权访问的URL会显示到空白区域中
+ 采用Jaro-Winkler距离算法进行页面相似度判断，提高准确度
+ 加入过滤器功能，用英文分号分隔可输入多个域名(xxx.xxx)作为过滤，大大提高挖洞效率
### 简易教程
在Release中下载插件，安装后会多出一个页面：UAScan。
进入这个页面勾选开启被动扫描，然后不用做任何操作，正常使用Burpsuite即可。
自动检测所有的流量，然后将可能存在未授权访问漏洞的URL显示到UAScan的页面中。
一般建议在登录某系统后使用，是挖掘未授权访问漏洞的利器。
目前只排除了静态文件，后续可以自定义排除规则
### 开发者
- 小迪安全团队（许少，修君）

![ua.png](https://xuyiqing-1257927651.cos.ap-beijing.myqcloud.com/burpsuite/ua.png)
****
# Burpsuite UAScan Plugin
Burpsuite Plugin: Passive Scanning For Unauthorized Access Vulnerabilities
+ Passive scanning goes through every request from burpsuite
+ Determine whether there is unauthorized access by deleting the cookie header
+ If an unauthorized URL is scanned, it will be displayed in a blank area
## Function after update
- Jaro Winkler distance algorithm is used to judge the similarity of pages to improve the accuracy
- Filter algorithm is used to input multiple domain names separated by English semicolons（ xxx.xxx ）As a filter, greatly improve the efficiency of digging holes
### Easy tutorial
Download the plug-in in release, and an additional page will appear after installation: uascan.
Enter this page, check to start passive scanning, and then use burpsuite normally without any operation.
Automatically detect all traffic, and then display the URL that may have unauthorized access vulnerability to the uascan page.
It is generally recommended to use it after logging in to a system, which is a sharp tool for mining unauthorized access vulnerabilities.
At present, only static files are excluded, and exclusion rules can be customized later
### Developer
- XiaoDi Team(Xu,Man Go Tea Cool)
****

