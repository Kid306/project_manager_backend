/**
 * 
 */
package com.mdp.core.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;


/**
 * @author chenyc
 * Xml解析工具类
 */
public class XmlUtils {
		
		public static Map<String,Object> string2Map(String xml) throws DocumentException{
			Document doc=DocumentHelper.parseText(xml);
			return dom2Map(doc);
		}
		
		public static   Map<String, Object> dom2Map(Document doc){
			Map<String, Object> map = new HashMap<String, Object>();
			if(doc == null)
				return map;
			Element root = doc.getRootElement();
			for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
				
				Element e = (Element) iterator.next();
				//System.out.println(e.getName());
				List list = e.elements();
				if(list.size() > 0){
					map.put(e.getName(), dom2Map(e));
				}else
					map.put(e.getName(), e.getText());
			}
			return map;
		}
		

		public static   Map dom2Map(Element e){
			Map	map = new HashMap();
			List list = e.elements();
			if(list.size() > 0){
				for (int i = 0;i < list.size(); i++) {
					Element iter = (Element) list.get(i);
					List mapList = new ArrayList();
					
					if(iter.elements().size() > 0){
						Map m = dom2Map(iter);
						if(map.get(iter.getName()) != null){
							Object obj = map.get(iter.getName());
							if(!obj.getClass().getName().equals("java.util.ArrayList")){
								mapList = new ArrayList();
								mapList.add(obj);
								mapList.add(m);
							}
							if(obj.getClass().getName().equals("java.util.ArrayList")){
								mapList = (List) obj;
								mapList.add(m);
							}
							map.put(iter.getName(), mapList);
						}else
							map.put(iter.getName(), m);
					}
					else{
						if(map.get(iter.getName()) != null){
							Object obj = map.get(iter.getName());
							if(!obj.getClass().getName().equals("java.util.ArrayList")){
								mapList = new ArrayList();
								mapList.add(obj);
								mapList.add(iter.getText());
							}
							if(obj.getClass().getName().equals("java.util.ArrayList")){
								mapList = (List) obj;
								mapList.add(iter.getText());
							}
							map.put(iter.getName(), mapList);
						}else
							map.put(iter.getName(), iter.getText());
					}
				}
			}else
				map.put(e.getName(), e.getText());
			return map;
		}

	}

