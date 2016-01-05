package jmgl_cars;

import java.util.Vector;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import config.DataPropMananger;
import config.WindowPropsMananger;
import data.DataPackage;
import data.CarRecord;
import ui.MainWindow;
import common.HandWriter;

public class AddCarDlg extends Dialog {

	protected Shell shell;
	private Combo idTypeCombo;
	
	private Text nameText;
	
	private byte[] watcherHW;
	private byte[] doorHW;
	
	private Text companyText;
	private Text idNumText;
	private Text reasonText;
	private Text carIdText;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddCarDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public int open() {
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
	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.setText("\u65B0\u589E\u8F66\u8F86\u8FDB\u51FA\u8BB0\u5F55");
		shell.setSize(515, 370);
		shell.setLocation((Display.getCurrent().getBounds().width - shell.getBounds().width)/2, (Display.getCurrent().getBounds().height - shell.getBounds().height)/2);
		
		companyText = new Text(shell, SWT.BORDER);
		companyText.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		companyText.setBounds(100, 100, 165, 30);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setText("\u5355\u4F4D\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_1.setBounds(5, 105, 89, 19);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u8FDB\u76D1\u4E8B\u7531\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_3.setBounds(5, 197, 89, 19);
		
		Label idtypelable = new Label(shell, SWT.NONE);
		idtypelable.setText("\u8BC1\u4EF6\u7C7B\u578B\uFF1A");
		idtypelable.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		idtypelable.setBounds(5, 152, 89, 19);
		
		idTypeCombo = new Combo(shell, SWT.NONE);
		idTypeCombo.setItems(new String[] {"\u8EAB\u4EFD\u8BC1", "\u5DE5\u4F5C\u8BC1", "\u8B66\u5B98\u8BC1", "\u9A7E\u7167"});
		idTypeCombo.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		idTypeCombo.setBounds(100, 149, 89, 27);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u5E26\u9886\u8B66\u5BDF\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_4.setBounds(5, 244, 89, 19);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u76D1\u95E8\u8B66\u5BDF\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_5.setBounds(246, 244, 89, 19);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				shell.dispose();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		btnNewButton.setBounds(414, 307, 89, 29);
		btnNewButton.setText("\u53D6 \u6D88");
		
		Button button_4 = new Button(shell, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String errMsg = checkInfo();
				if(errMsg.equals("")){
					MessageBox msgBox = new MessageBox(shell,SWT.ICON_WARNING|SWT.OK|SWT.CANCEL);
					msgBox.setText("请确认");
					msgBox.setMessage("是否提交本次进出记录？");
					if(msgBox.open() == SWT.OK){
						sendAddRequest();
						close();
					}
				}
				else MainWindow.showErrorMessageBox(shell, errMsg);
			}
		});
		button_4.setText("\u786E \u5B9A");
		button_4.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		button_4.setBounds(314, 307, 89, 29);
		
		Composite nameComposite = new Composite(shell, SWT.NONE);
		nameComposite.setBounds(5, 10, 355, 30);
		
		nameText = new Text(nameComposite, SWT.BORDER);
		nameText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		nameText.setBounds(167, 0, 178, 29);
		
		Label label_7 = new Label(nameComposite, SWT.NONE);
		label_7.setText("\u8BF7\u8F93\u5165\u9A7E\u9A76\u5458\u59D3\u540D\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_7.setBounds(0, 5, 163, 19);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u8BC1\u4EF6\u53F7\u7801\uFF1A");
		label.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label.setBounds(195, 152, 89, 19);
		
		idNumText = new Text(shell, SWT.BORDER);
		idNumText.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		idNumText.setBounds(286, 147, 217, 30);
		
		reasonText = new Text(shell, SWT.BORDER);
		reasonText.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		reasonText.setBounds(100, 192, 403, 30);
		
		final Button watcherBtn = new Button(shell, SWT.NONE);
		watcherBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HandWriter hWriter = new HandWriter();
				watcherHW = hWriter.open();
				if(watcherHW == null) MainWindow.showErrorMessageBox(shell, "带领警察签名读取错误，请重签!");
				else watcherBtn.setText("签名已保存！"); 
			}
		});
		watcherBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		watcherBtn.setBounds(100, 242, 140, 51);
		watcherBtn.setText("\u70B9\u51FB\u6309\u94AE\u8FDB\u884C\u7B7E\u540D");
		
		final Button doorBtn = new Button(shell, SWT.NONE);
		doorBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HandWriter hWriter = new HandWriter();
				doorHW = hWriter.open();
				if(doorHW == null) MainWindow.showErrorMessageBox(shell, "监门警察签名读取错误，请重签!");
				else doorBtn.setText("签名已保存！"); 
			}
		});
		doorBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		doorBtn.setText("\u70B9\u51FB\u6309\u94AE\u8FDB\u884C\u7B7E\u540D");
		doorBtn.setBounds(341, 242, 162, 51);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u8F66\u724C\u53F7\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_2.setAlignment(SWT.RIGHT);
		label_2.setBounds(5, 61, 89, 19);
		
		carIdText = new Text(shell, SWT.BORDER);
		carIdText.setText("\u4E91G");
		carIdText.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		carIdText.setBounds(100, 55, 165, 30);
		carIdText.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent arg0) {
				// TODO Auto-generated method stub
				arg0.text = arg0.text.toUpperCase();
			}
		});

	}
	
	protected Vector<CarRecord> getCarRecords(){
		Vector<CarRecord> carRecords = new Vector<CarRecord>();
		CarRecord carRecord = new CarRecord();
		carRecord.setCarId(carIdText.getText());
		carRecord.setDriverName(nameText.getText());
		carRecord.setIdType(idTypeCombo.getText());
		carRecord.setIdNum(idNumText.getText());
		carRecord.setCompany(companyText.getText());
		carRecord.setSt_reason(reasonText.getText());
		carRecord.setWatherHW(watcherHW);
		carRecord.setDoorHW(doorHW);
		carRecord.setStarter(MainWindow.my_company);
		carRecords.add(carRecord);
		
		return carRecords;
	}
	
	protected String checkInfo(){
		String errMsg = "";
		
		if(carIdText.getText().trim().length() < 5) return "车牌号填写错误！";
		
		if(nameText.getText().trim().equals("")) return "请填写驾驶员姓名！";
		
		if(reasonText.getText().trim().equals("")) return "请填写进监事由！";
		
		if(watcherHW == null) return "带领警察签名读取错误！请重签！";
		if(doorHW == null) return "监门警察签名读取错误！请重签！";
		
		return errMsg;
	}
	
	protected void sendAddRequest(){
		if(MainWindow.getInstance().connectServer()){
			MainWindow.getInstance().sendDataToServer(new DataPackage(MainWindow.VERSION, DataPropMananger.CMD_JMGL,
					DataPropMananger.CAR_ADD_RECORD, MainWindow.my_company, getCarRecords()));
		}
	}
	
	private void close(){
		shell.close();
		shell.dispose();
	}
}
