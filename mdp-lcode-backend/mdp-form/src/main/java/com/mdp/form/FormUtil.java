package com.mdp.form;

public class FormUtil {
	
	/**
	 * 完成任务时，如果任务数据有被更改，发送该事件到PROCESS_APPROVA_URL,如果存在restUrl，PROCESS_APPROVA_URL将被restUrl替换
	 */
	public static String TASK_COMPLETED_FORM_DATA_UPDATE="TASK_COMPLETED_FORM_DATA_UPDATE";
	
	/**
	 * 任务完成/流程完成/启动/取消时，回调地址
	 * 如果存在restUrl，PROCESS_APPROVA_URL将被restUrl替换
	 */
	public static String PROCESS_APPROVA_URL="/form/form/form/formData/processApprova";
	
	/**
	 * 推送formData数据时，指定需要更新的字段
	 */
	public static String NEED_UPDATE_FORM_DATA_FIELD_ID_LIST="updateFormDataFieldIds";
	
	
    /**
     * 任务提交完成时推送事件
     */
    public static final String TASK_COMPLETED = "TASK_COMPLETED";
	

}
