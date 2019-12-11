<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    
    <meta name="viewport" content="width= , initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content=" ">
    <title>Online SkyDrive Register</title>
    <link rel="stylesheet" href="css/register.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>

</head>

<body>
    <div class="header">
        <img src="images/notice.jpg" alt="" id="himg">
        <img id="icon" class="v-center " src="images/icon.jpg" alt="">
        <p id="welcomep">Welcome &nbsp; <span style="color:blue"> Online SkyDrive </span>！！！</p>
    </div>
    <div class="center">
        <div id="box">
            <img id="imgId5" src="images/bg10.jpg" />
            <img id="imgId1" src="images/bg9.jpg" />
            <img id="imgId2" src="images/bg8.jpg" />
            <img id="imgId3" src="images/bg1.jpg" />
            <img id="imgId4" src="images/bg11.jpg" />


            <ul id="btns">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
        <div class="info">

            <p>

                <span>
                    在线预览
                </span>
            </p>
            <p>
                文件即开即看
            </p>
        </div>
        <div class="login">
            <div>
                <p id="p1">账号密码登录</p>
                <form action="ZhuCe" method="post" name="from1">
                    <p id="p2">
                        <input type="text" name="userName" placeholder="用户名" class="ipt">
                    </p>
                    <p id="p3">
                        <input type="password" name="pwd" placeholder="密码" class="ipt">
                    </p>
                    <p id="p4">
                        <input type="password" name="ckpwd" placeholder="密码确认" class="ipt">
                    </p>
                    <p id="message">message</p>
                    <input type="submit" value="注册" class="submit">
                </form>
            </div>

            <div class="tologin">
                <a href="login.jsp">立即登录</a>
            </div>
        </div>

    </div>
    <div class="blocks">
        <div class="notice">
            <img src="images/notice.jpg" alt="" id="nimg">
            <p id="gg">
                公告：我们的功能在逐步上线，敬请期待 &nbsp;↓&nbsp;↓&nbsp;↓
            </p>
        </div>
    
        <div id="block239" class="block" style="height: auto;">
    
            <div class="container">
                <div class="title">
                    <p id="p10">Online SkyDrive Solution</p>
                    <h3>在线网盘帮您解决</h3>
                </div>
                <div class="imghold">
                    <img src="images/img1.png">
                </div>
                <div class="solution">
                    <div id="leftp">
                        <p><span>◆</span>
                            <font>传统系统维护，人力物力成本高。</font>
                        </p>
                        <p><span>◆</span>
    
                            <font>文件日益增多，分散紊乱无管理；</font>
                        </p>
                        <p><span>◆</span>
                            <font>文件检索不便，复用查找效率低；</font>
                        </p>
                        <p><span>◆</span>
                            <font>权限管理杂乱，文件丢失引麻烦；</font>
                        </p>
                        <p><span>◆</span>
                            <font>托管于第三方，数据安全隐患大；</font>
                        </p>
                    </div>
                    <div id="rightp">
                        <p><span>◆</span>
                            <font>文件备份困难，硬件损坏难还原；</font>
                        </p>
                        <p><span>◆</span>
                            <font>文件传输限速，调取文件速度慢；</font>
                        </p>
                        <p><span>◆</span>
                            <font>文件频繁修改，版本错乱易混淆；</font>
                        </p>
                        <p><span>◆</span>
                            <font>文件数据孤岛，共享协同不方便；</font>
                        </p>
                        <p><span>◆</span>
                            <font>出差文件遗忘，移动办公不方便；</font>
                        </p>
                    </div>
    
                </div>
    
            </div>
        </div>
    </div>
    <div class="footer clearfix">
        <div class="year v-center">

        </div>
        <div class="copyright v-center">
            版权所有 陈慷&nbsp;|&nbsp;姚雨卓|&nbsp;罗壮
        </div>
        <div class="inf v-center">
            <a href="#">公司简介</a>
            <a href="#">联系方法</a>
            <a href="#">联系方法</a>
            <a href="#">联系方法 </a>
            <a href="#">联系方法</a>
            <a href="#">联系方法</a>
            <a href="#">联系方法</a>
        </div>
    </div>
</body>
<script src="js/register.js"></script>
</html>