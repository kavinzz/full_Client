package jmgl_visitor;

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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import config.DataPropMananger;
import config.WindowPropsMananger;
import data.DataPackage;
import data.VisitorRecord;
import ui.MainWindow;

import org.eclipse.swt.widgets.Spinner;

import common.HandWriter;

public class AddVisitorsDlg extends Dialog {

	protected Shell shell;
	private Spinner numSpinner;
	private List list;
	private Combo idTypeCombo;
	
	private boolean nameType = false;
	
	private Text nameText;
	
	private byte[] watcherHW;
	private byte[] doorHW;
	
	private int defaultNum = 5;
	private Text companyText;
	private Text idNumText;
	private Text reasonText;
	private Text toolText;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddVisitorsDlg(Shell parent, int style) {
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
		shell.setText("\u65B0\u589E\u4EBA\u5458\u8FDB\u51FA\u8BB0\u5F55");
		shell.setSize(549, 386);
		shell.setLocation((Display.getCurrent().getBounds().width - shell.getBounds().width)/2, (Display.getCurrent().getBounds().height - shell.getBounds().height)/2);
		
		companyText = new Text(shell, SWT.BORDER);
		companyText.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		companyText.setBounds(100, 58, 140, 30);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setText("\u5355\u4F4D\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_1.setBounds(5, 63, 89, 19);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u8FDB\u76D1\u4E8B\u7531\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_3.setBounds(5, 163, 89, 19);
		
		Label idtypelable = new Label(shell, SWT.NONE);
		idtypelable.setText("\u8BC1\u4EF6\u7C7B\u578B\uFF1A");
		idtypelable.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		idtypelable.setBounds(5, 110, 89, 19);
		
		idTypeCombo = new Combo(shell, SWT.NONE);
		idTypeCombo.setItems(new String[] {"\u8EAB\u4EFD\u8BC1", "\u5DE5\u4F5C\u8BC1", "\u8B66\u5B98\u8BC1", "\u9A7E\u7167"});
		idTypeCombo.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		idTypeCombo.setBounds(100, 107, 89, 27);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u5E26\u9886\u8B66\u5BDF\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_4.setBounds(5, 253, 89, 19);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u76D1\u95E8\u8B66\u5BDF\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_5.setBounds(271, 253, 89, 19);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				shell.dispose();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		btnNewButton.setBounds(448, 318, 89, 29);
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
		button_4.setBounds(348, 318, 89, 29);
		
		Composite numComposite = new Composite(shell, SWT.NONE);
		numComposite.setLocation(4, 11);
		numComposite.setSize(181, 27);
		
		Label label_8 = new Label(numComposite, SWT.NONE);
		label_8.setText("\u8BF7\u8F93\u5165\u4EBA\u6570\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_8.setBounds(0, 5, 106, 19);
		
		numSpinner = new Spinner(numComposite, SWT.BORDER);
		numSpinner.setMaximum(1000);
		numSpinner.setMinimum(5);
		numSpinner.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		numSpinner.setBounds(109, 1, 72, 23);
		numSpinner.setSelection(defaultNum);
		
		Composite nameComposite = new Composite(shell, SWT.NONE);
		nameComposite.setBounds(5, 10, 532, 80);
		
		list = new List(nameComposite, SWT.BORDER | SWT.V_SCROLL);
		list.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		list.setBounds(342, 0, 185, 80);
		
		Button button_5 = new Button(nameComposite, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(list.getSelectionIndex() != -1)list.remove(list.getSelectionIndex());
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
		label_7.setText("\u8BF7\u8F93\u5165\u4EBA\u5458\u59D3\u540D\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_7.setBounds(0, 5, 140, 19);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u8BC1\u4EF6\u53F7\u7801\uFF1A");
		label.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label.setBounds(195, 110, 89, 19);
		
		idNumText = new Text(shell, SWT.BORDER);
		idNumText.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		idNumText.setBounds(286, 105, 251, 30);
		
		reasonText = new Text(shell, SWT.BORDER);
		reasonText.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		reasonText.setBounds(100, 158, 437, 30);
		
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
		watcherBtn.setBounds(100, 253, 165, 51);
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
		doorBtn.setBounds(366, 253, 171, 51);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u7269\u54C1\u767B\u8BB0\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_2.setBounds(5, 212, 89, 19);
		
		toolText = new Text(shell, SWT.BORDER);
		toolText.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		toolText.setBounds(100, 206, 437, 30);
		
		if(nameType)nameComposite.setVisible(false);
		else numComposite.setVisible(false);

	}
	
	protected Vector<VisitorRecord> getVisitorRecords(boolean isMuti){
		Vector<VisitorRecord> visitorRecords = new Vector<VisitorRecord>();
		VisitorRecord visitorRecord = new VisitorRecord();
		if(!isMuti){
			
			String[] names = list.getItems();
			String name = "";
			for(int i=0;i<names.length;i++){
				name = name + names[i] + " ";
			}
			visitorRecord.setName(name);
			visitorRecord.setNum(list.getItemCount());
			visitorRecord.setIdType(idTypeCombo.getText());
			visitorRecord.setIdNum(idNumText.getText());
		}else{
			visitorRecord.setMuti(true);
			visitorRecord.setNum(numSpinner.getSelection());
		}
		visitorRecord.setCompany(companyText.getText());
		visitorRecord.setSt_reason(reasonText.getText());
		visitorRecord.setWatherHW(watcherHW);
		visitorRecord.setDoorHW(doorHW);
		visitorRecord.setTools(toolText.getText());
		visitorRecord.setStarter(MainWindow.my_company);
		visitorRecords.add(visitorRecord);
		
		return visitorRecords;
	}
	
	protected String checkInfo(){
		String errMsg = "";
		
		if(!nameType){
			if(list.getItemCount() == 0) return "姓名列表不能为空！";
			if(idTypeCombo.getText().trim().equals("")) return "请选择证件类型！";
		}else if(numSpinner.getSelection() ==0 || numSpinner.getSelection() < 5) return "批量进出人数必须大于5人！";
		
		if(reasonText.getText().trim().equals("")) return "请填写进监事由！";
		
		if(watcherHW == null) return "带领警察签名读取错误！请重签！";
		if(doorHW == null) return "监门警察签名读取错误！请重签！";
		
		return errMsg;
	}
	
	protected void sendAddRequest(){
		if(MainWindow.getInstance().connectServer()){
			MainWindow.getInstance().sendDataToServer(new DataPackage(MainWindow.VERSION, DataPropMananger.CMD_JMGL,
					DataPropMananger.VISITOR_ADD_RECORD, MainWindow.my_company, getVisitorRecords(nameType)));
		}
	}
	
	public void close(){
		shell.close();
		shell.dispose();
	}
}
