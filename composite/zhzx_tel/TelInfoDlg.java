package zhzx_tel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import ui.MainWindow;
import config.ConfigPropMananger;
import config.DataPropMananger;
import config.WindowPropsMananger;
import data.DataPackage;
import data.TelRecord;


public class TelInfoDlg extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text numberText;
	private Table table;
	private Combo leaderCombo;
	private Combo companyCombo;
	private Button okButton;
	private DateTime dateTime;
	private Button questButton;
	private Button nextSaveBtn;
	private Button backSaveBtn;
	private Label infoText;
	
	private TelRecord tempRecord;
	private TelRecord changeTempRecord;
	
	private Vector<TelRecord> telRecords;
	private String[] leaders;
	private Text reasonText;
	
	private int defaultLines = 30;
	private Text nextInfoText;
	private Text backInfoText;
	private Label nextSaveLable;
	private Label backSaveLable;
	private Button delButton;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public TelInfoDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public int open() {
		createContents();
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage("D:\\CTS\\source\\icon.png"));
		shell.setSize(749, 755);
		shell.setText("\u6765\u7535\u8BB0\u5F55");
		shell.setLocation((Display.getCurrent().getBounds().width - shell.getBounds().width)/2, (Display.getCurrent().getBounds().height - shell.getBounds().height)/2);
		
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				//ClientMain.getInstance().disconnectServer();
			}
		});
		telRecords = new Vector<TelRecord>();
		
		dateTime = new DateTime(shell, SWT.BORDER | SWT.TIME);
		dateTime.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		dateTime.setBounds(10, 32, 101, 27);
		
		Button getTimeButton = new Button(shell, SWT.NONE);
		getTimeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Date date = new Date(System.currentTimeMillis());
				dateTime.setTime(date.getHours(), date.getMinutes(), date.getSeconds());
			}
		});
		getTimeButton.setBounds(113, 32, 80, 27);
		getTimeButton.setText("\u83B7\u53D6\u5F53\u524D\u65F6\u95F4");
		
		companyCombo = new Combo(shell, SWT.NONE);
		if(MainWindow.my_company == 0)companyCombo.setItems(new String[] {"\u4E00\u5206\u76D1\u72F1", "\u4E8C\u5206\u76D1\u72F1", "\u4E09\u5206\u76D1\u72F1", "\u56DB\u5206\u76D1\u72F1", "\u4E94\u5206\u76D1\u72F1", "\u4E2D\u5FC3\u533B\u9662", "\u7701\u5C40\u6307\u6325\u4E2D\u5FC3"});
		companyCombo.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		companyCombo.setBounds(199, 33, 125, 24);
		
		
		leaderCombo = new Combo(shell, SWT.NONE);
		leaderCombo.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		leaderCombo.setBounds(330, 33, 124, 24);
		leaderCombo.addSelectionListener(new SelectionAdapter() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub
						numberText.setFocus();
					}
					
				});
		
		companyCombo.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				int selectIndex = companyCombo.getSelectionIndex();
				if(leaders == null) return;
				if(selectIndex == -1 || selectIndex > 5) {
					leaderCombo.setText("");
					leaderCombo.removeAll();
					return;
				}
				leaderCombo.setItems(leaders[selectIndex].split("，"));
				leaderCombo.select(0);
			}
			
		});
		
		numberText = new Text(shell, SWT.BORDER);
		numberText.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		numberText.setBounds(460, 34, 180, 23);
		numberText.setFocus();
		
		okButton = new Button(shell, SWT.NONE);
		okButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		okButton.setBounds(659, 43, 60, 43);
		okButton.setText("\u63D0\u4EA4");
		okButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				String err = check();	
				if(err.equals("")){
					okButton.setEnabled(false);
					sendAddRequest();
				}
				else{
					MainWindow.showErrorMessageBox(shell,err);
				}
			}
			
		});
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		lblNewLabel.setBounds(10, 9, 61, 17);
		lblNewLabel.setText("\u65F6\u95F4");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u5355\u4F4D");
		label.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		label.setBounds(199, 9, 61, 17);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u59D3\u540D");
		label_1.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		label_1.setBounds(330, 9, 61, 17);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u7535\u8BDD");
		label_2.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		label_2.setBounds(460, 9, 61, 17);
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		table.setBounds(10, 258, 726, 284);
		table.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				int index = table.getSelectionIndex();
				if(telRecords == null || telRecords.size() == 0|| index == -1) return;
				if(telRecords.get(index) == null) return;
				changeTempRecord = telRecords.get(index);
				if(changeTempRecord.getSt_reason() != null) infoText.setText(changeTempRecord.getSt_reason());
				else infoText.setText("");
				if(changeTempRecord.getNext_info() != null) nextInfoText.setText(changeTempRecord.getNext_info());
				else nextInfoText.setText("");
				nextInfoText.setEnabled(true);
				nextSaveBtn.setEnabled(true);
				if(changeTempRecord.getBack_info() != null) backInfoText.setText(changeTempRecord.getBack_info());
				else backInfoText.setText("");
				backInfoText.setEnabled(true);
				backSaveBtn.setEnabled(true);
				delButton.setEnabled(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(195);
		tableColumn.setText("\u65F6  \u95F4");
		tableColumn.setResizable(false);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(115);
		tableColumn_1.setText("\u5355\u4F4D");
		tableColumn_1.setResizable(false);
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(85);
		tableColumn_2.setText("\u59D3\u540D");
		tableColumn_2.setResizable(false);
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(130);
		tableColumn_3.setText("\u7535\u8BDD\u53F7\u7801");
		tableColumn_3.setResizable(false);
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(175);
		tableColumn_4.setText("\u7535\u8BDD\u5185\u5BB9");
		tableColumn_4.setResizable(false);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u6765\u7535\u8BB0\u5F55");
		label_3.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		label_3.setBounds(10, 80, 74, 17);
		
		reasonText = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		reasonText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		reasonText.setBounds(90, 80, 549, 100);
		
		Button rButton30 = new Button(shell, SWT.RADIO);
		rButton30.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				defaultLines = 30;
			}
		});
		rButton30.setSelection(true);
		rButton30.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		rButton30.setBounds(154, 223, 97, 17);
		rButton30.setText("30\u6761\u8BB0\u5F55");
		
		Button rButton100 = new Button(shell, SWT.RADIO);
		rButton100.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				defaultLines = 100;
			}
		});
		rButton100.setText("100\u6761\u8BB0\u5F55");
		rButton100.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		rButton100.setBounds(277, 223, 97, 17);
		
		Button rButtonAll = new Button(shell, SWT.RADIO);
		rButtonAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				defaultLines = 99999999;
			}
		});
		rButtonAll.setText("\u6240\u6709\u8BB0\u5F55");
		rButtonAll.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		rButtonAll.setBounds(408, 223, 85, 17);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u67E5\u8BE2");
		label_4.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		label_4.setBounds(100, 223, 32, 17);
		
		questButton = new Button(shell, SWT.NONE);
		questButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sendGetRequest(defaultLines);
			}
		});
		questButton.setText("\u63D0\u4EA4");
		questButton.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		questButton.setBounds(513, 218, 60, 27);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u6765\u7535\u5904\u7406");
		label_5.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		label_5.setBounds(10, 637, 74, 17);
		
		nextInfoText = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		nextInfoText.setEnabled(false);
		nextInfoText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		nextInfoText.setBounds(90, 637, 230, 72);
		nextInfoText.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent arg0) {
				// TODO Auto-generated method stub
				nextSaveLable.setText("未保存");
			}
		});
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("\u53CD\u9988\u60C5\u51B5");
		label_6.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		label_6.setBounds(330, 637, 74, 17);
		
		backInfoText = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		backInfoText.setEnabled(false);
		backInfoText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		backInfoText.setBounds(408, 637, 230, 72);
		backInfoText.addVerifyListener(new VerifyListener() {
					
					@Override
					public void verifyText(VerifyEvent arg0) {
						// TODO Auto-generated method stub
						backSaveLable.setText("未保存");
					}
				});
		
		nextSaveBtn = new Button(shell, SWT.NONE);
		nextSaveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(nextInfoText.getText().trim().equals("")) return;
				sendNextInfoUpdateRequest();
			}
		});
		nextSaveBtn.setEnabled(false);
		nextSaveBtn.setText("\u4FDD\u5B58");
		nextSaveBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		nextSaveBtn.setBounds(17, 660, 60, 27);
		
		backSaveBtn = new Button(shell, SWT.NONE);
		backSaveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(backInfoText.getText().trim().equals("")) return;
				sendBackInfoUpdateRequest();
			}
		});
		backSaveBtn.setEnabled(false);
		backSaveBtn.setText("\u4FDD\u5B58");
		backSaveBtn.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		backSaveBtn.setBounds(337, 660, 60, 27);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\u7535\u8BDD\u5185\u5BB9");
		label_7.setFont(SWTResourceManager.getFont("宋体", 12, SWT.BOLD));
		label_7.setBounds(10, 558, 74, 17);
		
		infoText = new Label(shell, SWT.BORDER | SWT.WRAP);
		infoText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		infoText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		infoText.setBounds(90, 558, 643, 62);
		
		nextSaveLable = new Label(shell, SWT.NONE);
		nextSaveLable.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		nextSaveLable.setBounds(17, 693, 61, 17);
		
		backSaveLable = new Label(shell, SWT.NONE);
		backSaveLable.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		backSaveLable.setBounds(337, 692, 61, 17);
		
		Label label_8 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_8.setBounds(10, 200, 726, 2);
		
		delButton = new Button(shell, SWT.NONE);
		delButton.setEnabled(false);
		delButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(changeTempRecord == null) return;
				MessageBox msgBox = new MessageBox(shell,SWT.OK|SWT.CANCEL|SWT.ICON_WARNING);
				msgBox.setMessage("是否要删除 "+changeTempRecord.getTime()+" 由 "+changeTempRecord.getName()+" 打来的电话记录");
				msgBox.setText("请确认！");
				if(msgBox.open() == SWT.OK)
				{
					sendDelRequest();
					msgBox = null;
				}
			}
		});
		delButton.setText("\u5220\u9664\u8BE5\u6761\u8BB0\u5F55");
		delButton.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		delButton.setBounds(644, 682, 92, 27);
		
		reasonText.addMouseListener(new MouseListener() {
			
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
				if(MainWindow.my_company == 0) reasonText.setText("报告分监狱情况正常");
				else reasonText.selectAll(); 
			}
		});
		
		if(MainWindow.my_company == 0)leaders = ConfigPropMananger.getInstance().LoadNamesConfig();
	}
	
	public void telDataGetHandler(Vector<TelRecord> telRecords){
		saveData(telRecords);
		showDataIntable();
	}
	
	public void telAddSuccess(Vector<TelRecord> tRecords){
		saveData(tRecords);
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				okButton.setEnabled(true);
				companyCombo.select(-1);
				companyCombo.setText("");
				leaderCombo.select(-1);
				leaderCombo.setText("");
				numberText.setText("");
				reasonText.setText("");
			}
		});
		showDataIntable();
	}
	
	private void saveData(Vector<TelRecord> tRecords){
		telRecords.removeAllElements();
		
		Iterator<TelRecord> iterator = tRecords.iterator();	
		while (iterator.hasNext())
		{
			TelRecord tRecord = iterator.next();
			telRecords.add(tRecord);
		}
	}
	
	private void showDataIntable(){
		System.out.print(telRecords.size());
		if(telRecords.size() == 0) return;
		
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				
				tempRecord = null;
				changeTempRecord = null;
				table.removeAll();
				infoText.setText("");
				nextInfoText.setText("");
				nextInfoText.setEnabled(false);
				nextSaveBtn.setEnabled(false);
				nextSaveLable.setText("");
				backInfoText.setText("");
				backInfoText.setEnabled(false);
				backSaveBtn.setEnabled(false);
				backSaveLable.setText("");
				
				Iterator<TelRecord> iterator = telRecords.iterator();
				
				try {
					while(iterator.hasNext())
					{
						TelRecord telRecord = iterator.next();
						
						TableItem item = new TableItem(table,SWT.NONE);
						setTableItemFormat(item,telRecord);
						
					}
					
				} catch (Exception e) {
					if(MainWindow.isDebug)e.printStackTrace();
					else MainWindow.showErrorMessageBox(shell, e.getMessage());
				}
			}
		});
	}
	
	private void setTableItemFormat(final TableItem item,final TelRecord telRecord){
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				
				
				item.setText(new String[] {telRecord.getTime(),telRecord.getCompany(),telRecord.getName()
						,telRecord.getNumber(),telRecord.getSt_reason()});
				
			}
		});
	}
	
	private String check(){
		String str = "";
		
		if(numberText.getText().trim().equals("")) return "请填写来电号码";
		if(leaderCombo.getText().trim().equals("")) return "请填写来电人姓名";
		if(companyCombo.getText().trim().equals("")) return "请填写单位";
		if(reasonText.getText().trim().equals("")) return "请填写来电内容";
		
		return str;
	}
	
	private Vector<TelRecord> getTelRecords(){
		Vector<TelRecord> telRecords = new Vector<TelRecord>();
		tempRecord = new TelRecord();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String date = sdf.format(System.currentTimeMillis());
		tempRecord.setTime(date + dateTime.getHours() + "时" + dateTime.getMinutes() + "分");
		tempRecord.setCompany(companyCombo.getText());
		tempRecord.setName(leaderCombo.getText());
		tempRecord.setNumber(numberText.getText());
		tempRecord.setSt_reason(reasonText.getText());
		tempRecord.setStarter(MainWindow.my_company);
		tempRecord.setLines(1);
		//telRecord.setDuty_index(duty_index);
		telRecords.add(tempRecord);
		
		return telRecords;
	}
	
	private void sendAddRequest(){
		if(MainWindow.getInstance().connectServer()){
			MainWindow.getInstance().sendDataToServer(new DataPackage(MainWindow.VERSION, DataPropMananger.CMD_ZHZX,
					DataPropMananger.ZHZX_TEL_ADD, MainWindow.my_company, getTelRecords()));
		}
	}
	
	private void sendGetRequest(int lines){
		Vector<TelRecord> telRecords = new Vector<TelRecord>();
		TelRecord telRecord = new TelRecord();
		telRecord.setStarter(MainWindow.my_company);
		telRecord.setLines(lines);
		telRecords.add(telRecord);
		if(MainWindow.getInstance().connectServer()){
			MainWindow.getInstance().sendDataToServer(new DataPackage(MainWindow.VERSION, DataPropMananger.CMD_ZHZX,
					DataPropMananger.ZHZX_TEL_GET_ALL, MainWindow.my_company, telRecords));
		}
		
	}
	
	private void sendNextInfoUpdateRequest(){
		Vector<TelRecord> telRecords = new Vector<TelRecord>();
		TelRecord telRecord = new TelRecord();
		telRecord.setStarter(changeTempRecord.getStarter());
		telRecord.setNext_info(nextInfoText.getText().trim());
		telRecord.setSt_time(changeTempRecord.getSt_time());
		telRecords.add(telRecord);
		if(MainWindow.getInstance().connectServer()){
			MainWindow.getInstance().sendDataToServer(new DataPackage(MainWindow.VERSION, DataPropMananger.CMD_ZHZX,
					DataPropMananger.ZHZX_TEL_UPDATE_NEXT, MainWindow.my_company, telRecords));
		}
	}
	
	public void telUpdateSuccess(final int i){
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				if(i == 0){
					nextSaveBtn.setEnabled(true);
					nextSaveLable.setText("已保存");
				}
				else if(i == 1){
					backSaveBtn.setEnabled(true);
					backSaveLable.setText("已保存");
				}
			}
		});
	}
	
	private void sendBackInfoUpdateRequest(){
		Vector<TelRecord> telRecords = new Vector<TelRecord>();
		TelRecord telRecord = new TelRecord();
		telRecord.setStarter(changeTempRecord.getStarter());
		telRecord.setBack_info(backInfoText.getText().trim());
		telRecord.setSt_time(changeTempRecord.getSt_time());
		telRecords.add(telRecord);
		if(MainWindow.getInstance().connectServer()){
			MainWindow.getInstance().sendDataToServer(new DataPackage(MainWindow.VERSION, DataPropMananger.CMD_ZHZX,
					DataPropMananger.ZHZX_TEL_UPDATE_BACK, MainWindow.my_company, telRecords));
		}
	}
	
	private void sendDelRequest(){
		Vector<TelRecord> telRecords = new Vector<TelRecord>();
		TelRecord telRecord = new TelRecord();
		telRecord.setStarter(changeTempRecord.getStarter());
		telRecord.setSt_time(changeTempRecord.getSt_time());
		telRecords.add(telRecord);
		if(MainWindow.getInstance().connectServer()){
			MainWindow.getInstance().sendDataToServer(new DataPackage(MainWindow.VERSION, DataPropMananger.CMD_ZHZX,
					DataPropMananger.ZHZX_TEL_DEL, MainWindow.my_company, telRecords));
		}
	}
	
	public void telDelSuccess(){
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				infoText.setText("");
				nextInfoText.setText("");
				nextInfoText.setEnabled(false);
				nextSaveBtn.setEnabled(false);
				nextSaveLable.setText("");
				backInfoText.setText("");
				backInfoText.setEnabled(false);
				backSaveBtn.setEnabled(false);
				backSaveLable.setText("");
				if(table.getSelectionIndex() != -1){
					table.remove(table.getSelectionIndex());
					tempRecord = null;
				}
			}
		});
	}
}
