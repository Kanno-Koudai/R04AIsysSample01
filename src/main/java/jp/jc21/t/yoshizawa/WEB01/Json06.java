package jp.jc21.t.yoshizawa.WEB01;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Json06 {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		Language2 message = getKeyphrases("Stepover Toehold With Facelock");
		if (message != null) {
			for(String s : message.documents[0].keyPhrases)
				System.out.println(s);
		}
	}

	static Language2 getKeyphrases(String s) 
			throws IOException, URISyntaxException, InterruptedException {
		Language lan = Json05.getLanguage(s);
		Gson gson = new Gson();

		String url = "https://r04jk3a11.cognitiveservices.azure.com/"
				+"text/analytics/v3.1/keyPhrases";
		Map<String,String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key","a545f1db86954f1e855635b73dc8f89d");
		
		
		
		Docs2 doc=new Docs2();
		doc.id="1";
		doc.text=s;
		doc.language=lan.documents[0].detectedLanguage.name;
	

		Source2 src = new Source2();
		src.documents=new Docs2[1];
		src.documents[0]=doc;
		
		String jsonData = new Gson().toJson(src);
		
		//InetSocketAddress message =new InetSocketAddress("172.17.0.2", 80);
		//JsonReader reader = WebApiConnector.postJsonReader(reader,message,map,jsonData);

		JsonReader reader = WebApiConnector.postJsonReader(url,map,jsonData);
		Language2 message = null;
		if (reader != null) {
			message = gson.fromJson(reader, Language2.class);
			reader.close();
		}
		return message;
	}

}
class Language2{
	Documents2[] documents;
	String[] errors;
	String modelVersion;
}
class Documents2{
	String id;
	String[] keyPhrases;
	String[] warnings;
}
class Source2{
	Docs2[] documents;
}

class Docs2{
      String id;
      String text;
      String language;
}
