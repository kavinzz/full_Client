package jmgl_dutylog;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import ui.MainWindow;
import config.DataPropMananger;
import config.WindowPropsMananger;
import data.DataPackage;
import data.JmglDutyRecord;

public class DutyChangeDlg extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text nameText;
	private Text nextNameText;
	private JmglDutyRecord originRecord;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DutyChangeDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public int open(JmglDutyRecord dRecord) {
		createContents(dRecord.getName());
		originRecord = dRecord;
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
	private void createContents(String names) {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.setSize(357, 251);
		shell.setText("\u4EA4\u63A5\u73ED\u786E\u8BA4");
		shell.setLocation((Display.getCurrent().getBounds().width - shell.getBounds().width)/2, (Display.getCurrent().getBounds().height - shell.getBounds().height)/2);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		lblNewLabel.setBounds(10, 10, 93, 21);
		lblNewLabel.setText("\u4EA4\u73ED\u8B66\u5BDF\uFF1A");
		
		nameText = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		nameText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.BOLD));
		nameText.setBounds(109, 10, 232, 79);
		nameText.setText(names);
		
		Label lable = new Label(shell, SWT.NONE);
		lable.setText("\u63A5\u73ED\u8B66\u5BDF\uFF1A");
		lable.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		lable.setBounds(10, 97, 93, 21);
		
		nextNameText = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		nextNameText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.BOLD));
		nextNameText.setBounds(109, 96, 232, 79);
		nextNameText.setFocus();
		
		Button confirmButton = new Button(shell, SWT.NONE);
		confirmButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String err = checkComplete();
				if(err.equals("")){
					sendDutyChangeRequest();
					close();
				}
				else MainWindow.showErrorMessageBox(shell, err);
			}
		});
		confirmButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		confirmButton.setBounds(175, 185, 80, 27);
		confirmButton.setText("\u786E\u5B9A");
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
		button.setText("\u53D6\u6D88");
		button.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		button.setBounds(261, 185, 80, 27);

	}
	
	private String checkComplete(){
		String err = "";
		
		if(nameText.getText().trim().equals("")) return "请填写交班警察姓名";
		if(nextNameText.getText().trim().equals("")) return "请填写接班警察姓名";
		
		return err;
	}
	
	private void sendDutyChangeRequest(){
		Vector<JmglDutyRecord> jmglDutyRecords = new Vector<JmglDutyRecord>();
		//新的增加
		JmglDutyRecord newRecord = new JmglDutyRecord();
		newRecord.setName(nextNameText.getText());
		newRecord.setStarter(MainWindow.my_company);
		jmglDutyRecords.add(newRecord);
		
		//旧的修改
		JmglDutyRecord oldRecord = new JmglDutyRecord();
		oldRecord.setUuid(originRecord.getUuid());
		oldRecord.setNextName(nextNameText.getText());
		oldRecord.setEqpOk(originRecord.isEqpOk());
		oldRecord.setEqpInfo(originRecord.getEqpInfo());
		oldRecord.setClean(originRecord.isClean());
		jmglDutyRecords.add(oldRecord);
		
		if(MainWindow.getInstance().connectServer())
		{
			DataPackage data = new DataPackage(MainWindow.VERSION,DataPropMananger.CMD_JMGL,DataPropMananger.JM_DUTY_CHANGE_DUTY,MainWindow.my_company, jmglDutyRecords);
			MainWindow.getInstance().sendDataToServer(data);
		}
	}

	private void close(){
		shell.close();
		shell.dispose();
	}
}
