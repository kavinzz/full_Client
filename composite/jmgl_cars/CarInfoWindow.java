package jmgl_cars;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import ui.MainWindow;
import config.DataPropMananger;
import config.WindowPropsMananger;
import data.DataPackage;
import data.CarRecord;

public class CarInfoWindow extends Shell {
	private ScrolledComposite scrolledComposite;
	private Composite composite;
	
	private Vector<CarRecord> visitorRecords;
	private Vector<CarRecord> visitorCards;
	private Vector<CarCard> cards;
	
	private AddCarDlg cid;
	private Timer timer;
	private int currentView = -1; 
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			CarInfoWindow shell = new CarInfoWindow(display);
			
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
	public CarInfoWindow(Display display) {
		super(display, SWT.MIN|SWT.CLOSE);
		setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		setImage(SWTResourceManager.getImage("D:\\CTS\\source\\car32.png"));
		visitorRecords = new Vector<CarRecord>();
		visitorCards = new Vector<CarRecord>();
		cards = new Vector<CarCard>();
		createContents();
		
		sendUpdateRequest();
		
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u8F66\u8F86\u8FDB\u51FA\u76D1\u8BB0\u5F55");
		setSize(1170,900);
		setLocation((this.getDisplay().getBounds().width - this.getBounds().width)/2, (this.getDisplay().getBounds().height - this.getBounds().height)/2);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		composite = new Composite(scrolledComposite, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, true);
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
		mntmNewSubmenu.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				cid = new AddCarDlg(getShell(), SWT.DIALOG_TRIM);
				if(cid.open()==WindowPropsMananger.CLOSE_FLAG) cid = null;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		if(MainWindow.my_company < 10) mntmNewSubmenu.setEnabled(false);
		
		
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
		
		refreshDataTimerStart();
	}
	
	public void saveData(final Vector<CarRecord> data){
		visitorRecords.removeAllElements();
		visitorRecords = data;
		saveCard(data);
		showAllCards(DataPropMananger.VIEW_UNFINISH_ONLY,true);
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
								Iterator<CarCard> itCard = cards.iterator();
								while (itCard.hasNext()) {
									CarCard card = itCard.next();
									card.dispose();
								}
								cards.removeAllElements();
							}
							
							composite.layout();
							
							switch (view) {
								case DataPropMananger.VIEW_UNFINISH_ONLY:
									Iterator<CarRecord> it = visitorCards.iterator();
									while(it.hasNext()){
										CarRecord criminalRecord = it.next();
										if(isFinish(criminalRecord))
										{
											continue;
										}
										CarCard temp = new CarCard(composite, SWT.DEFAULT);
										temp.addData(criminalRecord);
										cards.add(temp);
									}
									break;
								case DataPropMananger.VIEW_ALL:
									Iterator<CarRecord> it2 = visitorCards.iterator();
									while(it2.hasNext()){
										CarRecord criminalRecord = it2.next();
										
										CarCard temp = new CarCard(composite, SWT.DEFAULT);
										temp.addData(criminalRecord);
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
	
	private boolean isFinish(CarRecord criminalRecord){
		return criminalRecord.isFinish();
	}
	
	private void saveCard(Vector<CarRecord> cRecords){
		visitorCards.removeAllElements();
		Iterator<CarRecord> it = cRecords.iterator();

		while(it.hasNext()){
			
			CarRecord criRecord = it.next();
			
			if(visitorCards.size() == 0){
				visitorCards.add(criRecord);
			}
			else{
				boolean isNotExist = true;
				for(int j = 0; j < visitorCards.size(); j++){
					if(visitorCards.get(j).getUuid().equals(criRecord.getUuid())){
						visitorCards.set(j, criRecord);
						isNotExist = false;
						break;
					}
					else {
						isNotExist = true;
					}
				}
				if(isNotExist){
					visitorCards.add(0, criRecord);
				}
			}
		}
	}
	
	private void sendUpdateRequest(){
		if(MainWindow.getInstance().connectServer())
		{
			DataPackage data = new DataPackage(MainWindow.VERSION,DataPropMananger.CMD_JMGL,DataPropMananger.CAR_UPDATE,MainWindow.my_company, null);
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
