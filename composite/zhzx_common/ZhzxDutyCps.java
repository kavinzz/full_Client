package zhzx_common;

import java.util.Vector;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import ui.MainWindow;
import zhzx_tel.TelInfoDlg;
import config.DataPropMananger;
import config.WindowPropsMananger;
import data.DataPackage;
import data.JmglDutyRecord;
import data.TelRecord;

public class ZhzxDutyCps extends Composite {
	
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
	private Label lblNewLabel_1;
	private Label label_9;
	private Text infoText;
	private Button editImpTxtButton;
	private Button editInfoTxtButton;
	private Button confirmButton;
	private Button eqpYButton;
	
	private boolean isEditImpTxt = false;
	private boolean isEidtInfoTxt = false;
	private Label label_10;
	private Button cleanYButton;
	private Button cleanNbutton;
	private Composite composite;
	private Label safeSaveStLable;
	
	
	private JmglDutyRecord dutyRecord;
	
	private TelInfoDlg telInfoDlg;
	
	private boolean isNoticeChanged = false;
	private boolean isInfoChanged = false;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ZhzxDutyCps(Composite parent, int style) {
		super(parent, SWT.BORDER);
		createContents();
	}
	
	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		lblNewLabel.setBounds(10, 65, 95, 19);
		lblNewLabel.setText("\u503C\u73ED\u4EBA\u5458\uFF1A");
		
		nameLabel = new Label(this, SWT.NONE);
		nameLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		nameLabel.setText("\u6307\u6325\u4E2D\u5FC3\u503C\u73ED\u8BB0\u5F55\u6D4B\u8BD5");
		nameLabel.setFont(SWTResourceManager.getFont("·ÂËÎ", 20, SWT.BOLD));
		nameLabel.setBounds(111, 61, 460, 27);
		
		label = new Label(this, SWT.NONE);
		label.setText("\u503C\u73ED\u65F6\u95F4\uFF1A");
		label.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		label.setBounds(10, 116, 95, 19);
		
		timeStartLable = new Label(this, SWT.BORDER);
		timeStartLable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		timeStartLable.setText("2015\u5E7412\u670812\u65E5 24\uFF1A00\uFF1A00");
		timeStartLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		timeStartLable.setFont(SWTResourceManager.getFont("·ÂËÎ", 20, SWT.BOLD));
		timeStartLable.setBounds(111, 113, 370, 27);
		
		label_1 = new Label(this, SWT.NONE);
		label_1.setText("\u81F3");
		label_1.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		label_1.setBounds(287, 145, 19, 19);
		
		timeEndLable = new Label(this, SWT.BORDER);
		timeEndLable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		timeEndLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		timeEndLable.setFont(SWTResourceManager.getFont("·ÂËÎ", 20, SWT.BOLD));
		timeEndLable.setBounds(111, 167, 370, 27);
		
		label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u91CD\u8981\u4E8B\u9879\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		label_3.setBounds(10, 208, 95, 19);
		
		impText = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		impText.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		impText.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
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
		label_4.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		label_4.setBounds(10, 572, 95, 42);
		
		eqpText = new Text(this, SWT.BORDER);
		eqpText.setVisible(false);
		eqpText.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		eqpText.setBounds(268, 580, 303, 27);
		
		lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("·ÂËÎ", 24, SWT.BOLD));
		lblNewLabel_1.setBounds(155, 10, 264, 33);
		lblNewLabel_1.setText("\u6307\u6325\u4E2D\u5FC3\u503C\u73ED\u65E5\u5FD7");
		
		label_9 = new Label(this, SWT.NONE);
		label_9.setText("\u503C\u73ED\u60C5\u51B5\uFF1A");
		label_9.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		label_9.setBounds(10, 356, 95, 19);
		
		infoText = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		infoText.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		infoText.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		infoText.setBounds(111, 356, 460, 130);
		infoText.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent arg0) {
				// TODO Auto-generated method stub
				isInfoChanged = true;
			}
		});
		
		editImpTxtButton = new Button(this, SWT.NONE);
		editImpTxtButton.setEnabled(false);
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
						editImpTxtButton.setText("±à¼­");
					}
				}
				else {
					isEditImpTxt = true;
					impText.setEditable(true);
					editImpTxtButton.setText("±£´æ");
					impText.setFocus();
				}
			}
		});
		editImpTxtButton.setFont(SWTResourceManager.getFont("ËÎÌå", 12, SWT.NORMAL));
		editImpTxtButton.setBounds(20, 233, 61, 27);
		editImpTxtButton.setText("\u7F16\u8F91");
		
		editInfoTxtButton = new Button(this, SWT.NONE);
		editInfoTxtButton.setEnabled(false);
		editInfoTxtButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isEidtInfoTxt){
					if(isInfoChanged) sendInfoChangeRequest();
					else{
						isEidtInfoTxt = false;
						infoText.setEditable(false);
						editInfoTxtButton.setText("±à¼­");
					}
				}
				else {
					isEidtInfoTxt = true;
					infoText.setEditable(true);
					editInfoTxtButton.setText("±£´æ");
					infoText.setFocus();
				}
			}
		});
		editInfoTxtButton.setText("\u7F16\u8F91");
		editInfoTxtButton.setFont(SWTResourceManager.getFont("ËÎÌå", 12, SWT.NORMAL));
		editInfoTxtButton.setBounds(20, 381, 61, 27);
		
		confirmButton = new Button(this, SWT.NONE);
		confirmButton.setEnabled(false);
		confirmButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		confirmButton.setFont(SWTResourceManager.getFont("ËÎÌå", 16, SWT.BOLD));
		confirmButton.setBounds(190, 736, 195, 42);
		confirmButton.setText("\u4EA4 \u63A5 \u73ED");
		
		label_10 = new Label(this, SWT.WRAP | SWT.RIGHT);
		label_10.setText("\u76D1\u95E8\u536B\u751F\u6253\u626B\u60C5\u51B5\uFF1A");
		label_10.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		label_10.setBounds(10, 641, 95, 42);
		
		cleanYButton = new Button(this, SWT.RADIO);
		cleanYButton.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		cleanYButton.setBounds(122, 653, 76, 17);
		cleanYButton.setText("\u5DF2\u6253\u626B");
		
		cleanNbutton = new Button(this, SWT.RADIO);
		cleanNbutton.setSelection(true);
		cleanNbutton.setText("\u672A\u6253\u626B");
		cleanNbutton.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
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
		eqpYButton.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
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
		eqpNButton.setFont(SWTResourceManager.getFont("ËÎÌå", 14, SWT.NORMAL));
		
		safeSaveStLable = new Label(this, SWT.NONE);
		safeSaveStLable.setFont(SWTResourceManager.getFont("ËÎÌå", 12, SWT.NORMAL));
		safeSaveStLable.setBounds(503, 520, 68, 16);
		
		Button telButton = new Button(this, SWT.NONE);
		telButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				telInfoDlg = new TelInfoDlg(getShell(), getStyle());
				if(telInfoDlg.open() ==WindowPropsMananger.CLOSE_FLAG){
					telInfoDlg = null;
				}
			}
		});
		telButton.setFont(SWTResourceManager.getFont("ËÎÌå", 12, SWT.NORMAL));
		telButton.setBounds(111, 509, 114, 33);
		telButton.setText("\u7535\u8BDD\u8BB0\u5F55");
		
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
	
	public void setTelData(Vector<TelRecord> telRecords){
		if(telInfoDlg == null) return;
		telInfoDlg.telDataGetHandler(telRecords);
	}
	
	public void setTelAddSuccess(Vector<TelRecord> tRecords){
		if(telInfoDlg == null) return;
		telInfoDlg.telAddSuccess(tRecords);
	}
	
	public void setTelUpdateSuccess(int i){
		if(telInfoDlg == null) return;
		telInfoDlg.telUpdateSuccess(i);
	}
	
	public void setTelDelSuccess(){
		if(telInfoDlg == null) return;
		telInfoDlg.telDelSuccess();;
	}
	
	public void setInfoChangeSuccess(){
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				isEidtInfoTxt = false;
				infoText.setEditable(false);
				editInfoTxtButton.setText("±à¼­");
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
				editImpTxtButton.setText("±à¼­");
				isNoticeChanged = false;
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
		jmglDutyRecord.setUuid(dutyRecord.getUuid());
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
