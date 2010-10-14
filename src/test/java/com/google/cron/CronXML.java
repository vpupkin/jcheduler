package com.google.cron;

import com.google.apphosting.utils.config.CronXml;
import com.google.apphosting.utils.config.CronXmlReader;
import com.google.apphosting.utils.config.CronXml.Entry;

import junit.framework.TestCase;

/** 
 * <b>Description:TODO</b>
 * @author      vipup<br>
 * <br>
 * <b>Copyright:</b>     Copyright (c) 2006-2008 Monster AG <br>
 * <b>Company:</b>       Monster AG  <br>
 * 
 * Creation:  11.10.2010::18:24:05<br> 
 */
public class CronXML extends TestCase {
	
	public void testXml(){
		CronXml  c = new  CronXml();
		Entry e = c.addNewEntry();
		e.setDescription("blabla");
		e.setUrl("/toGo.hmlrl");
		e.setTimezone("GMT");
		e.setSchedule("any second");
		System.out.println( c.toYaml());
		System.out.println( c.toString());
		
		LocalCronXmlReader r = new LocalCronXmlReader("/") ;
		CronXml a = r.readCronXml();
		
		System.out.println( a.toYaml());
		System.out.println( a.toString());		
	}

}


 