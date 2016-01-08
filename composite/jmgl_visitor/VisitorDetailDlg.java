package jmgl_visitor;

import jmgl_common.ImageViewDlg;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import config.WindowPropsMananger;
import data.VisitorRecord;

public class VisitorDetailDlg extends Dialog {

	protected Shell shell;
	private Label nameLable;
	private Label companyLable;
	private Label reasonLable;
	private Label inTimeLable;
	private Label label;
	private Label outTimeLable;
	private Label label_4;
	private Label idTypeLable;
	private Label label_5;
	private Label idNumLable;
	private Label label_7;
	private VisitorRecord visitorRecord;
	private Label label_6;
	private Label toolLable;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public VisitorDetailDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		createContents();
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public int open(boolean type) {
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
		shell.setText("\u4EBA\u5458\u8BE6\u7EC6\u60C5\u51B5");
		shell.setSize(345, 505);
		shell.setLocation((Display.getCurrent().getBounds().width - shell.getBounds().width)/2, (Display.getCurrent().getBounds().height - shell.getBounds().height)/2);
		
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		lblNewLabel.setBounds(10, 24, 105, 21);
		lblNewLabel.setText("\u59D3\u540D\uFF1A");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setText("\u5355\u4F4D\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_1.setBounds(10, 69, 105, 21);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setAlignment(SWT.RIGHT);
		label_2.setText("\u8FDB\u76D1\u4E8B\u7531\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_2.setBounds(10, 204, 105, 21);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setText("\u8FDB\u76D1\u65F6\u95F4\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_3.setBounds(10, 314, 105, 21);
		
		nameLable = new Label(shell, SWT.NONE);
		nameLable.setForeground(SWTResourceManager.getColor(0, 0, 0));
		nameLable.setFont(SWTResourceManager.getFont("仿宋", 18, SWT.BOLD));
		nameLable.setBounds(118, 21, 211, 26);
		
		companyLable = new Label(shell, SWT.NONE);
		companyLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		companyLable.setBounds(118, 69, 211, 21);
		
		reasonLable = new Label(shell, SWT.BORDER | SWT.WRAP | SWT.SHADOW_NONE);
		reasonLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		reasonLable.setBounds(118, 204, 211, 53);
		
		inTimeLable = new Label(shell, SWT.NONE);
		inTimeLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		inTimeLable.setBounds(118, 314, 211, 21);
		
		label = new Label(shell, SWT.NONE);
		label.setText("\u51FA\u76D1\u65F6\u95F4\uFF1A");
		label.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label.setAlignment(SWT.RIGHT);
		label.setBounds(10, 359, 105, 21);
		
		outTimeLable = new Label(shell, SWT.NONE);
		outTimeLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		outTimeLable.setBounds(118, 359, 211, 21);
		
		label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u8BC1\u4EF6\u7C7B\u578B\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_4.setAlignment(SWT.RIGHT);
		label_4.setBounds(10, 114, 105, 21);
		
		idTypeLable = new Label(shell, SWT.NONE);
		idTypeLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		idTypeLable.setBounds(118, 114, 211, 21);
		
		label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u8BC1\u4EF6\u53F7\u7801\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_5.setAlignment(SWT.RIGHT);
		label_5.setBounds(10, 159, 105, 21);
		
		idNumLable = new Label(shell, SWT.NONE);
		idNumLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		idNumLable.setBounds(118, 159, 211, 21);
		
		label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\u8B66\u5BDF\u7B7E\u540D\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_7.setAlignment(SWT.RIGHT);
		label_7.setBounds(10, 404, 105, 21);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ImageViewDlg imageViewDlg = new ImageViewDlg(shell, getStyle());
				imageViewDlg.shwoImg(visitorRecord.getWatherHW(), visitorRecord.getDoorHW(),visitorRecord.getOut_doorHW(),visitorRecord.getOut_watcherHW());
				if(imageViewDlg.open() == WindowPropsMananger.CLOSE_FLAG){
					imageViewDlg = null;
				}
			}
		});
		btnNewButton.setBounds(118, 401, 105, 27);
		btnNewButton.setText("\u70B9\u51FB\u67E5\u770B");
		
		label_6 = new Label(shell, SWT.NONE);
		label_6.setText("\u7269\u54C1\u767B\u8BB0\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_6.setAlignment(SWT.RIGHT);
		label_6.setBounds(10, 275, 105, 21);
		
		toolLable = new Label(shell, SWT.NONE);
		toolLable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		toolLable.setBounds(118, 275, 211, 21);
	}
	
	public void setData(VisitorRecord vRecord){
		visitorRecord = vRecord;
		if(vRecord.isMuti()){
			nameLable.setText(vRecord.getNum() + "人");
		}else{
			nameLable.setText(vRecord.getName());
		}
		
		companyLable.setText(vRecord.getCompany());
		if(vRecord.getIdType() != null) idTypeLable.setText(vRecord.getIdType());
		if(vRecord.getIdNum() != null)idNumLable.setText(vRecord.getIdNum());
		reasonLable.setText(vRecord.getSt_reason());
		inTimeLable.setText(vRecord.getIn_time());
		if(vRecord.getOut_time() != null)outTimeLable.setText(vRecord.getOut_time());
		if(vRecord.getTools() != null) toolLable.setText(vRecord.getTools());
	}
	
	public void close(){
		shell.close();
		shell.dispose();
	}
}
