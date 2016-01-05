package jmgl_criminal;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import config.DataPropMananger;
import config.WindowPropsMananger;
import data.CriminalRecord;
import data.DataPackage;
import ui.MainWindow;

import org.eclipse.swt.layout.FillLayout;

public class CriminalInfoWindow extends Shell {
	private ScrolledComposite scrolledComposite;
	private Composite composite;
	
	private Vector<CriminalRecord> criminalRecords;
	private Vector<CriminalRecord> criminalCards;
	private Vector<CriminalCard> cards;
	
	private CriminalInfoDlg cid;
	private Timer timer;
	private int currentView = DataPropMananger.VIEW_UNFINISH_ONLY; 
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			CriminalInfoWindow shell = new CriminalInfoWindow(display);
			
			shell.open();
			shell.layout();
			
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public CriminalInfoWindow(Display display) {
		super(display, SWT.MIN|SWT.CLOSE);
		setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		setImage(SWTResourceManager.getImage("D:\\CTS\\source\\criminal32.png"));
		criminalRecords = new Vector<CriminalRecord>();
		criminalCards = new Vector<CriminalRecord>();
		cards = new Vector<CriminalCard>();
		createContents();
		
		sendUpdateRequest();
		
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u7F6A\u72AF\u8FDB\u51FA\u76D1\u8BB0\u5F55");
		setSize(1350, 900);
		setLocation((this.getDisplay().getBounds().width - this.getBounds().width)/2, (this.getDisplay().getBounds().height - this.getBounds().height)/2);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		composite = new Composite(scrolledComposite, SWT.NONE);
		GridLayout gl_composite = new GridLayout(4, true);
		gl_composite.marginTop = 10;
		gl_composite.marginBottom = 5;
		gl_composite.marginLeft = 30;
		gl_composite.horizontalSpacing = 30;
		gl_composite.verticalSpacing = 30;
		
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setLayout(gl_composite);
		
		scrolledComposite.setContent(composite);
		
		
		Menu menu = new Menu(this, SWT.BAR);
		setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("\u65B0\u589E\u8BB0\u5F55");
		if(MainWindow.my_company < 10) mntmNewSubmenu.setEnabled(false);
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.setText("\u6B63\u5E38\u8FDB\u51FA...");
		mntmNewItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				cid = new CriminalInfoDlg(getShell(), SWT.DIALOG_TRIM);
				if(cid.open(false)==WindowPropsMananger.CLOSE_FLAG) cid = null;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		MenuItem mntmNewItem_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_1.setText("\u5927\u6279\u91CF\u8FDB\u51FA...");
		mntmNewItem_1.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				cid = new CriminalInfoDlg(getShell(), SWT.DIALOG_TRIM);
				if(cid.open(true)==WindowPropsMananger.CLOSE_FLAG) cid = null;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		MenuItem mntmNewSubmenu_2 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_2.setText("\u67E5\u770B");
		
		Menu menu_3 = new Menu(mntmNewSubmenu_2);
		mntmNewSubmenu_2.setMenu(menu_3);
		
		MenuItem mntmNewRadiobutton = new MenuItem(menu_3, SWT.RADIO);
		mntmNewRadiobutton.setSelection(true);
		mntmNewRadiobutton.setText("\u663E\u793A\u6240\u6709\u672A\u5B8C\u6210");
		mntmNewRadiobutton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				showAllCards(DataPropMananger.VIEW_UNFINISH_ONLY,false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		MenuItem mntmNewRadiobutton_1 = new MenuItem(menu_3, SWT.RADIO);
		mntmNewRadiobutton_1.setEnabled(false);
		mntmNewRadiobutton_1.setText("\u4EC5\u67E5\u770B\u51FA\u76D1");
		
		MenuItem mntmNewRadiobutton_2 = new MenuItem(menu_3, SWT.RADIO);
		mntmNewRadiobutton_2.setEnabled(false);
		mntmNewRadiobutton_2.setText("\u4EC5\u67E5\u770B\u5165\u76D1");
		
		MenuItem menuItem = new MenuItem(menu_3, SWT.RADIO);
		menuItem.setText("\u67E5\u770B\u6240\u6709\u8BB0\u5F55");
		menuItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				showAllCards(DataPropMananger.VIEW_ALL,false);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		MenuItem mntmNewSubmenu_1 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_1.setText("\u7CFB\u7EDF");
		
		Menu menu_2 = new Menu(mntmNewSubmenu_1);
		mntmNewSubmenu_1.setMenu(menu_2);
		
		MenuItem mntmNewItem_3 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_3.setText("\u586B\u62A5\u8BF4\u660E...");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmNewItem_4 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_4.setText("\u7248\u672C");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.SEPARATOR);
		
		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_2.setText("\u6279\u91CF\u8FDB/\u51FA");
		mntmNewItem_2.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				openMutiInOutDlg();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		if(MainWindow.my_company < 10) mntmNewItem_2.setEnabled(false);
		
		refreshDataTimerStart();
	}
	
	public void saveData(final Vector<CriminalRecord> data){
		criminalRecords.removeAllElements();
		criminalRecords = data;
		saveCard(data);
		showAllCards(currentView,true);
	}
	
