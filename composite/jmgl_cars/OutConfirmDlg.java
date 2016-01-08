package jmgl_cars;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import ui.MainWindow;
import common.HandWriter;
import config.DataPropMananger;
import config.WindowPropsMananger;
import data.CarRecord;
import data.DataPackage;

public class OutConfirmDlg extends Dialog {

	protected Object result;
	protected Shell shell;
	private Button watcherButton;
	private Button doorButton;
	private CarRecord tempRecord;
	
	private byte[] watcherHW;
	private byte[] doorHW;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public OutConfirmDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public int open(CarRecord cr) {
		tempRecord = cr;
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return WindowPropsMananger.CLOSE_FLAG;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.setSize(512, 179);
		shell.setText("\u8BF7\u7B7E\u540D");
		shell.setLocation((Display.getCurrent().getBounds().width - shell.getBounds().width)/2, (Display.getCurrent().getBounds().height - shell.getBounds().height)/2);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u5E26\u9886\u8B66\u5BDF\uFF1A");
		label.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label.setBounds(10, 10, 89, 19);
		
		watcherButton = new Button(shell, SWT.NONE);
		watcherButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HandWriter hWriter = new HandWriter();
				watcherHW = hWriter.open();
				if(watcherHW == null){
					watcherButton.setText("点击按钮进行签名");
					MainWindow.showErrorMessageBox(shell, "带领警察签名读取错误，请重签!");
				}
				else watcherButton.setText("签名已保存！"); 
			}
		});
		watcherButton.setText("\u70B9\u51FB\u6309\u94AE\u8FDB\u884C\u7B7E\u540D");
		watcherButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		watcherButton.setBounds(105, 10, 140, 68);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u76D1\u95E8\u8B66\u5BDF\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_1.setBounds(258, 10, 89, 19);
		
		doorButton = new Button(shell, SWT.NONE);
		doorButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HandWriter hWriter = new HandWriter();
				doorHW = hWriter.open();
				if(doorHW == null){
					doorButton.setText("点击按钮进行签名");
					MainWindow.showErrorMessageBox(shell, "监门警察签名读取错误，请重签!");
				}
				else doorButton.setText("签名已保存！"); 
			}
		});
		
		doorButton.setText("\u70B9\u51FB\u6309\u94AE\u8FDB\u884C\u7B7E\u540D");
		doorButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		doorButton.setBounds(352, 10, 140, 68);
		
		Button okBtn = new Button(shell, SWT.NONE);
		okBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(doorHW != null && watcherHW != null){
					sendChangeRequest();
					close();
				}else{
					MainWindow.showErrorMessageBox(shell, "警察签名读取错误，请重签!");
				}
			}
		});
		okBtn.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		okBtn.setBounds(138, 110, 89, 28);
		okBtn.setText("\u63D0\u4EA4");
		
		Button cancelBtn = new Button(shell, SWT.NONE);
		cancelBtn.setText("\u53D6\u6D88");
		cancelBtn.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		cancelBtn.setBounds(284, 110, 89, 28);

	}
	
	private void sendChangeRequest(){
		if(MainWindow.getInstance().connectServer()){
			MainWindow.getInstance().sendDataToServer(new DataPackage(MainWindow.VERSION, DataPropMananger.CMD_JMGL,
					DataPropMananger.CAR_CHANGE_RECORD, MainWindow.my_company, packRecords()));
		}
	}
	

	
	private Vector<CarRecord> packRecords(){
		if(tempRecord == null) return null;
		
		Vector<CarRecord> carRecords = new Vector<CarRecord>();
		CarRecord cRecord = new CarRecord();
		cRecord.setUuid(tempRecord.getUuid());
		cRecord.setOut_doorHW(doorHW);
		cRecord.setOut_watcherHW(watcherHW);
		carRecords.add(cRecord);
		
		return carRecords;
	}
	
	public void close(){
		shell.close();
		shell.dispose();
	}
}
