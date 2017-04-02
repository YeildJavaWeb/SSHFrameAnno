package com.yeild.ssh.mvc;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping("/develop")
public class DevelopController {
	@Autowired
    private RequestMappingHandlerMapping handlerMapping;
	
	@RequestMapping("/test")
	public String index() {
		return "develop";
	}
	
	@RequestMapping("/views")
	@ResponseBody
	public String getAllViews() {
		StringBuffer viewResults = new StringBuffer();
		Map map =  this.handlerMapping.getHandlerMethods();
        Iterator<?> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            viewResults.append(entry.getKey() +"\n" + entry.getValue()+"\n");
        }
        return viewResults.toString();
	}
}
