
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Spider spider = new Spider("http://cs.lth.se/pierre_nugues/");
		for(int i =0;i<10;i++){
			new Processor(spider).start();
		}
		Thread.sleep(10000);
		for(String s : spider.returnExplored()){
			System.out.println(s);
		}
	}

}