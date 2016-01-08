package common;
import java.awt.Point;
import java.io.ByteArrayOutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import config.WindowPropsMananger;


public class HandWriter {

	protected Shell shell;
	
	protected ByteArrayOutputStream bitmapdata;
	protected boolean isEmpty = true;
	
	protected GC gc1;
	protected GC gc2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HandWriter window = new HandWriter();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public byte[] open() {
		Display display = Display.getDefault();
		bitmapdata = new ByteArrayOutputStream();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		if(isEmpty) return null;
		return bitmapdata.toByteArray();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.NONE);
		shell.setBounds(Display.getDefault().getBounds());
		shell.setText("SWT Application");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("ËÎÌו", 25, SWT.NORMAL));
		lblNewLabel.setBounds(0, 0, 961, 33);
		lblNewLabel.setText("\u8BF7\u5728\u7A7A\u767D\u5904\u7B7E\u540D\uFF1A(\u201C\u91CD\u5199\u201D\u8BF7\u6309 F2\uFF0C\u201C\u4FDD\u5B58\u201D\u8BF7\u6309 \u7A7A\u683C\u952E\uFF09");
		
		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		canvas.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		canvas.setBounds(shell.getBounds());
		
		final MouseDrawTraker mouseDrawTraker = new MouseDrawTraker(canvas);
		
		shell.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.keyCode == SWT.F2){
					mouseDrawTraker.clear();
				}
				else if(arg0.keyCode == SWT.SPACE){
					mouseDrawTraker.save();
					close();
				}
				else if(arg0.keyCode == SWT.ESC){
					mouseDrawTraker.clear();
					close();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

	}
	
	protected class MouseDrawTraker implements MouseListener,MouseMoveListener {
		boolean isDrawing = false;
		protected Image img;
		protected Canvas canvas;
		protected Point originPoint = new Point(0,0);
		
		private int STROKE = 4;
		
		public MouseDrawTraker(Canvas canvas){
			this.canvas = canvas;
			img = new Image(canvas.getDisplay(), canvas.getBounds().width, canvas.getBounds().height);
			
			gc1 = new GC(canvas);
			gc1.setLineWidth(STROKE);
			
			gc2 = new GC(img);
			gc2.setLineWidth(STROKE);
			
			canvas.addMouseListener(this);
			canvas.addMouseMoveListener(this);
		}
		
		public void clear(){
			img = new Image(canvas.getDisplay(), canvas.getBounds().width, canvas.getBounds().height);
			gc2 = new GC(img);
			gc2.setAdvanced(true);
			gc2.setLineStyle(SWT.LINE_SOLID);
			gc2.setLineWidth(STROKE);
			
			canvas.redraw();
			isEmpty = true;
		}
		
		public void save(){
			//ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageLoader il = new ImageLoader();
			il.data = new ImageData[]{img.getImageData().scaledTo(WindowPropsMananger.HW_IMG_WIDTH, WindowPropsMananger.HW_IMG_HEIGHT)};
			il.save(bitmapdata, SWT.IMAGE_JPEG);
			il = null;
		}
		
		@Override
		public void mouseMove(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(isDrawing){
				gc1.drawLine(originPoint.x, originPoint.y, arg0.x, arg0.y);
				gc2.drawLine(originPoint.x, originPoint.y, arg0.x, arg0.y);
				originPoint.x = arg0.x;
				originPoint.y = arg0.y;
			}
		}

		@Override
		public void mouseDoubleClick(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.button == 1){
				
				originPoint.x = arg0.x;
				originPoint.y = arg0.y;
				isDrawing = true;
			}
			isEmpty = false;
		}

		@Override
		public void mouseUp(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.button == 1){
				isDrawing = false;
			}
		}
		
	}
	
	private void close(){
		if(gc1 != null)gc1.dispose();
		if(gc2 != null)gc2.dispose();
		shell.close();
		
	}
}
