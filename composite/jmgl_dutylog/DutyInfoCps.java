package jmgl_dutylog;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import ui.MainWindow;
import config.DataPropMananger;
import config.WindowPropsMananger;
import data.DataPackage;
import data.JmglDutyRecord;

public class DutyInfoCps extends Composite {
	
	private Label nameLabel;
	private Label label;
	private Label timeStartLable;
	private Label label_1;
	private Label timeEndLable;
	private Label label_3;
	private Text impText;
	private Label label_4;
	private Button eqpNButton;
	private Text eqpText;
	private Label label_5;
	private Label label_6;
	private Label menNumLable;
	private Label label_7;
	private Label carNumLable;
	private Label label_8;
	private Label lblNewLabel_1;
	private Label label_9;
	private Text infoText;
	private Button editImpTxtButton;
	private Button editInfoTxtButton;
	private Button confirmButton;
	private Button eqpYButton;
	
	private boolean isEditImpTxt = false;
	private boolean isEidtInfoTxt = false;
	private Button saveButton;
	private Label label_10;
	private Button cleanYButton;
	private Button cleanNbutton;
	private Composite composite;
	private Label safeSaveStLable;
	
	
	private JmglDutyRecord dutyRecord;
	private DutyHistoryDlg dutyHistoryDlg;
	
	private boolean isSafeChanged = false;
	private boolean isNoticeChanged = false;
	private boolean isInfoChanged = false;
	private Button button;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DutyInfoCps(Composite parent, int style) {
		super(parent, SWT.BORDER);
		createContents();
	}
	
	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel.setBounds(10, 65, 95, 19);
		lblNewLabel.setText("\u503C\u73ED\u4EBA\u5458\uFF1A");
		
		nameLabel = new Label(this, SWT.NONE);
		nameLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		nameLabel.setText("\u6570\u636E\u83B7\u53D6\u4E2D\uFF0C\u8BF7\u7A0D\u7B49......");
		nameLabel.setFont(SWTResourceManager.getFont("仿宋", 20, SWT.BOLD));
		nameLabel.setBounds(111, 61, 460, 27);
		
		label = new Label(this, SWT.NONE);
		label.setText("\u503C\u73ED\u65F6\u95F4\uFF1A");
		label.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label.setBounds(10, 116, 95, 19);
		
		timeStartLable = new Label(this, SWT.BORDER);
		timeStartLable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		timeStartLable.setText("2015\u5E7412\u670812\u65E5 24\uFF1A00\uFF1A00");
		timeStartLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		timeStartLable.setFont(SWTResourceManager.getFont("仿宋", 20, SWT.BOLD));
		timeStartLable.setBounds(111, 113, 370, 27);
		
		label_1 = new Label(this, SWT.NONE);
		label_1.setText("\u81F3");
		label_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_1.setBounds(287, 145, 19, 19);
		
		timeEndLable = new Label(this, SWT.BORDER);
		timeEndLable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		timeEndLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		timeEndLable.setFont(SWTResourceManager.getFont("仿宋", 20, SWT.BOLD));
		timeEndLable.setBounds(111, 167, 370, 27);
		
		label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u91CD\u8981\u4E8B\u9879\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_3.setBounds(10, 208, 95, 19);
		
