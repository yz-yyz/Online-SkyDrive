<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <% 
  	ArrayList<String> files = (ArrayList<String>)session.getAttribute("filelist");
 	int fileNum=0;
 	if(files==null){
 	}else{
 		fileNum=files.size();
 	}
 	ArrayList<String> recordmessage = (ArrayList<String>)session.getAttribute("recordmessage");
 	
 	int recordeNum = 0; 
 	if(recordmessage==null){
 	}else{
 		recordeNum =(int)(recordmessage.size()/3);
 	}
 
 %>
 
<head>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Online SkyDrive</title>
    <script src="js/jquery-1.8.3.js"></script>
    <script src="js/jquery.min.js"></script>
    <link rel="stylesheet"type="text/css" href="css/main.css">
</head>

<body>
    <div class="header clearfix">
        <img src="images/notice.jpg" alt="1" id="himg">
        <img id="icon" class="v-center" src="images/icon.jpg" alt="1">
        <p id="welcomep">Welcome &nbsp; <span style="color:blue"> Online SkyDrive </span>！！！
            <span style="color: chocolate;">UserName</span>
        </p>
        <div id="userhold">
            <img src="images/userhead.jpg" alt="1" id="userhead">
            <div id="exit" class="hide">
                <a href="#">退出</a>
            </div>
        </div>

    </div>
    <div class="center">
        <div class="left">
            <div class="mainfunction">
                <p id="mainfunctionfont">
                    主要功能
                </p>
            </div>
            <div id="upload" class="leftArea select">
                <p class="leftfont">
                    upload
                </p>

            </div>
            <div id="download" class="leftArea">
                <p class="leftfont">
                    MyFiles
                </p>

            </div>
            <div id="checkrecords" class="leftArea">
                <p class="leftfont">
                   <a href="Filelist">Records</a> 
                </p>

            </div>
        </div>
        <div class="right">
            <div id="uploadArea" class="rightArea selected">

                <div id="uploadtop">
                    <p id="uploadfont">
                        上传
                    </p>
                </div>
                <div id="uploadbottom">
                    <form action="UploadServlet" name="uploadform" method="POST"  enctype="multipart/form-data">
                        <input type="file" id="file" name="uploadFile" value="选择">
                        <input type="submit" value="上传" id="uploadbtn">
                    </form>
                    <span class="message">message</span>
                    <div class="tips">
                        <p class="tip">特别提醒：</p>
                        <p class="tip">1）仅支持文本、音乐、视频、且文件小于40M</p>
                        <p class="tip">2）文件不得存放黄赌毒相关信息！</p>
                        <p class="tip">3）文件不能是exe、bat、sys、com、dll类型</p>
                        <p class="tip">4）不得将本网盘用作储存非法信息！</p>
                        <p class="tip">5）否则平台将随时删除相关文件！</p>
                    </div>
                </div>
            </div>
            <div id="downloadArea" class="rightArea">
                <div id="downloadtop">
                    <p id="downloadfont">
                        MYFiles
                    </p>
                </div>
                <div id="files">
<%
					for(int j= 0;j<fileNum;j++){
						%>
						 <div class="file">
	                        <img src="images/fileicon.png" class="fileicon" alt="1">
	                        <div class="filename">
	                        <%  out.print(files.get(j));    %>                                             
	                        </div>
	                        <a href="login.html" class="links"><button type="button">download</button></a>
	                        <span class="message">message</span>
	                    </div>
						<%
					}
%>              
                    
                     

                </div>

            </div>
            <div id="checkrecordsArea" class="rightArea">
                <div id="recordstop">
                    <p id="recordsfont">
                        文件上传下载记录
                    </p>
                </div>
                <div id="recordsbottom">
                    <div id="recordhead">
                        <div class="recordheaddetal" id="recordFNanme">文件名</div>
                        <div class="recordheaddetal" id="recordOperation">操作</div>
                        <div class="recordheaddetal" id="recordDate">时间</div>
                    </div>
                    <% for( int i=0;i<recordeNum;i++){%>            	
                    <div class="record">
                            <div class="recorddetal detalFname"><% out.print(files.get(i*3));%></div>
                            <div class="recorddetal detalOperation"><% out.print(files.get(i*3+1));%></div>
                            <div class="recorddetal detalDate"><% out.print(files.get(i*3+2));%></div>
                    </div>	
                    	<%} %>
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
<script src="js/main.js"></script>

</html>