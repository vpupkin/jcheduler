package com.google.cron;

import org.jcrontab.data.CrontabEntryBean;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

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
	
	public void testXml2Yaml(){
		CrontabEntryBean b = new CrontabEntryBean();
		b.setDescription("blallba");
		Yaml yaml = new Yaml(new SafeConstructor());
		System.out.println(  yaml.dump(  b ) );
		 	
	}	
	
	public void testYAML(){
		LocalCronXmlReader r = new LocalCronXmlReader("/") ;
		CronXml a = r.readCronXml();
		
		String yamlCronTmp = a.toYaml();
		
		
		Yaml yaml = new Yaml();
		Object obj = yaml.load(yamlCronTmp);
		System.out.println(obj);
		assertTrue(""+obj, obj instanceof java.util.Map);
		assertTrue(""+obj, ((java.util.Map)obj).size() == 1 );
		assertTrue(""+obj, ((java.util.Map)obj).get("cron" ) instanceof java.util.List );
		assertTrue(""+obj, ((java.util.List)((java.util.Map)obj).get("cron" )).size() == 2 );
	}

	
	public void testYAML2OBJ(){
		LocalCronXmlReader r = new LocalCronXmlReader("/") ;
		CronXml a = r.readCronXml();
		
		String yamlCronTmp = a.toYaml();
		System.out.println(yamlCronTmp);
		//{cron=[{description=Repopulate the cache every 2 minutes, url=/recache, schedule=every 2 minutes, timezone=UTC}, {description=Mail out a weekly report, url=/weeklyreport, schedule=every monday 08:30, timezone=America/New_York}]}

		yamlCronTmp  = yamlCronTmp.replace("- ", "--- !"+CrontabEntryBean.class.getName()+"\n  ");
		yamlCronTmp  = yamlCronTmp.replace("description:", "  description:");
		yamlCronTmp  = yamlCronTmp.replace("url:", "  url:");
		yamlCronTmp  = yamlCronTmp.replace("schedule:", "  schedule:");
		yamlCronTmp  = yamlCronTmp.replace("timezone:", "  timezone:");
		System.out.println(yamlCronTmp);
		Yaml yaml = new Yaml();//new SafeConstructor()
		Object obj = yaml.load(yamlCronTmp);
		System.out.println(obj);
		assertTrue(""+obj, obj instanceof java.util.Map);
		assertTrue(""+obj, ((java.util.Map)obj).size() == 1 );
		assertTrue(""+obj, ((java.util.Map)obj).get("cron" ) instanceof java.util.List );
		assertTrue(""+obj, ((java.util.List)((java.util.Map)obj).get("cron" )).size() == 2 );
	}
	
}


 