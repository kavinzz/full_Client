package jmgl_criminal;

import java.util.Vector;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import config.DataPropMananger;
import config.WindowPropsMananger;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;

import ui.MainWindow;
import data.CriminalRecord;

public class CriminalCard extends Composite {
	
	private Label nameLable;
	private Label sexLable;
	private Label companyLable;
	private Label destLable;
	private Label reasonLable;
	private Button selectButton;
	private Button inoutBtn;
	
	private Vector<CriminalRecord> criminalRecords;
	private String UUID = "-1";
	
	private boolean isSelected = false;
	
	private boolean isInoutEnable = false;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CriminalCard(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setTouchEnabled(true);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		selectButton = new Button(this, SWT.CHECK);
		selectButton.setBounds(10, 187, 13, 17);
		selectButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				isSelected = !isSelected;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button changeBtn = new Button(this, SWT.NONE);
		changeBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openChangeInfoDlg();				
			}
		});
		changeBtn.setVisible(false);
		changeBtn.setText("\u4FEE\u6539");
		changeBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		changeBtn.setBounds(63, 177, 68, 27);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		lblNewLabel.setBounds(10, 12, 105, 21);
		lblNewLabel.setText("\u7F6A\u72AF\u59D3\u540D\uFF1A");
		
		Label label = new Label(this, SWT.NONE);
		label.setAlignment(SWT.RIGHT);
		label.setText("\u6027\u522B\uFF1A");
		label.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label.setBounds(10, 45, 105, 21);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setText("\u6240\u5C5E\u5355\u4F4D\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_1.setBounds(10, 78, 105, 21);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setAlignment(SWT.RIGHT);
		label_2.setText("\u76EE\u7684\u5730\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_2.setBounds(10, 111, 105, 21);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setText("\u72B6\u6001\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_3.setBounds(10, 144, 105, 65);
		
		nameLable = new Label(this, SWT.NONE);
		nameLable.setForeground(SWTResourceManager.getColor(0, 0, 0));
		nameLable.setText("\u7F6A\u72AF\u59D3\u540D");
		nameLable.setFont(SWTResourceManager.getFont("仿宋", 20, SWT.BOLD));
		nameLable.setBounds(118, 9, 172, 26);
		
		sexLable = new Label(this, SWT.NONE);
		sexLable.setText("\u6027\u522B\uFF1A");
		sexLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		sexLable.setBounds(118, 45, 172, 21);
		
		companyLable = new Label(this, SWT.NONE);
		companyLable.setText("\u6027\u522B\uFF1A");
		companyLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		companyLable.setBounds(118, 78, 172, 21);
		
		destLable = new Label(this, SWT.NONE);
		destLable.setText("\u6027\u522B\uFF1A");
		destLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		destLable.setBounds(118, 111, 172, 21);
		
		reasonLable = new Label(this, SWT.NONE);
		reasonLable.setText("\u6027\u522B\uFF1A");
		reasonLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		reasonLable.setBounds(118, 144, 172, 21);
		
		
		
		criminalRecords = new Vector<CriminalRecord>();
		this.pack();
		
		Button detailBtn = new Button(this, SWT.NONE);
		detailBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openRecordDetailDlg();
			}
		});
		detailBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		detailBtn.setBounds(215, 177, 68, 27);
		detailBtn.setText("\u8BE6\u7EC6...");
		
		inoutBtn = new Button(this, SWT.NONE);
		inoutBtn.setText("\u51FA/\u5165");
		inoutBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		inoutBtn.setBounds(138, 177, 68, 27);
		inoutBtn.setEnabled(isInoutEnable);
		inoutBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openAddInOutDlg();
			}
		});
		
		if(MainWindow.my_company < 10) changeBtn.setVisible(true);
		
	}
	
	public void addData(CriminalRecord criminalInfo,Vector<CriminalRecord> myInfos){
		criminalRecords = myInfos;
		this.UUID = criminalInfo.getUuid();
		if(!criminalInfo.isMuti()) nameLable.setText(criminalInfo.getName());
		else{
			nameLable.setText(criminalInfo.getNum() + "名罪犯");
			selectButton.setEnabled(false);
			selectButton.setVisible(false);
		}
		sexLable.setText(criminalInfo.getSex()==0?"男":"女");
		companyLable.setText(DataPropMananger.getCompanyName(criminalInfo.getCompany()));
		String dest = "";
		if(criminalInfo.getOther_place()!=null) dest= "(" + criminalInfo.getOther_place() + ")";
		destLable.setText(DataPropMananger.getCompanyName(criminalInfo.getDestination()) + dest);
		reasonLable.setText(criminalInfo.getSt_reason());
		
		nameLable.setToolTipText(nameLable.getText());
		
		
		if(criminalInfo.getDestination() *10 +1 == MainWindow.my_company){
			isInoutEnable = true;
			inoutBtn.setEnabled(isInoutEnable);
			selectButton.setVisible(isInoutEnable);
		}else if(criminalInfo.getDestination() == 7 && (criminalInfo.getCompany() * 10 +1) == MainWindow.my_company){
			isInoutEnable = true;
			inoutBtn.setEnabled(isInoutEnable);
			selectButton.setVisible(isInoutEnable);
		}else{
			isInoutEnable = false;
			inoutBtn.setEnabled(isInoutEnable);
			selectButton.setVisible(isInoutEnable);
		}
		
		if((criminalInfo.getCompany() == criminalInfo.getDestination() && criminalInfo.getState() == DataPropMananger.ST_IN && (criminalInfo.getStarter()/10) == criminalInfo.getCompany())
				|| criminalInfo.isFinish()){
			this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
			selectButton.setEnabled(false);
			selectButton.setVisible(false);
		}
			
		else if(criminalInfo.getState() == DataPropMananger.ST_OUT) 
			this.setBackground(new Color(this.getDisplay(), 255,0,0));
		else if(criminalInfo.getState() == DataPropMananger.ST_IN)
			this.setBackground(new Color(this.getDisplay(), 0,255,255));
	}
	
	public String getUUID(){
		return this.UUID;
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public CriminalRecord getCriminalRecord(){
		return criminalRecords.get(criminalRecords.size() - 1);
	}
	
	private void openRecordDetailDlg(){
		RecordDetailDlg rd = new RecordDetailDlg(this.getParent().getShell(), SWT.DIALOG_TRIM|SWT.PRIMARY_MODAL);
		rd.setData(nameLable.getText(), criminalRecords);
		rd.open();
	}
	
	private void openAddInOutDlg(){
		InOutAddDlg cr = new InOutAddDlg(this.getParent().getShell(), SWT.DIALOG_TRIM|SWT.PRIMARY_MODAL);
		CriminalRecord criminalRecord = criminalRecords.get(criminalRecords.size() -1);
		cr.setData(criminalRecord);
		int num = cr.open();
		if(num == WindowPropsMananger.CLOSE_FLAG) return;
		if(0 < num && num < 10){
			CriminalInfoDlg criminalInfoDlg = new CriminalInfoDlg(getParent().getShell(), getStyle());
			criminalInfoDlg.open(false);
		}
		else if(num >= 10){
			CriminalInfoDlg criminalInfoDlg = new CriminalInfoDlg(getParent().getShell(), getStyle());
			criminalInfoDlg.open(true,num);
		}
	}
	
	private void openChangeInfoDlg(){
		ChangeInfoDlg cInfoDlg = new ChangeInfoDlg(getParent().getShell(), getStyle());
		CriminalRecord criminalRecord = criminalRecords.get(criminalRecords.size() - 1);
		cInfoDlg.setData(criminalRecord);
		if(cInfoDlg.open() == WindowPropsMananger.CLOSE_FLAG) cInfoDlg = null;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
