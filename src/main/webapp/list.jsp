<%@page import="org.jcrontab.web.CrontabServletXML"%>
<%@page import="org.jcrontab.CrontabRegistry"%>
<%@page import="org.jcrontab.CrontabBean"%>
<%@page 
import="org.jcrontab.data.CrontabEntryDAO"%><%@page 
import="org.jcrontab.log.Log"%><%@page 
import="org.jcrontab.data.DataNotFoundException"%><%@page 
import="org.jcrontab.data.CrontabParser"%><%@page 
import="org.jcrontab.data.CrontabEntryBean"%><%@page  contentType="text/html;charset=UTF-8"%><%
response.setContentType("text/html;charset=UTF-8");
%><%
try{
int event = Integer.parseInt(request.getParameter("event"));
switch (event) {
	case 0 : {
		CrontabServletXML ctsTmp = new CrontabServletXML();
		// TODO replace to static
		ctsTmp.store(request, response);
		break;
	}
	case 1 : {
		CrontabServletXML ctsTmp = new CrontabServletXML();
		// TODO replace to static
		ctsTmp.remove(request, response);
		break;
	}
}
}catch(Exception e){/* e.printStackTrace(); */};

CrontabEntryBean[] listOfBeans = null;
try {
	listOfBeans = CrontabEntryDAO.getInstance().findAll();
} catch (Exception e) {
	if (e instanceof DataNotFoundException) {
		listOfBeans = new CrontabEntryBean[1];
		CrontabParser cbp = new CrontabParser();
		listOfBeans[0] = cbp
				.marshall("* * * * * org.jcrontab.tests.Example put your own");
	} else {
		Log.error(e.toString(), e);
	}
} 

%>
<!-- HEAD --><HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<META http-equiv="Refresh" content="30">
<TITLE>Welcome to jcrontabEntry Editor</TITLE>
</HEAD>
<BODY>
<H2>Welcome to jcrontabEntry Editor</H2>
<P>
<TABLE border="0" width="100%" cellspacing="0" cellspading="0"></TABLE>
</P>
<TABLE border="1" width="100%" >
<TR>
<TH width="1"><a href="help.html#remove">Remove</a></TH>
<TH width="1"><a href="help.html#minutes">Minutes</a></TH>
<TH width="1"><a href="help.html#hours">Hours</a></TH>
<TH width="1"><a href="help.html#daysOfMonth">Days of month</a></TH>
<TH><a href="help.html#month">Month</a></TH>
<TH><a href="help.html#daysOfWeek">Days of week</a></TH>
<TH>last started</TH>
<TH>$retval</TH>
<TH>execID</TH>
<TH>callcount</TH>
<TH><a href="help.html#class">Classname#Methodname</a></TH>
<TH><a href="help.html#extraInfo">Extrainfo</a></TH>

</TR>
<form   method="post" name="delete">
<input type="hidden" name="event" value="1">
<!--EO HEAD -->
<%
for (int i = 0; listOfBeans!=null && i < listOfBeans.length; i++) {
	CrontabEntryBean crontabEntryBean = listOfBeans[i];
%>
<TR>
<TD width="1"><input name="remove" type="checkbox" value="<%=crontabEntryBean.getId() %>"></TD>
<TD width="1"><%=crontabEntryBean.getMinutes()%></TD>
<TD width="1"><%=crontabEntryBean.getHours()%></TD>
<TD width="1"><%=crontabEntryBean.getDaysOfMonth()%></TD>
<TD width="1"><%=crontabEntryBean.getMonths()%></TD>
<TD width="1"><%=crontabEntryBean.getDaysOfWeek()%></TD>

 <%
    CrontabBean beanTmp = CrontabRegistry.getBean(crontabEntryBean);
	if (beanTmp!=null){
 %>		
<TD width="1"><%=beanTmp.getLastExecution()%></TD>
<TD width="1"><%=beanTmp.getExecutionResult()%></TD>
<TD width="1"><%=beanTmp.getLastResult()%></TD>
<TD width="1"><%=beanTmp.getExecCount()%></TD> 

<TD width="3"><%=beanTmp.getClassName()%>
#<%=beanTmp.getMethodName()%></TD>
<%
		String extraInforTmp = "";
 		if (beanTmp.getExtraInfo()!=null  ) {               
			for (int j = 0; j < beanTmp.getExtraInfo().length ; j++) {
				extraInforTmp += beanTmp.getExtraInfo() [j];
				extraInforTmp += " ";
			}
        }
%>
<TD width="3"><%=extraInforTmp%></TD>
 
 <% 		 
	}else{
 %>
<TD width="3">.</TD>
<TD width="3">..</TD>
<TD width="3">...</TD> 
 <%
	}
 %>
 


</TR>
<%
}
%>



<!-- FOOT -->
<TR>
<TD><input type="submit" name="delete" value="delete"></TD>
</TR>
</form>
<form  method="post" name="add">
<input type="hidden" name="event" value="0">
<TR>
<TD width="3"><input type="checkbox" name="" size="1"></TD>
<TD><input type="text" name="Minutes" value="*" size="1"></TD>
<TD><input type="text" name="Hours" value="*" size="1"></TD>
<TD><input type="text" name="Daysofmonth" value="*" size="1"></TD>
<TD><input type="text" name="Month" value="*"  size="1"></TD>
<TD><input type="text" name="Daysofweek" value="*" size="1"></TD>
<TD width="3"></TD>
<TD width="3"> </TD>
<TD width="3">  </TD>
<TD width="3">   </TD> 
<TD><input type="text" name="Classname" size="55"></TD>
<TD><input type="text" name="Extrainfo"  size="55"></TD>
</TR>
<TR>
<TD><input type="submit" name="save" value="save"></TD>
</TR>
</form>
</TABLE>
</BODY>

</HTML>
