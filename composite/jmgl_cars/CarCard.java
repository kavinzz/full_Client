package jmgl_cars;

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

import common.HandWriter;
import ui.MainWindow;
import data.DataPackage;
import data.CarRecord;

public class CarCard extends Composite {
	
	private Label nameLable;
	private Label companyLable;
	private Label reasonLable;
	private Label timeLable;
	private Label carIdLable;
	private Button outBtn;
	
	private CarRecord carRecord;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CarCard(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setTouchEnabled(true);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		lblNewLabel.setBounds(10, 48, 105, 21);
		lblNewLabel.setText("\u9A7E\u9A76\u5458\uFF1A");
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setText("\u5355\u4F4D\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_1.setBounds(10, 82, 105, 21);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setAlignment(SWT.RIGHT);
		label_2.setText("\u8FDB\u76D1\u4E8B\u7531\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_2.setBounds(10, 111, 105, 21);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setText("\u8FDB\u76D1\u65F6\u95F4\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_3.setBounds(10, 172, 105, 75);
		
		nameLable = new Label(this, SWT.NONE);
		nameLable.setForeground(SWTResourceManager.getColor(0, 0, 0));
		nameLable.setFont(SWTResourceManager.getFont("仿宋", 18, SWT.BOLD));
		nameLable.setBounds(118, 45, 211, 26);
		
		companyLable = new Label(this, SWT.NONE);
		companyLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		companyLable.setBounds(118, 82, 222, 21);
		
		reasonLable = new Label(this, SWT.BORDER | SWT.WRAP | SWT.SHADOW_NONE);
		reasonLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		reasonLable.setBounds(118, 111, 211, 53);
		
		timeLable = new Label(this, SWT.NONE);
		timeLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		timeLable.setBounds(118, 172, 211, 21);
		
		
		
		carRecord = new CarRecord();
		this.pack();
		
		Button detailBtn = new Button(this, SWT.NONE);
		detailBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openRecordDetailDlg();
			}
		});
		detailBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		detailBtn.setBounds(261, 210, 68, 27);
		detailBtn.setText("\u8BE6\u7EC6...");
		
		outBtn = new Button(this, SWT.NONE);
		outBtn.setText("\u51FA\u76D1");
		outBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		outBtn.setBounds(187, 210, 68, 27);
		outBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				OutConfirmDlg outConfirmDlg = new OutConfirmDlg(getShell(), getStyle());
				if(outConfirmDlg.open(carRecord) == WindowPropsMananger.CLOSE_FLAG){
					outConfirmDlg = null;
				}
			}
		});
		
		Label label = new Label(this, SWT.NONE);
		label.setText("\u8F66\u724C\u53F7\uFF1A");
		label.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label.setAlignment(SWT.RIGHT);
		label.setBounds(10, 10, 105, 21);
		
		carIdLable = new Label(this, SWT.NONE);
		carIdLable.setForeground(SWTResourceManager.getColor(0, 0, 0));
		carIdLable.setFont(SWTResourceManager.getFont("仿宋", 18, SWT.BOLD));
		carIdLable.setBounds(118, 7, 211, 26);
		
		
		
	}
	
	public void addData(CarRecord vRecord){
		carRecord = vRecord;
		carIdLable.setText(vRecord.getCarId());
		nameLable.setText(vRecord.getDriverName());
		companyLable.setText(vRecord.getCompany());
		reasonLable.setText(vRecord.getSt_reason());
		timeLable.setText(vRecord.getIn_time());
		if(vRecord.isFinish()){
			outBtn.setText("完成");
			outBtn.setEnabled(false); 
			this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		}
	}
	
	private void openRecordDetailDlg(){
		CarDetailDlg rd = new CarDetailDlg(this.getParent().getShell(), SWT.DIALOG_TRIM|SWT.PRIMARY_MODAL);
		rd.setData(carRecord);
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
