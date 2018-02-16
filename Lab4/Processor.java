import java.io.InputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Processor extends Thread {
	Spider spider;

	public Processor(Spider spider) {
		this.spider = spider;
	}

	@Override
	public void run() {
		String temp;
		while (spider.getSize()<100) {
			
			URL url = null;
			try {
				temp = spider.get();
				url = new URL(temp);
				spider.addExplored(temp);
			} catch (Exception e) {
				System.out.println("sum ting wong");
			}
			try {
				InputStream is = url.openStream();
				String baseUrl = url.getProtocol() + "://" + url.getHost();
				Document doc = Jsoup.parse(is, "UTF-8", baseUrl);
				Elements links = doc.getElementsByTag("a");
				for (Element link : links) {
					String linkAbsHref = link.attr("abs:href");
					
						spider.add(linkAbsHref);
						System.out.println("RIGHT"+spider.getSize());
					
				}
			} catch (Exception e) {
				System.out.println("Wrong");
			}
		}
	}
}
