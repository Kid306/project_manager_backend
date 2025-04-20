package com.mdp.audit.log.client.service;

import com.mdp.audit.log.client.entity.OperLog;
import com.mdp.core.service.SequenceService;
import com.mdp.micro.client.CallBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OperLogClientService {

	private ExecutorService executorService = Executors.newFixedThreadPool(20);


	SequenceService sequence=new SequenceService();
	/**
	 *
	 mdp.audit.audit-log-url=/audit/audit/audit/base/operLog/add
	 */
	@Value(value = "${mdp.audit.audit-log-url:/audit/audit/audit/base/operLog/add}")
	String auditLogUrl="";

	@Autowired
	CallBizService callBizService;

	/**
	 * 空的话，服务器会自动补充
	 * @param fieldId
	 * @return
	 */
	public String createKey(String fieldId){
		return sequence.getTablePK("auditLog","id");
	}


	public   void saveAsync(OperLog log) {
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				callBizService.postForMap(auditLogUrl,log);
			}
		});

	}

}

