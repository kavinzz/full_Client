package jmgl_criminal;

import java.util.Vector;

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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import config.DataPropMananger;
import config.WindowPropsMananger;
import data.CriminalRecord;
import data.DataPackage;
import ui.MainWindow;

public class ChangeInfoDlg extends CriminalInfoDlg {

	private Text nameText;
	private Label label;
	private Text watcherText;
	private Text doorText;
	private Combo companyCombo;
	private Composite changeComposite;
	private Button womanButton;
	private Button manButton;
	private Button inButton;
	private Button outButton;
	private Combo reasonCombo;
	private Combo destCombo;
	private Combo starterCombo;
	private Button delCheckButton;
	private Button finishCheckButton;
	private CriminalRecord oCriminalRecord;
	

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ChangeInfoDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		createContents();
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public int open() {
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
		shell.setText("\u4FEE\u6539\u7F6A\u72AF\u8FDB\u51FA\u8BB0\u5F55");
		shell.setSize(600, 361);
		shell.setLocation((Display.getCurrent().getBounds().width - shell.getBounds().width)/2, (Display.getCurrent().getBounds().height - shell.getBounds().height)/2);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel.setBounds(5, 10, 89, 19);
		lblNewLabel.setText("\u7F6A\u72AF\u59D3\u540D\uFF1A");
		
		nameText = new Text(shell, SWT.BORDER);
		nameText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		nameText.setBounds(100, 7, 180, 29);
		
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
		label_2.setBounds(271, 103, 89, 19);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(100, 48, 101, 35);
		composite.setEnabled(false);
		
		manButton = new Button(composite, SWT.RADIO);
		manButton.setText("\u7537");
		manButton.setSelection(true);
		manButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		manButton.setBounds(0, 10, 42, 17);
		
		womanButton = new Button(composite, SWT.RADIO);
		womanButton.setText("\u5973");
		womanButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		womanButton.setBounds(48, 10, 42, 17);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBounds(365, 94, 90, 35);
		composite_1.setEnabled(false);
		
		inButton = new Button(composite_1, SWT.RADIO);
		inButton.setText("\u8FDB");
		inButton.setSelection(true);
		inButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		inButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		inButton.setBounds(0, 10, 42, 17);
		
		outButton = new Button(composite_1, SWT.RADIO);
		outButton.setText("\u51FA");
		outButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		outButton.setBounds(48, 10, 42, 17);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u51FA\u5165\u539F\u56E0\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_3.setBounds(5, 155, 89, 19);
		
		reasonCombo = new Combo(shell, SWT.NONE);
		reasonCombo.setEnabled(false);
		reasonCombo.setItems(new String[] {"\u4F4F\u9662", "\u51FA\u9662", "\u4E2D\u5FC3\u533B\u9662\u770B\u75C5", "\u56DE\u76D1", "\u65B0\u6536\u62BC", "\u53C2\u52A0\u6D3B\u52A8", "\u6280\u80FD\u57F9\u8BAD", "\u5916\u51FA\u5C31\u533B", "\u5206\u76D1\u72F1\u8C03\u52A8", "\u4FDD\u5916\u5C31\u533B"});
		reasonCombo.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		reasonCombo.setBounds(100, 151, 140, 27);
		reasonCombo.setText("\u8BF7\u9009\u62E9");
		
		Label destlable = new Label(shell, SWT.NONE);
		destlable.setText("\u76EE \u7684 \u5730\uFF1A");
		destlable.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		destlable.setBounds(271, 155, 89, 19);
		
		destCombo = new Combo(shell, SWT.READ_ONLY);
		destCombo.setItems(new String[] {"\u4E00\u5206\u76D1\u72F1", "\u4E8C\u5206\u76D1\u72F1", "\u4E09\u5206\u76D1\u72F1", "\u56DB\u5206\u76D1\u72F1", "\u4E94\u5206\u76D1\u72F1", "\u4E2D\u5FC3\u533B\u9662"});
		destCombo.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		destCombo.setBounds(365, 152, 110, 27);
		destCombo.setEnabled(false);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u62BC\u89E3\u8B66\u5BDF\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_4.setBounds(5, 208, 89, 19);
		
		watcherText = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		watcherText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		watcherText.setBounds(100, 208, 140, 68);
		watcherText.setEnabled(false);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u76D1\u95E8\u8B66\u5BDF\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_5.setBounds(271, 208, 89, 19);
		
		doorText = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		doorText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		doorText.setBounds(365, 208, 140, 68);
		doorText.setEnabled(false);
		
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
		
		Label label_10 = new Label(shell, SWT.NONE);
		label_10.setText("\uFF08\u4E0D\u53EF\u66F4\u6539\uFF09");
		label_10.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		label_10.setBounds(-1, 233, 89, 19);
		
		Label label_11 = new Label(shell, SWT.NONE);
		label_11.setText("\uFF08\u4E0D\u53EF\u66F4\u6539\uFF09");
		label_11.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		label_11.setBounds(266, 233, 89, 19);
		
		Label label_12 = new Label(shell, SWT.NONE);
		label_12.setText("\uFF08\u4E0D\u53EF\u66F4\u6539\uFF09");
		label_12.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		label_12.setBounds(-1, 180, 96, 19);
		
		Label label_13 = new Label(shell, SWT.NONE);
		label_13.setText("\uFF08\u4E0D\u53EF\u66F4\u6539\uFF09");
		label_13.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		label_13.setBounds(265, 128, 110, 19);
		
		Label label_14 = new Label(shell, SWT.NONE);
		label_14.setText("\uFF08\u4E0D\u53EF\u66F4\u6539\uFF09");
		label_14.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		label_14.setBounds(266, 180, 110, 19);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\uFF08\u4E0D\u53EF\u66F4\u6539\uFF09");
		label_7.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		label_7.setBounds(-2, 78, 96, 19);
		
		finishCheckButton= new Button(shell, SWT.CHECK);
		finishCheckButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		finishCheckButton.setBounds(349, 11, 126, 26);
		finishCheckButton.setText("\u6807\u8BB0\u4E3A\u5B8C\u6210");
		
		delCheckButton = new Button(shell, SWT.CHECK);
		delCheckButton.setText("\u6807\u8BB0\u4E3A\u5220\u9664");
		delCheckButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		delCheckButton.setBounds(349, 49, 126, 26);
		
		if(MainWindow.my_company != 0) changeComposite.setVisible(false);

	}
	
