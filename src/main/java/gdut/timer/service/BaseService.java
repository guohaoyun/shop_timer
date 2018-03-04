package gdut.timer.service;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @Description 服务基类
 * 
 * 
 */
public class BaseService {

	/**
	 * 响应Hash错误码键
	 */
	public static final String KEY_RESP_CODE = "code";
	/**
	 * 响应原因Hash键
	 */
	public static final String KEY_RESP_REASON = "reason";

	/**
	 * 请求响应成功状态
	 */
	public final static String RESPONE_MESSAGE_STATUS_SUCCESS = "0";

	/**
	 * 请求响应失败状态
	 */
	public final static String RESPONE_MESSAGE_STATUS_FAILURE = "1";
	
	

	/**
	 * 获取操作失败的响应
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildErrorRespone(String errorMessage) {
		return buildRespone(errorMessage, RESPONE_MESSAGE_STATUS_FAILURE);
	}

	/**
	 * 获取操作失败的缺省响应
	 * 
	 * @return
	 */
	protected String buildDefaultErrorRespone() {
		return buildRespone("", RESPONE_MESSAGE_STATUS_FAILURE);
	}

	/**
	 * 
	 * 获取操作成功的缺省响应
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildDefaultSuccessRespone() {
		return buildRespone("成功!", RESPONE_MESSAGE_STATUS_SUCCESS);
	}

	/**
	 * 
	 * 获取操作成功的响应
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildSuccessRespone(String message) {
		return buildRespone(message, RESPONE_MESSAGE_STATUS_SUCCESS);
	}

	/**
	 * 
	 * 获取操作成功的响应
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildSuccessRespone(Map<String, Object> parms) {
		return buildRespone(parms, "操作成功!", RESPONE_MESSAGE_STATUS_SUCCESS);
	}
	
	protected String buildSuccessRespone(Object obj) {
		return buildRespone(obj, "操作成功!", RESPONE_MESSAGE_STATUS_SUCCESS);
	}

	/**
	 * 建造响应信息
	 * 
	 * @param messageCategory
	 * @param message
	 * @param code
	 * @return
	 */
	private String buildRespone(Map<String, Object> parms, String message, String code) {
		Map<String, Object> respone = parms;
		if (respone == null) {
			respone = new HashMap<String, Object>();
		}
		respone.put("message", message);
		respone.put("code", code);
		if("0".equals(code)){
			respone.put("success", true);
		}else{
			respone.put("success", false);
		}
		return JSONObject.toJSONString(respone,SerializerFeature.WriteDateUseDateFormat);
	}
	
	private String buildRespone(Object obj, String message, String code) {
		Map<String, Object> respone =  new HashMap<String, Object>();
		respone.put("data", obj);
		respone.put("code", code);
		if("0".equals(code)){
			respone.put("success", true);
		}else{
			respone.put("success", false);
		}
		return JSONObject.toJSONString(respone,SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * 建造响应信息
	 * 
	 * @param messageCategory
	 * @param message
	 * @return
	 */
	private String buildRespone(String message, String code) {
		return buildRespone(null, message, code);
	}
	
	/**
	 * 将一个对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	protected String toJsonString(Object obj) {
		String result = JSONObject.toJSONString(obj,SerializerFeature.WriteDateUseDateFormat);
		return result;
	}
}
