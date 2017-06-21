import java.net.*;
import java.io.*;
import java.awt.*;
class Server extends Thread{
	public final static int FILE_SIZE = 6022386;
	ServerSocket ss=null;
	Socket server=null;
	Server(int port)throws IOException{
		ss=new ServerSocket(port);
		
	}
	public void run(){
		while(true){
			try{
				int bytesRead;
    				int current = 0;
				System.out.println("Waiting for client ");
				server=ss.accept();
				FileInputStream fis = null;
    				BufferedInputStream bis = null;
    				OutputStream out = null;
				FileOutputStream fos=null;
				BufferedOutputStream bos=null;
				out=server.getOutputStream();
				DataOutputStream o=new DataOutputStream(out);
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

				InputStream in=server.getInputStream();
				DataInputStream i = new DataInputStream(in);
				//Read data sent by the client 

				String command=i.readUTF();
				String filename=i.readUTF();
				
				
					File myFile=null;

					if(command.equalsIgnoreCase("GET")){
						try{
							myFile = new File (filename);
							fis = new FileInputStream(myFile);
							String s="200 OK";
							System.out.println(s);
							o.writeUTF(s);
						}
						catch(FileNotFoundException e){
							String s="404 Not Found";
							System.out.println(s);
							o.writeUTF(s);
				
						}
          					byte [] mybytearray  = new byte [(int)myFile.length()];
          		
          					bis = new BufferedInputStream(fis);
          					bis.read(mybytearray,0,mybytearray.length);
          				
          					System.out.println("Sending " + filename + "(" + mybytearray.length + " bytes)");
          					out.write(mybytearray,0,mybytearray.length);
          					out.flush();
          					System.out.println("Done.");
					
						
						bis.close();
						out.close();
						o.close();
					}
					else if(command.equalsIgnoreCase("put")){
						InputStream is=null;
						String str=i.readUTF();
						if(str.equals("Absent")){
							System.out.println("Not a valid file sent by the client. ");
							continue;
						}
						o.writeUTF("200 ok file created.");
					
      					
      						byte [] mybytearray  = new byte [FILE_SIZE];
      						is = server.getInputStream();
      						fos = new FileOutputStream(filename);
     						bos = new BufferedOutputStream(fos);
		
      						bytesRead = is.read(mybytearray,0,mybytearray.length);
		
      						current = bytesRead;
						System.out.println("Now");
					
      						while(bytesRead > -1){
         						bytesRead =is.read(mybytearray, current, (mybytearray.length-current));
         						if(bytesRead >= 0) 
							current += bytesRead;
      						}
      						bos.write(mybytearray, 0 , current);
      					
      						System.out.println("File " + filename + " downloaded (" + current + " bytes read)");
					
						bos.flush();
					
						while(true){
							System.out.println("Do you wish to view the file in the command prompt or in a browser ??? ");
							System.out.println("Enter : 1) Command Prompt \n2)Browser");
							int choice = Integer.parseInt(br.readLine());
							if(choice == 1){
								FileReader fr=new FileReader(new File(filename));
								BufferedReader br1=new BufferedReader(fr);
								String s=br1.readLine();
								while(s!=null){
									System.out.println(s);
									s=br1.readLine();
								}
								break;
							}
							else if(choice==2){
								//Check whether it is an HTML file or not
								if(filename.indexOf(".html")!=-1){
									if(Desktop.isDesktopSupported())
									{
  										Desktop.getDesktop().browse(new URI("index.html"));
										break;
									}
								}
								else{
									System.out.println("Not an html file");	
					
								}
							}
							else{
								System.out.println("Wrong option. Enter again:");
							}
						}
					
   	 
					}
					else if(command.equalsIgnoreCase("shutdown")){
						if(filename.equalsIgnoreCase("server")){
							System.out.println("Server shutting down.");
							server.close();
							return;
						}
					}			       
    					server.close();		
				}
			
			catch(Exception e){
				System.out.println(e);
			}
			
		}
	}			
	public static void main(String[]args)throws Exception{
		int port = Integer.parseInt(args[0]);
		Thread t=new Server(port);
		t.start();
		
	}
}