	public void setData(CriminalRecord cr){
		oCriminalRecord = cr;
		if(cr.isMuti()){
			nameText.setText("批量罪犯不可修改"); 
			nameText.setEnabled(false);
		}
		else {
			nameText.setText(cr.getName());
			nameText.selectAll();
		}
		
		if(cr.getSex() ==0){
			womanButton.setSelection(false);
			manButton.setSelection(true);
		}
		else{
			manButton.setSelection(false);
			womanButton.setSelection(true);
		}
		companyCombo.select(cr.getCompany() - 1);
		if(cr.getState() == DataPropMananger.ST_IN){
			outButton.setSelection(true); 
			inButton.setSelection(false);
		}
		else{
			outButton.setSelection(false);
			inButton.setSelection(true);
		}
		reasonCombo.setText(cr.getSt_reason());
		destCombo.select(cr.getDestination() - 1);
		
		delCheckButton.setSelection(cr.isDel());
		if(cr.isDel()) delCheckButton.setEnabled(false);
		
		finishCheckButton.setSelection(cr.isFinish());
		if(cr.isFinish()) finishCheckButton.setEnabled(false);
		
	}
	
	protected Vector<CriminalRecord> getCriminalRecords(boolean isMuti){
		Vector<CriminalRecord> criminalRecords = new Vector<CriminalRecord>();
		
		CriminalRecord criminalRecord = new CriminalRecord();
		criminalRecord.setUuid(oCriminalRecord.getUuid());
		criminalRecord.setName(nameText.getText().trim());
		criminalRecord.setCompany(companyCombo.getSelectionIndex()+1);
		criminalRecord.setStarter((MainWindow.my_company == 0)?starterCombo.getSelectionIndex()+1:MainWindow.my_company);
		criminalRecord.setFinish(finishCheckButton.getSelection());
		criminalRecord.setDel(delCheckButton.getSelection());
		criminalRecords.add(criminalRecord);
		
		return criminalRecords;
	}
	
	protected String checkInfo(){
		String errMsg = "";
		int changeCount = 0;
		
		if(nameText.getText().trim().equals("")) return "请填写姓名 ！";
		
		if(!nameText.getText().trim().equals(oCriminalRecord.getName())) changeCount ++;
		if(companyCombo.getSelectionIndex() != (oCriminalRecord.getCompany()-1)) changeCount ++;
		if(delCheckButton.getSelection() != oCriminalRecord.isDel()) changeCount ++;
		if(finishCheckButton.getSelection() != oCriminalRecord.isFinish()) changeCount++;
		
		if(changeCount == 0) return "数据未改动，提交失败！";
		else if(changeCount > 1) return "每次提交仅能改动一项，请修改！";
		
		return errMsg;
	}
	
	protected void sendAddRequest(){
		if(MainWindow.getInstance().connectServer()){
			MainWindow.getInstance().sendDataToServer(new DataPackage(MainWindow.VERSION, DataPropMananger.CMD_JMGL,
					DataPropMananger.CRIMINAL_CHANGE_RECORD, MainWindow.my_company, getCriminalRecords(false)));
		}
	}
}
