package jmgl_criminal;

import java.util.Vector;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import config.DataPropMananger;
import config.WindowPropsMananger;
import data.CriminalRecord;
import data.DataPackage;
import ui.MainWindow;

import org.eclipse.swt.widgets.Spinner;

import common.HandWriter;

public class CriminalInfoDlg extends Dialog {

	protected Shell shell;
	private Label label;
	private Combo companyCombo;
	private Composite changeComposite;
	private Spinner numSpinner;
	private List list;
	private Combo destCombo;
	private Combo reasonCombo;
	private Button inButton;
	private Button womanButton;
	private Button manbutton;
	private Button outButton;
	
	private boolean nameType = false;
	
	private Text nameText;
	private Text otherText;
	private Combo starterCombo;
	
	private byte[] watcherHW;
	private byte[] doorHW;
	
	private int defaultNum = 5;
	private Button watcherButton;
	private Button doorButton;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CriminalInfoDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public int open(boolean type) {
		nameType = type;
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
	
	public int open(boolean type,int num) {
		nameType = type;
		defaultNum = num;
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
		shell.setText("\u65B0\u589E\u7F6A\u72AF\u8FDB\u51FA\u8BB0\u5F55");
		shell.setSize(584, 360);
		shell.setLocation((Display.getCurrent().getBounds().width - shell.getBounds().width)/2, (Display.getCurrent().getBounds().height - shell.getBounds().height)/2);
		
		label = new Label(shell, SWT.NONE);
		label.setText("\u7F6A\u72AF\u6027\u522B\uFF1A");
		label.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label.setBounds(5, 56, 89, 19);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u7F6A\u72AF\u6240\u5C5E\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_1.setBounds(5, 103, 89, 19);
		
		companyCombo = new Combo(shell, SWT.READ_ONLY);
		companyCombo.setItems(new String[] {"\u4E00\u5206\u76D1\u72F1", "\u4E8C\u5206\u76D1\u72F1", "\u4E09\u5206\u76D1\u72F1", "\u56DB\u5206\u76D1\u72F1", "\u4E94\u5206\u76D1\u72F1", "\u4E2D\u5FC3\u533B\u9662"});
		companyCombo.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		companyCombo.setBounds(100, 99, 110, 27);
		companyCombo.select(0);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u8FDB \u6216 \u51FA\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_2.setBounds(253, 103, 89, 19);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(100, 48, 101, 35);
		
		manbutton = new Button(composite, SWT.RADIO);
		manbutton.setText("\u7537");
		manbutton.setSelection(true);
		manbutton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		manbutton.setBounds(0, 10, 42, 17);
		
		womanButton = new Button(composite, SWT.RADIO);
		womanButton.setText("\u5973");
		womanButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		womanButton.setBounds(48, 10, 42, 17);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBounds(347, 94, 90, 35);
		
		inButton = new Button(composite_1, SWT.RADIO);
		inButton.setText("\u8FDB");
		inButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		inButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		inButton.setBounds(0, 10, 42, 17);
		
		outButton = new Button(composite_1, SWT.RADIO);
		outButton.setSelection(true);
		outButton.setText("\u51FA");
		outButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		outButton.setBounds(48, 10, 42, 17);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u51FA\u5165\u539F\u56E0\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_3.setBounds(5, 155, 89, 19);
		
		reasonCombo = new Combo(shell, SWT.NONE);
		reasonCombo.setItems(new String[] {"\u4F4F\u9662", "\u51FA\u9662", "\u4E2D\u5FC3\u533B\u9662\u770B\u75C5", "\u56DE\u76D1", "\u65B0\u6536\u62BC", "\u53C2\u52A0\u6D3B\u52A8", "\u6280\u80FD\u57F9\u8BAD", "\u5916\u51FA\u5C31\u533B", "\u5206\u76D1\u72F1\u8C03\u52A8", "\u4FDD\u5916\u5C31\u533B"});
		reasonCombo.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		reasonCombo.setBounds(100, 151, 140, 27);
		reasonCombo.setText("\u8BF7\u9009\u62E9");
		
		Label destlable = new Label(shell, SWT.NONE);
		destlable.setText("\u76EE \u7684 \u5730\uFF1A");
		destlable.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		destlable.setBounds(253, 155, 89, 19);
		
		destCombo = new Combo(shell, SWT.READ_ONLY);
		destCombo.setItems(new String[] {"\u4E00\u5206\u76D1\u72F1", "\u4E8C\u5206\u76D1\u72F1", "\u4E09\u5206\u76D1\u72F1", "\u56DB\u5206\u76D1\u72F1", "\u4E94\u5206\u76D1\u72F1", "\u4E2D\u5FC3\u533B\u9662", "\u5176\u4ED6"});
		destCombo.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		destCombo.setBounds(347, 152, 110, 27);
		destCombo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(destCombo.getSelectionIndex() == 6){
					otherText.setVisible(true);
				}
				else {
					otherText.setText("");
					otherText.setVisible(false);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u62BC\u89E3\u8B66\u5BDF\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_4.setBounds(5, 208, 89, 19);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u76D1\u95E8\u8B66\u5BDF\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_5.setBounds(253, 208, 89, 19);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				shell.dispose();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		btnNewButton.setBounds(396, 293, 89, 29);
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
		button_4.setBounds(286, 293, 89, 29);
		
		changeComposite = new Composite(shell, SWT.NONE);
		changeComposite.setBounds(5, 293, 222, 29);
		
		starterCombo = new Combo(changeComposite, SWT.READ_ONLY);
		starterCombo.setItems(new String[] {"\u4E00\u5206\u76D1\u72F1", "\u4E8C\u5206\u76D1\u72F1", "\u4E09\u5206\u76D1\u72F1", "\u56DB\u5206\u76D1\u72F1", "\u4E94\u5206\u76D1\u72F1", "\u4E2D\u5FC3\u533B\u9662"});
		starterCombo.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		starterCombo.setBounds(106, 0, 110, 27);
		
		Label label_6 = new Label(changeComposite, SWT.NONE);
		label_6.setText("\u4FEE\u6539\u53D1\u8D77\u4EBA\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_6.setBounds(0, 4, 101, 19);
		
		Composite numComposite = new Composite(shell, SWT.NONE);
		numComposite.setLocation(4, 11);
		numComposite.setSize(181, 27);
		
		Label label_8 = new Label(numComposite, SWT.NONE);
		label_8.setText("\u8BF7\u8F93\u5165\u6570\u91CF\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_8.setBounds(0, 5, 106, 19);
		
		numSpinner = new Spinner(numComposite, SWT.BORDER);
		numSpinner.setMaximum(1000);
		numSpinner.setMinimum(5);
		numSpinner.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		numSpinner.setBounds(109, 1, 72, 23);
		numSpinner.setSelection(defaultNum);
		
		Composite nameComposite = new Composite(shell, SWT.NONE);
		nameComposite.setBounds(5, 10, 568, 80);
		
		list = new List(nameComposite, SWT.BORDER | SWT.V_SCROLL);
		list.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		list.setBounds(342, 0, 185, 80);
		
		Button button_5 = new Button(nameComposite, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(list.getSelectionIndex() != -1) list.remove(list.getSelectionIndex());
			}
		});
		button_5.setText("<<");
		button_5.setFont(SWTResourceManager.getFont("宋体", 9, SWT.NORMAL));
		button_5.setBounds(285, 33, 45, 27);
		
		Button button_6 = new Button(nameComposite, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				list.add(nameText.getText(),0);
				nameText.setText("");
			}
		});
		button_6.setText(">>");
		button_6.setFont(SWTResourceManager.getFont("宋体", 9, SWT.NORMAL));
		button_6.setBounds(285, 0, 45, 27);
		
		nameText = new Text(nameComposite, SWT.BORDER);
		nameText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		nameText.setBounds(149, 0, 126, 29);
		
		Label label_7 = new Label(nameComposite, SWT.NONE);
		label_7.setText("\u8BF7\u8F93\u5165\u7F6A\u72AF\u59D3\u540D\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_7.setBounds(0, 5, 140, 19);
		
		otherText = new Text(shell, SWT.BORDER);
		otherText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		otherText.setBounds(463, 151, 110, 29);
		otherText.setVisible(false);
		
		watcherButton = new Button(shell, SWT.NONE);
		watcherButton.setText("\u70B9\u51FB\u6309\u94AE\u8FDB\u884C\u7B7E\u540D");
		watcherButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		watcherButton.setBounds(100, 208, 140, 68);
		watcherButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HandWriter hWriter = new HandWriter();
				watcherHW = hWriter.open();
				if(watcherHW == null) MainWindow.showErrorMessageBox(shell, "押解警察签名读取错误，请重签!");
				else watcherButton.setText("签名已保存！"); 
			}
		});
		
		doorButton = new Button(shell, SWT.NONE);
		doorButton.setText("\u70B9\u51FB\u6309\u94AE\u8FDB\u884C\u7B7E\u540D");
		doorButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		doorButton.setBounds(347, 208, 140, 68);
		doorButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HandWriter hWriter = new HandWriter();
				doorHW = hWriter.open();
				if(doorHW == null) MainWindow.showErrorMessageBox(shell, "监门警察签名读取错误，请重签!");
				else doorButton.setText("签名已保存！"); 
			}
		});
		
		
		if(nameType){
			nameComposite.setVisible(false);
		}else numComposite.setVisible(false);
		
		if(MainWindow.my_company != 0) changeComposite.setVisible(false);

	}
	
	protected Vector<CriminalRecord> getCriminalRecords(boolean isMuti){
		Vector<CriminalRecord> criminalRecords = new Vector<CriminalRecord>();
		
		if(!isMuti){
			for(int i=0;i<list.getItemCount();i++){
				CriminalRecord criminalRecord = new CriminalRecord();
				criminalRecord.setName(list.getItem(i));
				criminalRecord.setSex(manbutton.getSelection()?0:1);
				criminalRecord.setCompany(companyCombo.getSelectionIndex()+1);
				criminalRecord.setState(inButton.getSelection()?DataPropMananger.ST_IN:DataPropMananger.ST_OUT);
				criminalRecord.setSt_reason(reasonCombo.getText());
				criminalRecord.setDestination(destCombo.getSelectionIndex()+1);
				criminalRecord.setOther_place(otherText.getText());
				criminalRecord.setStarter((MainWindow.my_company == 0)?starterCombo.getSelectionIndex()+1:MainWindow.my_company);
				criminalRecord.setWatherHW(watcherHW);
				criminalRecord.setDoorHW(doorHW);
				criminalRecords.add(criminalRecord);
			}
		}else{
			CriminalRecord criminalRecord = new CriminalRecord();
			criminalRecord.setNum(numSpinner.getSelection());
			criminalRecord.setSex(manbutton.getSelection()?0:1);
			criminalRecord.setCompany(companyCombo.getSelectionIndex()+1);
			criminalRecord.setState(inButton.getSelection()?DataPropMananger.ST_IN:DataPropMananger.ST_OUT);
			criminalRecord.setSt_reason(reasonCombo.getText());
			criminalRecord.setDestination(destCombo.getSelectionIndex()+1);
			criminalRecord.setOther_place(otherText.getText());
			criminalRecord.setStarter(MainWindow.my_company);
			criminalRecord.setWatherHW(watcherHW);
			criminalRecord.setDoorHW(doorHW);
			criminalRecord.setMuti(true);
			criminalRecords.add(criminalRecord);
		}
		
		return criminalRecords;
	}
	
	protected String checkInfo(){
		String errMsg = "";
		
		if(!nameType){
			if(list.getItemCount() == 0) return "姓名列表不能为空！";
		}else if(numSpinner.getSelection() ==0 || numSpinner.getSelection() < defaultNum) return "批量罪犯人数必须大于"+defaultNum+"人！";
		
		if(reasonCombo.getText().trim().equals("请选择") || reasonCombo.getText().trim().equals("")) return "请填写进出原因!";
		
		if(destCombo.getSelectionIndex() == 6){
			if(otherText.getText().trim().equals("")) return "请填写目的地!";
		}else if(destCombo.getSelectionIndex() == -1) return "请选择目的地！";
		
		if(watcherHW == null) return "押解警察签名读取错误！请重签！";
		if(doorHW == null) return "监门警察签名读取错误！请重签！";
		
		return errMsg;
	}
	
	protected void sendAddRequest(){
		if(MainWindow.getInstance().connectServer()){
			MainWindow.getInstance().sendDataToServer(new DataPackage(MainWindow.VERSION, DataPropMananger.CMD_JMGL,
					DataPropMananger.CRIMINAL_ADD_RECORD, MainWindow.my_company, getCriminalRecords(nameType)));
		}
	}
	
	public void close(){
		shell.close();
		shell.dispose();
	}
}
