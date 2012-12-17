<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><tiles:insertAttribute name="title" ignore="true" /></title>
<style>
  h1, p, body, html {
	margin:0;
	padding:0;}

/*Just visual styles*/
body {
	
	font:12px verdana, sans-serif;
	color:#000;
	background: "background.jpg" no-repeat top left fixed;
	}

/*Our full length header. 
We align the text center so it shows up in the middle. 
If you prefer you could right or left align it.*/	
#headerbg {
	background:#3BB9FF;
	text-align:center;
	padding:20px;
	
	}
	
/*Center Content*/	
#container {
	position:relative;
	margin:0 auto;
	width:800px;
	}

/*Full width footer*/	
#footer {
	background:#3BB9FF;
	padding:10px;
	text-align:center;
	}

/*These are just styles used for the example for the content.*/	
.left {
	float:left;
	width:500px;
	background:#ccff66;
	}
	
.right {
	float:left;
	width:200px;
	clear:right;
	background:#66ff33;
	}
	
.left p, .right p {
	padding:10px;
	width=2000px;
	}

</style>
</head>
<body background="background.jpg">
<table  cellpadding="1" cellspacing="1">
	<tr>
		<td  width="2000px" height="100px" bgcolor="#3BB9FF" align="center" colspan="1"><tiles:insertAttribute name="header"  />
		</td>
	</tr>
	<tr>
		<td class="left"><tiles:insertAttribute name="menu" /></td>
		
	</tr>
	<tr>
	    <td class="contaier" height="500px"><tiles:insertAttribute name="body"  /></td>
	</tr>
	<tr>
		<td width="100%" colspan="1" bgcolor="#3BB9FF" class="footer"><tiles:insertAttribute name="footer" />
		</td>
	</tr>
</table>
</body>
</html>