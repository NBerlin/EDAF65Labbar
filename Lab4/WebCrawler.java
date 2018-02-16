import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	ArrayList<String> exploredUrl = new ArrayList<>();
	LinkedList<String> unexploredUrl = new LinkedList<>();

	public static void main(String[] args) {
		new WebCrawler();
	}

	public WebCrawler() {
		unexploredUrl.add("http://cs.lth.se/pierre_nugues/");
		crawl();
		ArrayList<String> emails = (ArrayList<String>) exploredUrl.stream().filter(s -> s.startsWith("mail")).collect(Collectors.toList());
		for (String s : exploredUrl) {
			System.out.println(s);
		}
	}

	private void crawl() {
		while (exploredUrl.size() < 100) {
			URL url = null;
			try {
				String temp = unexploredUrl.removeFirst();
				url = new URL(temp);
				exploredUrl.add(temp);
			} catch (Exception e) {
				System.out.println("sum ting wong");
				continue;
			}
			try {
				InputStream is = url.openStream();
				String baseUrl = url.getProtocol() + "://" + url.getHost();
				Document doc = Jsoup.parse(is, "UTF-8", baseUrl);
				Elements links = doc.getElementsByTag("a");
				for (Element link : links) {
					String linkAbsHref = link.attr("abs:href");
					if (!exploredUrl.contains(linkAbsHref)) {
						unexploredUrl.addLast(linkAbsHref);
					}
				}
			} catch (Exception e) {
				System.out.println("Wrong");
			}
		}
	}

}
