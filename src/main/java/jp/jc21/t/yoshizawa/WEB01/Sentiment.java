package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Sentiment {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		SLanguage message = getLanguage("Stepover Toehold With Facelock");
		if (message != null) {
			System.out.println("negative"+message.documents[0].confidenceScores.negative);
			System.out.println("neutral"+message.documents[0].confidenceScores.neutral);
			System.out.println("positive"+message.documents[0].confidenceScores.positive);
		}
	}

	static SLanguage getLanguage(String s) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();

		String url = "https://r04jk3a06-text.cognitiveservices.azure.com//" + "text/analytics/v3.0/sentiment";
		Map<String, String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key", "a2328e94b11644d59e7a4392b2cfc70e");

		SDocs doc = new SDocs();
		doc.id = "1";
		doc.text = s;

		SSource src = new SSource();
		src.documents = new SDocs[1];
		src.documents[0] = doc;

		String jsonData = new Gson().toJson(src);

		InetSocketAddress proxy = new InetSocketAddress("172.17.0.2", 80);

		JsonReader reader = WebApiConnector.postJsonReader(url, proxy, map, jsonData);
		SLanguage message = null;
		if (reader != null) {
			message = gson.fromJson(reader, SLanguage.class);
			reader.close();
		}
		return message;
	}

}

class SLanguage {
	SDocuments[] documents;
	String[] errors;
	String modelVersion;
}

class SDocuments {
	ConfidenceScores confidenceScores;
}

class ConfidenceScores {
	double negative;
	double neutral;
	double positive;
}

class SSource {
	SDocs[] documents;
}

class SDocs {
	String id;
	String text;
}
