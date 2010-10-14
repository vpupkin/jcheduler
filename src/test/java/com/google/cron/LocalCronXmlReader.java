package com.google.cron;

import java.io.InputStream;

import com.google.apphosting.utils.config.AbstractConfigXmlReader;
import com.google.apphosting.utils.config.CronXml;
import com.google.apphosting.utils.config.CronXmlReader;

/**
 * <b>Description:TODO</b>
 * 
 * @author vipup<br>
 *         <br>
 *         <b>Copyright:</b> Copyright (c) 2006-2008 Monster AG <br>
 *         <b>Company:</b> Monster AG <br>
 * 
 * Creation: 14.10.2010::14:39:13<br>
 */
public class LocalCronXmlReader extends CronXmlReader  {

 
	protected InputStream getGeneratedStream(){
		return getInputStream();
	}
	
	protected boolean fileExists(){
		return true;
	}
	
    protected String getRelativeFilename()
    {
        return "WEB-INF/cron.xml";
    }

	public LocalCronXmlReader(String appDir) {
		super(appDir); 
	}


	protected InputStream getInputStream(){
		return this.getClass().getClassLoader().getResourceAsStream("WEB-INF/cron.xml");
	}

 
	@Override
	protected CronXml processXml(InputStream inputstream) {
		return super.processXml(inputstream) ;
	}
}
