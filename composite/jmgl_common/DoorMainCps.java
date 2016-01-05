package jmgl_common;

import java.util.Vector;

import jmgl_cars.CarInfoWindow;
import jmgl_criminal.CriminalInfoWindow;
import jmgl_dutylog.DutyHistoryDlg;
import jmgl_visitor.VisitorInfoWindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import config.WindowPropsMananger;
import ui.MainWindow;
import data.CarRecord;
import data.CriminalRecord;
import data.JmglDutyRecord;
import data.VisitorRecord;

public class DoorMainCps extends Composite {
	
	private CriminalInfoWindow criminalInfoWindow;
	private VisitorInfoWindow visitorInfoWindow;
	private CarInfoWindow carInfoWindow;
	private DutyHistoryDlg dutyHistoryDlg;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DoorMainCps(Composite parent, int style) {
		super(parent, SWT.NONE);
		setBounds(0, 0, 300, 400);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		rowLayout.justify = true;
		rowLayout.center = true;
		setLayout(rowLayout);
		
		Button criminalBtn = new Button(this, SWT.NONE);
		criminalBtn.setImage(SWTResourceManager.getImage("D:\\CTS\\source\\criminal32.png"));
		criminalBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openCriminalInfoWindow();
			}
		});
		criminalBtn.setLayoutData(new RowData(400, 70));
		criminalBtn.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		criminalBtn.setFont(SWTResourceManager.getFont("宋体", 22, SWT.BOLD));
		criminalBtn.setText("\u7F6A \u72AF \u8FDB \u51FA \u76D1 \u7BA1 \u7406");
		
		Button visitorButton = new Button(this, SWT.NONE);
		visitorButton.setImage(SWTResourceManager.getImage("D:\\CTS\\source\\men48.png"));
		visitorButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openVisitorInfoWindow();
			}
		});
		visitorButton.setLayoutData(new RowData(400, 70));
		visitorButton.setText("\u4EBA \u5458 \u8FDB \u51FA \u76D1 \u7BA1 \u7406");
		visitorButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		visitorButton.setFont(SWTResourceManager.getFont("宋体", 22, SWT.BOLD));
		
		Button carButton = new Button(this, SWT.NONE);
		carButton.setImage(SWTResourceManager.getImage("D:\\CTS\\source\\car32.png"));
		carButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openCarInfoWindow();
			}
		});
		carButton.setLayoutData(new RowData(400, 70));
		carButton.setText("\u8F66 \u8F86 \u8FDB \u51FA \u76D1 \u7BA1 \u7406");
		carButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		carButton.setFont(SWTResourceManager.getFont("宋体", 22, SWT.BOLD));
		
		if(MainWindow.my_company < 10){
			Button dutyHisBtn = new Button(this, SWT.NONE);
			dutyHisBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(dutyHistoryDlg == null)
					{
						dutyHistoryDlg = new DutyHistoryDlg(getShell(), SWT.CLOSE);
						if(dutyHistoryDlg.open() == WindowPropsMananger.CLOSE_FLAG){
							dutyHistoryDlg = null;
						}
					}
				}
			});
			dutyHisBtn.setLayoutData(new RowData(395, 73));
			dutyHisBtn.setText("\u76D1\u95E8\u4EA4\u63A5\u73ED\u8BB0\u5F55\u67E5\u8BE2");
			dutyHisBtn.setImage(null);
			dutyHisBtn.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			dutyHisBtn.setFont(SWTResourceManager.getFont("宋体", 22, SWT.BOLD));
		}
	}
	
	private void openCriminalInfoWindow(){
		if(criminalInfoWindow == null){
			criminalInfoWindow = new CriminalInfoWindow(this.getDisplay());
			criminalInfoWindow.addDisposeListener(new DisposeListener() {
				
				@Override
				public void widgetDisposed(DisposeEvent arg0) {
					// TODO Auto-generated method stub
					criminalInfoWindow.stopTimer();
					criminalInfoWindow = null;
				}
			});
			criminalInfoWindow.open();
		}
		else{
			if(criminalInfoWindow.isDisposed()){
				criminalInfoWindow = null;
				openCriminalInfoWindow();
			}
			else 
			{
				criminalInfoWindow.setMinimized(false);
				criminalInfoWindow.setFocus();
			}
		}
	}
	
	private void openVisitorInfoWindow(){
		if(visitorInfoWindow == null){
			visitorInfoWindow = new VisitorInfoWindow(this.getDisplay());
			visitorInfoWindow.addDisposeListener(new DisposeListener() {
				
				@Override
				public void widgetDisposed(DisposeEvent arg0) {
					// TODO Auto-generated method stub
					visitorInfoWindow.stopTimer();
					visitorInfoWindow = null;
				}
			});
			visitorInfoWindow.open();
		}
		else{
			if(visitorInfoWindow.isDisposed()){
				visitorInfoWindow = null;
				openVisitorInfoWindow();
			}
			else 
			{
				visitorInfoWindow.setMinimized(false);
				visitorInfoWindow.setFocus();
			}
		}
	}
	
	private void openCarInfoWindow(){
		if(carInfoWindow == null){
			carInfoWindow = new CarInfoWindow(this.getDisplay());
			carInfoWindow.addDisposeListener(new DisposeListener() {
				
				@Override
				public void widgetDisposed(DisposeEvent arg0) {
					// TODO Auto-generated method stub
					carInfoWindow.stopTimer();
					carInfoWindow = null;
				}
			});
			carInfoWindow.open();
		}
		else{
			if(carInfoWindow.isDisposed()){
				carInfoWindow = null;
				openCarInfoWindow();
			}
			else 
			{
				carInfoWindow.setMinimized(false);
				carInfoWindow.setFocus();
			}
		}
	}
	
	public void criminalDataHandler(Vector<CriminalRecord> data){
		if(criminalInfoWindow != null && !criminalInfoWindow.isDisposed()){
			criminalInfoWindow.saveData(data);
		}
	}
	
	public void visitorDataHandler(Vector<VisitorRecord> data){
		if(visitorInfoWindow != null && !visitorInfoWindow.isDisposed()){
			visitorInfoWindow.saveData(data);
		}
	}
	
	public void carDataHandler(Vector<CarRecord> data){
		if(carInfoWindow != null && !carInfoWindow.isDisposed()){
			carInfoWindow.saveData(data);
		}
	}
	
	public void dutyHistoryDataHandler(Vector<JmglDutyRecord> data){
		if(dutyHistoryDlg != null){
			dutyHistoryDlg.setData(data);
		}
	}
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