		impText = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		impText.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		impText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		impText.setBounds(111, 208, 460, 130);
		impText.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent arg0) {
				// TODO Auto-generated method stub
				isNoticeChanged = true;
			}
		});
		
		label_4 = new Label(this, SWT.WRAP | SWT.RIGHT);
		label_4.setText("\u8BBE\u5907\u8BBE\u65BD\u5DE1\u68C0\u60C5\u51B5\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_4.setBounds(10, 572, 95, 42);
		
		eqpText = new Text(this, SWT.BORDER);
		eqpText.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		eqpText.setVisible(false);
		eqpText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		eqpText.setBounds(268, 577, 303, 27);
		
		label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u5B89\u68C0\u60C5\u51B5\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_5.setBounds(10, 519, 95, 19);
		
		label_6 = new Label(this, SWT.NONE);
		label_6.setText("\u672C\u73ED\u5B89\u68C0");
		label_6.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_6.setBounds(111, 519, 76, 19);
		
		menNumLable = new Label(this, SWT.BORDER | SWT.CENTER);
		menNumLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		menNumLable.setText("0");
		menNumLable.setFont(SWTResourceManager.getFont("宋体", 22, SWT.BOLD));
		menNumLable.setBounds(189, 508, 48, 33);
		menNumLable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.button == 1){
					menNumLable.setText(String.valueOf(Integer.parseInt(menNumLable.getText()) + 1));
				}
				else menNumLable.setText(String.valueOf(Integer.parseInt(menNumLable.getText()) - 1));
				menNumLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				safeSaveStLable.setText("未保存");
				isSafeChanged = true;
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		label_7 = new Label(this, SWT.NONE);
		label_7.setText("\u4EBA\u6B21\uFF0C\u8F66\u8F86");
		label_7.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_7.setBounds(236, 519, 95, 19);
		
		carNumLable = new Label(this, SWT.BORDER | SWT.CENTER);
		carNumLable.setToolTipText("\u5DE6\u952E\u70B9\u51FB+1\uFF0C\u53F3\u952E\u70B9\u51FB-1");
		carNumLable.setText("0");
		carNumLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		carNumLable.setFont(SWTResourceManager.getFont("宋体", 22, SWT.BOLD));
		carNumLable.setBounds(332, 508, 48, 33);
		carNumLable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.button == 1) carNumLable.setText(String.valueOf(Integer.parseInt(carNumLable.getText()) + 1));
				else carNumLable.setText(String.valueOf(Integer.parseInt(carNumLable.getText()) - 1));
				carNumLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				safeSaveStLable.setText("未保存");
				isSafeChanged = true;
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		label_8 = new Label(this, SWT.NONE);
		label_8.setText("\u8F86\u6B21\u3002");
		label_8.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_8.setBounds(381, 519, 48, 19);
		
		lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("仿宋", 24, SWT.BOLD));
		lblNewLabel_1.setBounds(153, 10, 269, 33);
		lblNewLabel_1.setText("\u76D1\u95E8\u7BA1\u7406\u503C\u73ED\u65E5\u5FD7");
		
		label_9 = new Label(this, SWT.NONE);
		label_9.setText("\u503C\u73ED\u60C5\u51B5\uFF1A");
		label_9.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_9.setBounds(10, 356, 95, 19);
		
		infoText = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		infoText.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		infoText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		infoText.setBounds(111, 356, 460, 130);
		infoText.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent arg0) {
				// TODO Auto-generated method stub
				isInfoChanged = true;
			}
		});
		
		editImpTxtButton = new Button(this, SWT.NONE);
		editImpTxtButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isEditImpTxt){
					if(isNoticeChanged){
						sendNoticeChangeRequest();
					}
					else{
						isEditImpTxt = false;
						impText.setEditable(false);
						editImpTxtButton.setText("编辑");
					}
				}
				else {
					isEditImpTxt = true;
					impText.setEditable(true);
					editImpTxtButton.setText("保存");
					impText.setFocus();
				}
			}
		});
		editImpTxtButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		editImpTxtButton.setBounds(20, 233, 61, 27);
		editImpTxtButton.setText("\u7F16\u8F91");
		
		editInfoTxtButton = new Button(this, SWT.NONE);
		editInfoTxtButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isEidtInfoTxt){
					if(isInfoChanged) sendInfoChangeRequest();
					else{
						isEidtInfoTxt = false;
						infoText.setEditable(false);
						editInfoTxtButton.setText("编辑");
					}
				}
				else {
					isEidtInfoTxt = true;
					infoText.setEditable(true);
					editInfoTxtButton.setText("保存");
					infoText.setFocus();
				}
			}
		});
		editInfoTxtButton.setText("\u7F16\u8F91");
		editInfoTxtButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		editInfoTxtButton.setBounds(20, 381, 61, 27);
		
		confirmButton = new Button(this, SWT.NONE);
		confirmButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DutyChangeDlg dcDutyChangeDlg = new DutyChangeDlg(getParent().getShell(), SWT.CLOSE);
				dutyRecord.setEqpOk(eqpYButton.getSelection());
				if(!eqpYButton.getSelection()) dutyRecord.setEqpInfo(eqpText.getText());
				dutyRecord.setClean(cleanYButton.getSelection());
				if(dcDutyChangeDlg.open(dutyRecord) == WindowPropsMananger.CLOSE_FLAG){
					dcDutyChangeDlg = null;
				}
			}
		});
		confirmButton.setFont(SWTResourceManager.getFont("宋体", 16, SWT.BOLD));
		confirmButton.setBounds(190, 736, 195, 42);
		confirmButton.setText("\u4EA4 \u63A5 \u73ED");
		
		saveButton = new Button(this, SWT.NONE);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!isSafeChanged) return;
				saveButton.setEnabled(false);
				sendSafeChangeRequest();
			}
		});
		saveButton.setText("\u4FDD\u5B58");
		saveButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		saveButton.setBounds(435, 515, 68, 27);
		
		label_10 = new Label(this, SWT.WRAP | SWT.RIGHT);
		label_10.setText("\u76D1\u95E8\u536B\u751F\u6253\u626B\u60C5\u51B5\uFF1A");
		label_10.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_10.setBounds(10, 641, 95, 42);
		
		cleanYButton = new Button(this, SWT.RADIO);
		cleanYButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		cleanYButton.setBounds(122, 653, 76, 17);
		cleanYButton.setText("\u5DF2\u6253\u626B");
		
		cleanNbutton = new Button(this, SWT.RADIO);
		cleanNbutton.setSelection(true);
		cleanNbutton.setText("\u672A\u6253\u626B");
		cleanNbutton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		cleanNbutton.setBounds(209, 653, 97, 17);
		
		composite = new Composite(this, SWT.NONE);
		composite.setBounds(119, 572, 143, 42);
		
		eqpYButton = new Button(composite, SWT.RADIO);
		eqpYButton.setSelection(true);
		eqpYButton.setBounds(0, 10, 61, 17);
		eqpYButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eqpText.setText("");
				eqpText.setVisible(false);
			}
		});
		eqpYButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		eqpYButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		eqpYButton.setText("\u6B63\u5E38");
		
		eqpNButton = new Button(composite, SWT.RADIO);
		eqpNButton.setBounds(82, 10, 61, 17);
		eqpNButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eqpText.setVisible(true);
			}
		});
		eqpNButton.setText("\u5F02\u5E38");
		eqpNButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		eqpNButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		
		safeSaveStLable = new Label(this, SWT.NONE);
		safeSaveStLable.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		safeSaveStLable.setBounds(505, 520, 68, 16);
		
		button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(dutyHistoryDlg == null)
				{
					dutyHistoryDlg = new DutyHistoryDlg(getShell(), SWT.CLOSE);
					if(dutyHistoryDlg.open() == WindowPropsMananger.CLOSE_FLAG){
						dutyHistoryDlg = null;
					}
				}
				
				
			}
		});
		button.setText("\u65E5\u5FD7\u67E5\u8BE2");
		button.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		button.setBounds(488, 10, 83, 27);
	}
	

	public void setData(final JmglDutyRecord dRecord){
		if(dRecord == null) return;
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				dutyRecord = dRecord;
				nameLabel.setText(dRecord.getName());
				nameLabel.setToolTipText(dRecord.getUuid() + "");
				timeStartLable.setText(dRecord.getStartTime());
				if(dRecord.getEndTime() !=null) timeEndLable.setText(dRecord.getEndTime());
				if(dRecord.getNotice() != null) impText.setText(dRecord.getNotice());
				if(dRecord.getInfo() != null) infoText.setText(dRecord.getInfo());
				else infoText.setText("");
				menNumLable.setText(String.valueOf(dRecord.getSafeMenNum()));
				carNumLable.setText(String.valueOf(dRecord.getSafeCarNum()));
				if(dRecord.isEqpOk()){
					eqpYButton.setSelection(true);
					eqpNButton.setSelection(false);
					eqpText.setVisible(false);
				}else{
					eqpYButton.setSelection(false);
					eqpNButton.setSelection(true);
					if(dRecord.getEqpInfo() != null){
						eqpText.setText(dRecord.getEqpInfo());
						eqpText.setVisible(true);
					}
				}
				cleanYButton.setSelection(dRecord.isClean());
				cleanNbutton.setSelection(!dRecord.isClean());
				
			}
		});
	}
	
	public void setHistoryData(Vector<JmglDutyRecord> dutyRecords){
		if(dutyHistoryDlg != null) dutyHistoryDlg.setData(dutyRecords);
	}
	
	public void setInfoChangeSuccess(){
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				isEidtInfoTxt = false;
				infoText.setEditable(false);
				editInfoTxtButton.setText("编辑");
				isInfoChanged = false;
			}
		});
	}
	
	public void setNoticeChangeSuccess(){
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				isEditImpTxt = false;
				impText.setEditable(false);
				editImpTxtButton.setText("编辑");
				isNoticeChanged = false;
			}
		});
	}
	
	public void setSafeChangeSuccess(){
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				carNumLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				menNumLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				safeSaveStLable.setText("已保存");
				saveButton.setEnabled(true);
				isSafeChanged = false;
			}
		});
	}
	
	public void sendUpdateRequest(){
		if(MainWindow.getInstance().connectServer())
		{
			DataPackage data = new DataPackage(MainWindow.VERSION,DataPropMananger.CMD_JMGL,DataPropMananger.JM_DUTY_GET_CURRENT,MainWindow.my_company, null);
			MainWindow.getInstance().sendDataToServer(data);
		}
	}
	
	private void sendInfoChangeRequest(){
		Vector<JmglDutyRecord> jmglDutyRecords = new Vector<JmglDutyRecord>();
		JmglDutyRecord jmglDutyRecord = new JmglDutyRecord();
		jmglDutyRecord.setUuid(dutyRecord.getUuid());
		jmglDutyRecord.setInfo(infoText.getText());
		jmglDutyRecords.add(jmglDutyRecord);
		if(MainWindow.getInstance().connectServer())
		{
			DataPackage data = new DataPackage(MainWindow.VERSION,DataPropMananger.CMD_JMGL,DataPropMananger.JM_DUTY_CHANGE_INFO,MainWindow.my_company, jmglDutyRecords);
			MainWindow.getInstance().sendDataToServer(data);
		}
	}
	
	private void sendNoticeChangeRequest(){
		Vector<JmglDutyRecord> jmglDutyRecords = new Vector<JmglDutyRecord>();
		JmglDutyRecord jmglDutyRecord = new JmglDutyRecord();
		jmglDutyRecord.setStarter(MainWindow.my_company);
		jmglDutyRecord.setNotice(impText.getText());
		jmglDutyRecords.add(jmglDutyRecord);
		if(MainWindow.getInstance().connectServer())
		{
			DataPackage data = new DataPackage(MainWindow.VERSION,DataPropMananger.CMD_JMGL,DataPropMananger.JM_DUTY_CHANGE_NOTICE,MainWindow.my_company, jmglDutyRecords);
			MainWindow.getInstance().sendDataToServer(data);
		}
	}
	
	private void sendSafeChangeRequest(){
		Vector<JmglDutyRecord> jmglDutyRecords = new Vector<JmglDutyRecord>();
		JmglDutyRecord jmglDutyRecord = new JmglDutyRecord();
		jmglDutyRecord.setUuid(dutyRecord.getUuid());
		jmglDutyRecord.setSafeMenNum(Integer.parseInt(menNumLable.getText()));
		jmglDutyRecord.setSafeCarNum(Integer.parseInt(carNumLable.getText()));
		jmglDutyRecords.add(jmglDutyRecord);
		if(MainWindow.getInstance().connectServer())
		{
			DataPackage data = new DataPackage(MainWindow.VERSION,DataPropMananger.CMD_JMGL,DataPropMananger.JM_DUTY_CHANGE_SAFE,MainWindow.my_company, jmglDutyRecords);
			MainWindow.getInstance().sendDataToServer(data);
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
