package com.zot.autorun.moudules.apitest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.util.Map;

public class JsonUtil {

	/**
	 * 拼装发送的json 
	 * @param jsonMap 原json
	 * @param keyMap  需要替换的字符串
	 * @return
	 */
    public static JSONObject changeJsonObj(Map jsonMap, Map<String, String> keyMap) {
        JSONObject resJson = new JSONObject();
		JSONObject jsonObj = (JSONObject) JSON.toJSON(jsonMap);
//        Set<String> keySet = jsonObj.keySet();
        for (String key : jsonObj.keySet()) {
        	// TODO 当Map 的value 为整形或其他非String 类型数据时 怎么处理
			// 已完成，用object声明变量reskey
//            Object resKey = keyMap.get(key) == null ? key : keyMap.get(key);

            try {
                JSONObject jsonobj1 = jsonObj.getJSONObject(key);
                resJson.put(key, changeJsonObj(jsonobj1, keyMap));
            } catch (Exception e) {
                try {
                    JSONArray jsonArr = jsonObj.getJSONArray(key);
                    resJson.put(key, changeJsonArr(jsonArr, keyMap));
                } catch (Exception x) {
                    //TODO 如果传进来map 的key 和value相等则会出bug
//                    if (resKey != key)
//                        resJson.put(key, keyMap.get(key));
//                    else
//                        resJson.put(key, jsonObj.get(key));

                    if(keyMap.containsKey(key)){
                    	resJson.put(key, keyMap.get(key));
					}else{
						resJson.put(key, jsonObj.get(key));
					}
                }
            }
        }
        return resJson;
    }

    public static JSONArray changeJsonArr(JSONArray jsonArr,Map<String, String> keyMap) {
        JSONArray resJson = new JSONArray();
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            resJson.add(changeJsonObj(jsonObj, keyMap));
        }
        return resJson;
    }
    
    /**
     * 读取json文件
     * @param pactFile
     * @return
     * @throws IOException
     */
	public static String readJsonData(String pactFile) throws IOException {
		// 读取文件数据
		//System.out.println("读取文件数据util");
		
		StringBuffer strbuffer = new StringBuffer();
		File myFile = new File(pactFile);//"D:"+File.separatorChar+"DStores.json"
		if (!myFile.exists()) {
			System.err.println("Can't Find " + pactFile);
		}
		try {
			FileInputStream fis = new FileInputStream(pactFile);
			InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
			BufferedReader in  = new BufferedReader(inputStreamReader);
			
			String str;
			while ((str = in.readLine()) != null) {
				strbuffer.append(str);  //new String(str,"UTF-8")
			}
			in.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
		//System.out.println("读取文件结束util");
		return strbuffer.toString();
	}
	
	/**
	 * 将json 字符串转换为map
	 * @param jsonStr
	 * @return
	 */
    public static Map<String, String> jsonToMap(String jsonStr) {
//		PropertyConfigurator.configure("log4j.properties");
		Logger log = Logger.getLogger(JsonUtil.class);
    	JSONObject jsonObject =null;
    	try {
    		 jsonObject = JSONObject.parseObject(jsonStr);
    	}catch(Exception e) {
			log.error("请检查传入data数据是否为json格式\n" +jsonStr);
			e.printStackTrace();
		}
        Map<String, String> map = (Map)jsonObject;
        return map;
    }


	/**
	 * 判断传入对象是否为 为json 字符串或者对象
	 * @param json
	 * @return true 真  false 假
	 */
	public static boolean checkJson(Object json){

    	if(json != null && !"".equals(json)){
    		if ( json instanceof String){

				try {
					JSONObject.parseObject(json.toString());
					return true;
				}catch (Exception e){
					e.printStackTrace();
					return false;

				}
			}else {
    			try {
					Map map = (Map) json;
					return true;
				}catch (Exception e){
    				e.printStackTrace();
    				return false;
				}
			}
		}else {
    		return false;
		}
	}



//    public static void main(String []args) {
//		String str = " {\n" +
//				" \"lotto\":{\n" +
//				"   \"lottoId\":5,\n" +
//				"   \"winning-numbers\":[2,45,34,23,7,5,3],\n" +
//				"   \"winners\":[{\n" +
//				"     \"winnerId\":23,\n" +
//				"     \"numbers\":[2,45,34,23,3,5]\n" +
//				"   },{\n" +
//				"     \"winnerId\":54,\n" +
//				"     \"numbers\":[52,3,12,11,18,22]\n" +
//				"   }]\n" +
//				"  }\n" +
//				" }";
////		try {
////			JsonUtil.jsonToMap(str);
////		}catch(Exception e) {
////			e.printStackTrace();
////			System.out.println("请检查传入data数据是否为json格式\n" +str );
////		}
////
////		System.out.println(JsonUtil.getType(with(str).get("lotto.winners.winnerId")));
////		List winnerIds = with(str).get("lotto.winners.winnerId");
////		for (int i = 0; i< winnerIds.size(); i++){
////			System.out.println(winnerIds.get(i));
////		}
////		System.out.println(JsonUtil.getType(with(str).get("lotto.winners[0].winnerId")));
////		System.out.println(JsonUtil.getType(with(str).get("lotto.lottoId")));
////		System.out.println(JsonUtil.getType(with(str).get("lotto.winning-numbers")));
//
//	}
    
}
