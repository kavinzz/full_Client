package jmgl_visitor;

import java.util.Vector;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import config.DataPropMananger;
import config.WindowPropsMananger;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import common.HandWriter;
import ui.MainWindow;
import data.DataPackage;
import data.VisitorRecord;

public class VisitorCard extends Composite {
	
	private Label nameLable;
	private Label companyLable;
	private Label reasonLable;
	private Label timeLable;
	private Label companyNumLable;
	private Button outBtn;
	
	private VisitorRecord visitorRecord;
	private byte[] watcherHW;
	private Button selectBtn;
	private boolean isSelected = false;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VisitorCard(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setTouchEnabled(true);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		
		companyNumLable = new Label(this, SWT.WRAP);
		companyNumLable.setBounds(0, 0, 47, 63);
		
		selectBtn = new Button(this, SWT.CHECK);
		selectBtn.setBounds(10, 180, 13, 17);
		selectBtn.addSelectionListener(new SelectionListener() {
					
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
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		lblNewLabel.setBounds(10, 12, 105, 21);
		lblNewLabel.setText("\u59D3\u540D\uFF1A");
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setText("\u5355\u4F4D\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_1.setBounds(10, 42, 105, 21);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setAlignment(SWT.RIGHT);
		label_2.setText("\u8FDB\u76D1\u4E8B\u7531\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_2.setBounds(10, 71, 105, 21);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setText("\u8FDB\u76D1\u65F6\u95F4\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_3.setBounds(10, 132, 105, 75);
		
		nameLable = new Label(this, SWT.NONE);
		nameLable.setForeground(SWTResourceManager.getColor(0, 0, 0));
		nameLable.setText("\u7F6A\u72AF\u59D3\u540D");
		nameLable.setFont(SWTResourceManager.getFont("仿宋", 18, SWT.BOLD));
		nameLable.setBounds(118, 9, 211, 26);
		
		companyLable = new Label(this, SWT.NONE);
		companyLable.setText("\u6027\u522B\uFF1A");
		companyLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		companyLable.setBounds(118, 42, 222, 21);
		
		reasonLable = new Label(this, SWT.BORDER | SWT.WRAP | SWT.SHADOW_NONE);
		reasonLable.setText("\u6027\u522B\uFF1A");
		reasonLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		reasonLable.setBounds(118, 71, 211, 53);
		
		timeLable = new Label(this, SWT.NONE);
		timeLable.setText("\u6027\u522B\uFF1A");
		timeLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		timeLable.setBounds(118, 132, 211, 21);
		
		
		
		visitorRecord = new VisitorRecord();
		this.pack();
		
		Button detailBtn = new Button(this, SWT.NONE);
		detailBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openRecordDetailDlg();
			}
		});
		detailBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		detailBtn.setBounds(261, 170, 68, 27);
		detailBtn.setText("\u8BE6\u7EC6...");
		
		outBtn = new Button(this, SWT.NONE);
		outBtn.setText("\u51FA\u76D1");
		outBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		outBtn.setBounds(187, 170, 68, 27);
		outBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/*HandWriter hWriter = new HandWriter();
				watcherHW = hWriter.open();
				if(watcherHW != null){
					MessageBox confirmBox  = new MessageBox(getParent().getShell(),SWT.OK|SWT.CANCEL);
					confirmBox.setText("请确认");
					confirmBox.setMessage("是否确认该名人员已经离开监管区？");
					if(confirmBox.open() == SWT.OK){
						sendChangeRequest();
					}
				}*/
				OutConfirmDlg outConfirmDlg = new OutConfirmDlg(getShell(), getStyle());
				if(outConfirmDlg.open(visitorRecord) == WindowPropsMananger.CLOSE_FLAG){
					outConfirmDlg = null;
				}
			}
		});
		
		
	}
	
	public void addData(VisitorRecord vRecord){
		visitorRecord = vRecord;
		if(vRecord.isMuti()){
			nameLable.setText(vRecord.getNum() + "人");
		}
		else nameLable.setText(vRecord.getName());
		nameLable.setToolTipText(vRecord.getName());
		companyLable.setText(vRecord.getCompany());
		reasonLable.setText(vRecord.getSt_reason());
		timeLable.setText(vRecord.getIn_time());
		if(vRecord.isFinish()){
			outBtn.setText("完成");
			outBtn.setEnabled(false);
			selectBtn.setEnabled(false);
			this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		}
		
		if(MainWindow.my_company == 0){
			companyNumLable.setText(DataPropMananger.getCompanyName(vRecord.getStarter()));
		}
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public VisitorRecord getVisitorRecord(){
		return visitorRecord;
	}
	
	
	private void openRecordDetailDlg(){
		VisitorDetailDlg rd = new VisitorDetailDlg(this.getParent().getShell(), SWT.DIALOG_TRIM|SWT.PRIMARY_MODAL);
		rd.setData(visitorRecord);
		rd.open();
	}
	
	/*
	
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
	}*/

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