	private void showAllCards(final int view,final boolean isNew){
		if(currentView == view && !isNew) return;
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				/*ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
				try {
					
					progressMonitorDialog.run(false, false, new IRunnableWithProgress() {
						@Override
						public void run(IProgressMonitor arg0) throws InvocationTargetException,
								InterruptedException {
							arg0.beginTask("获取数据", IProgressMonitor.UNKNOWN);
							arg0.setTaskName("正在获取数据，请稍等。。。。。。");*/
							
							composite.setVisible(false);
							if(cards.size() != 0){
								Iterator<CriminalCard> itCard = cards.iterator();
								while (itCard.hasNext()) {
									CriminalCard card = itCard.next();
									card.dispose();
								}
								cards.removeAllElements();
							}
							
							composite.layout();
							
							switch (view) {
								case DataPropMananger.VIEW_UNFINISH_ONLY:
									Iterator<CriminalRecord> it = criminalCards.iterator();
									while(it.hasNext()){
										CriminalRecord criminalRecord = it.next();
										if(isFinish(criminalRecord))
										{
											continue;
										}
										CriminalCard temp = new CriminalCard(composite, SWT.DEFAULT);
										temp.addData(criminalRecord,getCriminalRecordsByUUID(criminalRecord.getUuid()));
										cards.add(temp);
									}
									break;
								case DataPropMananger.VIEW_ALL:
									Iterator<CriminalRecord> it2 = criminalCards.iterator();
									while(it2.hasNext()){
										CriminalRecord criminalRecord = it2.next();
										
										CriminalCard temp = new CriminalCard(composite, SWT.DEFAULT);
										temp.addData(criminalRecord,getCriminalRecordsByUUID(criminalRecord.getUuid()));
										cards.add(temp);
									}
									break;
							}
							currentView = view;
							composite.layout();
							composite.setVisible(true);
							scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
						/*}
					});
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
			}
		});
	}
	
	private void openMutiInOutDlg(){
		Vector<CriminalRecord> selections = getSeletedCards();
		if(selections == null){
			MainWindow.showErrorMessageBox(getShell(), "批量选择错误，请勿同时勾选 目的地不同 或 进出状态不同 的记录");
			return;
		}
		if(selections.size() == 0) return;
		MutiInOutAddDlg mutiInOutAddDlg = new MutiInOutAddDlg(getShell(), getStyle());
		mutiInOutAddDlg.setData(selections);
		mutiInOutAddDlg.open();
	}
	
	private Vector<CriminalRecord> getSeletedCards(){
		Iterator<CriminalCard> it = cards.iterator();
		Vector<CriminalRecord> criminalCards = new Vector<CriminalRecord>();
		int tempDest = -1;
		int tempState = -1;
		while(it.hasNext()){
			CriminalCard card = it.next();
			if(card.isSelected()){
				if(tempDest == -1) tempDest = card.getCriminalRecord().getDestination();
				else if(tempDest != card.getCriminalRecord().getDestination()) return null;
				
				if(tempState == -1) tempState = card.getCriminalRecord().getState();
				else if(tempState != card.getCriminalRecord().getState()) return null;
				criminalCards.add(card.getCriminalRecord());
			}
		}
		return criminalCards;
	}
	
	private boolean isFinish(CriminalRecord criminalRecord){
		return ((criminalRecord.getCompany() == criminalRecord.getDestination()
				&& criminalRecord.getState() == DataPropMananger.ST_IN 
				&& (criminalRecord.getStarter()/10) == criminalRecord.getCompany()))
				|| criminalRecord.isFinish();
	}
	
	private void saveCard(Vector<CriminalRecord> cRecords){
		criminalCards.removeAllElements();
		Iterator<CriminalRecord> it = cRecords.iterator();

		while(it.hasNext()){
			
			CriminalRecord criRecord = it.next();
			
			if(criminalCards.size() == 0){
				criminalCards.add(criRecord);
			}
			else{
				boolean isNotExist = true;
				for(int j = 0; j < criminalCards.size(); j++){
					if(criminalCards.get(j).getUuid().equals(criRecord.getUuid())){
						criminalCards.set(j, criRecord);
						isNotExist = false;
						break;
					}
					else {
						isNotExist = true;
					}
				}
				if(isNotExist){
					criminalCards.add(0, criRecord);
				}
			}
		}
	}
	
	private Vector<CriminalRecord> getCriminalRecordsByUUID(String uuid){
		Vector<CriminalRecord> newRecords = new Vector<CriminalRecord>();
		Iterator<CriminalRecord> iterator = criminalRecords.iterator();
		while (iterator.hasNext()) {
			CriminalRecord criminalRecord = iterator.next();
			if(criminalRecord.getUuid().equals(uuid)){
				newRecords.add(criminalRecord);
			}
		}
		return newRecords;
	}
	
	private void sendUpdateRequest(){
		if(MainWindow.getInstance().connectServer())
		{
			DataPackage data = new DataPackage(MainWindow.VERSION,DataPropMananger.CMD_JMGL,DataPropMananger.CRIMINAL_UPDATE,MainWindow.my_company, null);
			MainWindow.getInstance().sendDataToServer(data);
		}
	}
	
	private void refreshDataTimerStart(){
		timer =new Timer();//实例化Timer类   
		timer.schedule(new TimerTask(){   
			public void run(){
				if(!MainWindow.refreshLock) sendUpdateRequest();
			}
		},180000,180000);//3分钟刷新
	}
	
	public void stopTimer(){
		timer.cancel();
		timer = null;
	}
	
	protected void checkSubclass() {
        // TODO Auto-generated method stub
    
    }
}
