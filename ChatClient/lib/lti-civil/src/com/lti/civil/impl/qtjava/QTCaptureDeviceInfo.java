/*
 * Created on May 27, 2005
 */
package com.lti.civil.impl.qtjava;

import com.lti.civil.CaptureDeviceInfo;

/**
 * 
 * @author Ken Larson
 */
public class QTCaptureDeviceInfo implements CaptureDeviceInfo
{
	private String deviceID;
	private String description;
	public QTCaptureDeviceInfo(String deviceID, String description)
	{
		super();
		
		this.deviceID = deviceID;
		this.description = description;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getDeviceID()
	{
		return deviceID;
	}
	public void setDeviceID(String deviceID)
	{
		this.deviceID = deviceID;
	}
	
	
	
}
