package ui;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import jmgl_common.DoorMainCps;
import jmgl_dutylog.DutyInfoCps;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import zhzx_common.ZhzxDutyCps;
import config.ConfigPropMananger;
import config.DataPropMananger;
import config.WindowPropsMananger;
import data.DataPackage;


public class MainWindow {
	
	private static Shell shell;
	
	public static int my_company = -1;
	public static boolean isDebug = false;
	private static String SERVERIP;
	private static int PORT;
	public static String VERSION;
	
	public static MainWindow mainWindow;
	
	private MessageThread messageThread;// 负责接收消息的线程
	private ObjectInputStream reader;
	private Socket socket;
	private ObjectOutputStream printWriter;
	private DutyInfoCps dutyInfoCps;
	private DoorMainCps doorMainCps;
	private ZhzxDutyCps zhzxDutyCps;
	public static boolean refreshLock = false;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		mainWindow = new MainWindow();
		mainWindow.open();
		
	}
	
	public static MainWindow getInstance(){
		return mainWindow;
	}
	
	public void open(){
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(socket != null) disconnectServer();
					Display.getDefault().dispose();
					System.exit(0);
				} catch (Exception e) {
					if(isDebug)e.printStackTrace();
					else showErrorMessageBox(shell,e.getMessage());
				}
			}
		});
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	protected void createContents() {
		shell = new Shell(SWT.MIN|SWT.CLOSE);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shell.setImage(SWTResourceManager.getImage("D:\\CTS\\source\\icon.png"));
		shell.setSize(WindowPropsMananger.WD_WIDTH, WindowPropsMananger.WD_HEIGHT);
		shell.setText("\u5C0F\u9F99\u6F6D\u76D1\u72F1\u6307\u6325\u4E2D\u5FC3\u7EFC\u5408\u4E1A\u52A1\u7BA1\u7406\u7CFB\u7EDF");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width)/2, (Display.getDefault().getBounds().height - shell.getBounds().height)/2);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u90E8\u95E8\u540D\u79F0");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("宋体", 24, SWT.BOLD));
		label.setBounds(10, 10, 434, 39);
		
		if(ConfigPropMananger.getInstance().LoadServerConfig()){
			SERVERIP = ConfigPropMananger.getInstance().getServerIP().trim();
	        PORT = ConfigPropMananger.getInstance().getPort();
	        my_company = ConfigPropMananger.getInstance().getUser();
	        VERSION = ConfigPropMananger.getInstance().getVersion();
	       
	        shell.setText(shell.getText() + "  ---  " + DataPropMananger.getCompanyName(my_company));
	        label.setText(DataPropMananger.getCompanyName(my_company));
	        
		}
		else {
			showErrorMessageBox(shell,"配置文件读取错误，情联系管理员！");
		}

		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(630, 10, 5, 840);
		
		doorMainCps = new DoorMainCps(shell, shell.getStyle());
		doorMainCps.setSize(406, 850);
		doorMainCps.setLocation(750,0);
		
		if(my_company > 10){
			dutyInfoCps = new DutyInfoCps(shell, shell.getStyle());
			dutyInfoCps.setSize(600, 800);
			dutyInfoCps.setLocation(15,50);
			dutyInfoCps.sendUpdateRequest();
		}else{
			zhzxDutyCps = new ZhzxDutyCps(shell, shell.getStyle());
			zhzxDutyCps.setSize(600,800);
			zhzxDutyCps.setLocation(15,50);
		}
	}
	
	private void sysDataHandler(DataPackage data){
		if(data == null) return;
		switch(data.getType()){
			case DataPropMananger.SYS_CLIENT_ERROR:
				showErrorMessageBox(shell,"客户端版本验证失败！请重启本程序！");
				disconnectServer();
				break;
		}
	}
	
	public void jmglDataHandler(DataPackage data) {
		if(data == null) return;
		if(doorMainCps == null) return;
		if(my_company != 0){
			if(my_company > 10 && dutyInfoCps == null) return;
		}
		int dataType = data.getType() % 200;
		if(dataType < 20){
			switch(data.getType()){
			case DataPropMananger.CRIMINAL_UPDATE:
				System.out.print("Client recieve UPDATE cmd \n");
				doorMainCps.criminalDataHandler(data.getCriminalData());
				disconnectServer();
				break;
			case DataPropMananger.CRIMINAL_ADD_IN_OUT:
				System.out.print("Client recieve ADD_IN_OUT cmd \n");
				doorMainCps.criminalDataHandler(data.getCriminalData());
				disconnectServer();
				break;
			case DataPropMananger.CRIMINAL_ADD_RECORD:
				System.out.print("Client recieve ADD_RECORD cmd \n");
				doorMainCps.criminalDataHandler(data.getCriminalData());
				disconnectServer();
				break;
			case DataPropMananger.CRIMINAL_CHANGE_RECORD:
				System.out.print("Client recieve CHANGE_RECORD cmd \n");
				doorMainCps.criminalDataHandler(data.getCriminalData());
				disconnectServer();
				break;
			default:
				System.out.print("Client recieve a message:" + data.getCmd() + " \n");
				break;
			}
		}
		else if((dataType > 20) && (dataType < 30)){
			switch(data.getType()){
			case DataPropMananger.VISITOR_UPDATE:
				System.out.print("Client recieve VISITOR_UPDATE cmd \n");
				doorMainCps.visitorDataHandler(data.getVisitorData());
				disconnectServer();
				break;
			case DataPropMananger.VISITOR_ADD_RECORD:
				System.out.print("Client recieve VISITOR_ADD_RECORD cmd \n");
				doorMainCps.visitorDataHandler(data.getVisitorData());
				disconnectServer();
				break;
			case DataPropMananger.VISITOR_CHANGE_RECORD:
				System.out.print("Client recieve VISITOR_CHANGE_RECORD cmd \n");
				doorMainCps.visitorDataHandler(data.getVisitorData());
				disconnectServer();
				break;
			default:
				System.out.print("Client recieve a message:" + data.getCmd() + " \n");
				break;
			}
		}
		else if(dataType > 30 && dataType < 40){
			switch(data.getType()){
			case DataPropMananger.CAR_UPDATE:
				System.out.print("Client recieve CAR_UPDATE cmd \n");
				doorMainCps.carDataHandler(data.getCarData());
				disconnectServer();
				break;
			case DataPropMananger.CAR_ADD_RECORD:
				System.out.print("Client recieve CAR_ADD_RECORD cmd \n");
				doorMainCps.carDataHandler(data.getCarData());
				disconnectServer();
				break;
			case DataPropMananger.CAR_CHANGE_RECORD:
				System.out.print("Client recieve CAR_CHANGE_RECORD cmd \n");
				doorMainCps.carDataHandler(data.getCarData());
				disconnectServer();
				break;
			default:
				System.out.print("Client recieve a message:" + data.getCmd() + " \n");
				break;
			}
		}
		else if(dataType > 40){
			switch(data.getType()){
			case DataPropMananger.JM_DUTY_GET_CURRENT:
				System.out.print("Client recieve JM_DUTY_GET_CURRENT cmd \n");
				dutyInfoCps.setData(data.getJmDutyData().get(0));
				disconnectServer();
				break;
			case DataPropMananger.JM_DUTY_CHANGE_DUTY:
				System.out.print("Client recieve JM_DUTY_GET_CURRENT cmd \n");
				dutyInfoCps.setData(data.getJmDutyData().get(0));
				disconnectServer();
				break;
			case DataPropMananger.JM_DUTY_CHANGE_INFO:
				System.out.print("Client recieve JM_DUTY_CHANGE_INFO cmd \n");
				dutyInfoCps.setInfoChangeSuccess();
				disconnectServer();
				break;
			case DataPropMananger.JM_DUTY_CHANGE_NOTICE:
				System.out.print("Client recieve JM_DUTY_CHANGE_NOTICE cmd \n");
				dutyInfoCps.setNoticeChangeSuccess();
				disconnectServer();
				break;
			case DataPropMananger.JM_DUTY_CHANGE_SAFE:
				System.out.print("Client recieve JM_DUTY_CHANGE_SAFE cmd \n");
				dutyInfoCps.setSafeChangeSuccess();
				disconnectServer();
				break;
			case DataPropMananger.JM_DUTY_GET_HISTORY:
				System.out.print("Client recieve JM_DUTY_GET_HISTORY cmd \n");
				
				if(my_company < 10) doorMainCps.dutyHistoryDataHandler(data.getJmDutyData()); 
				else dutyInfoCps.setHistoryData(data.getJmDutyData());
				disconnectServer();
				break;
			default:
				System.out.print("Client recieve a message:" + data.getCmd() + " \n");
				break;
			}
		}
	}
	
	private void zhzxDataHandler(DataPackage data){
		if(data == null) return;
		if(zhzxDutyCps == null) return;
		int dataType = data.getType() % 300;
		if(dataType < 20){
			switch (data.getType()) {
			case DataPropMananger.ZHZX_TEL_GET_ALL:
				System.out.print("Client recieve ZHZX_TEL_GET_ALL cmd \n");
				zhzxDutyCps.setTelData(data.getTelData());
				disconnectServer();
				break;
			case DataPropMananger.ZHZX_TEL_ADD:
				System.out.print("Client recieve ZHZX_TEL_ADD cmd \n");
				zhzxDutyCps.setTelAddSuccess(data.getTelData());
				disconnectServer();
				break;
			case DataPropMananger.ZHZX_TEL_UPDATE_NEXT:
				System.out.print("Client recieve ZHZX_TEL_UPDATE_NEXT cmd \n");
				zhzxDutyCps.setTelUpdateSuccess(0);
				disconnectServer();
				break;
			case DataPropMananger.ZHZX_TEL_UPDATE_BACK:
				System.out.print("Client recieve ZHZX_TEL_UPDATE_BACK cmd \n");
				zhzxDutyCps.setTelUpdateSuccess(1);
				disconnectServer();
				break;
			case DataPropMananger.ZHZX_TEL_DEL:
				System.out.print("Client recieve ZHZX_TEL_DEL cmd \n");
				zhzxDutyCps.setTelDelSuccess();
				disconnectServer();
				break;
			default:
				System.out.print("Client recieve a message:" + data.getCmd() + " \n");
				disconnectServer();
				break;
			}
		}
	}
	
	public void sendDataToServer(final DataPackage data){
		System.out.print("Client send "+data.getCmd() + "==" + data.getType() + "\n");
		
		try {
			printWriter.writeObject(data);
			printWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean connectServer() {  
		refreshLock = true;
		// 连接服务器  
        try {
        	System.out.print("Client socket connecting start \n");
        	socket = new Socket(SERVERIP, PORT);
        	System.out.print("Client socket connecting1");
            printWriter = new ObjectOutputStream(socket.getOutputStream());  
            reader = new ObjectInputStream(socket.getInputStream());  
            // 发送客户端用户基本信息(用户名和ip地址)  
            System.out.print("Client socket connecting \n");
            // 开启接收消息的线程  
            messageThread = new MessageThread(reader);  
            messageThread.start();  
            
            System.out.print("Client socket connected \n");
            
            return true;  
        } catch (Exception e) {
        	refreshLock = false;
        	System.out.print("Client socket connecte error : "+e.getMessage()+" \n");
        	if(isDebug)e.printStackTrace();
			else showErrorMessageBox(shell,"服务器连接失败，请重试");
        	System.exit(0);
            return false;  
        }  
	}
	
	public void disconnectServer(){
		refreshLock = false;
		if(!socket.isClosed())
		{
			sendDataToServer(new DataPackage(VERSION,DataPropMananger.CMD_SYSTEM,DataPropMananger.SYS_CLOSE, my_company, null));
			try {
				messageThread.closeCon();
				messageThread.stop();
				messageThread = null;
				//messageThread.destroy();
			} catch (Exception e) {
				if(isDebug)e.printStackTrace();
				else showErrorMessageBox(shell,e.getMessage());
			}
		}
	}
	
	public static void showErrorMessageBox(final Shell shell,final String msg){
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				MessageBox msgBox = new MessageBox(shell,SWT.OK|SWT.ICON_ERROR);
				msgBox.setMessage(msg);
				msgBox.setText("出错啦！");
				if(msgBox.open() == SWT.OK)
				{
					msgBox = null;
				}
			}
		});
	}
	
	class MessageThread extends Thread {  
	    private ObjectInputStream reader;  

	    // 接收消息线程的构造方法  
	    public MessageThread(ObjectInputStream reader2) {  
	        this.reader = reader2;  
	    }  

	    // 被动的关闭连接  
	    public synchronized void closeCon() throws Exception {  
	        // 被动的关闭连接释放资源  
	        if (reader != null) {  
	            reader.close();  
	        }  
	        if (printWriter != null) {  
	            printWriter.close();  
	        }  
	        if (socket != null) {  
	            socket.close();  
	            System.out.print("Client socket close :" + socket.isClosed() + " \n");
	        }
	    }  

	    public void run() {  
	        DataPackage data = null;  
	        while (socket.isConnected()) {  
	            try {  
	            	data = (DataPackage) reader.readObject(); 
	                if(data == null) return;
	                if (data.getCmd()==DataPropMananger.CMD_SYSTEM) 
	                {
	                	if(data.getType() == DataPropMananger.SYS_CLOSE){
	                		closeCon();// 被动的关闭连接
	                		return;
	                	}
	                	sysDataHandler(data);
	                } else if(data.getCmd() == DataPropMananger.CMD_JMGL) {// 普通消息  
	                	jmglDataHandler(data);
	                } else if(data.getCmd() == DataPropMananger.CMD_ZHZX) {
	                	zhzxDataHandler(data);
	                }
	            } catch (IOException e) {  
	            	if(isDebug)e.printStackTrace();
					else showErrorMessageBox(shell,e.getMessage());
	            } catch (Exception e) {  
	            	if(isDebug)e.printStackTrace();
					else showErrorMessageBox(shell,e.getMessage());
	            }  
	        } 
	    }
	}  
}
