package jmgl_dutylog;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import config.DataPropMananger;
import config.WindowPropsMananger;
import data.JmglDutyRecord;

public class DutyHistoryDetailDlg extends Dialog {

	protected Object result;
	protected Shell shell;
	
	private Label nameLabel;
	private Label label;
	private Label timeStartLable;
	private Label label_1;
	private Label timeEndLable;
	private Label label_4;
	private Button eqpNButton;
	private Text eqpText;
	private Label label_5;
	private Label label_6;
	private Label menNumLable;
	private Label label_7;
	private Label carNumLable;
	private Label label_8;
	private Label label_9;
	private Text infoText;
	private Button eqpYButton;
	
	private Label label_10;
	private Button cleanYButton;
	private Button cleanNbutton;
	private Composite composite;
	private Label companyLable;
	

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DutyHistoryDetailDlg(Shell parent, int style) {
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
	private void createContents() {
		shell = new Shell(getParent(), SWT.SYSTEM_MODAL|SWT.CLOSE|SWT.TITLE|SWT.PRIMARY_MODAL);
		shell.setSize(595, 625);
		shell.setText("\u5386\u53F2\u8BB0\u5F55");
		shell.setLocation((Display.getCurrent().getBounds().width - shell.getBounds().width)/2, (Display.getCurrent().getBounds().height - shell.getBounds().height)/2);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel.setBounds(10, 52, 95, 19);
		lblNewLabel.setText("\u503C\u73ED\u4EBA\u5458\uFF1A");
		
		nameLabel = new Label(shell, SWT.NONE);
		nameLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		nameLabel.setText("\u4E00\u4E2A\u4EBA \u4E24\u4E2A\u4EBA \u4E09\u4E2A\u4EBA \u56DB\u4E2A\u4EBA");
		nameLabel.setFont(SWTResourceManager.getFont("仿宋", 20, SWT.BOLD));
		nameLabel.setBounds(111, 48, 460, 27);
		
		label = new Label(shell, SWT.NONE);
		label.setText("\u503C\u73ED\u65F6\u95F4\uFF1A");
		label.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label.setBounds(10, 103, 95, 19);
		
		timeStartLable = new Label(shell, SWT.BORDER);
		timeStartLable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		timeStartLable.setText("2015\u5E7412\u670812\u65E5 24\uFF1A00\uFF1A00");
		timeStartLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		timeStartLable.setFont(SWTResourceManager.getFont("仿宋", 20, SWT.BOLD));
		timeStartLable.setBounds(111, 100, 370, 27);
		
		label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u81F3");
		label_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_1.setBounds(287, 132, 19, 19);
		
		timeEndLable = new Label(shell, SWT.BORDER);
		timeEndLable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		timeEndLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		timeEndLable.setFont(SWTResourceManager.getFont("仿宋", 20, SWT.BOLD));
		timeEndLable.setBounds(111, 154, 370, 27);
		
		
		label_4 = new Label(shell, SWT.WRAP | SWT.RIGHT);
		label_4.setText("\u8BBE\u5907\u8BBE\u65BD\u5DE1\u68C0\u60C5\u51B5\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_4.setBounds(10, 474, 95, 42);
		
		eqpText = new Text(shell, SWT.BORDER);
		eqpText.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		eqpText.setVisible(false);
		eqpText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		eqpText.setBounds(268, 479, 303, 27);
		
		label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u5B89\u68C0\u60C5\u51B5\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_5.setBounds(10, 414, 95, 19);
		
		label_6 = new Label(shell, SWT.NONE);
		label_6.setText("\u672C\u73ED\u5B89\u68C0");
		label_6.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_6.setBounds(111, 414, 76, 19);
		
		menNumLable = new Label(shell, SWT.BORDER | SWT.CENTER);
		menNumLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		menNumLable.setText("0");
		menNumLable.setFont(SWTResourceManager.getFont("宋体", 22, SWT.BOLD));
		menNumLable.setBounds(189, 403, 48, 33);
		
		label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\u4EBA\u6B21\uFF0C\u8F66\u8F86");
		label_7.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_7.setBounds(236, 414, 95, 19);
		
		carNumLable = new Label(shell, SWT.BORDER | SWT.CENTER);
		carNumLable.setToolTipText("\u5DE6\u952E\u70B9\u51FB+1\uFF0C\u53F3\u952E\u70B9\u51FB-1");
		carNumLable.setText("0");
		carNumLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		carNumLable.setFont(SWTResourceManager.getFont("宋体", 22, SWT.BOLD));
		carNumLable.setBounds(332, 403, 48, 33);
		
		label_8 = new Label(shell, SWT.NONE);
		label_8.setText("\u8F86\u6B21\u3002");
		label_8.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_8.setBounds(381, 414, 48, 19);
		
		label_9 = new Label(shell, SWT.NONE);
		label_9.setText("\u503C\u73ED\u60C5\u51B5\uFF1A");
		label_9.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_9.setBounds(10, 198, 95, 19);
		
		infoText = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		infoText.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		infoText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		infoText.setBounds(111, 198, 460, 188);
		
		label_10 = new Label(shell, SWT.WRAP | SWT.RIGHT);
		label_10.setText("\u76D1\u95E8\u536B\u751F\u6253\u626B\u60C5\u51B5\uFF1A");
		label_10.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		label_10.setBounds(10, 543, 95, 42);
		
		cleanYButton = new Button(shell, SWT.RADIO);
		cleanYButton.setEnabled(false);
		cleanYButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		cleanYButton.setBounds(122, 555, 76, 17);
		cleanYButton.setText("\u5DF2\u6253\u626B");
		
		cleanNbutton = new Button(shell, SWT.RADIO);
		cleanNbutton.setEnabled(false);
		cleanNbutton.setSelection(true);
		cleanNbutton.setText("\u672A\u6253\u626B");
		cleanNbutton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		cleanNbutton.setBounds(209, 555, 97, 17);
		
		composite = new Composite(shell, SWT.NONE);
		composite.setBounds(119, 474, 143, 42);
		
		eqpYButton = new Button(composite, SWT.RADIO);
		eqpYButton.setEnabled(false);
		eqpYButton.setSelection(true);
		eqpYButton.setBounds(0, 10, 61, 17);
		
		eqpYButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		eqpYButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		eqpYButton.setText("\u6B63\u5E38");
		
		eqpNButton = new Button(composite, SWT.RADIO);
		eqpNButton.setEnabled(false);
		eqpNButton.setBounds(82, 10, 61, 17);
		
		eqpNButton.setText("\u5F02\u5E38");
		eqpNButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		eqpNButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		
		companyLable = new Label(shell, SWT.NONE);
		companyLable.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		companyLable.setFont(SWTResourceManager.getFont("仿宋", 18, SWT.BOLD | SWT.ITALIC));
		companyLable.setBounds(10, 10, 460, 27);
	}
	
	public void setData(final JmglDutyRecord dRecord){
		if(dRecord == null) return;
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				nameLabel.setText(dRecord.getName());
				nameLabel.setToolTipText(dRecord.getUuid() + "");
				timeStartLable.setText(dRecord.getStartTime());
				if(dRecord.getEndTime() !=null) timeEndLable.setText(dRecord.getEndTime());
				if(dRecord.getInfo() != null) infoText.setText(dRecord.getInfo());
				menNumLable.setText(String.valueOf(dRecord.getSafeMenNum()));
				carNumLable.setText(String.valueOf(dRecord.getSafeCarNum()));
				companyLable.setText(DataPropMananger.getCompanyName(dRecord.getStarter()));
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

}
