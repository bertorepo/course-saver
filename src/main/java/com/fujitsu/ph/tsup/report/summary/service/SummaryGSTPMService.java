package com.fujitsu.ph.tsup.report.summary.service;

import java.time.ZonedDateTime;


public interface SummaryGSTPMService {
	
	
	public long percentageFinishedToday();
	
	public long percentageFinishedLastWeek(ZonedDateTime StartDateTime, 
			ZonedDateTime EndDateTime );

	public long countTotalNumberOfJDUPM();

	public long countTotalNoJDUPMLastWeek();

	public long countTotalNoOrigMem();

	public long countTotalNoNewMem();

	public long countTotalNoJDUPMF();

	public long countTotalNoJDUPMLastWkF (ZonedDateTime StartDateTime, 
			ZonedDateTime EndDateTime );


	
	
	

}
