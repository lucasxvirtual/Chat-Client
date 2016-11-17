import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
	
	private static Socket socket;

	public static void main(String args[]){
		try{
			socket = new Socket("127.0.0.1", 12344);
			System.out.println("o cliente se conectou ao servidor");
			Client client = new Client();
			Thread tClient = new Thread(client);
			tClient.start();
			Scanner scanner = new Scanner(socket.getInputStream());
			while(true){
				if(scanner.hasNext())
					System.out.println(scanner.next());
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			PrintStream printStream = new PrintStream(socket.getOutputStream());
			Scanner scanner = new Scanner(System.in);
			while(true){
				String msg = scanner.nextLine();
				printStream.println(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
