package jmgl_dutylog;

import java.util.Iterator;
import java.util.Vector;

import jmgl_common.ImageViewDlg;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TableColumn;

import ui.MainWindow;
import config.DataPropMananger;
import config.WindowPropsMananger;
import data.CriminalRecord;

public class RecordDetailDlg extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private Label nameLable;
	private Vector<CriminalRecord> cRecords;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public RecordDetailDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		createContents();
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(Display.getDefault(), SWT.SYSTEM_MODAL|SWT.CLOSE|SWT.TITLE|SWT.PRIMARY_MODAL);
		shell.setSize(1201, 351);
		shell.setText("\u8FDB\u51FA\u8BB0\u5F55");
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width)/2, (Display.getDefault().getBounds().height - shell.getBounds().height)/2);
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		table.setBounds(10, 46, 1175, 261);
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
				ImageViewDlg imageViewDlg = new ImageViewDlg(shell, getStyle());
				imageViewDlg.shwoImg(cRecords.get(index).getWatherHW(), cRecords.get(index).getDoorHW());
				if(imageViewDlg.open() == WindowPropsMananger.CLOSE_FLAG){
					imageViewDlg = null;
				}
			}
		});
		
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setResizable(false);
		tblclmnNewColumn.setWidth(220);
		tblclmnNewColumn.setText("\u65F6 \u95F4");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setResizable(false);
		tblclmnNewColumn_1.setWidth(92);
		tblclmnNewColumn_1.setText("\u8FDB/\u51FA");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setResizable(false);
		tblclmnNewColumn_2.setWidth(134);
		tblclmnNewColumn_2.setText("\u72B6\u6001");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setResizable(false);
		tblclmnNewColumn_3.setWidth(129);
		tblclmnNewColumn_3.setText("\u6240\u5C5E\u5206\u76D1\u72F1");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setResizable(false);
		tblclmnNewColumn_4.setWidth(140);
		tblclmnNewColumn_4.setText("\u76EE\u7684\u5730");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_5.setResizable(false);
		tblclmnNewColumn_5.setWidth(165);
		tblclmnNewColumn_5.setText("\u62BC\u89E3\u8B66\u5BDF");
		
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_6.setResizable(false);
		tblclmnNewColumn_6.setWidth(148);
		tblclmnNewColumn_6.setText("\u76D1\u95E8\u8B66\u5BDF");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_7.setResizable(false);
		tblclmnNewColumn_7.setWidth(142);
		tblclmnNewColumn_7.setText("\u64CD\u4F5C\u4EBA");
		
		nameLable = new Label(shell, SWT.NONE);
		nameLable.setFont(SWTResourceManager.getFont("仿宋", 20, SWT.BOLD));
		nameLable.setBounds(10, 10, 252, 27);
		nameLable.setText("\u7F6A\u72AF\u59D3\u540D");

	}
	
	public void setData(final String name,final Vector<CriminalRecord> criminalRecords){
		cRecords = criminalRecords;
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				nameLable.setText(name);
				Iterator<CriminalRecord> iterator = criminalRecords.iterator();
				try {
					while(iterator.hasNext())
					{
						CriminalRecord criRecords = iterator.next();
							String dest = "";
							if(criRecords.getOther_place()!=null) dest= "(" + criRecords.getOther_place() + ")";
							TableItem item = new TableItem(table,SWT.NONE);
							item.setText(new String[] {criRecords.getSt_time(),DataPropMananger.getInOutString(criRecords.getState()),criRecords.getSt_reason(),
														DataPropMananger.getCompanyName(criRecords.getCompany()),
														DataPropMananger.getCompanyName(criRecords.getDestination()) + dest,"(双击查看签名)","(双击查看签名)",
														DataPropMananger.getCompanyName(criRecords.getStarter())});
							
					}
				} catch (Exception e) {
					if(MainWindow.isDebug)e.printStackTrace();
					else MainWindow.showErrorMessageBox(shell,e.getMessage());
				}
			}
		});
	}
}
