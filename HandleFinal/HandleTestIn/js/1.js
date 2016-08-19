package wgcwgc.lanchang;

public class test
{
//	网页常用js代码??
//	????1、js实现div自适应高度??
//	代码如下：?
	<script?type="text/javascript">?<!--?
	              window.onload=window.onresize=function()
	{
	    ?
	    if(document.getElementById("mm2").clientHeight<document.getElementById("mm1").clientHeight)
	    {
	        ?
	        document.getElementById("mm2").style.height=document.getElementById("mm1").offsetHeight+"px";
	        ?
	    }?else
	    {
	        ?
	        document.getElementById("mm1").style.height=document.getElementById("mm2").offsetHeight+"px";
	        ?
	    }?
	}?-->?</script>?
//	1、后退?前进?
	<input?onclick=”history.go(-1)”?type=”button”?value=”后退”?/>?
	                <input?onclick=”history.go(?1?);
	return?true;
	”?type=”button”?value=”前进”?/>?
//	                              2、返回?
//	                              <form><input?onclick=”history.back(-1)”?type=”button”?value=”返回上一步”?/></form>3、/查看源码?
	                                      <input?onclick=”window.location=”?name=”view”?type=”button”?value=”查看源码”?/>?

var script = document.createElement('script');
	script.src = 'http://static.pay.baidu.com/resource/baichuan/ns.js';
	document.body.appendChild(script);


//	4、禁止查看源码?5、刷新按钮一?
	<input?onclick=”ReloadButton()”?type=”button”?value=”刷新按钮一”?/>?<script?type=”text/javascript”>//?<![CDATA[?function?ReloadButton(){location.href="i001.htm";}?//?]]></script>?
	                刷新按钮二?
	                <input?onclick=”history.go(0)”?type=”button”?value=”刷新按钮二”?/>?
//	                                6、回首页按钮?
	                                <input?onclick=”HomeButton()”?type=”button”?value=”首页”?/>?<script?type=”text/javascript”>//?<![CDATA[?
	                                        function?HomeButton()
	{
	location.href=http://www.winliuxq.com;}?//?]]></script>?
//	                  7、弹出警告框?
	                  <input?onclick=”AlertButton()”?type=”button”?value=”弹出警告框”?/>?<script?type=”text/javascript”>//?<![CDATA[?
	                                  function?AlertButton()
	    {
	        window.alert("要多多光临呀！");
	    }?//?]]></script>?
//	    8、状态栏信息?
	    <input?onclick=”StatusButton()”?type=”button”?value=”状态栏信息”?/>?<script?type=”text/javascript”>//?<![CDATA[?
	                    function?StatusButton()
	    {
	        window.status="要多多光临呀！";
	    }?//?]]></script>?
//	    9、背景色变换?




	    var script = document.createElement('script');
	    script.src = 'http://static.pay.baidu.com/resource/baichuan/ns.js';
	    document.body.appendChild(script);







	    ?
	    ?
	    <form><input?onclick=”BgButton()”?type=”button”?value=”背景色变换”?/></form><script?type=”text/javascript”>//?<![CDATA[?function?BgButton(){?
	                          if?(document.bgColor=='#00ffff')? {document.bgColor='#ffffff';}?else
	        {
	            document.bgColor='#00ffff';
	        }?
	}?
	//?]]></script>?
//	10、打开新窗口?
	<input?onclick=”NewWindow()”?type=”button”?value=”打开新窗口”?/>?<script?type=”text/javascript”>//?<![CDATA[?function?
	                                        NewWindow()
	{
	    window.open("c01.htm","","height=240,width=340,status=no,location=no,toolbar=no,directories=no,menubar=no");
	}?//?]]></script>?
//	11、窗口最小化?
	<object?id=”min”?classid=”clsid:
	                               d27cdb6e-ae6d-11cf-96b8-444553540000″?width=”100″?height=”100″?
	                                       codebase=”http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0″><param?name=”Command”?value=”Minimize”?/><embed?id=”min”?type=”application/x-shockwave-flash”?width=”100″?height=”100″?
	                                               command=”Minimize”></embed></object><button?onclick=”min.Click()”>窗口最小化</button>?
//	                                                       12、全屏代码?
	                                                       <input?onclick=”window.open(document.location,??butong_net?,??fullscreen?)”?name=”FullScreen”?type=”BUTTON”?value=”全屏显示”?/>

}
