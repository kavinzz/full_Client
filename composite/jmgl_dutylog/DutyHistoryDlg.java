package jmgl_dutylog;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import ui.MainWindow;
import config.DataPropMananger;
import config.WindowPropsMananger;
import data.DataPackage;
import data.JmglDutyRecord;
import org.eclipse.swt.widgets.Combo;

public class DutyHistoryDlg extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private DateTime startDateTime;
	private DateTime endDateTime;
	private Vector<JmglDutyRecord> saveRecords;
	private Combo companyCombo;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DutyHistoryDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage("D:\\CTS\\source\\icon.png"));
		shell.setSize(854, 565);
		shell.setText("\u503C\u73ED\u65E5\u5FD7\u67E5\u8BE2");
		shell.setLocation((Display.getCurrent().getBounds().width - shell.getBounds().width)/2, (Display.getCurrent().getBounds().height - shell.getBounds().height)/2);
		shell.setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(0, 38);
		fd_lblNewLabel.right = new FormAttachment(0, 117);
		fd_lblNewLabel.top = new FormAttachment(0, 21);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel.setText("\u8BF7\u9009\u62E9\u65E5\u671F\uFF1A");
		
		startDateTime = new DateTime(shell, SWT.BORDER);
		startDateTime.setMonth(0);
		startDateTime.setDay(1);
		FormData fd_StartDateTime = new FormData();
		fd_StartDateTime.right = new FormAttachment(0, 335);
		fd_StartDateTime.top = new FormAttachment(0, 17);
		fd_StartDateTime.left = new FormAttachment(0, 123);
		startDateTime.setLayoutData(fd_StartDateTime);
		startDateTime.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		
		endDateTime = new DateTime(shell, SWT.BORDER);
		FormData fd_endDateTime = new FormData();
		fd_endDateTime.right = new FormAttachment(0, 593);
		fd_endDateTime.top = new FormAttachment(0, 17);
		fd_endDateTime.left = new FormAttachment(0, 381);
		endDateTime.setLayoutData(fd_endDateTime);
		endDateTime.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		
		Label label = new Label(shell, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(0, 38);
		fd_label.top = new FormAttachment(0, 21);
		fd_label.left = new FormAttachment(0, 349);
		label.setLayoutData(fd_label);
		label.setText("\u81F3");
		label.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sendGetHistoryRequest();
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(0, 16);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		btnNewButton.setText("\u67E5\u8BE2");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 64, 64, 64);
		FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(lblNewLabel, 830);
		fd_composite.top = new FormAttachment(startDateTime, 21);
		fd_composite.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_composite.bottom = new FormAttachment(0, 528);
		composite.setLayoutData(fd_composite);
		TableColumnLayout tcl_composite = new TableColumnLayout();
		composite.setLayout(tcl_composite);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addMouseListener(new MouseListener() {
					
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int index = table.getSelectionIndex();
				if(saveRecords == null || saveRecords.size() == 0|| index == -1) return;
				if(saveRecords.get(index) == null) return;
				DutyHistoryDetailDlg dutyHistoryDetailDlg = new DutyHistoryDetailDlg(shell, SWT.CLOSE);
				dutyHistoryDetailDlg.setData(saveRecords.get(index));
				if(dutyHistoryDetailDlg.open() == WindowPropsMananger.CLOSE_FLAG){
					dutyHistoryDetailDlg = null;
				}
				table.deselectAll();
			}
		});
		
		TableColumn nameColumn = new TableColumn(table, SWT.NONE);
		nameColumn.setText("\u503C\u73ED\u8B66\u5BDF");
		nameColumn.setResizable(false);
		tcl_composite.setColumnData(nameColumn, new ColumnPixelData(230, true, true));
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setText("\u63A5\u73ED\u65F6\u95F4");
		tableColumn.setResizable(false);
		tcl_composite.setColumnData(tableColumn, new ColumnPixelData(215, true, true));
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setText("\u4EA4\u73ED\u65F6\u95F4");
		tableColumn_1.setResizable(false);
		tcl_composite.setColumnData(tableColumn_1, new ColumnPixelData(215, true, true));
		
		companyCombo = new Combo(shell, SWT.READ_ONLY);
		companyCombo.setEnabled(false);
		companyCombo.setItems(new String[] {"\u6240\u6709\u5206\u76D1\u72F1", "\u4E00\u5206\u76D1\u72F1", "\u4E8C\u5206\u76D1\u72F1", "\u4E09\u5206\u76D1\u72F1", "\u56DB\u5206\u76D1\u72F1", "\u4E94\u5206\u76D1\u72F1", "\u4E2D\u5FC3\u533B\u9662"});
		fd_btnNewButton.right = new FormAttachment(companyCombo, 99, SWT.RIGHT);
		fd_btnNewButton.left = new FormAttachment(companyCombo, 19);
		companyCombo.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		FormData fd_companyCombo = new FormData();
		fd_companyCombo.right = new FormAttachment(endDateTime, 148, SWT.RIGHT);
		fd_companyCombo.left = new FormAttachment(endDateTime, 18);
		fd_companyCombo.top = new FormAttachment(endDateTime, 0, SWT.TOP);
		companyCombo.setLayoutData(fd_companyCombo);
		if(MainWindow.my_company == 0){
			companyCombo.setEnabled(true);
			companyCombo.select(0);
		}else if(MainWindow.my_company > 10 ) companyCombo.select(MainWindow.my_company / 10);
		else companyCombo.select(MainWindow.my_company);
		
	}
	
	public void setData(final Vector<JmglDutyRecord> dutyRecords){
		if(dutyRecords == null){
			return;
		}
		saveRecords = dutyRecords;
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				table.removeAll();
				Iterator<JmglDutyRecord> iterator = dutyRecords.iterator();
				try {
					while(iterator.hasNext())
					{
						
						JmglDutyRecord dRecords = iterator.next();
						TableItem item = new TableItem(table,SWT.NONE);
						item.setText(new String[] {dRecords.getName(),dRecords.getStartTime(),dRecords.getEndTime()});
						
					}
				} catch (Exception e) {
					if(MainWindow.isDebug)e.printStackTrace();
					else MainWindow.showErrorMessageBox(shell,e.getMessage());
				}
			}
		});
	}
	
	private void sendGetHistoryRequest(){
		Vector<JmglDutyRecord> dutyRecords = new Vector<JmglDutyRecord>();
		JmglDutyRecord dutyRecord = new JmglDutyRecord();
		String startMonth = "" + (startDateTime.getMonth()+1);
		if((startDateTime.getMonth()+1)<10) startMonth = "0" + startMonth;
		
		String startDay = "" + startDateTime.getDay();
		if(startDateTime.getDay()<10) startDay = "0" + startDay;
		
		String endMonth = "" + (endDateTime.getMonth()+1);
		if((endDateTime.getMonth()+1)<10) endMonth = "0" + endMonth;
		
		String endDay = "" + endDateTime.getDay();
		if(endDateTime.getDay()<10) endDay = "0" + endDay;
		
		dutyRecord.setStartTime(""+startDateTime.getYear() + startMonth + startDay + "000000");
		dutyRecord.setEndTime(""+endDateTime.getYear() + endMonth + endDay + "235959");
		dutyRecord.setStarter(companyCombo.getSelectionIndex());
		dutyRecords.add(dutyRecord);
		
		if(MainWindow.getInstance().connectServer())
		{
			DataPackage data = new DataPackage(MainWindow.VERSION,DataPropMananger.CMD_JMGL,DataPropMananger.JM_DUTY_GET_HISTORY,MainWindow.my_company, dutyRecords);
			MainWindow.getInstance().sendDataToServer(data);
		}
	}
}
